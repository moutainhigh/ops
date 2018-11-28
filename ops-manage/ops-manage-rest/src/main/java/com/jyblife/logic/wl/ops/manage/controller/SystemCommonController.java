package com.jyblife.logic.wl.ops.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.service.OilStationService;

@RestController
@RequestMapping("/admin/common")
public class SystemCommonController extends BaseController {

	@Autowired
	OilStationService oilStationServiceImpl;

    @RequestMapping("/dropDownListMap")
    public RespJson dropDownListMap() {
        try {
            Map<String, List> data = new HashMap<String, List>();
            this.create_module_status(data);
            this.create_module_is_public(data);
            this.create_module_is_menu(data);
            this.create_user_status(data);
            this.create_role_status(data);
            this.create_module_is_external(data);
            return RespJson.success(data);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    private void create_module_status(Map<String, List> data) {
        List<Map<String, Object>> module_status = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getValue().intValue() == 1 ? "已启用" : "未启用");
        	module_status.add(map);
        }
        data.put("module_status", module_status);
    }

    private void create_module_is_public(Map<String, List> data) {
        List<Map<String, Object>> module_is_public = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getValue().intValue() == 1 ? "公开" : "不公开");
            module_is_public.add(map);
        }
        data.put("module_is_public", module_is_public);
    }
    
    private void create_module_is_menu(Map<String, List> data) {
        List<Map<String, Object>> module_is_menu = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getValue().intValue() == 1 ? "是菜单" : "不是菜单");
            module_is_menu.add(map);
        }
        data.put("module_is_menu", module_is_menu);
    }
    
    private void create_user_status(Map<String, List> data) {
        List<Map<String, Object>> user_status = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getValue().intValue() == 1 ? "启用" : "未启用");
            user_status.add(map);
        }
        data.put("user_status", user_status);
    }
    
    private void create_role_status(Map<String, List> data) {
        List<Map<String, Object>> role_status = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getValue().intValue() == 1 ? "启用" : "未启用");
            role_status.add(map);
        }
        data.put("role_status", role_status);
    }
    
    private void create_module_is_external(Map<String, List> data) {
        List<Map<String, Object>> module_is_external = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
        	Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("id", statusEnum.getValue());
            map.put("value", statusEnum.getValue().intValue() == 1 ? "外部" : "内部");
            module_is_external.add(map);
        }
        data.put("module_is_external", module_is_external);
    }
    
    @RequestMapping("/oilStationList")
    public RespJson oilStationList() {
        try {
            return oilStationServiceImpl.oilStationList();
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
    
}


