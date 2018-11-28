package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class LogisticsCompanyAddDto {

	@NotBlank(message = "银管家物流企业标识不能为空")
	private String identity;
	@NotBlank(message = "企业名称不能为空")
	private String name;
	@Max(value = 1000)
	@NotNull(message = "银管家状态不能为空")
	private Integer status;
	@NotBlank(message = "授信额度不能为空")
	private String creditQuota;
	@NotBlank(message = "额度有效开始日期不能为空")
	private String startDate;
	@NotBlank(message = "额度有效截止日期不能为空")
	private String endDate;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreditQuota() {
		return creditQuota;
	}

	public void setCreditQuota(String creditQuota) {
		this.creditQuota = creditQuota;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
