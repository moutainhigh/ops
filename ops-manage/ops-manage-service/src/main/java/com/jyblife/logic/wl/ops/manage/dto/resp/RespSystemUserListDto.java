package com.jyblife.logic.wl.ops.manage.dto.resp;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class RespSystemUserListDto extends RespPager<RespSystemUserListDto.UserItem> {

	public static class UserItem {

		private String userId;
		private String userName;
		private String password;
		private String roleIds;
		private String rightCodes;
		private String loginKey;
		private String loginCount;
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		private Date loginTime;
		private String identity;
		private String name;
		private String mainRoleId;
		private String weixin;
		private String phone;
		private String email;
		private String status;
		private String isRightRole;
		private String corpIds;
		private String remark;
		private String createUserId;
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		private Date createTime;
		private String updateUserId;
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		private Date updateTime;
		private String roleName;
		private boolean isCanDel;
		private boolean isCanAuth;
		private boolean isCanEdit;
		private boolean isCanView;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
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

		public String getRightCodes() {
			return rightCodes;
		}

		public void setRightCodes(String rightCodes) {
			this.rightCodes = rightCodes;
		}

		public String getLoginKey() {
			return loginKey;
		}

		public void setLoginKey(String loginKey) {
			this.loginKey = loginKey;
		}

		public String getLoginCount() {
			return loginCount;
		}

		public void setLoginCount(String loginCount) {
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMainRoleId() {
			return mainRoleId;
		}

		public void setMainRoleId(String mainRoleId) {
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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getIsRightRole() {
			return isRightRole;
		}

		public void setIsRightRole(String isRightRole) {
			this.isRightRole = isRightRole;
		}

		public String getCorpIds() {
			return corpIds;
		}

		public void setCorpIds(String corpIds) {
			this.corpIds = corpIds;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getCreateUserId() {
			return createUserId;
		}

		public void setCreateUserId(String createUserId) {
			this.createUserId = createUserId;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getUpdateUserId() {
			return updateUserId;
		}

		public void setUpdateUserId(String updateUserId) {
			this.updateUserId = updateUserId;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public boolean getIsCanDel() {
			return isCanDel;
		}

		public void setCanDel(boolean isCanDel) {
			this.isCanDel = isCanDel;
		}

		public boolean getIsCanAuth() {
			return isCanAuth;
		}

		public void setCanAuth(boolean isCanAuth) {
			this.isCanAuth = isCanAuth;
		}

		public boolean getIsCanEdit() {
			return isCanEdit;
		}

		public void setCanEdit(boolean isCanEdit) {
			this.isCanEdit = isCanEdit;
		}

		public boolean getIsCanView() {
			return isCanView;
		}

		public void setCanView(boolean isCanView) {
			this.isCanView = isCanView;
		}

	}

}
