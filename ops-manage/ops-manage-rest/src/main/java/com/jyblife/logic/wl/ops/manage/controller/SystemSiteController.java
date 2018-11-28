package com.jyblife.logic.wl.ops.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.EncryptUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.entity.SystemRole;
import com.jyblife.logic.wl.ops.entity.SystemRoleRight;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.entity.SystemUserRight;
import com.jyblife.logic.wl.ops.manage.dto.UpdatePasswordDto;
import com.jyblife.logic.wl.ops.manage.dto.UserLoginDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.UserInfoDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Authentication;
import com.jyblife.logic.wl.ops.manage.service.SystemRoleService;
import com.jyblife.logic.wl.ops.manage.service.SystemUserService;
import com.jyblife.logic.wl.ops.manage.service.UserCenterService;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/site")
public class SystemSiteController extends BaseController {
	
    private static Logger logger = LoggerFactory.getLogger(SystemSiteController.class);
    
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private UserCenterService userCenterService;
    @Autowired
    private RedisTemplates redis;
    @Autowired
    private SystemRoleService roleService;

    /**
     * 用户登录
     * @param userLoginDto
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public RespJson login(@RequestBody @Validated UserLoginDto userLoginDto, HttpServletResponse response) {
        try {
        	RespJson respJson = RespJson.success();
        	SystemUser systemUser = systemUserService.selectByUserName(userLoginDto.getUsername());
        	//用户中心登录
        	if(systemUser == null || StringUtils.isBlank(systemUser.getPassword())) {
        		respJson = userCenterService.verifyUsernamePassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        		 if (respJson.isSuccess()) {
                     if(systemUser == null) {
                         userCenterService.synUser();
                         systemUser = systemUserService.selectByUserName(userLoginDto.getUsername());
                     }

                     if(systemUser == null) {
                    	 respJson = RespJson.error(ResultCodeEnum.USER_NOT_EXIST);
                     }
                 }
        	} else { //内部系统密码登录
        		String pwd = EncryptUtil.encryptTo32Md5(userLoginDto.getPassword());
        		if(!pwd.equals(systemUser.getPassword())) {
        			respJson = RespJson.error(ResultCodeEnum.USER_PASSWORD_ERROR);
        		}
        	}
        	
        	if(systemUser != null && systemUser.getStatus() == 0) {
        		respJson = RespJson.error(ResultCodeEnum.USER_DISABLED);
        	}
        	
        	if(respJson.isSuccess()) {
        		//更新登录信息
        		systemUserService.updateUserLoginInfo(systemUser.getUserId());
        		
        		String token = StrUtils.getRandomStr();
                //保存会话信息
                redis.set(Constants.USER_LOGIN_TOKEN_REDIS_KEY_+token, JSON.toJSONString(systemUser), Constants.USER_LOGIN_TOKEN_EXPIRE_TIME);
                //添加cookie
                addCookie(response, Constants.USER_LOGIN_COOKIE_KEY, token);
                //保存权限信息
                this.getUserInfo(token);
        	}
        	
            return respJson;
        } catch (Exception e) {
            logger.error("登录异常请稍后重试: " + e.getMessage(), e);
            throw new RestException(ResultCodeEnum.FAIL);
        }

    }

    /**
     * 退出登录
     * @param sessionId
     * @return
     */
    @RequestMapping("/logout")
    public RespJson login(@CookieValue(name=Constants.USER_LOGIN_COOKIE_KEY, required=false) String sessionId, 
    						HttpServletResponse response, HttpServletRequest request) {
    	
    	if (StringUtils.isNotBlank(sessionId)) {
    		redis.remove(Constants.USER_LOGIN_TOKEN_REDIS_KEY_ + sessionId);
    		delAllCookie(request, response);
    	}
    	
        return RespJson.success();
    }

