package com.jyblife.logic.wl.ops.external.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class ReqCodeLoginDto {

	@NotBlank(message = "手机号phone不能为空")
    private String phone;
	@NotBlank(message = "验证码code不能为空")
    private String code;
    private String openId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
