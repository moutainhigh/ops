package com.jyblife.logic.wl.ops.message.mq.consumer;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.common.utils.HttpUtils;
import com.jyblife.logic.wl.ops.message.mq.contants.MqContant;
import com.jyblife.logic.wl.ops.message.mq.entity.MqMessage;
import com.jyblife.logic.wl.ops.message.mq.producer.MqProducer;
import com.jyblife.logic.wl.ops.message.mq.utils.MqUtils;

/**
 * 推送订单消息监听
 */
@Component
@RabbitListener(queues = MqContant.ORDER_QUEUE)
public class PushOrderQueueConsumer {

	public static Logger logger = LoggerFactory.getLogger(PushOrderQueueConsumer.class);
	
	@Value("${push.oil.order.api}")
	private String api;
	
	@Autowired
	private MqProducer mqProducer;
	
	@SuppressWarnings("rawtypes")
	@RabbitHandler
    public void onMessage(Object msg) {
		String mqContent = MqUtils.getMqContent(msg);
		logger.info("推送订单消息监听mq监听参数：" + mqContent);
		
		MqMessage message = JSON.parseObject(mqContent, MqMessage.class);
		String resp = null;
		try {
			Map m = JSON.parseObject(message.getContent(), Map.class);
			logger.info("推送订单数据请求api: " + api + " data: " + message.getContent());
			resp = HttpUtils.post(api, m, "UTF-8");
			logger.info("推送订单数据请求响应: " + resp);
		} catch (Exception e) {
			logger.error("推送订单数据请求异常: " + e.getMessage());
		} finally {
			JSONObject json = null;
			if(StringUtils.isNotBlank(resp)) {
				json = JSON.parseObject(resp);
			}
			
			if(json == null || !"0".equals(json.getString("code"))) {
				message.setRetry(message.getRetry() + 1);
				if(message.getRetry() > 5) {
					message.setRetryTime(MqContant.ORDER_RETRYTIME);
				}
				mqProducer.sendDelayMessage(message);
			}
		}
		
    }
	
}
