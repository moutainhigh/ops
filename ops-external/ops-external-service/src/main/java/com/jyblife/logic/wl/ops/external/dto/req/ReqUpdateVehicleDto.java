package com.jyblife.logic.wl.ops.external.dto.req;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReqUpdateVehicleDto {

	@Min(value = 0, message = "请填写正确的vehicle_id")
	@NotNull(message = "vehicle_id不能为空")
	private Integer vehicleId;
	
	@Min(value = 0, message = "请填写正确的capacity")
	@NotNull(message = "capacity不能为空")
	private BigDecimal capacity;
	
	@Min(value = 0, message = "请填写正确的state")
	@Max(value = 1, message = "请填写正确的state")
	@NotNull(message = "state不能为空")
	private Integer state;
	
	private String remark;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
