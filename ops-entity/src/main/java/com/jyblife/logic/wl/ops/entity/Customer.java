package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_customer")
public class Customer {
    /**
     * 标识
     */
    @Id
    private Integer id;

    /**
     * 帐号
     */
    private String account;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 登录标识
     */
    private String token;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 
     */
    private Date statusTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private Integer createUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新用户
     */
    private Integer updateUserId;

    /**
     * 标识
     * @return id 标识
     */
    public Integer getId() {
        return id;
    }

    /**
     * 标识
     * @param id 标识
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 帐号
     * @return account 帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 帐号
     * @param account 帐号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 手机号
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 注册时间
     * @return register_time 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 注册时间
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 登录次数
     * @return login_count 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 登录次数
     * @param loginCount 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * 最后登录时间
     * @return login_time 最后登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 最后登录时间
     * @param loginTime 最后登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 登录标识
     * @return token 登录标识
     */
    public String getToken() {
        return token;
    }

    /**
     * 登录标识
     * @param token 登录标识
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return status_time 
     */
    public Date getStatusTime() {
        return statusTime;
    }

    /**
     * 
     * @param statusTime 
     */
    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建用户
     * @return create_user_id 创建用户
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建用户
     * @param createUserId 创建用户
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新用户
     * @return update_user_id 更新用户
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新用户
     * @param updateUserId 更新用户
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}