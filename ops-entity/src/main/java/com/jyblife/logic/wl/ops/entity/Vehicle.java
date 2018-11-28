package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;
@Table("t_vehicle")
public class Vehicle {
    /**
     * 标识
     */
    @Id
    private Integer vehicleId;

    /**
     * 企业id
     */
    private Integer logisticsId;

    /**
     * 车牌号
     */
    private String number;

    /**
     * 车型
     */
    private String model;

    /**
     * 添加人
     */
    private String optor;

    /**
     * 邮箱容量
     */
    private BigDecimal capacity;

    /**
     * 行驶证起效日
     */
    private Date startDate;

    /**
     * 行驶证的截止日
     */
    private Date endDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否启用: 0禁用，1启用
     */
    private Integer state;

    /**
     * 
     */
    private Date statusTime;

    /**
     * 生效时间
     */
    private Date effectTime;

    /**
     * 创建用户
     */
    private Integer createUserId;

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
     * 标识
     * @return vehicle_id 标识
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * 标识
     * @param vehicleId 标识
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 企业id
     * @return logistics_id 企业id
     */
    public Integer getLogisticsId() {
        return logisticsId;
    }

    /**
     * 企业id
     * @param logisticsId 企业id
     */
    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    /**
     * 车牌号
     * @return number 车牌号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 车牌号
     * @param number 车牌号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 车型
     * @return model 车型
     */
    public String getModel() {
        return model;
    }

    /**
     * 车型
     * @param model 车型
     */
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**
     * 添加人
     * @return optor 添加人
     */
    public String getOptor() {
        return optor;
    }

    /**
     * 添加人
     * @param optor 添加人
     */
    public void setOptor(String optor) {
        this.optor = optor == null ? null : optor.trim();
    }

    /**
     * 邮箱容量
     * @return capacity 邮箱容量
     */
    public BigDecimal getCapacity() {
        return capacity;
    }

    /**
     * 邮箱容量
     * @param capacity 邮箱容量
     */
    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    /**
     * 行驶证起效日
     * @return start_date 行驶证起效日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 行驶证起效日
     * @param startDate 行驶证起效日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 行驶证的截止日
     * @return end_date 行驶证的截止日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 行驶证的截止日
     * @param endDate 行驶证的截止日
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * 是否启用: 0禁用，1启用
     * @return state 是否启用: 0禁用，1启用
     */
    public Integer getState() {
        return state;
    }

    /**
     * 是否启用: 0禁用，1启用
     * @param state 是否启用: 0禁用，1启用
     */
    public void setState(Integer state) {
        this.state = state;
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