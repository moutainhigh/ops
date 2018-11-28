package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

/**
 * @author yangcey
 * @date 2018/11/13
 **/
public class ReqCustomerLocSwitchDto {

    @NotNull(message = "客户id不能为空")
    private Integer customerId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
