package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

public class ReqCustomerInfoDto {

	@NotNull(message = "客户id不能为空")
    private Integer customerId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
