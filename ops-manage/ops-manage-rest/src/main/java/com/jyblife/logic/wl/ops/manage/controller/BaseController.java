package com.jyblife.logic.wl.ops.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.core.spring.ApplicationContextHolder;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.resp.UserInfoDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.factory.TencentCloudFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 公共控制类方法
 *
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 判断是否拥有某权限
     * @param userId
     * @param rightCode 权限操作码
     * @return
     */
    protected boolean isHadPermission(String sessionId, String rightCode) {
    	UserInfoDto userInfoDto = getCurUserInfo(sessionId);
    	if(userInfoDto != null) {
			JSONArray userRight = userInfoDto.getUserRight();
			String code = "";
			String action = "";
			JSONArray actions = null;
			for(int i=0; i<userRight.size(); i++) {
				code = userRight.getJSONObject(i).getString("code");
				actions = userRight.getJSONObject(i).getJSONArray("actions");
				for(int j=0; j<actions.size(); j++) {
					action = actions.getJSONObject(j).getString("code");
					if(rightCode.equals(code + "-" + action)) {
						return true;
					}
				}
			}
		}
    	
    	return false;
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    protected UserInfoDto getCurUserInfo(String sessionId) {
        Integer userId = this.getCurUserId(sessionId);
        if(userId == null) {
        	return null;
        }

        RedisTemplates redis = ApplicationContextHolder.getBean(RedisTemplates.class);
        String userInfo = redis.get(Constants.USER_INFO_REDIS_KEY_ + userId);

        if (StringUtils.isBlank(userInfo)) {
        	return null;
        }
        
        return JSON.parseObject(userInfo, UserInfoDto.class);
    }
    
    /**
     * 获取当前登录的系统用户
     *
     * @return
     */
    protected SystemUser getCurSystemUser(String sessionId) {
        if (StringUtils.isNotBlank(sessionId)) {
            RedisTemplates redis = ApplicationContextHolder.getBean(RedisTemplates.class);
            String user = redis.get(Constants.USER_LOGIN_TOKEN_REDIS_KEY_ + sessionId);

            if (StringUtils.isNotBlank(user)) {
                return JSON.parseObject(user, SystemUser.class);
            }
        }

        return null;
    }

    /**
     * 获取当前登录用户编号
     *
     * @param sessionId
     * @return
     */
    protected Integer getCurUserId(String sessionId) {
        SystemUser systemUser = this.getCurSystemUser(sessionId);
        if (systemUser != null) {
            return systemUser.getUserId();
        }
        return null;
    }

    /**
     * 添加Cookie
     *
     * @param response
     * @param name
     * @param value
     */
    protected void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(Integer.valueOf(Constants.USER_LOGIN_TOKEN_EXPIRE_TIME.toString()));
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);
    }

    /**
     * 删除所有Cookie
     *
     * @param request
     * @param response
     */
    public void delAllCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }


    protected String baseUpload(MultipartFile file, int type, String keyPath, HttpServletRequest request) throws Exception {
        FileOutputStream fos = null;
        TencentCloudFactory tencentCloudFactory = null;
        File tempFile = null;
        try {
            String fileName = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("/");
            String randomFileName = StrUtils.getRandomFileName(0, type, 6, fileName.substring(fileName.lastIndexOf(".")));
            path = path + File.separator + randomFileName;
            //写文件
            tempFile = new File(path);
            fos = FileUtils.openOutputStream(tempFile);
            IOUtils.copy(file.getInputStream(), fos);

            // 保存文件到文件服务器
            tencentCloudFactory = new TencentCloudFactory();
            String key = keyPath + DateUtil.format(new Date(),"yyyyMMdd")+"/"+ randomFileName;
            tencentCloudFactory.uploadSimpleFile(tempFile, key);

            return key;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("关闭上传文件流异常:{}", e.getMessage());
                }
            }
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
            if (tencentCloudFactory != null) {
                tencentCloudFactory.shutdown();
            }
        }
    }
    
    protected boolean baseDownLoad(String filePath, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	FileOutputStream fos = null;
        TencentCloudFactory tencentCloudFactory = null;
        File tempFile = null;
        boolean isDownLoad = false;
        try {
        	String path = request.getServletContext().getRealPath("/");
        	tempFile = new File(path + filePath);
            tencentCloudFactory = new TencentCloudFactory();
            tencentCloudFactory.downLoadFile(tempFile, filePath);

            if (!tempFile.exists()) {
                throw new RestException(ResultCodeEnum.FAIL.getCode(), "文件信息不存在");
            }
            
            // 以流的形式下载文件。
            FileInputStream fileInputStream = new FileInputStream(tempFile);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Content-Length", "" + tempFile.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            isDownLoad = true;
        } catch (IOException ex) {
        	logger.error("下载文件流异常:{}", ex.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("关闭下载文件流异常:{}", e.getMessage());
                }
            }
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
            if (tencentCloudFactory != null) {
                tencentCloudFactory.shutdown();
            }
        }
    	
        return isDownLoad;
    }

}
