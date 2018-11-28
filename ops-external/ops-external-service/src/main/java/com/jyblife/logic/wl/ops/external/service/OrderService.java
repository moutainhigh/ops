package com.jyblife.logic.wl.ops.external.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.ReqLogicConsumeStatisticsDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqLogicOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilCompanyOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilOrderPrintDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOrderSubmitDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqUserOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogicConsumeStatisticsDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogicOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilCompanyOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilOrderPrintDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOrderDetailDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespUserOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespUserOrderVehicleDto;

public interface OrderService {

    RespOrderDetailDto selectOrderDetail(Long orderId);

    RespUserOrderListDto selectPageByCustomerId(ReqUserOrderListDto dto);

    List<RespUserOrderVehicleDto> selectOrderVehicles(Integer customerId,Integer stationId,Integer goodsId) throws OpsException ;

    OutRespJson submitOrder(ReqOrderSubmitDto submitDto);
    
    /**
     * 生成订单编号
     * 
     * @return
     */
    String generateOrderCode();

    /**
     *  物流企业订单查询
     * @param dto
     * @return
     */
    Page<RespLogicOrderListDto.OrderItem> selectLogicPageList(ReqLogicOrderListDto dto);

    /**
     * 查询物流企业某日的消费统计信息
     * @param dto
     * @return
     * @throws
     */
    RespLogicConsumeStatisticsDto getLogicConsumeStatistics(ReqLogicConsumeStatisticsDto dto);

    /**
     * 油企订单查询
     * @param dto
     * @return
     */
    Page<RespOilCompanyOrderListDto.OilCompanyOrderItem> selectOilCompanyPageList(ReqOilCompanyOrderListDto dto);
    
    
    /**
     * 油企订单打印
     * @param dto
     * @return
     */
    RespOilOrderPrintDto oilOrderPrint(ReqOilOrderPrintDto dto);


}
