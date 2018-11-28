package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

public class ReqUserOrderListDto extends ReqPager {

	@NotNull(message = "用户id不能为空")
	private Integer customerId;
	private String status; //10已完成，-1交易失败，不传或其他默认查询全部
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
