package com.jyblife.logic.wl.ops.message.dto;

/**
 * 订单短信
 */
public class OrderSmsDto {

	private String vehicle_number; // 车牌号
	private String trans_time; // 订单时间
	private String quantity; // 加油升数
	private String order_code; // 订单编号

	public String getVehicle_number() {
		return vehicle_number;
	}

	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number;
	}

	public String getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

}
