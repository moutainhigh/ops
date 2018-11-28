package com.jyblife.logic.wl.ops.external.handler;

import com.jyblife.logic.wl.ops.common.command.annotation.CommandMethod;
import com.jyblife.logic.wl.ops.common.command.annotation.CommandService;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.*;
import com.jyblife.logic.wl.ops.external.exception.RestException;

@CommandService("910100")
public interface OutOrderHandler {

    /**
     * 下订单
     *
     * @param dto
     * @return
     */
    @CommandMethod("01")
    OutRespJson submitOrder(ReqOrderSubmitDto dto);

    /**
     * 获取订单详情
     *
     * @param dto
     * @return
     */

    @CommandMethod("02")
    OutRespJson getOrderDetail(ReqOrderDetailDto dto);

    /**
     * 获取用户订单列表
     *
     * @param dto
     * @return
     */
    @CommandMethod("03")
    OutRespJson getUserOrderList(ReqUserOrderListDto dto);

    /**
     * 获取用户下订单时能使用的车辆
     *
     * @param dto
     * @return
     */
    @CommandMethod("04")
    OutRespJson getUserOrderVehicles(ReqUserOrderVehicleDto dto);

    /**
     * 物流b端订单列表查询
     *
     * @param dto
     * @return
     */
    @CommandMethod("05")
    OutRespJson getLogicOrderList(ReqLogicOrderListDto dto);


    /**
     * 查询物流企业某日的消费统计信息
     * @param dto
     * @return
     * @throws RestException
     */
    @CommandMethod("06")
    public OutRespJson getLogicConsumeStatistics(ReqLogicConsumeStatisticsDto dto) throws RestException;


    /**
     * 油企b端订单列表查询
     *
     * @param dto
     * @return
     */
    @CommandMethod("07")
    OutRespJson getOilCompanyOrderList(ReqOilCompanyOrderListDto dto);
    
    /**
     * 油企b端订单列表查询
     *
     * @param dto
     * @return
     */
    @CommandMethod("08")
    OutRespJson oilOrderPrint(ReqOilOrderPrintDto dto);

}
