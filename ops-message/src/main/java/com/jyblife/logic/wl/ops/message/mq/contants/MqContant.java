package com.jyblife.logic.wl.ops.message.mq.contants;

public class MqContant {

	/**
	 * 油站消息配置
	 */
	public static final String OIL_STATION_EXCHANGE = "oil_station.direct"; //油站交换机
	public static final String OIL_STATION_QUEUE = "oil_station_queue"; //队列
	public static final String OIL_STATION_DELAY_QUEUE = "oil_station_delay_queue"; //延时队列
	public static final String OIL_STATION_ROUTINGKEY = "oil_station_routingKey"; //路由建
	public static final String OIL_STATION_DELAY_ROUTINGKEY = "oil_station_delay_routingKey"; //延时路由建 
	public static final Long OIL_STATION_QUEUE_RETRYTIME = 1*60*1000l; //重试时间间隔(毫秒)
	
	
	/**
	 * 物流企业信息
	 */
	public static final String LOGISTICS_COMPANY_EXCHANGE = "logistics_company.direct"; //油站交换机
	public static final String LOGISTICS_COMPANY_QUEUE = "logistics_company_queue"; //队列
	public static final String LOGISTICS_COMPANY_DELAY_QUEUE = "logistics_company_delay_queue"; //延时队列
	public static final String LOGISTICS_COMPANY_ROUTINGKEY = "logistics_company_routingKey"; //路由建
	public static final String LOGISTICS_COMPANY_DELAY_ROUTINGKEY = "logistics_company_delay_routingKey"; //延时路由建 
	public static final Long LOGISTICS_COMPANY_RETRYTIME = 1*60*1000l; //重试时间间隔(毫秒)
	
	
	/**
	 * 下单信息
	 */
	public static final String ORDER_EXCHANGE = "retail_oil_order.direct"; //订单交换机
	public static final String ORDER_QUEUE = "retail_oil_order_queue"; //队列
	public static final String ORDER_DELAY_QUEUE = "retail_oil_order_delay_queue"; //延时队列
	public static final String ORDER_ROUTINGKEY = "retail_oil_order_routingKey"; //路由建
	public static final String ORDER_DELAY_ROUTINGKEY = "retail_oil_order_delay_routingKey"; //延时路由建 
	public static final Long ORDER_RETRYTIME = 1*60*1000l; //重试时间间隔(毫秒)
	
	
}
