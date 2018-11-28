package com.jyblife.logic.wl.ops.message.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.common.utils.EncryptUtil;
import com.jyblife.logic.wl.ops.common.utils.HttpUtils;
import com.jyblife.logic.wl.ops.message.constants.MessageConstants;
import com.jyblife.logic.wl.ops.message.constants.MessageTplEnum;
import com.jyblife.logic.wl.ops.message.dto.MessageSendDto;

public class MessageUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);

	/**
	 * 发送功能短信
	 */
	public static void sendFunctionMessage(String phone, MessageTplEnum tplEnum, JSONObject jsonObj) {

		// 签名串 
		String signStr = null;

		if (null != jsonObj) {
			// 参数数字签名
			signStr = String.format(MessageConstants.FUNCTION_PARAM_SIGN_STR, MessageConstants.SMS_MER_NO, phone, tplEnum.getTplId(), jsonObj, MessageConstants.SMS_MER_KEY);
		} else {
			signStr = String.format(MessageConstants.FUNCTION_SIGN_STR, MessageConstants.SMS_MER_NO, phone, tplEnum.getTplId(), MessageConstants.SMS_MER_KEY);
		}

		// 签名MD5 32位加密
		String sign = EncryptUtil.encryptTo32Md5(signStr);

		MessageSendDto sendMessageDto = new MessageSendDto();
		sendMessageDto.setMer_no(MessageConstants.SMS_MER_NO);
		sendMessageDto.setPhone(phone);
		sendMessageDto.setTpl_id(tplEnum.getTplId());
		sendMessageDto.setTpl_params(jsonObj);
		//加密转大写
		sendMessageDto.setSign(sign.toUpperCase());
		sendMessageDto.setIs_java("1");

		String sendJsonStr = JSONObject.toJSONString(sendMessageDto);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("send function message json:{}", sendJsonStr);
		}

		// 发送短信
		LOGGER.info("发送短信参数: {}", sendJsonStr);
		HttpUtils.postJson(MessageConstants.SMS_URL, sendJsonStr);
	}

}
