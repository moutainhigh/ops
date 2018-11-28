package com.jyblife.logic.wl.ops.manage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties", encoding = "utf-8")
public class UserCenterConfig {

	@Value("${userCenter.api}")
	private String api;
	@Value("${userCenter.syn.cmd}")
	private String synCmd;
	@Value("${userCenter.login.cmd}")
	private String loginCmd;
	@Value("${userCenter.client.id}")
	private String clientId;
	@Value("${userCenter.client.secret}")
	private String clientSecret;

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getSynCmd() {
		return synCmd;
	}

	public void setSynCmd(String synCmd) {
		this.synCmd = synCmd;
	}

	public String getLoginCmd() {
		return loginCmd;
	}

	public void setLoginCmd(String loginCmd) {
		this.loginCmd = loginCmd;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

}