    /**
     * 更新密码
     * @param dto
     * @return
     */
    @Authentication
    @RequestMapping("/updatePwd")
    public RespJson updatePwd(@RequestBody @Validated UpdatePasswordDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
    	if(!dto.getNewPassword().equals(dto.getConfirmPassword())) {
    		return RespJson.error(ResultCodeEnum.NEW_CONFIRM_PASSWORD_ERROR);
    	}
    	
    	SystemUser user = getCurSystemUser(sessionId);
    	String pwd = EncryptUtil.encryptTo32Md5(dto.getPassword());
    	if(!pwd.equals(user.getPassword())) {
    		return RespJson.error(ResultCodeEnum.ORGINAL_PASSWORD_ERROR);
    	}
    	
    	try {
    		user.setPassword(EncryptUtil.encryptTo32Md5(dto.getNewPassword()));
    		user.setUpdateTime(new Date());
    		user.setUpdateUserId(user.getUserId());
        	systemUserService.updatePwd(user);
		} catch (Exception e) {
            logger.error("更新密码异常: {}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
        return RespJson.success();
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @Authentication
    @RequestMapping("/getUserInfo")
    public RespJson getUserInfo(@RequestParam("token") String token) throws IllegalAccessException, InvocationTargetException {
    	if(StringUtils.isBlank(token)) {
    		return RespJson.error(ResultCodeEnum.PARAM_ERROR);
    	}
    	
    	SystemUser user = getCurSystemUser(token);
    	UserInfoDto info = new UserInfoDto();
    	BeanUtils.copyProperties(user, info);
    	
    	SystemRole detailRole = roleService.detailRole(user.getMainRoleId());
    	info.setMainRoleName(detailRole.getName());
    	//角色列表
    	List<SystemRole> roleList = roleService.selectByUserId(user.getUserId());
    	JSONArray roleArray = new JSONArray();
    	JSONObject json = null;
    	for(SystemRole sr : roleList) {
    		json = new JSONObject();
    		json.put("id", sr.getRoleId().toString());
    		json.put("name", sr.getName());
    		roleArray.add(json);
    	}
    	info.setRoleArray(roleArray);
    	
    	//权限列表
    	if (user.getIsRightRole() == 0) {
    		SystemUserRight userRight = systemUserService.getUserRightByUserId(user.getUserId());
    		info.setUserRight(userRight != null ? JSON.parseArray(userRight.getRightCodes()) : new JSONArray());
    	} else {
    		List<SystemRoleRight> roleRightList = roleService.selectRoleRightByUserId(user.getUserId());
    		JSONArray userRight = new JSONArray();
    		JSONArray rightCodes = null;
    		//合并不同角色权限
    		for(SystemRoleRight rr : roleRightList) {
    			rightCodes = JSON.parseArray(rr.getRightCodes());
    			cur : for(int i=0; i<rightCodes.size(); i++) {
    				Object m_id = rightCodes.getJSONObject(i).get("id");
    				final int num = userRight.size();
    				for(int j=0; j<num; j++) {
    					if(m_id.toString().equals(userRight.getJSONObject(j).get("id"))) {
    						JSONArray right_actions = rightCodes.getJSONObject(i).getJSONArray("actions");
    						JSONArray user_actions = userRight.getJSONObject(j).getJSONArray("actions");
    						//组装actions
    						temp : for(int k=0; k<right_actions.size(); k++) {
    							final int num1 = user_actions.size();
    							for(int u=0; u<num1; u++) {
    								if(right_actions.getJSONObject(k).getString("code").equals(user_actions.getJSONObject(u).getString("code"))) {
    									continue temp;
    								}
    							}
    							userRight.getJSONObject(j).getJSONArray("actions").add(right_actions.getJSONObject(k));
    						}
    						continue cur;
    					}
    				}
    				
    				userRight.add(rightCodes.getJSONObject(i));
    			}
    		}
    		info.setUserRight(userRight);
    	}
    	
    	//保存用户详细信息
    	redis.set(Constants.USER_INFO_REDIS_KEY_+info.getUserId(), JSON.toJSONString(info), Constants.USER_LOGIN_TOKEN_EXPIRE_TIME);
    	
        return RespJson.success(info);
    }

    /**
     * 获取用户菜单树
     * @param sessionId
     * @return
     */
    @Authentication
    @RequestMapping("/getMenu")
    public RespJson getMenu(@CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
    	if(StringUtils.isNotBlank(sessionId)) {
    		Integer userId = getCurUserId(sessionId);
    		SystemUser user = systemUserService.selectByPrimaryKey(userId);
    		
    		int isRightRole = user.getIsRightRole();
    		if(isRightRole == 1) {
    			return systemUserService.getUserMenuByRoleRight(userId);
    		} else {
    			return systemUserService.getUserMenuByUserRight(userId);
    		}
    	}
    	
        return RespJson.success();
    }


}
