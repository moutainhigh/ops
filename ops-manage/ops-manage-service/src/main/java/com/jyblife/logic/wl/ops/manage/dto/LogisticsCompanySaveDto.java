package com.jyblife.logic.wl.ops.manage.dto;

import javax.validation.constraints.NotNull;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public class LogisticsCompanySaveDto {
    @NotNull(message = "物流企业不能为空")
    private Integer logisticsId;
    @NotNull(message = "企业状态不能为空")
    private Integer status;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
