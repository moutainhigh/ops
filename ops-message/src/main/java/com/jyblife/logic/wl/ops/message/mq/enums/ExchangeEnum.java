package com.jyblife.logic.wl.ops.message.mq.enums;

public enum ExchangeEnum {

	;
	
	private String code;

    private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	ExchangeEnum(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}
	
}
