package com.jyblife.logic.wl.ops.external.service;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.*;
import com.jyblife.logic.wl.ops.external.dto.resp.RespDriverVehicleInfoDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogicQuotaUsedDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogisticsQuotaDto;

public interface LogisticsService {

	/**
	 * 添加物流企业
	 * @param dto
	 * @return
	 * @throws OpsException
	 */
	public OutRespJson addLogiCompany(LogisticsCompanyAddDto dto) throws OpsException;
	
	/**
	 * 添加司机
	 * @param dto
	 * @return
	 * @throws OpsException
	 */
	OutRespJson addDriver(ReqDriverAddDto dto) throws OpsException;
	
	/**
	 * 添加车辆
	 * @param dto
	 * @return
	 * @throws OpsException
	 */
	OutRespJson addVehicle(ReqVehicleAddDto dto) throws OpsException;
	
	/**
	 * 绑定司机车辆
	 * @param dto
	 * @return
	 * @throws OpsException
	 */
	OutRespJson bindDriverVehicle(ReqBindDriverVehicleDto dto) throws OpsException;
	
	/**
	 * 车辆信息
	 * @param dto
	 * @return
	 */
	RespDriverVehicleInfoDto getDriverVehicleInfo(ReqDriverVehicleInfoDto dto) throws OpsException;
	
	/**
	 * 更新物流企业状态
	 * @param dto
	 * @return
	 */
	OutRespJson updateLogiCompanyStatus(ReqUpdateLogiCompanyStatusDto dto);
	
	/**
	 * 更新司机信息
	 * @param dto
	 * @return
	 */
	OutRespJson updateDriverInfo(ReqUpdateDriverDto dto);


	RespLogisticsQuotaDto getLogicQuota(ReqLogisticsQuotaDto dto);

	/**
	 * 查询物流企业一段时间内的额度使用情况
	 */
	RespLogicQuotaUsedDto getLogicQuotaUsed(ReqLogicQuotaUsedDto dto);
	
	/**
	 * 物流企业还款
	 * @param dto
	 * @return
	 */
	OutRespJson repayment(ReqLogisticsRepaymentDto dto) throws OpsException;
	
	/**
	 * 更新车俩信息
	 * @param dto
	 * @return
	 * @throws OpsException
	 */
	OutRespJson updateLogiVehicle(ReqUpdateVehicleDto dto) throws OpsException;
	
}
