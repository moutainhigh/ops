package com.jyblife.logic.wl.ops.manage.dto;

import com.jyblife.logic.wl.ops.entity.SystemRole;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author yangcey
 */
public class SystemUserSaveDto implements Serializable {

    //用户ID 修改时必填
    private Integer userId;

    //用户名 必填
    @NotBlank(message = "用户名不能为空")
    private String userName;

    //企业微信Id
    private String identity;

    //密码 必填
    //@NotBlank(message = "密码不能为空")
    private String password;

    //姓名 必填
    @NotBlank(message = "姓名不能为空")
    private String name;

    //微信号
    private String weixin;

    //手机 必填
    @NotBlank(message = "手机号不能为空")
    private String phone;

    //Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    //主角色 必填
    @NotNull(message = "主角色不能为空")
    private Integer mainRoleId;

    //状态 必填 //0未启用 1启用
    @NotNull(message = "启用状态不能为空")
    private Integer status;

    //所属角色
    private List<SystemRoleInner> roleArray;

    //权限变更 1/选中 0不选
    private Byte isRightRole;

    //说明
    private String remark;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getMainRoleId() {
        return mainRoleId;
    }

    public void setMainRoleId(Integer mainRoleId) {
        this.mainRoleId = mainRoleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SystemRoleInner> getRoleArray() {
        return roleArray;
    }

    public void setRoleArray(List<SystemRoleInner> roleArray) {
        this.roleArray = roleArray;
    }

    public Byte getIsRightRole() {
        return isRightRole;
    }

    public void setIsRightRole(Byte isRightRole) {
        this.isRightRole = isRightRole;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public static class SystemRoleInner {
        private String id;
        private String name;

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
}
