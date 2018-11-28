package com.jyblife.logic.wl.ops.message.mq.queues;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jyblife.logic.wl.ops.message.mq.contants.MqContant;


/**
 * 延时推送订单信息队列
 */
@Configuration
public class PushOrderQueue {

	@Autowired
	@Qualifier("orderExchange")
	private DirectExchange exchange;
	
	/**
	 * 延迟队列
	 * @return
	 */
	@Bean
	public Queue delayPushOrderQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", MqContant.ORDER_EXCHANGE);//设置交换机路由
		arguments.put("x-dead-letter-routing-key", MqContant.ORDER_DELAY_ROUTINGKEY);
		Queue queue = new Queue(MqContant.ORDER_DELAY_QUEUE, true, false, false, arguments);
		return queue; 
	}

	@Bean
	public Binding delayPushOrderQueueBinding() {
		Binding bind = BindingBuilder.bind(delayPushOrderQueue()).to(exchange).with(MqContant.ORDER_ROUTINGKEY);
		return bind;
	}
	
	/**
	 * 消息队列
	 * @return
	 */
	@Bean
	public Queue orderQueue() {
		Queue queue = new Queue(MqContant.ORDER_QUEUE, true, false, false);
		return queue; 
	}
	
	@Bean
	public Binding orderQueueBinding() {
		return BindingBuilder.bind(orderQueue()).to(exchange).with(MqContant.ORDER_DELAY_ROUTINGKEY);
	}
	
}
