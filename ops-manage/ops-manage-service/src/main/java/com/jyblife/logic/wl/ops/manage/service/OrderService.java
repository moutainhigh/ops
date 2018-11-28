package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.OrderListDto;
import com.jyblife.logic.wl.ops.manage.dto.RepairOrderDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOrderDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOrderListDto;

/**
 * 订单服务
 * 
 */
public interface OrderService {

    Page<RespOrderListDto.OrderItem> selectPageList(OrderListDto.Search search, int page, int pageSize);

    RespOrderDetailDto selectOrderDetail(Long orderId);
   
    /**
     * 补订单
     * @param dto
     * @return
     */
    RespJson repairOrder(RepairOrderDto dto, SystemUser user);
    
}
