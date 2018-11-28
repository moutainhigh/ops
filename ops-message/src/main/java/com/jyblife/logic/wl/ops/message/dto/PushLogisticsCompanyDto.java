package com.jyblife.logic.wl.ops.message.dto;

public class PushLogisticsCompanyDto {

	private String logi_code;
	private String logistics_name;
	private String status;
	private String address;
	private String remark;

	public String getLogi_code() {
		return logi_code;
	}

	public void setLogi_code(String logi_code) {
		this.logi_code = logi_code;
	}

	public String getLogistics_name() {
		return logistics_name;
	}

	public void setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
