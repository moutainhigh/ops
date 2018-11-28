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
 * 延时推送油站信息队列
 */
@Configuration
public class PushOilStationQueue {

	@Autowired
	@Qualifier("oilStationExchange")
	private DirectExchange exchange;
	
	/**
	 * 延迟队列
	 * @return
	 */
	@Bean
	public Queue delayPushOilStationQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", MqContant.OIL_STATION_EXCHANGE);//设置交换机路由
		arguments.put("x-dead-letter-routing-key", MqContant.OIL_STATION_DELAY_ROUTINGKEY);
		Queue queue = new Queue(MqContant.OIL_STATION_DELAY_QUEUE, true, false, false, arguments);
		return queue; 
	}

	@Bean
	public Binding delayPushOilStationQueueBinding() {
		Binding bind = BindingBuilder.bind(delayPushOilStationQueue()).to(exchange).with(MqContant.OIL_STATION_ROUTINGKEY);
		return bind;
	}
	
	/**
	 * 消息队列
	 * @return
	 */
	@Bean
	public Queue oilStationQueue() {
		Queue queue = new Queue(MqContant.OIL_STATION_QUEUE, true, false, false);
		return queue; 
	}
	
	@Bean
	public Binding oilStationQueueBinding() {
		return BindingBuilder.bind(oilStationQueue()).to(exchange).with(MqContant.OIL_STATION_DELAY_ROUTINGKEY);
	}
	
}
