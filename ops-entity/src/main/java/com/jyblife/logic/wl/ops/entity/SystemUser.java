package com.jyblife.logic.wl.ops.entity;

import java.io.Serializable;
import java.util.Date;

import com.jyblife.logic.wl.ops.common.annotation.Column;
import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

/**
 * 系统用户
 */
@Table("t_system_user")
public class SystemUser implements Serializable  {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Id
	private Integer userId;

	/**
	 *
	 */
	private Integer userCenterId;

	/**
	 *
	 */
	private String userName;

	/**
	 *
	 */
	private String password;

	/**
	 *
	 */
	private String roleIds;

	/**
	 *
	 */
	private String loginKey;

	/**
	 *
	 */
	private Integer loginCount;

	/**
	 *
	 */
	private Date loginTime;

	/**
	 *
	 */
	private String identity;

	/**
	 *
	 */
	private Integer mainRoleId;

	/**
	 *
	 */
	private String weixin;

	/**
	 *
	 */
	private String phone;

	/**
	 *
	 */
	private String email;

	/**
	 *
	 */
	private Integer status;

	/**
	 *
	 */
	private Byte isRightRole;

	/**
	 *
	 */
	private String corpIds;

	/**
	 *
	 */
	private String name;

	/**
	 *
	 */
	private String remark;

	/**
	 *
	 */
	private Integer createUserId;

	/**
	 *
	 */
	private Date createTime;

	/**
	 *
	 */
	private Integer updateUserId;

	/**
	 *
	 */
	private Date updateTime;

	/**
	 *
	 */
	private String rightCodes;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserCenterId() {
		return userCenterId;
	}

	public void setUserCenterId(Integer userCenterId) {
		this.userCenterId = userCenterId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Integer getMainRoleId() {
		return mainRoleId;
	}

	public void setMainRoleId(Integer mainRoleId) {
		this.mainRoleId = mainRoleId;
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

	public Byte getIsRightRole() {
		return isRightRole;
	}

	public void setIsRightRole(Byte isRightRole) {
		this.isRightRole = isRightRole;
	}

	public String getCorpIds() {
		return corpIds;
	}

	public void setCorpIds(String corpIds) {
		this.corpIds = corpIds;
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

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRightCodes() {
		return rightCodes;
	}

	public void setRightCodes(String rightCodes) {
		this.rightCodes = rightCodes;
	}
}
