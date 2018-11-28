package com.jyblife.logic.wl.ops.manage.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class RepairOrderDto {

	@NotNull(message = "logisticsId不能为空")
	private Integer logisticsId;
	@NotNull(message = "customerId不能为空")
	private Integer customerId;
	@NotNull(message = "vehicleId不能为空")
	private Integer vehicleId;
	@NotNull(message = "stationId不能为空")
	private Integer stationId;
	@NotNull(message = "goodsId不能为空")
	private Integer goodsId;
	@NotBlank(message = "quantity不能为空")
	private String quantity;
	@NotBlank(message = "remark不能为空")
	private String remark;

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
