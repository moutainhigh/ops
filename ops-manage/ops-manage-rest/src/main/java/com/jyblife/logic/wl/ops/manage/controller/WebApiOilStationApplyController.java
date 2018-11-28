package com.jyblife.logic.wl.ops.manage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jyblife.logic.wl.ops.common.enums.OilStationApplyStatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.OilStationApplyAttachment;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyDelFileDto;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplySaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespAttachementSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyListDto.OilStationApplyItem;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.factory.TencentCloudFactory;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.OilStationApplyService;

@RestController
@RequestMapping("/webAPI/oilStationApply")
public class WebApiOilStationApplyController extends BaseController {
	
	@Autowired
	private OilStationApplyService service;
	
	@RequestMapping("/list")
	@Permission(value="oil-station-apply-list")
    public RespJson list(@RequestBody(required = false) OilStationApplyListDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
		if (dto == null) {
			dto = new OilStationApplyListDto();
        }
		
		try {
			Page<OilStationApplyItem> page = service.listPage(dto.getSearch(), dto.getPage(), dto.getPageSize());
			RepOilStationApplyListDto respData = new RepOilStationApplyListDto();
			
			boolean isCanEdit = isHadPermission(sessionId, "oil-station-apply-edit");
            boolean isCanView = isHadPermission(sessionId, "oil-station-apply-detail");
			List<RepOilStationApplyListDto.OilStationApplyItem> items = page.getResult();
			for(RepOilStationApplyListDto.OilStationApplyItem item : items) {
				item.setStatusName(OilStationApplyStatusEnum.getText(StrUtils.str2Int(item.getStatus())));
				item.setCanEdit(isCanEdit);
				item.setCanView(isCanView);
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
	
	@RequestMapping("/save")
	@Permission(value="oil-station-apply-save")
	public RespJson save(@RequestBody @Validated OilStationApplySaveDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
		if(dto.getLongitude() > 360) {
			return RespJson.error("经度不能大于360度");
		} 
		if(dto.getLatitude() > 360) {
			return RespJson.error("纬度不能大于360度");
		}
		
		try {
			Integer userId = getCurUserId(sessionId);
			return service.save(dto, userId);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	@RequestMapping("/detail")
	@Permission(value="oil-station-apply-detail")
	public RespJson detail(@RequestParam("apply_id") Integer applyId) {
		if(applyId == null) {
			return RespJson.error("参数apply_id不能为空");
		}
		
		try {
			return service.detail(applyId);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	@RequestMapping("/delFile")
	@Permission(value="oil-station-apply-delFile")
	public RespJson delFile(@RequestBody @Validated OilStationApplyDelFileDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
		TencentCloudFactory tencentCloudFactory = null;
		try {
			OilStationApplyAttachment attachment = service.selectAttachmentByFileId(dto.getId());
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
	
	@Permission(value="oil-station-apply-saveFile")
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
            String key = this.baseUpload(file, type, Constants.OIL_STATION_APPLY_ATTACHMENT_PATH, request);

            //保存文件地址到数据库
            OilStationApplyAttachment attachment = new OilStationApplyAttachment();
            attachment.setBaseId(id);
            attachment.setType(type);
            attachment.setCreateTime(new Date());
            attachment.setFilePath(key);
            attachment.setFileUrl(key);
            attachment.setName(file.getOriginalFilename());
            attachment.setStatus(1);
            attachment.setRemark("");
            attachment.setUpdateTime(new Date());
            SystemUser systemUser = this.getCurSystemUser(sessionId);
            if (systemUser != null) {
            	attachment.setCreateUserId(systemUser.getUserId());
            	attachment.setUpdateUserId(systemUser.getUserId());
            }
            service.insertAttachment(attachment);

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
        		OilStationApplyAttachment attachment = service.selectAttachmentByFileId(id);
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
