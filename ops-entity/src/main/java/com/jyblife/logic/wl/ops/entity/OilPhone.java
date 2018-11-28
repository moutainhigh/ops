package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_oil_phone")
public class OilPhone {
    /**
     * 标识
     */
    @Id
    private Integer phoneId;

    /**
     * 油企
     */
    private Integer companyId;

    /**
     * 油站
     */
    private Integer stationId;

    /**
     * 用途
     */
    private Integer useType;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 状态时间
     */
    private Date statusTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户
     */
    private Integer updateUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建用户
     */
    private Integer createUserId;

    /**
     * 标识
     * @return phone_id 标识
     */
    public Integer getPhoneId() {
        return phoneId;
    }

    /**
     * 标识
     * @param phoneId 标识
     */
    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    /**
     * 油企
     * @return company_id 油企
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 油企
     * @param companyId 油企
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 油站
     * @return station_id 油站
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * 油站
     * @param stationId 油站
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * 用途
     * @return use_type 用途
     */
    public Integer getUseType() {
        return useType;
    }

    /**
     * 用途
     * @param useType 用途
     */
    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    /**
     * 电话号码
     * @return phone_number 电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 电话号码
     * @param phoneNumber 电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
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
     * 状态时间
     * @return status_time 状态时间
     */
    public Date getStatusTime() {
        return statusTime;
    }

    /**
     * 状态时间
     * @param statusTime 状态时间
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
}