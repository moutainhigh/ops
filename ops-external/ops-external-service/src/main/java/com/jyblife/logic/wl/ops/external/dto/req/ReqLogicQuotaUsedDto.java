package com.jyblife.logic.wl.ops.external.dto.req;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author yangcey
 * @date 2018/10/31
 **/
public class ReqLogicQuotaUsedDto {
    @NotNull(message = "物流企业id不能为空")
    private Integer logisticsId;
    //yyyy-MM-dd
    @NotBlank(message = "起始日期不能为空")
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$",message = "日期格式不正确")
    private String startDate;
    //yyyy-MM-dd
    @NotBlank(message = "结束日期不能为空")
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$",message = "日期格式不正确")
    private String endDate;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
