package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public class RespVehicleDetailDto {

    private String vehicleId;
    private String number;
    private String model;
    private String capacity;
    private String logisticsId;
    private String logisticsName;
    private Date addTime;
    private String operator;
    private String remark;
    private Date startDate;
    private Date endDate;
    private String status;
    private String statusName;
    private String dayCapacity;
    private String balanceCapacity;
    private String stateName;
    private Integer state;
    private List<RespFileItemDto> files;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDayCapacity() {
        return dayCapacity;
    }

    public void setDayCapacity(String dayCapacity) {
        this.dayCapacity = dayCapacity;
    }

    public String getBalanceCapacity() {
        return balanceCapacity;
    }

    public void setBalanceCapacity(String balanceCapacity) {
        this.balanceCapacity = balanceCapacity;
    }

    public List<RespFileItemDto> getFiles() {
        return files;
    }

    public void setFiles(List<RespFileItemDto> files) {
        this.files = files;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
