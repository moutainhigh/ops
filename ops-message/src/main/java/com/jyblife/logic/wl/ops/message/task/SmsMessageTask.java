package com.jyblife.logic.wl.ops.message.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.message.constants.MessageTplEnum;
import com.jyblife.logic.wl.ops.message.utils.MessageUtils;

/**
 * 短信消息发送任务
 */
public class SmsMessageTask implements Runnable {
	
	public static Logger logger = LoggerFactory.getLogger(SmsMessageTask.class);

	private String phone; //接收短信的号码
	private JSONObject jsonObj; //短信模板参数内容
	private MessageTplEnum tpl; //短信模板
	
	public SmsMessageTask(String phone, JSONObject jsonObj, MessageTplEnum tpl) {
		super();
		this.phone = phone;
		this.jsonObj = jsonObj;
		this.tpl = tpl;
	}

	@Override
	public void run() {
		try {
			logger.info("短信消息发送任务参数: phone=" + phone + " tpl=" + tpl.getTplId() + " params=" + jsonObj);
			MessageUtils.sendFunctionMessage(phone, tpl, jsonObj);
		} catch (Exception e) {
			logger.error("短信任务消息发送失败: " + e.getMessage());
		}
	}

}
