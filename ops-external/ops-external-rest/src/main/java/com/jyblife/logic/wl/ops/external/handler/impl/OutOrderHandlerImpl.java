package com.jyblife.logic.wl.ops.external.handler.impl;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.*;
import com.jyblife.logic.wl.ops.external.dto.resp.*;
import com.jyblife.logic.wl.ops.external.exception.RestException;
import com.jyblife.logic.wl.ops.external.handler.AbstractHandler;
import com.jyblife.logic.wl.ops.external.handler.OutOrderHandler;
import com.jyblife.logic.wl.ops.external.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutOrderHandlerImpl extends AbstractHandler implements OutOrderHandler {

    @Autowired
    private OrderService orderService;

    @Override
    public OutRespJson submitOrder(ReqOrderSubmitDto dto) {
        try {
            return orderService.submitOrder(dto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getOrderDetail(ReqOrderDetailDto dto) {
        try {
            RespOrderDetailDto respOrderDetailDto = orderService.selectOrderDetail(Long.parseLong(dto.getOrderId()));
            return OutRespJson.success(respOrderDetailDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getUserOrderList(ReqUserOrderListDto dto) {
        try {
            RespUserOrderListDto respUserOrderListDto = orderService.selectPageByCustomerId(dto);
            return OutRespJson.success(respUserOrderListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getUserOrderVehicles(ReqUserOrderVehicleDto dto) throws OpsException {
        try {
            List<RespUserOrderVehicleDto> list = orderService.selectOrderVehicles(dto.getCustomerId(), dto.getStationId(), dto.getGoodsId());
            return OutRespJson.success(list);
        } catch (OpsException e) {
        	throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getLogicOrderList(ReqLogicOrderListDto dto) {
        try {
            Page<RespLogicOrderListDto.OrderItem> page = orderService.selectLogicPageList(dto);
            RespLogicOrderListDto respOrderListDto = new RespLogicOrderListDto();
            respOrderListDto.setPage(page.getPageNum());
            respOrderListDto.setTotalPages(page.getPages());
            respOrderListDto.setTotalRows(page.getTotal());
            respOrderListDto.setData(page.getResult());
            respOrderListDto.setPageSize(page.getPageSize());
            return OutRespJson.success(respOrderListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getLogicConsumeStatistics(ReqLogicConsumeStatisticsDto dto) throws RestException {
        try {
            RespLogicConsumeStatisticsDto respDto = orderService.getLogicConsumeStatistics(dto);
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
    public OutRespJson getOilCompanyOrderList(ReqOilCompanyOrderListDto dto) {

        try {
            Page<RespOilCompanyOrderListDto.OilCompanyOrderItem> page = orderService.selectOilCompanyPageList(dto);
            RespOilCompanyOrderListDto respOrderListDto = new RespOilCompanyOrderListDto();
            respOrderListDto.setPage(page.getPageNum());
            respOrderListDto.setTotalPages(page.getPages());
            respOrderListDto.setTotalRows(page.getTotal());
            respOrderListDto.setData(page.getResult());
            respOrderListDto.setPageSize(page.getPageSize());
            return OutRespJson.success(respOrderListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

	@Override
	public OutRespJson oilOrderPrint(ReqOilOrderPrintDto dto) {
		
		try {
			RespOilOrderPrintDto data = orderService.oilOrderPrint(dto);
            return OutRespJson.success(data);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
	}
}
