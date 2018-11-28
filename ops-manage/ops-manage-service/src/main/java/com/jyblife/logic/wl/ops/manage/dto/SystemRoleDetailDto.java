package com.jyblife.logic.wl.ops.manage.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.jyblife.logic.wl.ops.entity.SystemUser;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangcey
 * @date 2018/9/19
 **/
public class SystemRoleDetailDto implements Serializable {
    private String roleId;
    private String name;
    private String rightCodes;
    private String orderIndex;
    private String type;
    private String status;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private SystemUser updateUser;
    private SystemUser createUser;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRightCodes() {
        return rightCodes;
    }

    public void setRightCodes(String rightCodes) {
        this.rightCodes = rightCodes;
    }

    public String getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public SystemUser getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(SystemUser updateUser) {
        this.updateUser = updateUser;
    }

    public SystemUser getCreateUser() {
        return createUser;
    }

    public void setCreateUser(SystemUser createUser) {
        this.createUser = createUser;
    }

    private class SystemUser {
        private String id;
        private String name;

        SystemUser(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public void newUpdateUser(String id, String name) {
        this.updateUser = new SystemRoleDetailDto.SystemUser(id, name);

    }

    public void newCreateUser(String id, String name) {
        this.createUser = new SystemRoleDetailDto.SystemUser(id, name);

    }
}
