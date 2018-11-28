package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaLimit;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsLimitListDto;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaLimitSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsLimitDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsLimitListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.LogisticsQuotaLimitMapper;
import com.jyblife.logic.wl.ops.manage.service.LogisticsLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.function.Consumer;

/**
 * 企业每日限额
 **/
@RestController
@RequestMapping("/webAPI/logisticsQuotaLimit")
public class WebApiLogisticsLimitController extends BaseController {
    @Autowired
    private LogisticsLimitService logisticsLimitService;
    @Autowired
    private LogisticsQuotaLimitMapper logisticsQuotaLimitMapper;
    @Autowired
    private RedisTemplates redisTemplates;

    @Permission(value="enterpriseDayQuota-list")
    @RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) LogisticsLimitListDto logisticsLimitListDto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (logisticsLimitListDto == null) {
                logisticsLimitListDto = new LogisticsLimitListDto();
            }
            
            boolean isCanEdit = isHadPermission(sessionId, "enterpriseDayQuota-edit");
            boolean isCanView = isHadPermission(sessionId, "enterpriseDayQuota-detail");
            
            RespLogisticsLimitListDto respLogisticsLimitListDto = new RespLogisticsLimitListDto();
            Page<RespLogisticsLimitListDto.LogisticsLimitItem> page = logisticsLimitService.selectPageList(logisticsLimitListDto.getSearch(), logisticsLimitListDto.getPage(), logisticsLimitListDto.getPageSize());
            page.getResult().stream().forEach(new Consumer<RespLogisticsLimitListDto.LogisticsLimitItem>() {
                @Override
                public void accept(RespLogisticsLimitListDto.LogisticsLimitItem item) {
                	item.setIsCanEdit(isCanEdit);
                	item.setIsCanView(isCanView);
                }
            });
            
            respLogisticsLimitListDto.setData(page.getResult());
            respLogisticsLimitListDto.setPage(page.getPageNum());
            respLogisticsLimitListDto.setPageSize(page.getPageSize());
            respLogisticsLimitListDto.setTotalPages(page.getPages());
            respLogisticsLimitListDto.setTotalRows((int) page.getTotal());
            respLogisticsLimitListDto.setSearchItems(logisticsLimitListDto.getSearch());

            return RespJson.success(respLogisticsLimitListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="enterpriseDayQuota-add")
    @RequestMapping("/add")
    public RespJson add(@RequestBody @Validated LogisticsQuotaLimitSaveDto logisticsQuotaLimitSaveDto,
                        @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            LogisticsQuotaLimit logisticsQuotaLimit = new LogisticsQuotaLimit();
            logisticsQuotaLimit.setRate(logisticsQuotaLimitSaveDto.getRate());
            logisticsQuotaLimit.setCreateTime(new Date());
            logisticsQuotaLimit.setUpdateTime(new Date());
            logisticsQuotaLimit.setStatus(1);
            logisticsQuotaLimit.setStatusTime(new Date());
            logisticsQuotaLimit.setEffectTime(new Date());

            String day = DateUtil.format(new Date(), "yyyyMMdd");
            Long value = redisTemplates.incrementAndGet(Constants.OPS_LOGISTICS_QUOTA_LIMIT_CODE_PREFIX + day, 1,1*24*60*60L);
            String valueString = StrUtils.getfixedNum(value.intValue(), 2);
            logisticsQuotaLimit.setCode(Constants.LOGISTICS_QUOTA_LIMIT_CODE_PREFIX + day + valueString);
            SystemUser systemUser = this.getCurSystemUser(sessionId);
            if (systemUser != null) {
                logisticsQuotaLimit.setCreateUserId(systemUser.getUserId());
                logisticsQuotaLimit.setUpdateUserId(systemUser.getUserId());
            }
            logisticsLimitService.save(logisticsQuotaLimit);
            return RespJson.success();
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="enterpriseDayQuota-detail")
    @RequestMapping("/detail")
    public RespJson detail() {
        try {
            RespLogisticsLimitDetailDto detailDto = logisticsQuotaLimitMapper.selectCurrentDetail();
            if (detailDto != null) {
                return RespJson.success(detailDto);
            }
            return RespJson.error(ResultCodeEnum.LOGISTICS_QUOTA_LIMIT_NOT_EXIST);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
}
