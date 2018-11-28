package com.jyblife.logic.wl.ops.message.mq.producer;

import com.jyblife.logic.wl.ops.message.mq.entity.MqMessage;

public interface MqProducer {
	
	/**
	 * 发送即时消息
	 * @param mqMessage
	 */
	public void sendMessage(MqMessage mqMessage);
	
	/**
	 * 发送延时消息
	 * @param mqMessage
	 */
	public void sendDelayMessage(MqMessage mqMessage);

}
