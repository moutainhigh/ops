package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.OrderStatusEnum;
import com.jyblife.logic.wl.ops.common.enums.OrderTypeEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.ExcelUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.OrderListDto;
import com.jyblife.logic.wl.ops.manage.dto.RepairOrderDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOrderDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOrderListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOrderListDto.OrderItem;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.export.ExportOrderVo;
import com.jyblife.logic.wl.ops.manage.intercept.Authentication;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.OrderMapper;
import com.jyblife.logic.wl.ops.manage.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webAPI/order")
public class WebApiOrderController extends BaseController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderMapper orderMapper;

    @Authentication
    @Permission(value="order-list")
    @RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) OrderListDto orderListDto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (orderListDto == null) {
                orderListDto = new OrderListDto();
            }
            RespOrderListDto respOrderListDto = new RespOrderListDto();
            Page<RespOrderListDto.OrderItem> page = orderService.selectPageList(orderListDto.getSearch(), orderListDto.getPage(), orderListDto.getPageSize());
            boolean isCanEdit = isHadPermission(sessionId, "order-edit");
            boolean isCanView = isHadPermission(sessionId, "order-detail");
            page.getResult().stream().forEach(new Consumer<RespOrderListDto.OrderItem>() {
                @Override
                public void accept(RespOrderListDto.OrderItem item) {
                	item.setIsCanEdit(isCanEdit);
                	item.setIsCanView(isCanView);
                	item.setStatusName(OrderStatusEnum.getText(StrUtils.str2Int(item.getStatus())));
                	item.setOrderType(OrderTypeEnum.getText(StrUtils.str2Int(item.getOrderType())));
                }
            });
            
            respOrderListDto.setData(page.getResult());
            respOrderListDto.setPage(page.getPageNum());
            respOrderListDto.setPageSize(page.getPageSize());
            respOrderListDto.setTotalPages(page.getPages());
            respOrderListDto.setTotalRows((int) page.getTotal());
            respOrderListDto.setSearchItems(orderListDto.getSearch());

            return RespJson.success(respOrderListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Authentication
    @Permission(value="order-detail")
    @RequestMapping("/detail")
    public RespJson detail(@RequestParam("order_id") Long orderId) {
        try {

            RespOrderDetailDto respOrderDetailDto = orderService.selectOrderDetail(orderId);

            return RespJson.success(respOrderDetailDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
    
    @Authentication
    @Permission(value="order-repairOrder")
    @RequestMapping("/repairOrder")
    public RespJson repairOrder(@RequestBody RepairOrderDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
        	SystemUser user = getCurSystemUser(sessionId);
            return orderService.repairOrder(dto, user);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
    
    @Permission(value="order-export")
    @RequestMapping("/export")
    public void export(OrderListDto dto, HttpServletResponse response) {
        try {
        	List<OrderItem> list = orderMapper.selectPageList(dto.getSearch());
        	List<ExportOrderVo> expList = new ArrayList<>();

        	BigDecimal p100 = new BigDecimal("100");
        	for(OrderItem item : list) {
        		ExportOrderVo vo = new ExportOrderVo();
        		BeanUtils.copyProperties(item, vo);
        		vo.setQuantity(new BigDecimal(item.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " 升");
        		vo.setPriceSell(new BigDecimal(item.getPriceSell()).divide(p100).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " 元/升");
        		vo.setSellAmount(new BigDecimal(item.getSellAmount()).divide(p100).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " 元");
        		vo.setStatusName(OrderStatusEnum.getText(StrUtils.str2Int(item.getStatus())));
        		expList.add(vo);
        	}
        	
        	ExcelUtil<ExportOrderVo> eu = new ExcelUtil<ExportOrderVo>(ExportOrderVo.class);
            eu.exportExcel(expList, response);
        } catch (Exception e) {
            logger.error("导出文件文件出错，:{}", e.getMessage());
        }
    }

}
