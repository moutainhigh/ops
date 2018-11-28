package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_logistics_quota_log")
public class LogisticsQuotaLog {
    /**
     * id
     */
    @Id
    private Long logId;

    /**
     * 物流企业id
     */
    private Integer logisticsId;

    /**
     * 变更原因 10：订单支付 20：物流还款
     */
    private Integer category;

    /**
     * 变更类型 1：增加 -1：减少
     */
    private Integer method;

    /**
     * 关联id
     */
    private Long relationId;

    /**
     * 变更额度
     */
    private Integer quota;

    /**
     * 当前总额度
     */
    private Integer quotaTotal;

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
     * @return log_id id
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * id
     * @param logId id
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 物流企业id
     * @return logistics_id 物流企业id
     */
    public Integer getLogisticsId() {
        return logisticsId;
    }

    /**
     * 物流企业id
     * @param logisticsId 物流企业id
     */
    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    /**
     * 变更原因 10：订单支付 20：物流还款
     * @return category 变更原因 10：订单支付 20：物流还款
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 变更原因 10：订单支付 20：物流还款
     * @param category 变更原因 10：订单支付 20：物流还款
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 变更类型 1：增加 -1：减少
     * @return method 变更类型 1：增加 -1：减少
     */
    public Integer getMethod() {
        return method;
    }

    /**
     * 变更类型 1：增加 -1：减少
     * @param method 变更类型 1：增加 -1：减少
     */
    public void setMethod(Integer method) {
        this.method = method;
    }

    /**
     * 关联id
     * @return relation_id 关联id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 关联id
     * @param relationId 关联id
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 变更额度
     * @return quota 变更额度
     */
    public Integer getQuota() {
        return quota;
    }

    /**
     * 变更额度
     * @param quota 变更额度
     */
    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    /**
     * 当前总额度
     * @return quota_total 当前总额度
     */
    public Integer getQuotaTotal() {
        return quotaTotal;
    }

    /**
     * 当前总额度
     * @param quotaTotal 当前总额度
     */
    public void setQuotaTotal(Integer quotaTotal) {
        this.quotaTotal = quotaTotal;
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