package com.jyblife.logic.wl.ops.message.mq.entity;

/**
 * 消息队列实体类
 */
public class MqMessage {
	
	private String exchange; // 交换机
	private String routingKey; // 路由建
	private String content; // 消息内容
	private Long retry = 0l; // 当前重试次数
	private Long maxRetry = -99l; // 最多重试次数 -99 表示无限重试
	private Long retryTime = 0l; // 重试时间间隔（毫秒）：0及不延迟
	
	public MqMessage() {
		super();
	}

	public MqMessage(String exchange, String routingKey, String content, Long retry, Long maxRetry, Long retryTime) {
		super();
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.content = content;
		this.retry = retry;
		this.maxRetry = maxRetry;
		this.retryTime = retryTime;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getRetry() {
		return retry;
	}

	public void setRetry(Long retry) {
		this.retry = retry;
	}

	public Long getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(Long maxRetry) {
		this.maxRetry = maxRetry;
	}

	public Long getRetryTime() {
		return retryTime;
	}

	public void setRetryTime(Long retryTime) {
		this.retryTime = retryTime;
	}
	
}
