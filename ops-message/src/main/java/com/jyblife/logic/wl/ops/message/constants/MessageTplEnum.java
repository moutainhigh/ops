package com.jyblife.logic.wl.ops.message.constants;

/**
 * 模板类型
 */
public enum MessageTplEnum {

	/**
	 * 短信验证码模板
	 */
	VALIEDATE_MESSAGE_TEMPLATE("101"),
	
	/**
	 * 订单短信模板
	 */
	ORDER_MESSAGE_TEMPLATE("103"),
	
	/**
	 * 价格审批通过通知模板
	 */
	OIL_PRICE_APPROVE_TEMPATE("107"),
	
	/**
	 * 价格立即执行通知模板
	 */
	OIL_PRICE_EXECUTED_IMMEDIATELY_TEMPATE("108"),
	
	;
	
	/**
	 * 放款状态值
	 */
	private String tplId;
	
	MessageTplEnum(String tplId) {
		this.tplId = tplId;
	}

	public String getTplId() {
		return tplId;
	}

	public void setTplId(String tplId) {
		this.tplId = tplId;
	}

	
	


}
