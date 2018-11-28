package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

/**
 * @author yangcey
 * @date 2018/9/28
 **/
public class ReqUserOrderVehicleDto {
    @NotNull(message = "用户id不能为空")
    private Integer customerId;
    @NotNull(message = "加油站id不能为空")
    private Integer stationId;
    private Integer goodsId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
}
