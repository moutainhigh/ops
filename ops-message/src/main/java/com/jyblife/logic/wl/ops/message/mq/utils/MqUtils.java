package com.jyblife.logic.wl.ops.message.mq.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

public class MqUtils {
	
	public static Logger logger = LoggerFactory.getLogger(MqUtils.class);

	/**
	 * 获取MQ消息实体内容
	 * @param message
	 * @return
	 */
	public static String getMqContent(Object message) {
		String content = null;
		try {
			if (message instanceof Message) {
				Message m = (Message) message;
				content = new String(m.getBody());
			} else if (message instanceof byte[]) {
				content = new String((byte[]) message);
			} else if (message instanceof String) {
				content = (String) message;
			} else {
				content = String.valueOf(message);
			}
		} catch (Exception e) {
			logger.error("获取mq消息内容失败：message="+message, e);
		}
		
		return content;
	}

}
