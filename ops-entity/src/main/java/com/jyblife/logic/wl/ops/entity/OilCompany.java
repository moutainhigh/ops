package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_oil_company")
public class OilCompany {
    /**
     * 标识ID
     */
    @Id
    private Integer companyId;

    /**
     * 油企名称
     */
    private String name;

    /**
     * 企业简称
     */
    private String shortName;

    /**
     * 纳税人识别号
     */
    private String taxCode;

    /**
     * 法人代表
     */
    private String corporate;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 1,国有
             2,民营
     */
    private Integer ownership;

    /**
     * 成立日期
     */
    private Date buildDate;

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
     * 生效时间
     */
    private Date effectTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private Integer createUserId;

    /**
     * 更新用户
     */
    private Integer updateUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标识ID
     * @return company_id 标识ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 标识ID
     * @param companyId 标识ID
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 油企名称
     * @return name 油企名称
     */
    public String getName() {
        return name;
    }

    /**
     * 油企名称
     * @param name 油企名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 企业简称
     * @return short_name 企业简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 企业简称
     * @param shortName 企业简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
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
     * 法人代表
     * @return corporate 法人代表
     */
    public String getCorporate() {
        return corporate;
    }

    /**
     * 法人代表
     * @param corporate 法人代表
     */
    public void setCorporate(String corporate) {
        this.corporate = corporate == null ? null : corporate.trim();
    }

    /**
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 联系电话
     * @return contact_phone 联系电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 联系电话
     * @param contactPhone 联系电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    /**
     * 1,国有
             2,民营
     * @return ownership 1,国有
             2,民营
     */
    public Integer getOwnership() {
        return ownership;
    }

    /**
     * 1,国有
             2,民营
     * @param ownership 1,国有
             2,民营
     */
    public void setOwnership(Integer ownership) {
        this.ownership = ownership;
    }

    /**
     * 成立日期
     * @return build_date 成立日期
     */
    public Date getBuildDate() {
        return buildDate;
    }

    /**
     * 成立日期
     * @param buildDate 成立日期
     */
    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
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