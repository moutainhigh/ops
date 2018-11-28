package com.jyblife.logic.wl.ops.external.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class ReqCustomerIdDto {
	
	@NotBlank(message = "微信openId不能为空")
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
