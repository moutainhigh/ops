package com.jyblife.logic.wl.ops.external.dto.req;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReqBindDriverVehicleDto {

	@Min(value = 0, message = "请填写正确的客户id")
	@NotNull(message = "客户id不能为空")
	public Integer customerId;

	public List<Vehicle> vehicles;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public List<Vehicle> getVehicles() {
		if(vehicles == null) {
			vehicles = new ArrayList<ReqBindDriverVehicleDto.Vehicle>();
		}
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public class Vehicle {

		@Min(value = 0, message = "请填写正确的车辆id")
		@NotNull(message = "车辆id不能为空")
		public Integer vehicleId;

		public Integer getVehicleId() {
			return vehicleId;
		}

		public void setVehicleId(Integer vehicleId) {
			this.vehicleId = vehicleId;
		}
		
	}

}
