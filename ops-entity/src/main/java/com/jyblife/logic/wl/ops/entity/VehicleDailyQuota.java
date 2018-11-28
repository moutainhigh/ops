package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;
@Table("t_vehicle_daily_quota")
public class VehicleDailyQuota {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 车辆id
     */
    private Integer vehicleId;

    /**
     * 日期
     */
    private Date currentDate;

    /**
     * 冻结额度
     */
    private BigDecimal frozenQuota;

    /**
     * 已使用额度
     */
    private BigDecimal usedQuota;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 状态时间
     */
    private Date statusTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建用户
     */
    private Integer createUserId;

    /**
     * 生效时间
     */
    private Date effectTime;

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
     * id
     * @return id id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 车辆id
     * @return vehicle_id 车辆id
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * 车辆id
     * @param vehicleId 车辆id
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 日期
     * @return current_date 日期
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * 日期
     * @param currentDate 日期
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * 冻结额度
     * @return frozen_quota 冻结额度
     */
    public BigDecimal getFrozenQuota() {
        return frozenQuota;
    }

    /**
     * 冻结额度
     * @param frozenQuota 冻结额度
     */
    public void setFrozenQuota(BigDecimal frozenQuota) {
        this.frozenQuota = frozenQuota;
    }

    /**
     * 已使用额度
     * @return used_quota 已使用额度
     */
    public BigDecimal getUsedQuota() {
        return usedQuota;
    }

    /**
     * 已使用额度
     * @param usedQuota 已使用额度
     */
    public void setUsedQuota(BigDecimal usedQuota) {
        this.usedQuota = usedQuota;
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
     * 生效时间
     * @return effect_time 生效时间
     */
    public Date getEffectTime() {
        return effectTime;
    }

    /**
     * 生效时间
     * @param effectTime 生效时间
     */
    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
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
}