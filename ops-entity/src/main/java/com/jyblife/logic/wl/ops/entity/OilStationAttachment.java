package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_oil_station_attachment")
public class OilStationAttachment {
    /**
     * 标识
     */
    @Id
    private Integer id;

    /**
     * 关联的主信息Id
     */
    private Integer baseId;

    /**
     * 附件类型
     */
    private Integer type;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 物理路径
     */
    private String filePath;

    /**
     * 地址url
     */
    private String fileUrl;

    /**
     * 状态
     */
    private Integer status;

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
     * 标识
     * @return id 标识
     */
    public Integer getId() {
        return id;
    }

    /**
     * 标识
     * @param id 标识
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 关联的主信息Id
     * @return base_id 关联的主信息Id
     */
    public Integer getBaseId() {
        return baseId;
    }

    /**
     * 关联的主信息Id
     * @param baseId 关联的主信息Id
     */
    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    /**
     * 附件类型
     * @return type 附件类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 附件类型
     * @param type 附件类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 附件名称
     * @return name 附件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 附件名称
     * @param name 附件名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 物理路径
     * @return file_path 物理路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 物理路径
     * @param filePath 物理路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * 地址url
     * @return file_url 地址url
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 地址url
     * @param fileUrl 地址url
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
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