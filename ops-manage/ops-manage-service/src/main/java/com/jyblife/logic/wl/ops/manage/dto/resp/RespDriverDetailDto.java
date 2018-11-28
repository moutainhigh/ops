package com.jyblife.logic.wl.ops.manage.dto.resp;

import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public class RespDriverDetailDto {
    private String id;
    private String customerId;
    private String name;
    private String logisticsId;
    private String logisticsName;
    private String phone;
    private String password;
    private String status;
    private String statusName;
    private String openLocation;

    private List<RespFileItemDto> files;//司机图片文件

    private List<RespVehicleDetailDto> vehicles;//车辆


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<RespFileItemDto> getFiles() {
        return files;
    }

    public void setFiles(List<RespFileItemDto> files) {
        this.files = files;
    }

    public List<RespVehicleDetailDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<RespVehicleDetailDto> vehicles) {
        this.vehicles = vehicles;
    }

    public String getOpenLocation() {
        return openLocation;
    }

    public void setOpenLocation(String openLocation) {
        this.openLocation = openLocation;
    }
}
