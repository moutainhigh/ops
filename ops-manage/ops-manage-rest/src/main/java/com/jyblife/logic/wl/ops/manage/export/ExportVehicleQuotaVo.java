package com.jyblife.logic.wl.ops.manage.export;

import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;

public class ExportVehicleQuotaVo {
	
    @ExcelAttribute(name = "编号", column = "A")
    private String vehicleId;
    @ExcelAttribute(name = "物流企业", column = "B")
    private String logisticsName;
    @ExcelAttribute(name = "车牌号", column = "C")
    private String number;
    @ExcelAttribute(name = "油箱容量/L", column = "D")
    private String capacity;
    @ExcelAttribute(name = "每日额度占比", column = "E")
    private String rate;
    @ExcelAttribute(name = "每日车辆容量/L", column = "F")
    private String dailyCapacity;
    @ExcelAttribute(name = "当日车辆可用容量/L", column = "G")
    private String dailyAvailableCapacity;


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDailyCapacity() {
        return dailyCapacity;
    }

    public void setDailyCapacity(String dailyCapacity) {
        this.dailyCapacity = dailyCapacity;
    }

    public String getDailyAvailableCapacity() {
        return dailyAvailableCapacity;
    }

    public void setDailyAvailableCapacity(String dailyAvailableCapacity) {
        this.dailyAvailableCapacity = dailyAvailableCapacity;
    }
}
