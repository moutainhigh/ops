package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_photo_attachment")
public class PhotoAttachment {
    /**
     * 标识id
     */
    @Id
    private Long id;

    /**
     * 关联id
     */
    private Long baseId;

    /**
     * 附件名
     */
    private String name;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private String outId;

    /**
     * 路径
     */
    private String filePath;

    /**
     * 路径url
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
     * 标识id
     * @return id 标识id
     */
    public Long getId() {
        return id;
    }

    /**
     * 标识id
     * @param id 标识id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 关联id
     * @return base_id 关联id
     */
    public Long getBaseId() {
        return baseId;
    }

    /**
     * 关联id
     * @param baseId 关联id
     */
    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    /**
     * 附件名
     * @return name 附件名
     */
    public String getName() {
        return name;
    }

    /**
     * 附件名
     * @param name 附件名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * @return type 
     */
    public Integer getType() {
        return type;
    }

    /**
     * 
     * @param type 
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 
     * @return out_id 
     */
    public String getOutId() {
        return outId;
    }

    /**
     * 
     * @param outId 
     */
    public void setOutId(String outId) {
        this.outId = outId == null ? null : outId.trim();
    }

    /**
     * 路径
     * @return file_path 路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 路径
     * @param filePath 路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * 路径url
     * @return file_url 路径url
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 路径url
     * @param fileUrl 路径url
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