package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqUpdateLogiCompanyStatusDto {

	@NotBlank(message = "银管家物流企业标识不能为空")
	private String identity;
	
	@Min(value = 0, message = "请填写正确的企业状态")
	@NotNull(message = "银管家状态不能为空")
	private Integer status;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
