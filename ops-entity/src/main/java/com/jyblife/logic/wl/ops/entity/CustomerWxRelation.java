package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_customer_wx_relation")
public class CustomerWxRelation {
    /**
     * 标识
     */
    @Id
    private Integer id;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 小程序或企业号标识
     */
    private String wxIdentity;

    /**
     * 微信标识
     */
    private String openId;

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
     * 用户id
     * @return customer_id 用户id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 用户id
     * @param customerId 用户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 小程序或企业号标识
     * @return wx_identity 小程序或企业号标识
     */
    public String getWxIdentity() {
        return wxIdentity;
    }

    /**
     * 小程序或企业号标识
     * @param wxIdentity 小程序或企业号标识
     */
    public void setWxIdentity(String wxIdentity) {
        this.wxIdentity = wxIdentity == null ? null : wxIdentity.trim();
    }

    /**
     * 微信标识
     * @return open_id 微信标识
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 微信标识
     * @param openId 微信标识
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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