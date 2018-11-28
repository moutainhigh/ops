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
 * 延时推送物流企业信息队列
 */
@Configuration
public class PushLogisticsCompanyQueue {

	@Autowired
	@Qualifier("logisticsCompanyExchange")
	private DirectExchange exchange;
	
	/**
	 * 延迟队列
	 * @return
	 */
	@Bean
	public Queue delayPushLogisticsCompanyQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", MqContant.LOGISTICS_COMPANY_EXCHANGE);//设置交换机路由
		arguments.put("x-dead-letter-routing-key", MqContant.LOGISTICS_COMPANY_DELAY_ROUTINGKEY);
		Queue queue = new Queue(MqContant.LOGISTICS_COMPANY_DELAY_QUEUE, true, false, false, arguments);
		return queue; 
	}

	@Bean
	public Binding delayPushLogisticsCompanyQueueBinding() {
		Binding bind = BindingBuilder.bind(delayPushLogisticsCompanyQueue()).to(exchange).with(MqContant.LOGISTICS_COMPANY_ROUTINGKEY);
		return bind;
	}
	
	/**
	 * 消息队列
	 * @return
	 */
	@Bean
	public Queue logisticsCompanyQueue() {
		Queue queue = new Queue(MqContant.LOGISTICS_COMPANY_QUEUE, true, false, false);
		return queue; 
	}
	
	@Bean
	public Binding logisticsCompanyQueueBinding() {
		return BindingBuilder.bind(logisticsCompanyQueue()).to(exchange).with(MqContant.LOGISTICS_COMPANY_DELAY_ROUTINGKEY);
	}
	
}
