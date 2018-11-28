package com.jyblife.logic.wl.ops.message.test;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.message.constants.MessageConstants;
import com.jyblife.logic.wl.ops.message.constants.MessageTplEnum;
import com.jyblife.logic.wl.ops.message.dto.MessageCodeDto;
import com.jyblife.logic.wl.ops.message.dto.OilPriceSmsDto;
import com.jyblife.logic.wl.ops.message.utils.MessageUtils;

public class MessageTest {

	public static void main(String[] args) {
		
		// 验证码
		//String messageCode = RandomStringUtils.random(6, MessageConstants.FOUR_RANDOM_NUM);
		
		OilPriceSmsDto sms = new OilPriceSmsDto();
		sms.setPrice_code("123456");
		sms.setAgreed_price("12.22");
		sms.setGoods_name("0#柴油");
		
		// 组装json参数
		//MessageCodeDto messageCodeDto = new MessageCodeDto();
		//messageCodeDto.setCode(messageCode);
		//JSONObject jsonObj = (JSONObject) JSONObject.toJSON(messageCodeDto);
		// 调用短信发送工具类
		MessageUtils.sendFunctionMessage("123456", MessageTplEnum.OIL_PRICE_EXECUTED_IMMEDIATELY_TEMPATE, (JSONObject)JSON.toJSON(sms));
	}

}
