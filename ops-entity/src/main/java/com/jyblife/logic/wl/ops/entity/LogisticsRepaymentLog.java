package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;
@Table("t_logistics_repayment_log")
public class LogisticsRepaymentLog {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 物流企业id
     */
    private Integer logisticsId;

    /**
     * 财务唯一流水号
     */
    private String txNo;

    /**
     * 还款金额（单位分）
     */
    private BigDecimal amount;

    /**
     * 状态：0还款失败，1还款成功
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
     * 创建时间
     */
    private Date createTime;

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
     * 财务唯一流水号
     * @return tx_no 财务唯一流水号
     */
    public String getTxNo() {
        return txNo;
    }

    /**
     * 财务唯一流水号
     * @param txNo 财务唯一流水号
     */
    public void setTxNo(String txNo) {
        this.txNo = txNo == null ? null : txNo.trim();
    }

    /**
     * 还款金额（单位分）
     * @return amount 还款金额（单位分）
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 还款金额（单位分）
     * @param amount 还款金额（单位分）
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 状态：0还款失败，1还款成功
     * @return status 状态：0还款失败，1还款成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0还款失败，1还款成功
     * @param status 状态：0还款失败，1还款成功
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
}