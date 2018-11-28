package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.BankKeeperStatusEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.LogisticsCompany;
import com.jyblife.logic.wl.ops.entity.LogisticsCreditQuota;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsCompanyListDto;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsCompanySaveDto;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsCompanyUpdateDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.LogisticsCreditQuotaMapper;
import com.jyblife.logic.wl.ops.manage.service.LogisticsCompanyService;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/webAPI/logisticsCompany")
public class WebApiLogisticsCompanyController extends BaseController {
    @Autowired
    private LogisticsCompanyService logisticsCompanyService;
    @Autowired
    private LogisticsCreditQuotaMapper logisticsCreditQuotaMapper;

    @Permission(value="logistics-detail")
    @RequestMapping("/detail")
    public RespJson areaDict(@RequestParam(value = "logistics_id") Long logisticsId) {
        try {

            LogisticsCompany logisticsCompany = logisticsCompanyService.selectById(logisticsId);
            if (logisticsCompany != null) {
                RespLogisticsDetailDto respLogisticsDetailDto = new RespLogisticsDetailDto();
                LogisticsCreditQuota logisticsCreditQuota = logisticsCreditQuotaMapper.selectByLogisticsId(logisticsId.intValue());
                BeanUtils.copyProperties(logisticsCompany, respLogisticsDetailDto);
                respLogisticsDetailDto.setLogisticsId(logisticsId.toString());
                respLogisticsDetailDto.setStatusName(StatusEnum.getText(logisticsCompany.getStatus()));
                respLogisticsDetailDto.setOutStatusName(BankKeeperStatusEnum.getText(logisticsCompany.getOutStatus()));
                if (logisticsCreditQuota != null) {
                    respLogisticsDetailDto.setStartDate(logisticsCreditQuota.getStartDate());
                    respLogisticsDetailDto.setEndDate(logisticsCreditQuota.getEndDate());
                    respLogisticsDetailDto.setCreditQuota(StrUtils.int2Str(logisticsCreditQuota.getCreditQuota().intValue()));
                }

                return RespJson.success(respLogisticsDetailDto);
            }

            return RespJson.error(ResultCodeEnum.LOGISTICSCOMPANY_NOT_EXIST);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="logistics-list")
    @RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) LogisticsCompanyListDto logisticsCompanyListDto, 
    					 @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (logisticsCompanyListDto == null) {
                logisticsCompanyListDto = new LogisticsCompanyListDto();
            }
            RespLogisticsListDto respLogisticsListDto = new RespLogisticsListDto();
            Page<LogisticsCompany> page = logisticsCompanyService.listPage(logisticsCompanyListDto.getSearch(), logisticsCompanyListDto.getPage(), logisticsCompanyListDto.getPageSize());
            List<RespLogisticsListDto.LogisticsItem> logisticsItems = new ArrayList<RespLogisticsListDto.LogisticsItem>();
            boolean isCanEdit = isHadPermission(sessionId, "logistics-edit");
            boolean isCanView = isHadPermission(sessionId, "logistics-detail");
            for (LogisticsCompany company : page.getResult()) {
                RespLogisticsListDto.LogisticsItem logisticsItem = new RespLogisticsListDto.LogisticsItem();
                logisticsItem.setLogisticsId(company.getLogisticsId().toString());
                logisticsItem.setName(company.getName());
                logisticsItem.setOutStatus(StrUtils.int2Str(company.getOutStatus()));
                logisticsItem.setStatus(StrUtils.int2Str(company.getStatus()));
                logisticsItem.setOutStatusName(BankKeeperStatusEnum.getText(company.getOutStatus()));
                logisticsItem.setStatusName(StatusEnum.getText(company.getStatus()));
                logisticsItem.setIsCanEdit(isCanEdit);
                logisticsItem.setIsCanView(isCanView);
                logisticsItems.add(logisticsItem);
            }

            respLogisticsListDto.setData(logisticsItems);
            respLogisticsListDto.setPage(page.getPageNum());
            respLogisticsListDto.setPageSize(page.getPageSize());
            respLogisticsListDto.setTotalPages(page.getPages());
            respLogisticsListDto.setTotalRows((int) page.getTotal());
            respLogisticsListDto.setSearchItems(logisticsCompanyListDto.getSearch());
            return RespJson.success(respLogisticsListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    /**
     * 物流企业后台只能修改
     *
     * @param logisticsCompanySaveDto
     * @param sessionId
     * @return
     */
    @Permission(value="logistics-save")
    @RequestMapping("/save")
    public RespJson save(@RequestBody @Validated LogisticsCompanySaveDto dto,
                         @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            LogisticsCompany logisticsCompany = new LogisticsCompany();
            logisticsCompany.setStatus(dto.getStatus());
            logisticsCompany.setLogisticsId(dto.getLogisticsId().longValue());
            logisticsCompany.setUpdateTime(new Date());
            SystemUser currentUser = this.getCurSystemUser(sessionId);
            logisticsCompany.setUpdateUserId(currentUser.getUserId());
            logisticsCompanyService.updateLogisticsCompany(logisticsCompany);
            return RespJson.success();
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
    /**
     * 物流企业修改名称
     * @param dto
     * @param sessionId
     * @return
     */
    @Permission(value="logistics-save")
    @RequestMapping("/update")
    public RespJson update(@RequestBody @Validated LogisticsCompanyUpdateDto dto,
    		 			   @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
    	try {
				LogisticsCompany logisticsCompany = new LogisticsCompany();
				logisticsCompany.setLogisticsId(dto.getLogisticsId().longValue());
				logisticsCompany.setName(dto.getName());
				logisticsCompany.setUpdateTime(new Date());
				SystemUser currentUser = this.getCurSystemUser(sessionId);
				logisticsCompany.setUpdateUserId(currentUser.getUserId());
				logisticsCompanyService.updateLogisticsName(logisticsCompany);
				return RespJson.success();
		} catch (Exception e) {
			logger.error("异常:{}",e.getMessage());
			throw new RestException(ResultCodeEnum.FAIL);
		}
		
    	
    }
}
