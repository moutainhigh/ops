package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.io.Serializable;
import java.util.Date;
@Table("t_system_user_right")
public class SystemUserRight implements Serializable {
    /**
     * 
     */
    @Id
    private Integer userId;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer updateUserId;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private String rightCodes;

    /**
     * 
     * @return user_id 
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId 
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 
     * @return create_user_id 
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 
     * @param createUserId 
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return update_user_id 
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 
     * @param updateUserId 
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 
     * @return update_time 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return right_codes 
     */
    public String getRightCodes() {
        return rightCodes;
    }

    /**
     * 
     * @param rightCodes 
     */
    public void setRightCodes(String rightCodes) {
        this.rightCodes = rightCodes == null ? null : rightCodes.trim();
    }
}