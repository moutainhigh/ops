package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

public class ReqCheckCustomerDto {
	
	@NotNull(message = "客户customerId不能为空")
    Integer customerId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
