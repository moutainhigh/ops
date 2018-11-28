package com.jyblife.logic.wl.ops.external.dto.resp;

import java.math.BigDecimal;

/**
 * @author yangcey
 * @date 2018/10/31
 **/
public class RespLogisticsQuotaDto {
    private Integer logisticsId;
    //'授信额度'
    private String creditQuota = "0.00";
    //'冻结额度'
    private String frozenQuota = "0.00";
    //已使用额度
    private String usedQuota = "0.00";
    //剩余可用额度(creditQuota-frozenQuota-usedQuota)
    private String surplusQutota = "0.00";

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getCreditQuota() {
        return creditQuota;
    }

    public void setCreditQuota(String creditQuota) {
        this.creditQuota = creditQuota;
    }

    public String getFrozenQuota() {
        return frozenQuota;
    }

    public void setFrozenQuota(String frozenQuota) {
        this.frozenQuota = frozenQuota;
    }

    public String getUsedQuota() {
        return usedQuota;
    }

    public void setUsedQuota(String usedQuota) {
        this.usedQuota = usedQuota;
    }

    public String getSurplusQutota() {
        return surplusQutota;
    }

    public void setSurplusQutota(String surplusQutota) {
        this.surplusQutota = surplusQutota;
    }
}
