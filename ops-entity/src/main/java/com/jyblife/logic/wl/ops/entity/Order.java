package com.jyblife.logic.wl.ops.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String code;

    /**
     * 订单类型：1正常，2补单，3退单
     */
    private Integer orderType;
    
    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 车辆id
     */
    private Integer vehicleId;

    /**
     * 油站id
     */
    private Integer stationId;

    /**
     * 油品id
     */
    private Integer goodsId;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 采购单价
     */
    private Integer priceBuy;

    /**
     * 销售单价
     */
    private Integer priceSell;

    /**
     * 零售单价
     */
    private Integer priceRetail;

    /**
     * 油企id
     */
    private Integer oilCompanyId;

    /**
     * 物流企业id
     */
    private Integer logisticsId;

    /**
     * 订单失败原因
     */
    private String failedReason;

    /**
     * 生效时间
     */
    private Date effectTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 状态时间
     */
    private Date statusTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建用户
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户
     */
    private Integer updateUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 订单id
     * @return order_id 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单编号
     * @return code 订单编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 订单编号
     * @param code 订单编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	/**
     * 用户id
     * @return customer_id 用户id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 用户id
     * @param customerId 用户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 车辆id
     * @return vehicle_id 车辆id
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * 车辆id
     * @param vehicleId 车辆id
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 油站id
     * @return station_id 油站id
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * 油站id
     * @param stationId 油站id
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * 油品id
     * @return goods_id 油品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 油品id
     * @param goodsId 油品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 数量
     * @return quantity 数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 数量
     * @param quantity 数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 采购单价
     * @return price_buy 采购单价
     */
    public Integer getPriceBuy() {
        return priceBuy;
    }

    /**
     * 采购单价
     * @param priceBuy 采购单价
     */
    public void setPriceBuy(Integer priceBuy) {
        this.priceBuy = priceBuy;
    }

    /**
     * 销售单价
     * @return price_sell 销售单价
     */
    public Integer getPriceSell() {
        return priceSell;
    }

    /**
     * 销售单价
     * @param priceSell 销售单价
     */
    public void setPriceSell(Integer priceSell) {
        this.priceSell = priceSell;
    }

    /**
     * 零售单价
     * @return price_retail 零售单价
     */
    public Integer getPriceRetail() {
        return priceRetail;
    }

    /**
     * 零售单价
     * @param priceRetail 零售单价
     */
    public void setPriceRetail(Integer priceRetail) {
        this.priceRetail = priceRetail;
    }

    /**
     * 油企id
     * @return oil_company_id 油企id
     */
    public Integer getOilCompanyId() {
        return oilCompanyId;
    }

    /**
     * 油企id
     * @param oilCompanyId 油企id
     */
    public void setOilCompanyId(Integer oilCompanyId) {
        this.oilCompanyId = oilCompanyId;
    }

    /**
     * 物流企业id
     * @return logistics_id 物流企业id
     */
    public Integer getLogisticsId() {
        return logisticsId;
    }

    /**
     * 物流企业id
     * @param logisticsId 物流企业id
     */
    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    /**
     * 订单失败原因
     * @return failed_reason 订单失败原因
     */
    public String getFailedReason() {
        return failedReason;
    }

    /**
     * 订单失败原因
     * @param failedReason 订单失败原因
     */
    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason == null ? null : failedReason.trim();
    }

    /**
     * 生效时间
     * @return effect_time 生效时间
     */
    public Date getEffectTime() {
        return effectTime;
    }

    /**
     * 生效时间
     * @param effectTime 生效时间
     */
    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 状态时间
     * @return status_time 状态时间
     */
    public Date getStatusTime() {
        return statusTime;
    }

    /**
     * 状态时间
     * @param statusTime 状态时间
     */
    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建用户
     * @return create_user_id 创建用户
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建用户
     * @param createUserId 创建用户
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新用户
     * @return update_user_id 更新用户
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新用户
     * @param updateUserId 更新用户
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}