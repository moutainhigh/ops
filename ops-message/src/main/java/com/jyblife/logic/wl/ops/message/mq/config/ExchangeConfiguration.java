package com.jyblife.logic.wl.ops.message.mq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jyblife.logic.wl.ops.message.mq.contants.MqContant;


/**
 * 统一交换机配置
 */
@Configuration
public class ExchangeConfiguration {

	/**
	 * 油站
	 */
	@Bean("oilStationExchange")
	public DirectExchange oilStationExchange() {
		DirectExchange psd = new DirectExchange(MqContant.OIL_STATION_EXCHANGE, true, false);
		return psd;
	}
	
	/**
	 * 物流企业
	 */
	@Bean("logisticsCompanyExchange")
	public DirectExchange logisticsCompanyExchange() {
		DirectExchange psd = new DirectExchange(MqContant.LOGISTICS_COMPANY_EXCHANGE, true, false);
		return psd;
	}
	
	/**
	 * 订单信息
	 */
	@Bean("orderExchange")
	public DirectExchange orderExchange() {
		DirectExchange psd = new DirectExchange(MqContant.ORDER_EXCHANGE, true, false);
		return psd;
	}
	
	/*
	@Bean("paygatewayTransactionExchange")
	public TopicExchange paygatewayTransactionExchange() {
		TopicExchange ptd = new TopicExchange(ExchangeEnum.TRANSACTION.getCode(), true, false);
		return ptd;
	}*/
	
}
