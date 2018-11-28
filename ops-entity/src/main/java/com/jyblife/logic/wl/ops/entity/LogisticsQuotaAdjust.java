package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;
@Table("t_logistics_quota_adjust")
public class LogisticsQuotaAdjust {
    /**
     * 
     */
    @Id
    private Integer id;

    /**
     * 物流企业id
     */
    private Integer logisticsId;

    /**
     * 调整的额度值，单位（分），续期时额度值为0
     */
    private BigDecimal quota;

    /**
     * 类型：1，额度调整，2，授信延期
     */
    private Byte type;

    /**
     * 有效期开始时间
     */
    private Date startDate;

    /**
     * 有效期结束时间
     */
    private Date endDate;

    /**
     * 审批时间
     */
    private Date auditTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 调整的额度值，单位（分），续期时额度值为0
     * @return quota 调整的额度值，单位（分），续期时额度值为0
     */
    public BigDecimal getQuota() {
        return quota;
    }

    /**
     * 调整的额度值，单位（分），续期时额度值为0
     * @param quota 调整的额度值，单位（分），续期时额度值为0
     */
    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    /**
     * 类型：1，额度调整，2，授信延期
     * @return type 类型：1，额度调整，2，授信延期
     */
    public Byte getType() {
        return type;
    }

    /**
     * 类型：1，额度调整，2，授信延期
     * @param type 类型：1，额度调整，2，授信延期
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 有效期开始时间
     * @return start_date 有效期开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 有效期开始时间
     * @param startDate 有效期开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 有效期结束时间
     * @return end_date 有效期结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 有效期结束时间
     * @param endDate 有效期结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 审批时间
     * @return audit_time 审批时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 审批时间
     * @param auditTime 审批时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
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