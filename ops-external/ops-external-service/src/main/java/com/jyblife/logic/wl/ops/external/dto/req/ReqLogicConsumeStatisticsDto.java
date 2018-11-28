package com.jyblife.logic.wl.ops.external.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author yangcey
 * @date 2018/10/31
 **/
public class ReqLogicConsumeStatisticsDto {
    @NotNull(message = "物流企业id不能为空")
    private Integer logisticsId;
    //yyyy-MM-dd
    //做一个简单日期格式的校验
    @NotBlank(message = "查询的时期不能为空")
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$",message = "日期格式不正确")
    private String date;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
