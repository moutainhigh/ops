package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_logistics_company")
public class LogisticsCompany {
    /**
     * 物流企业id
     */
    @Id
    private Long logisticsId;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 银管家标识
     */
    private String outIdentity;

    /**
     * 银管家状态
     */
    private Integer outStatus;

    /**
     * 纳税人识别号
     */
    private String taxCode;

    /**
     * 电话
     */
    private String phone;

    /**
     * 注册地址
     */
    private String address;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 
     */
    private Date statusTime;

    /**
     * 
     */
    private Date effectTime;

    /**
     * 备注
     */
    private String remark;

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
     * 物流企业id
     * @return logistics_id 物流企业id
     */
    public Long getLogisticsId() {
        return logisticsId;
    }

    /**
     * 物流企业id
     * @param logisticsId 物流企业id
     */
    public void setLogisticsId(Long logisticsId) {
        this.logisticsId = logisticsId;
    }

    /**
     * 企业名称
     * @return name 企业名称
     */
    public String getName() {
        return name;
    }

    /**
     * 企业名称
     * @param name 企业名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 银管家标识
     * @return out_identity 银管家标识
     */
    public String getOutIdentity() {
        return outIdentity;
    }

    /**
     * 银管家标识
     * @param outIdentity 银管家标识
     */
    public void setOutIdentity(String outIdentity) {
        this.outIdentity = outIdentity == null ? null : outIdentity.trim();
    }

    /**
     * 银管家状态
     * @return out_status 银管家状态
     */
    public Integer getOutStatus() {
        return outStatus;
    }

    /**
     * 银管家状态
     * @param outStatus 银管家状态
     */
    public void setOutStatus(Integer outStatus) {
        this.outStatus = outStatus;
    }

    /**
     * 纳税人识别号
     * @return tax_code 纳税人识别号
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * 纳税人识别号
     * @param taxCode 纳税人识别号
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode == null ? null : taxCode.trim();
    }

    /**
     * 电话
     * @return phone 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 注册地址
     * @return address 注册地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 注册地址
     * @param address 注册地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
     * 
     * @return effect_time 
     */
    public Date getEffectTime() {
        return effectTime;
    }

    /**
     * 
     * @param effectTime 
     */
    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
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