package com.jyblife.logic.wl.ops.external.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class ReqPhoneCodeDto {
	
	@NotBlank(message = "手机号phone不能为空")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
