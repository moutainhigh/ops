package com.jyblife.logic.wl.ops.manage.dto.resp;

import java.io.Serializable;

import com.alibaba.fastjson.JSONArray;

@SuppressWarnings("serial")
public class UserInfoDto implements Serializable {

	private Integer userId;
	private String userName;
	private Integer mainRoleId;
	private String mainRoleName;
	private String identity;
	private String weixin = "";
	private String phone;
	private String email;
	private Integer status;
	private String statusName = "";
	private Integer isRightRole;
	private String name;
	private String remark;
	private JSONArray roleArray;
	private JSONArray userRight;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMainRoleId() {
		return mainRoleId;
	}

	public void setMainRoleId(Integer mainRoleId) {
		this.mainRoleId = mainRoleId;
	}

	public String getMainRoleName() {
		return mainRoleName;
	}

	public void setMainRoleName(String mainRoleName) {
		this.mainRoleName = mainRoleName;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getIsRightRole() {
		return isRightRole;
	}

	public void setIsRightRole(Integer isRightRole) {
		this.isRightRole = isRightRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public JSONArray getRoleArray() {
		return roleArray;
	}

	public void setRoleArray(JSONArray roleArray) {
		this.roleArray = roleArray;
	}

	public JSONArray getUserRight() {
		return userRight;
	}

	public void setUserRight(JSONArray userRight) {
		this.userRight = userRight;
	}

}
