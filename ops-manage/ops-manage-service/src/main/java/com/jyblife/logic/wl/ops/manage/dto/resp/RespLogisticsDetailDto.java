package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public class RespLogisticsDetailDto {

    /**
     * 物流企业id
     */
    private String logisticsId;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 银管家标识
     */
    private String outIdentity;

    /**
     * 银管家状态
     */
    private Integer outStatus;

    private String outStatusName;

    /**
     * 状态
     */
    private Integer status;

    private String statusName;

    private String creditQuota;

    private Date startDate;
    private Date endDate;

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutIdentity() {
        return outIdentity;
    }

    public void setOutIdentity(String outIdentity) {
        this.outIdentity = outIdentity;
    }

    public Integer getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(Integer outStatus) {
        this.outStatus = outStatus;
    }

    public String getOutStatusName() {
        return outStatusName;
    }

    public void setOutStatusName(String outStatusName) {
        this.outStatusName = outStatusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCreditQuota() {
        return creditQuota;
    }

    public void setCreditQuota(String creditQuota) {
        this.creditQuota = creditQuota;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
