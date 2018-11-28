package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;
@Table("t_oil_station_apply")
public class OilStationApply {
    /**
     * 标识
     */
    @Id
    private Integer applyId;

    /**
     * 油站名称
     */
    private String name;

    /**
     * 油企ID
     */
    private Integer companyId;

    /**
     * 所在省份
     */
    private Integer provinceId;

    /**
     * 所在城市
     */
    private Integer cityId;

    /**
     * 地址
     */
    private String address;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

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
    
    private BigDecimal rate;

    public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
     * 标识
     * @return apply_id 标识
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * 标识
     * @param applyId 标识
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 油站名称
     * @return name 油站名称
     */
    public String getName() {
        return name;
    }

    /**
     * 油站名称
     * @param name 油站名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 油企ID
     * @return company_id 油企ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 油企ID
     * @param companyId 油企ID
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 所在省份
     * @return province_id 所在省份
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 所在省份
     * @param provinceId 所在省份
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 所在城市
     * @return city_id 所在城市
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 所在城市
     * @param cityId 所在城市
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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
     * 经度
     * @return longitude 经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 经度
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 纬度
     * @return latitude 纬度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 纬度
     * @param latitude 纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 联系人
     * @return contact_person 联系人
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * 联系人
     * @param contactPerson 联系人
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
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