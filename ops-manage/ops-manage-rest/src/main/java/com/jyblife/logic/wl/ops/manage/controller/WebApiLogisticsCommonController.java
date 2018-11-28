package com.jyblife.logic.wl.ops.manage.controller;

import com.jyblife.logic.wl.ops.common.enums.BankKeeperStatusEnum;
import com.jyblife.logic.wl.ops.common.enums.LogisticsQuotaLogCategoryEnum;
import com.jyblife.logic.wl.ops.common.enums.LogisticsQuotaStatusEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webAPI/logisticsCommon")
public class WebApiLogisticsCommonController extends BaseController {


    @RequestMapping("/dropDownListMap")
    public RespJson dropDownListMap() {
        try {
            Map<String, List> data = new HashMap<String, List>();
            this.create_logistics_company_status(data);
            this.create_logistics_company_out_status(data);
            this.create_driver_status(data);
            this.create_logistics_quota_status(data);
            this.create_logistics_quota_log_category(data);
            return RespJson.success(data);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }


    private void create_logistics_company_status(Map<String, List> data) {
        List<Map<String, Object>> logistics_company_status = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
            logistics_company_status.add(this.createMap(statusEnum.getValue(), statusEnum.getText()));
        }
        data.put("logistics_company_status", logistics_company_status);
    }

    private void create_logistics_company_out_status(Map<String, List> data) {
        List<Map<String, Object>> logistics_company_out_status = new ArrayList<Map<String, Object>>();
        for (BankKeeperStatusEnum statusEnum : BankKeeperStatusEnum.values()) {
            logistics_company_out_status.add(this.createMap(statusEnum.getValue(), statusEnum.getText()));
        }
        data.put("logistics_company_out_status", logistics_company_out_status);
    }

    private void create_driver_status(Map<String, List> data) {
        List<Map<String, Object>> driver_status = new ArrayList<Map<String, Object>>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
            driver_status.add(this.createMap(statusEnum.getValue(), statusEnum.getText()));
        }
        data.put("driver_status", driver_status);
    }

    private void create_logistics_quota_status(Map<String, List> data) {
        List<Map<String, Object>> logistics_quota_status = new ArrayList<Map<String, Object>>();
        for (LogisticsQuotaStatusEnum statusEnum : LogisticsQuotaStatusEnum.values()) {
            logistics_quota_status.add(this.createMap(statusEnum.getValue(), statusEnum.getText()));
        }
        data.put("logistics_quota_status", logistics_quota_status);


    }

    private void create_logistics_quota_log_category(Map<String, List> data) {
        List<Map<String, Object>> logistics_quota_log_category = new ArrayList<Map<String, Object>>();
        for (LogisticsQuotaLogCategoryEnum statusEnum : LogisticsQuotaLogCategoryEnum.values()) {
            logistics_quota_log_category.add(this.createMap(statusEnum.getValue(), statusEnum.getText()));
        }

        data.put("logistics_quota_log_category", logistics_quota_log_category);
    }

    private Map<String, Object> createMap(Integer id, String value) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("id", id);
        map.put("value", value);
        return map;
    }
}


