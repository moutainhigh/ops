package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqOrderSubmitDto {
	
    @NotNull(message = "司机不能为空")
    private Integer customerId;
    @NotNull(message = "车辆不能为空")
    private Integer vehicleId;
    @NotNull(message = "加油站不能为空")
    private Integer stationId;
    @NotNull(message = "油品不能为空")
    private Integer goodsId;
    @NotNull(message = "加油量不能为空")
    private String quantity;
    @NotBlank(message = "密码不能为空")
    private String password;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
