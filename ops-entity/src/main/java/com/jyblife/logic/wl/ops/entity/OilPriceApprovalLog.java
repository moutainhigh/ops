package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_oil_price_approval_log")
public class OilPriceApprovalLog {
    /**
     *
     */
    @Id
    private Integer id;

    /**
     * 申请id
     */
    private Integer applyId;

    /**
     * 审批人id
     */
    private Integer operatorId;

    /**
     * 审批人姓名
     */
    private String operatorName;

    /**
     * 审批时间
     */
    private Date createTime;

    /**
     * 审批动作：同意，驳回
     */
    private Integer action;

    /**
     * 审批建议
     */
    private String remark;

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
     * 申请id
     * @return apply_id 申请id
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * 申请id
     * @param applyId 申请id
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 审批人id
     * @return operator_id 审批人id
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * 审批人id
     * @param operatorId 审批人id
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 审批人姓名
     * @return operator_name 审批人姓名
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 审批人姓名
     * @param operatorName 审批人姓名
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * 审批时间
     * @return create_time 审批时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 审批时间
     * @param createTime 审批时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 审批动作：同意，驳回
     * @return action 审批动作：同意，驳回
     */
    public Integer getAction() {
        return action;
    }

    /**
     * 审批动作：同意，驳回
     * @param action 审批动作：同意，驳回
     */
    public void setAction(Integer action) {
        this.action = action;
    }

    /**
     * 审批建议
     * @return remark 审批建议
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 审批建议
     * @param remark 审批建议
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}