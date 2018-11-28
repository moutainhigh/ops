package com.jyblife.logic.wl.ops.manage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.OilCompanyOwnershipEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.OilCompanyAttachment;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanyDelFileDto;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanyListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanySaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilCompanyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespAttachementSaveDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.factory.TencentCloudFactory;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.OilCompanyAttachmentService;
import com.jyblife.logic.wl.ops.manage.service.OilCompanyService;

@RestController
@RequestMapping("/webAPI/oilCompany")
public class WebApiOilCompanyController extends BaseController {
	
	@Autowired
	private OilCompanyService service;
	@Autowired
	private OilCompanyAttachmentService attService;
	
	@Permission(value="oil-company-list")
	@RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) OilCompanyListDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
		if (dto == null) {
			dto = new OilCompanyListDto();
        }
		
		try {
			Page<RepOilCompanyListDto.OilCompanyItem> page = service.listPage(dto.getSearch(), dto.getPage(), dto.getPageSize());
			RepOilCompanyListDto respData = new RepOilCompanyListDto();
			
			boolean isCanEdit = isHadPermission(sessionId, "oil-company-edit");
            boolean isCanView = isHadPermission(sessionId, "oil-company-detail");
			List<RepOilCompanyListDto.OilCompanyItem> items = page.getResult();
			for(RepOilCompanyListDto.OilCompanyItem item : items) {
				item.setStatusName(StatusEnum.getText(item.getStatus()));
				if(item.getOwnership() != null) {
					item.setOwnershipName(OilCompanyOwnershipEnum.getText(item.getOwnership()));
				}
				item.setIsCanEdit(isCanEdit);
				item.setIsCanView(isCanView);
			}
			
			respData.setData(items);
			respData.setPage(page.getPageNum());
			respData.setPageSize(page.getPageSize());
			respData.setTotalPages(page.getPages());
			respData.setTotalRows((int) page.getTotal());
			respData.setSearchItems(dto.getSearch());
			return RespJson.success(respData);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	@Permission(value="oil-company-save")
	@RequestMapping("/save")
	public RespJson save(@RequestBody @Validated OilCompanySaveDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
		if(dto.getFiles() == null) {
			return RespJson.error("请先上传证书附件");
		} 
		boolean flag = true;
		for(OilCompanySaveDto.File file : dto.getFiles()) {
			if(file.getType() == 2) {
				flag = false;
				break;
			}
		}
		if(flag) {
			return RespJson.error("请先上传证书附件");
		}
		
		try {
			Integer userId = getCurUserId(sessionId);
			return service.save(dto, userId);
		}catch (OpsException e){
			logger.error("异常: {},{}" ,e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("异常: " + e.getMessage(), e);
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	@Permission(value="oil-company-detail")
	@RequestMapping("/detail")
	public RespJson detail(@RequestParam("company_id") Integer companyId) {
		if(companyId == null) {
			return RespJson.error("参数company_id不能为空");
		}
		
		try {
			return service.detail(companyId);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	@Permission(value="oil-company-delFile")
	@RequestMapping("/delFile")
	public RespJson delFile(@RequestBody @Validated OilCompanyDelFileDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
		TencentCloudFactory tencentCloudFactory = null;
		try {
			OilCompanyAttachment attachment = attService.selectById(dto.getId());
			if (attachment == null || attachment.getStatus().intValue() == -1) {
                return RespJson.error("文件不存在");
            }
			
			 //删除文件服务器文件
            tencentCloudFactory = new TencentCloudFactory();
            tencentCloudFactory.deleteFile(attachment.getFileUrl());
			
			Integer userId = getCurUserId(sessionId);
			return service.delFile(dto.getId(), userId);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		} finally {
            if (tencentCloudFactory != null) {
                tencentCloudFactory.shutdown();
            }
        }
	}
	
	//@Permission(value="oil-company-saveFile")
	@RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public RespJson saveFile(@RequestParam(value="files[]") MultipartFile[] files,
							 @RequestParam(value="type") Integer type,
							 @RequestParam(value="id") Integer id,
							 @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId, 
							 HttpServletRequest request) {

        if (files == null || files.length == 0) {
            throw new RestException(ResultCodeEnum.FAIL);
        }

        try {
            MultipartFile file = files[0]; //只让支持一个文件
            String key = this.baseUpload(file, type, Constants.OIL_COMPANY_APPLY_ATTACHMENT_PATH, request);

            //保存文件地址到数据库
            OilCompanyAttachment attachment = new OilCompanyAttachment();
            attachment.setBaseId(id);
            attachment.setType(type);
            attachment.setCreateTime(new Date());
            attachment.setFilePath(key);
            attachment.setFileUrl(key);
            attachment.setName(file.getOriginalFilename());
            attachment.setStatus(1);
            attachment.setUpdateTime(new Date());
            attachment.setRemark("");
            SystemUser systemUser = this.getCurSystemUser(sessionId);
            if (systemUser != null) {
            	attachment.setCreateUserId(systemUser.getUserId());
            	attachment.setUpdateUserId(systemUser.getUserId());
            }
            attService.insertAttachment(attachment);

            //返回文件id
            RespAttachementSaveDto saveDto = new RespAttachementSaveDto();
            saveDto.setId(StrUtils.int2Str(attachment.getId()));
            saveDto.setName(attachment.getName());
            saveDto.setStatus(0);
            saveDto.setType(StrUtils.int2Str(type));
            saveDto.setUrl(attachment.getFileUrl());
            return RespJson.success(saveDto);
        } catch (Exception e) {
        	logger.error("异常: " + e.getMessage(), e);
            throw new RestException(ResultCodeEnum.FAIL);
        }

    }
	
	@RequestMapping("/getFile")
    public void getFile(@RequestParam("id") Integer id, @RequestParam("fileName") String fileName,
            				HttpServletRequest request, HttpServletResponse response) {

    	boolean downLoad = false;
        try {
        	if(id != null) {
        		OilCompanyAttachment attachment = attService.selectById(id);
                if (attachment == null || attachment.getStatus().intValue() == -1) {
                    throw new RestException(ResultCodeEnum.PRICEAPPLY_ATTACHMENT_NOT_EXIST);
                }
                
                downLoad = this.baseDownLoad(attachment.getFileUrl(), (StringUtils.isBlank(fileName) ? attachment.getName() : fileName), request, response);
        	}
        	
        } catch (Exception e) {
            logger.error("下载文件异常:{}", e.getMessage());
        } finally {
        	 if(!downLoad) {
				try {
					response.setContentType("application/json;charset=UTF-8");
	         		PrintWriter out = response.getWriter();
	         		RespJson resp = (id == null) ? RespJson.error(ResultCodeEnum.PARAM_ERROR) : RespJson.error("文件不存在");
					out.write(JSON.toJSONString(resp));
	         		out.close();
				} catch (IOException e) {
					logger.error("异常:{}", e.getMessage());
				}
         	}
		}

    }
	
}
