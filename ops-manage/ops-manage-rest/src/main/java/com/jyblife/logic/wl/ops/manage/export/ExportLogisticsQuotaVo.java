package com.jyblife.logic.wl.ops.manage.export;

import com.jyblife.logic.wl.ops.common.excel.DateFormat;
import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;

import java.util.Date;

public class ExportLogisticsQuotaVo {
	
    @ExcelAttribute(name = "物流公司", column = "A")
    private String logisticsName;
    @ExcelAttribute(name = "额度状态", column = "B")
    private String statusName;
    @ExcelAttribute(name = "企业额度", column = "C")
    private String creditQuota;
    @ExcelAttribute(name = "企业可用额度", column = "D")
    private String availableQuota;
    @ExcelAttribute(name = "每日额度占比", column = "E")
    private String rate;
    @ExcelAttribute(name = "每日企业额度", column = "F")
    private String dailyCreditQuota;
    @ExcelAttribute(name = "当日可用额度", column = "G")
    private String dailyAvailableQuota;
    @ExcelAttribute(name = "开始时间", column = "H")
    @DateFormat(format = "yyyy-MM-dd")
    private Date startDate;
    @ExcelAttribute(name = "结束时间", column = "I")
    @DateFormat(format = "yyyy-MM-dd")
    private Date endDate;


    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
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

    public String getAvailableQuota() {
        return availableQuota;
    }

    public void setAvailableQuota(String availableQuota) {
        this.availableQuota = availableQuota;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDailyCreditQuota() {
        return dailyCreditQuota;
    }

    public void setDailyCreditQuota(String dailyCreditQuota) {
        this.dailyCreditQuota = dailyCreditQuota;
    }

    public String getDailyAvailableQuota() {
        return dailyAvailableQuota;
    }

    public void setDailyAvailableQuota(String dailyAvailableQuota) {
        this.dailyAvailableQuota = dailyAvailableQuota;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
