package com.jyblife.logic.wl.ops.message.dto;

/**
 * 油价短信
 */
public class OilPriceSmsDto {

	private String price_code; // 价格编码
	private String goods_name; // 油品名称
	private String agreed_price; // 协议价

	public String getPrice_code() {
		return price_code;
	}

	public void setPrice_code(String price_code) {
		this.price_code = price_code;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getAgreed_price() {
		return agreed_price;
	}

	public void setAgreed_price(String agreed_price) {
		this.agreed_price = agreed_price;
	}

}
