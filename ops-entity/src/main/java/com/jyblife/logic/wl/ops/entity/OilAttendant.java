package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;

@Table("t_oil_attendant")
public class OilAttendant {
    /**
     * 
     */
    @Id
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 工号
     */
    private String code;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 是否有效：1,有效；0,无效
     */
    private Byte status;

    /**
     * 加油站id
     */
    private Integer stationId;

    /**
     * 油企id
     */
    private Integer oilCompanyId;

    /**
     * 备注
     */
    private String remark;

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
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 工号
     * @return code 工号
     */
    public String getCode() {
        return code;
    }

    /**
     * 工号
     * @param code 工号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 手机号
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 是否有效：1,有效；0,无效
     * @return status 是否有效：1,有效；0,无效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 是否有效：1,有效；0,无效
     * @param status 是否有效：1,有效；0,无效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 加油站id
     * @return station_id 加油站id
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * 加油站id
     * @param stationId 加油站id
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * 油企id
     * @return oil_company_id 油企id
     */
    public Integer getOilCompanyId() {
        return oilCompanyId;
    }

    /**
     * 油企id
     * @param oilCompanyId 油企id
     */
    public void setOilCompanyId(Integer oilCompanyId) {
        this.oilCompanyId = oilCompanyId;
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