package com.jyblife.logic.wl.ops.message.mq.producer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jyblife.logic.wl.ops.message.mq.entity.MqMessage;
import com.jyblife.logic.wl.ops.message.mq.producer.MqProducer;

@Service
public class MqProducerImpl implements MqProducer {
	public static Logger logger = LoggerFactory.getLogger(MqProducerImpl.class);
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void sendMessage(MqMessage mqMessage) {
		String message = JSON.toJSONString(mqMessage);
		logger.info("sendMessage mq message={}",message);
		rabbitTemplate.convertAndSend(mqMessage.getExchange(), mqMessage.getRoutingKey(), message);
	}

	@Override
	public void sendDelayMessage(MqMessage mqMessage) {
		Long times = mqMessage.getRetryTime();
		if (times == null || times <= 0) {// 直接发送，无需进入死信延时队列
			this.sendMessage(mqMessage);
		} else {
			MessagePostProcessor processor = new MessagePostProcessor() {
				@Override
				public Message postProcessMessage(Message message) throws AmqpException {
					message.getMessageProperties().setExpiration(times + "");
					return message;
				}
			};
			String message = JSON.toJSONString(mqMessage);
			logger.info("sendDelayMessage mq message={}",message);
			rabbitTemplate.convertAndSend(mqMessage.getExchange(), mqMessage.getRoutingKey(), message, processor);
		}
	}

}
