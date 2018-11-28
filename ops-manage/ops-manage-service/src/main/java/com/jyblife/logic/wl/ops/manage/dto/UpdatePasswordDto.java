package com.jyblife.logic.wl.ops.manage.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UpdatePasswordDto implements Serializable {

	@NotBlank(message = "原始密码不能为空")
	private String password;
	@NotBlank(message = "新密码不能为空")
	private String newPassword; // 新密码
	@NotBlank(message = "确认密码不能为空")
	private String confirmPassword; // 确认密码

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
