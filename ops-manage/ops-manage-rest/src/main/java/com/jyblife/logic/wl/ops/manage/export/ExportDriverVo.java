package com.jyblife.logic.wl.ops.manage.export;

import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;

public class ExportDriverVo {
	
    @ExcelAttribute(name = "编号", column = "A")
    private String driverId;
    @ExcelAttribute(name = "姓名", column = "B")
    private String name;
    @ExcelAttribute(name = "企业名称", column = "C")
    private String logisticsName;
    @ExcelAttribute(name = "电话", column = "D")
    private String phone;
    @ExcelAttribute(name = "状态", column = "E")
    private String status;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
