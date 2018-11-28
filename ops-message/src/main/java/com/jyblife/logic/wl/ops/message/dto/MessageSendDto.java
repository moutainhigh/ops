package com.jyblife.logic.wl.ops.message.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 手机验证码DTO
 */
public class MessageSendDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * SMS平台用户ID
	 */
	private String mer_no;

	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * SMS平台消息模板ID
	 */
	private String tpl_id;
	
	/**
	 * SMS平台功能消息模板参数
	 */
	private JSONObject tpl_params;
	
	/**
	 * SMS平台数字签名
	 */
	private String sign;
	
	/**
	 * SMS平台是否java中文特殊处理
	 */
	private String is_java;
	
	public String getMer_no() {
		return mer_no;
	}

	public void setMer_no(String mer_no) {
		this.mer_no = mer_no;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTpl_id() {
		return tpl_id;
	}

	public void setTpl_id(String tpl_id) {
		this.tpl_id = tpl_id;
	}
	
	
	public JSONObject getTpl_params() {
		return tpl_params;
	}

	public void setTpl_params(JSONObject tpl_params) {
		this.tpl_params = tpl_params;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getIs_java() {
		return is_java;
	}

	public void setIs_java(String is_java) {
		this.is_java = is_java;
	}
	

}