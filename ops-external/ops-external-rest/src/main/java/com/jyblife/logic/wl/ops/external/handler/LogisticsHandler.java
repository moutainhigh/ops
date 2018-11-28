package com.jyblife.logic.wl.ops.external.handler;

import com.jyblife.logic.wl.ops.common.command.annotation.CommandMethod;
import com.jyblife.logic.wl.ops.common.command.annotation.CommandService;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.*;
import com.jyblife.logic.wl.ops.external.exception.RestException;

@CommandService("910200")
public interface LogisticsHandler {

	/**
	 * 添加物流企业
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("01")
	public OutRespJson addLogiCompany(LogisticsCompanyAddDto dto) throws RestException;
	
	/**
	 * 添加司机信息
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("03")
	public OutRespJson addLogiDriver(ReqDriverAddDto dto) throws RestException;
	
	/**
	 * 添加车辆信息
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("04")
	public OutRespJson addLogiVehicle(ReqVehicleAddDto dto) throws RestException;
	
	/**
	 * 绑定司机车辆
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("05")
	public OutRespJson bindDriverVehicle(ReqBindDriverVehicleDto dto) throws RestException;
	
	/**
	 * 获取司机车辆信息
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("06")
	public OutRespJson getDriverVehicleInfo(ReqDriverVehicleInfoDto dto) throws RestException;
	
	/**
	 * 更新物流企业状态
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("07")
	public OutRespJson updateLogiCompanyStatus(ReqUpdateLogiCompanyStatusDto dto) throws RestException;
	
	/**
	 * 更新司机信息
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("08")
	public OutRespJson updateDriverInfo(ReqUpdateDriverDto dto) throws RestException;

	/**
	 * 查询物流企业额度信息
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("09")
	public OutRespJson getLogicQuota(ReqLogisticsQuotaDto dto) throws RestException;

	/**
	 * 查询物流企业一段时间内的额度使用信息统计
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("10")
	public OutRespJson getLogicQuotaUsed(ReqLogicQuotaUsedDto dto) throws RestException;

	/**
	 * 物流企业还款
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("11")
	public OutRespJson repayment(ReqLogisticsRepaymentDto dto) throws RestException;
	
	/**
	 * 更新车辆信息
	 * @param dto
	 * @return
	 * @throws RestException
	 */
	@CommandMethod("12")
	public OutRespJson updateLogiVehicle(ReqUpdateVehicleDto dto) throws RestException;
	
}
