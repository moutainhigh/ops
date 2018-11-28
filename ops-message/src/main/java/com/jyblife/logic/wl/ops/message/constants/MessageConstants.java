package com.jyblife.logic.wl.ops.message.constants;

/**
 * 短信工具类常量
 */
public interface MessageConstants {

	/**
	 * 短信平台api地址
	 */
	public static final String SMS_URL = "http://101.132.141.119:8081/send/sms";
	
	/**
	 * 4位随机数常量
	 */
	public static final String FOUR_RANDOM_NUM = "0123456789";
	
	/** 
	 * 功能短信数字签名串 (有参数)
	 */
	public static final String FUNCTION_PARAM_SIGN_STR ="mer_no=%s&phone=%s&tpl_id=%s&tpl_params=%s&mer_key=%s";
	
	/**
	 * 功能短信数字签名串 (无参数)
	 */
	public static final String FUNCTION_SIGN_STR ="mer_no=%s&phone=%s&tpl_id=%s&mer_key=%s";
	
	/**
	 * 营销短信数字签名串
	 */
	public static final String MARKETING_SIGN_STR ="mer_no=%s&tpl_id=%s&data=%s&mer_key=%s";

	/**
	 * 短信平台用户id
	 */
	public static final String SMS_MER_NO = "10007";
	
	/**
	 * 短信平台密钥
	 */
	public static final String SMS_MER_KEY = "3022CE05486B2484";
	
	


}