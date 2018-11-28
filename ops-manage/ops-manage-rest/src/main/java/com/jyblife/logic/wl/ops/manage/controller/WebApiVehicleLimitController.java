package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotaLimitSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotalimitListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaLimitDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotalimitListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleQuotaLimitMapper;
import com.jyblife.logic.wl.ops.manage.service.VehicleQuotalimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.function.Consumer;

/**
 * 车辆每日限额
 **/
@RestController
@RequestMapping("/webAPI/vehicleQuotaLimit")
public class WebApiVehicleLimitController extends BaseController {
    @Autowired
    private VehicleQuotalimitService vehicleQuotalimitService;
    @Autowired
    private VehicleQuotaLimitMapper vehicleQuotaLimitMapper;
    @Autowired
    private RedisTemplates redisTemplates;

    @Permission(value="vehicleDayQuota-list")
    @RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) VehicleQuotalimitListDto vehicleQuotalimitListDto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (vehicleQuotalimitListDto == null) {
                vehicleQuotalimitListDto = new VehicleQuotalimitListDto();
            }
            RespVehicleQuotalimitListDto respVehicleQuotalimitListDto = new RespVehicleQuotalimitListDto();
            Page<RespVehicleQuotalimitListDto.QuotalimitItem> page = vehicleQuotalimitService.selectPageList(vehicleQuotalimitListDto.getSearch(), vehicleQuotalimitListDto.getPage(), vehicleQuotalimitListDto.getPageSize());
            boolean isCanEdit = isHadPermission(sessionId, "vehicleDayQuota-edit");
            boolean isCanView = isHadPermission(sessionId, "vehicleDayQuota-detail");
            page.getResult().stream().forEach(new Consumer<RespVehicleQuotalimitListDto.QuotalimitItem>() {
                @Override
                public void accept(RespVehicleQuotalimitListDto.QuotalimitItem item) {
                	item.setIsCanEdit(isCanEdit);
                	item.setIsCanView(isCanView);
                }
            });
            
            respVehicleQuotalimitListDto.setData(page.getResult());
            respVehicleQuotalimitListDto.setPage(page.getPageNum());
            respVehicleQuotalimitListDto.setPageSize(page.getPageSize());
            respVehicleQuotalimitListDto.setTotalPages(page.getPages());
            respVehicleQuotalimitListDto.setTotalRows((int) page.getTotal());
            respVehicleQuotalimitListDto.setSearchItems(vehicleQuotalimitListDto.getSearch());

            return RespJson.success(respVehicleQuotalimitListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="vehicleDayQuota-add")
    @RequestMapping("/add")
    public RespJson add(@RequestBody @Validated VehicleQuotaLimitSaveDto vehicleQuotaLimitSaveDto,
                        @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            VehicleQuotaLimit vehicleQuotaLimit = new VehicleQuotaLimit();
            vehicleQuotaLimit.setRate(vehicleQuotaLimitSaveDto.getRate());
            vehicleQuotaLimit.setCreateTime(new Date());
            vehicleQuotaLimit.setUpdateTime(new Date());
            vehicleQuotaLimit.setStatus(1);
            vehicleQuotaLimit.setStatusTime(new Date());
            vehicleQuotaLimit.setEffectTime(new Date());

            String day = DateUtil.format(new Date(), "yyyyMMdd");
            String key = "ops:vehicle_quota_limit:code:" + day;
            Long value = redisTemplates.incrementAndGet(key,1,1*24*60*60L);
            String valueString = StrUtils.getfixedNum(value.intValue(),2);
            vehicleQuotaLimit.setCode("CL" + day + valueString);
            SystemUser systemUser = this.getCurSystemUser(sessionId);
            if (systemUser != null) {
                vehicleQuotaLimit.setCreateUserId(systemUser.getUserId());
                vehicleQuotaLimit.setUpdateUserId(systemUser.getUserId());
            }
            vehicleQuotalimitService.save(vehicleQuotaLimit);
            return RespJson.success();
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="vehicleDayQuota-detail")
    @RequestMapping("/detail")
    public RespJson detail() {
        try {
            RespVehicleQuotaLimitDetailDto detailDto = vehicleQuotaLimitMapper.selectCurrentDetail();
            if (detailDto != null) {
                return RespJson.success(detailDto);
            }
            return RespJson.error(ResultCodeEnum.VEHICLE_QUOTA_LIMIT_NOT_EXIST);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
}
