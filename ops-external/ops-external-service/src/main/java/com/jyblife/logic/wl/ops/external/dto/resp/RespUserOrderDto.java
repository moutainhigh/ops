package com.jyblife.logic.wl.ops.external.dto.resp;

/**
 * @author yangcey
 * @date 2018/9/28
 **/
public class RespUserOrderDto {

    private Integer goodsId;
    private String goodsName;

    private Integer vehicleId;
    private String vehicleNumber;
    private String vehicleModel;
    private String maxAvailableQuantity;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getMaxAvailableQuantity() {
        return maxAvailableQuantity;
    }

    public void setMaxAvailableQuantity(String maxAvailableQuantity) {
        this.maxAvailableQuantity = maxAvailableQuantity;
    }
}
