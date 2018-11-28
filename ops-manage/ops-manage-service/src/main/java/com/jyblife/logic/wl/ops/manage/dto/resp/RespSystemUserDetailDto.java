package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.jyblife.logic.wl.ops.manage.dto.SystemUserSaveDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RespSystemUserDetailDto implements Serializable {
    //用户ID 修改时必填
    private Integer userId;

    //用户名 必填
    private String userName;

    //企业微信Id
    private String identity;

    //姓名 必填
    private String name;

    //微信号
    private String weixin;

    //手机 必填
    private String phone;

    //Email
    private String email;

    //主角色 必填
    private String mainRoleId;

    private String mainRoleName;

    //状态 必填 //0未启用 1启用
    private String status;

    //所属角色
    private List<SystemUserSaveDto.SystemRoleInner> roleArray = new ArrayList<>();

    //权限变更 1/选中 0不选
    private String isRightRole;

    //说明
    private String remark;

    private String[] userRight = new String[]{};

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

    public String getMainRoleId() {
        return mainRoleId;
    }

    public void setMainRoleId(String mainRoleId) {
        this.mainRoleId = mainRoleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SystemUserSaveDto.SystemRoleInner> getRoleArray() {
        return roleArray;
    }

    public void setRoleArray(List<SystemUserSaveDto.SystemRoleInner> roleArray) {
        this.roleArray = roleArray;
    }

    public String getIsRightRole() {
        return isRightRole;
    }

    public void setIsRightRole(String isRightRole) {
        this.isRightRole = isRightRole;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getUserRight() {
        return userRight;
    }

    public void setUserRight(String[] userRight) {
        this.userRight = userRight;
    }

    public String getMainRoleName() {
        return mainRoleName;
    }

    public void setMainRoleName(String mainRoleName) {
        this.mainRoleName = mainRoleName;
    }
}
