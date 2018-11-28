package com.jyblife.logic.wl.ops.external.dto.resp;

import java.util.List;

public class RespDriverVehicleInfoDto {

	private String id;
	private String customerId;
	private String name;
	private String logisticsId;
	private String logisticsName;
	private String phone;
	private String password;
	private String status;
	private String statusName;
	private List<RespFileItemDto> files;
	private List<Vehicle> vehicles;

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

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public static class Vehicle {
		
		private String vehicleId;
		private String number;
		private String model;
		private String capacity;
		private String logisticsId;
		private String logisticsName;
		private String addTime;
		private String operator;
		private String startDate;
		private String endDate;
		private String remark;
		private String status;
		private Integer state;
		private String statusName;
		private String dayCapacity;
		private String balanceCapacity;
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

		public String getAddTime() {
			return addTime;
		}

		public void setAddTime(String addTime) {
			this.addTime = addTime;
		}

		public String getOperator() {
			return operator;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
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

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}
	}

}
