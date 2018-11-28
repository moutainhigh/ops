package com.jyblife.logic.wl.ops.manage.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Author yangcey
 */
@SuppressWarnings("serial")
public class UserLoginDto implements Serializable {

	@NotBlank(message = "{user.login.username}")
	private String username;
	@NotBlank(message = "{user.login.password}")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
