package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.Order;
import com.jyblife.logic.wl.ops.manage.dto.OrderListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOrderDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOrderListDto;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order,Long> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param orderId
     * @return int
     */
    int deleteByPrimaryKey(Long orderId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(Order record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(Order record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param orderId
     * @return Order
     */
    Order selectByPrimaryKey(Long orderId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Order record);


    List<RespOrderListDto.OrderItem> selectPageList(OrderListDto.Search search);


    RespOrderDetailDto selectOrderDetail(Long orderId);
    
    int insertOrder(Order order);
}