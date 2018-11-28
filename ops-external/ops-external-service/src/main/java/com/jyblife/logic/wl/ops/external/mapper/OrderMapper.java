package com.jyblife.logic.wl.ops.external.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.Order;
import com.jyblife.logic.wl.ops.external.dto.req.ReqLogicOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilCompanyOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilOrderPrintDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqUserOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogicOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilCompanyOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilOrderPrintDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOrderDetailDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespUserOrderDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespUserOrderListDto;

public interface OrderMapper extends BaseMapper<Order, Long> {
    /**
     * deleteByPrimaryKey
     *
     * @param orderId
     * @return int
     */
    int deleteByPrimaryKey(Long orderId);

    /**
     * insert
     *
     * @param record
     * @return int
     */
    int insert(Order record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(Order record);

    /**
     * selectByPrimaryKey
     *
     * @param orderId
     * @return Order
     */
    Order selectByPrimaryKey(Long orderId);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Order record);


    RespOrderDetailDto selectOrderDetail(Long orderId);

    List<RespUserOrderListDto.OrderItem> selectPageByCustomerId(ReqUserOrderListDto dto);


    List<RespUserOrderDto> selectUserOrderList(@Param("customerId") Integer customerId, @Param("stationId") Integer stationId, @Param("goodsId") Integer goodsId);

    int insertOrder(Order order);

    String getCurDateLatestOrderCode();

    /**
     * 物流b端查询订单列表
     *
     * @param dto
     * @return
     */
    List<RespLogicOrderListDto.OrderItem> selectLogicPageList(ReqLogicOrderListDto dto);

    int selectVehicleCount(@Param("logisticsId") Integer logisticsId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    int selectOrderCount(@Param("logisticsId") Integer logisticsId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    BigDecimal selectConsumeAmount(@Param("logisticsId") Integer logisticsId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 油企b端查询订单列表
     *
     * @param dto
     * @return
     */
    List<RespOilCompanyOrderListDto.OilCompanyOrderItem> selectOilCompanyPageList(ReqOilCompanyOrderListDto dto);
    
    /**
     * 每月加油量总汇
     * @param customerId
     * @return
     */
    List<Map<String, Object>> selectMonthSumByCustomerId(@Param("customerId") Integer customerId);
    
    /**
     * 油企b端订单打印
     * @param dto
     * @return
     */
    RespOilOrderPrintDto selectPrintOilOrder(ReqOilOrderPrintDto dto);
    
    
}