package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqTradePasswordDto {
	
	@NotNull(message = "客户id不能为空")
    private Integer customerId;
	@NotBlank(message = "交易密码不能为空")
    private String password;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
