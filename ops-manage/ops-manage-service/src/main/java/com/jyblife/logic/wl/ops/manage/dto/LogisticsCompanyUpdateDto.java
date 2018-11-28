package com.jyblife.logic.wl.ops.manage.dto;

import javax.validation.constraints.NotNull;


public class LogisticsCompanyUpdateDto {
	
	@NotNull(message="物流企业不能为空")
	private Integer logisticsId;
	@NotNull(message="企业名称不能为空")
	private String name;
	
	
	public Integer getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
