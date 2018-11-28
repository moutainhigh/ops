package com.jyblife.logic.wl.ops.manage.controller;

import com.jyblife.logic.wl.ops.common.enums.*;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.entity.AreaCode;
import com.jyblife.logic.wl.ops.entity.OilCompany;
import com.jyblife.logic.wl.ops.manage.common.AreaCache;
import com.jyblife.logic.wl.ops.manage.dto.AreaCodeDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.mapper.AreaCodeMapper;
import com.jyblife.logic.wl.ops.manage.service.OilCompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webAPI/oilCommon")
public class WebApiOilCommonController extends BaseController {
    @Autowired
    private AreaCodeMapper areaCodeMapper;
    @Autowired
    private OilCompanyService oilCompanyService;

    @RequestMapping("/areaDict")
    public RespJson areaDict() {
        try {
            //查询数据比较缓慢最好只加载一次,从缓存获取
            if (AreaCache.areaCodeCache == null) {
            	List<AreaCode> all = areaCodeMapper.selectAll();
            	//List<AreaCode> all2 = all;
            	AreaCodeDto dto = new AreaCodeDto();
            	List<AreaCodeDto> level_1 = new ArrayList<>();
            	Map<Integer, List<AreaCodeDto>> level_2 = new HashMap<Integer, List<AreaCodeDto>>();
            	
            	for(int i=0; i<all.size(); i++) {
            		AreaCodeDto d = new AreaCodeDto();
        			d.setId(all.get(i).getAreaCode());
        			d.setName(all.get(i).getAreaName());
            		if (all.get(i).getLevel().intValue() == 0) {
            			dto = d;
            		} else if(all.get(i).getLevel().intValue() == 1) {
            			level_1.add(d);
            			level_2.put(all.get(i).getAreaCode(), new ArrayList<AreaCodeDto>());
            		} else if(all.get(i).getLevel().intValue() == 2) {
            			//暂时不需要县级别
            			/*for(int j=0; j<all2.size(); j++) {
            				if(all2.get(j).getpAreaCode().intValue() == d.getId().intValue()) {
            					AreaCodeDto area = new AreaCodeDto();
            					area.setId(all2.get(j).getAreaCode());
            					area.setName(all2.get(j).getAreaName());
            					d.getChildren().add(area);
            				}
            			}*/
            			level_2.get(all.get(i).getpAreaCode()).add(d);
            		}
            	}
            	
            	for(AreaCodeDto acd : level_1) {
            		acd.setChildren(level_2.get(acd.getId()));
            	}
            	dto.setChildren(level_1);
            	
                AreaCache.areaCodeCache = dto;
            }
            return RespJson.success(AreaCache.areaCodeCache);
        } catch (Exception e) {
            logger.error("异常: " + e.getMessage(), e);
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @RequestMapping("/dropDownListMap")
    public RespJson dropDownListMap() {
        try {
            Map<String, List> data = new HashMap<String, List>();
            this.createOilCompanyStatus(data);
            this.createOilCompanyOwnership(data);
            this.createOilStationStatus(data);
            this.createOilStationApplyStatus(data);
            this.createOilCompanyList(data);
            this.createOilGoodsStatus(data);
            this.createOilPriceStatus(data);
            this.createOrderStatus(data);
            this.createOilPriceApplyAuditStatus(data);
            return RespJson.success(data);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    //油企状态
    private void createOilCompanyStatus(Map<String, List> data) {
    	List<Map<String, Object>> oil_company_status = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getText());
            oil_company_status.add(map);
        }
        data.put("oil_company_status", oil_company_status);
    }
    
    //企业所有制
    private void createOilCompanyOwnership(Map<String, List> data) {
    	List<Map<String, Object>> ownership = new ArrayList<Map<String, Object>>();
        for (OilCompanyOwnershipEnum statusEnum : OilCompanyOwnershipEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getText());
            ownership.add(map);
        }
        data.put("ownership", ownership);
    }

    //油站状态
    private void createOilStationStatus(Map<String, List> data) {
    	List<Map<String, Object>> oil_station_status = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getText());
            oil_station_status.add(map);
        }
        data.put("oil_station_status", oil_station_status);
    }
    
    //油站申请状态
    private void createOilStationApplyStatus(Map<String, List> data) {
    	List<Map<String, Object>> oil_station_apply_status = new ArrayList<Map<String, Object>>();
        for (OilStationApplyStatusEnum statusEnum : OilStationApplyStatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getText());
            oil_station_apply_status.add(map);
        }
        data.put("oil_station_apply_status", oil_station_apply_status);
    }
    
    //油企下拉列表
    private void createOilCompanyList(Map<String, List> data) {
    	List<OilCompany> allEnable = oilCompanyService.listAllEnable();
    	List<Map<String, Object>> oil_company_id_name_map = new ArrayList<Map<String, Object>>();
        for (OilCompany company : allEnable) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", company.getCompanyId());
            map.put("value", company.getName());
            oil_company_id_name_map.add(map);
        }
        data.put("oil_company_id_name_map", oil_company_id_name_map);
    }
    
    //油品状态
    private void createOilGoodsStatus(Map<String, List> data) {
    	List<Map<String, Object>> oil_goods_status = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getText());
            oil_goods_status.add(map);
        }
        data.put("oil_goods_status", oil_goods_status);
    }
    
    //油价状态 价格状态 1-待生效，2-已生效，3已失效
    private void createOilPriceStatus(Map<String, List> data) {
    	List<Map<String, Object>> oil_price_status = new ArrayList<Map<String, Object>>();
    	Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("id", 1);
        map.put("value", "待生效");
        oil_price_status.add(map);
        Map<String, Object> map1 = new HashMap<String, Object>(2);
        map1.put("id", 2);
        map1.put("value", "已生效");
        oil_price_status.add(map1);
        Map<String, Object> map2 = new HashMap<String, Object>(2);
        map2.put("id", 3);
        map2.put("value", "已失效");
        oil_price_status.add(map2);
        data.put("oil_price_status", oil_price_status);
    }
    
    //订单状态状态
    private void createOrderStatus(Map<String, List> data) {
    	List<Map<String, Object>> order_status = new ArrayList<Map<String, Object>>();
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getText());
            order_status.add(map);
        }
        data.put("order_status", order_status);
    }

    //订单状态状态
    private void createOilPriceApplyAuditStatus(Map<String, List> data) {
        List<Map<String, Object>> order_status = new ArrayList<Map<String, Object>>();
        for (OilPriceApplyStatusEnum statusEnum : OilPriceApplyStatusEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getText());
            order_status.add(map);
        }
        data.put("oil_price_apply_status", order_status);
    }

}
