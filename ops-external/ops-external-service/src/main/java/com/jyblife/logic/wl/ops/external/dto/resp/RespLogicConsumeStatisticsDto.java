package com.jyblife.logic.wl.ops.external.dto.resp;

/**
 * @author yangcey
 * @date 2018/10/31
 **/
public class RespLogicConsumeStatisticsDto {
    //物流企业id
    private Integer logisticsId;
    //车辆数
    private Integer vehicleNum = 0;
    //订单数
    private Integer orderNum = 0;
    //销售额
    private String amount = "0.00";


    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(Integer vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
