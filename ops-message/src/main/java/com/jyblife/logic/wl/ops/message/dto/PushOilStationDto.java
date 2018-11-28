package com.jyblife.logic.wl.ops.message.dto;

public class PushOilStationDto {

	private String oil_code;
	private String oil_name;
	private String oil_parent_code; // 油企编码
	private String oil_parent_name; // 油企名称
	private String city;
	private String address;
	private String status;
	private String remark;
	private String payment_fee_rate;
	
	public String getPayment_fee_rate() {
		return payment_fee_rate;
	}

	public void setPayment_fee_rate(String payment_fee_rate) {
		this.payment_fee_rate = payment_fee_rate;
	}

	public String getOil_code() {
		return oil_code;
	}

	public void setOil_code(String oil_code) {
		this.oil_code = oil_code;
	}

	public String getOil_name() {
		return oil_name;
	}

	public void setOil_name(String oil_name) {
		this.oil_name = oil_name;
	}

	public String getOil_parent_code() {
		return oil_parent_code;
	}

	public void setOil_parent_code(String oil_parent_code) {
		this.oil_parent_code = oil_parent_code;
	}

	public String getOil_parent_name() {
		return oil_parent_name;
	}

	public void setOil_parent_name(String oil_parent_name) {
		this.oil_parent_name = oil_parent_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
