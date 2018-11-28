package com.jyblife.logic.wl.ops.message.dto;
import java.io.Serializable;

/**
 *  验证码
 */
public class MessageCodeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 验证码
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	


}