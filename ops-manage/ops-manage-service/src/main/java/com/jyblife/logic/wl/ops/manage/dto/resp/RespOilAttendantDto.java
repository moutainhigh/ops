package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/11/27
 **/
public class RespOilAttendantDto {

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

    private String statusName;

    /**
     * 加油站id
     */
    private Integer stationId;

    private String stationName;
    /**
     * 油企id
     */
    private Integer oilCompanyId;


    private String oilCompanyName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getOilCompanyId() {
        return oilCompanyId;
    }

    public void setOilCompanyId(Integer oilCompanyId) {
        this.oilCompanyId = oilCompanyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOilCompanyName() {
        return oilCompanyName;
    }

    public void setOilCompanyName(String oilCompanyName) {
        this.oilCompanyName = oilCompanyName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
