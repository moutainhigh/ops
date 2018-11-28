package com.jyblife.logic.wl.ops.external.handler.impl;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.external.dto.req.*;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogicQuotaUsedDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogisticsQuotaDto;
import org.springframework.beans.factory.annotation.Autowired;
import com.jyblife.logic.wl.ops.external.handler.AbstractHandler;
import org.springframework.stereotype.Service;

import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.external.dto.resp.RespDriverVehicleInfoDto;
import com.jyblife.logic.wl.ops.external.exception.RestException;
import com.jyblife.logic.wl.ops.external.handler.LogisticsHandler;
import com.jyblife.logic.wl.ops.external.service.LogisticsService;

@Service
public class LogisticsHandlerImpl extends AbstractHandler implements LogisticsHandler {

    @Autowired
    private LogisticsService service;

    @Override
    public OutRespJson addLogiCompany(LogisticsCompanyAddDto dto) throws RestException {
        return service.addLogiCompany(dto);
    }

    @Override
    public OutRespJson addLogiDriver(ReqDriverAddDto dto) throws RestException {
        return service.addDriver(dto);
    }

    @Override
    public OutRespJson addLogiVehicle(ReqVehicleAddDto dto) throws RestException {
        return service.addVehicle(dto);
    }

    @Override
    public OutRespJson bindDriverVehicle(ReqBindDriverVehicleDto dto) throws RestException {
        if (dto.getVehicles() == null || dto.getVehicles().size() == 0) {
            return OutRespJson.error(ResultCodeEnum.OUT_PARAM_ERROR);
        }

        return service.bindDriverVehicle(dto);
    }

    @Override
    public OutRespJson getDriverVehicleInfo(ReqDriverVehicleInfoDto dto) throws RestException {
        RespDriverVehicleInfoDto driverVehicleInfo = service.getDriverVehicleInfo(dto);
        if (driverVehicleInfo == null) {
            return OutRespJson.error(ResultCodeEnum.OUT_CUSTOMER_NULL);
        }

        return OutRespJson.success(driverVehicleInfo);
    }

    @Override
    public OutRespJson updateLogiCompanyStatus(ReqUpdateLogiCompanyStatusDto dto) throws RestException {
        return service.updateLogiCompanyStatus(dto);
    }

    @Override
    public OutRespJson updateDriverInfo(ReqUpdateDriverDto dto) throws RestException {
        return service.updateDriverInfo(dto);
    }

    @Override
    public OutRespJson getLogicQuota(ReqLogisticsQuotaDto dto) throws RestException {
        try {
            RespLogisticsQuotaDto respLogisticsQuotaDto = service.getLogicQuota(dto);
            return OutRespJson.success(respLogisticsQuotaDto);
        } catch (OpsException e) {
            logger.error("异常：{},{}", e, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("异常：{}", e);
            throw e;
        }
    }

    @Override
    public OutRespJson getLogicQuotaUsed(ReqLogicQuotaUsedDto dto) throws RestException {
        try {
            RespLogicQuotaUsedDto respDto = service.getLogicQuotaUsed(dto);
            return OutRespJson.success(respDto);
        } catch (OpsException e) {
            logger.error("异常：{},{}", e, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("异常：{}", e);
            throw e;
        }
    }

	@Override
	public OutRespJson repayment(ReqLogisticsRepaymentDto dto) throws RestException {
		try {
            return service.repayment(dto);
        } catch (OpsException e) {
            logger.error("异常：{},{}", e, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("异常：{}", e);
            throw e;
        }
	}

	@Override
	public OutRespJson updateLogiVehicle(ReqUpdateVehicleDto dto) throws RestException {
		try {
            return service.updateLogiVehicle(dto);
        } catch (OpsException e) {
            logger.error("异常：{},{}", e, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("异常：{}", e);
            throw e;
        }
	}
}
