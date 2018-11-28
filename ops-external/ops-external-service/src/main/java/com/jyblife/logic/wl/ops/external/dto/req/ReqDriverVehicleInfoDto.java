package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReqDriverVehicleInfoDto {

	@Min(value = 0, message = "请填写正确的客户id")
	@NotNull(message = "客户id不能为空")
	public Integer customerId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}
