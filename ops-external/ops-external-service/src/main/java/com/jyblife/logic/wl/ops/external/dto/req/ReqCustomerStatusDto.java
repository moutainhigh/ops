package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

public class ReqCustomerStatusDto {

	@NotNull(message = "客户id不能为空")
    private Integer customerId;
	@NotNull(message = "客户状态status不能为空")
    private Integer status;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
