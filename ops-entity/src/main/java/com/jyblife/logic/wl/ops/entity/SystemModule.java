package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table("t_system_module")
public class SystemModule implements Serializable {
    /**
     * 
     */
    @Id
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String icon;

    /**
     * 
     */
    private Integer systemId;

    /**
     * 
     */
    private Integer parentId;

    /**
     * 
     */
    private String parentIds;

    /**
     * 
     */
    private String pageUrl;

    /**
     * 
     */
    private String actions;

    /**
     * 
     */
    private Integer orderIndex;

    /**
     * 
     */
    private Byte isPublic;

    /**
     * 
     */
    private Byte isExternal;

    /**
     * 
     */
    private Byte isMenu;

    /**
     * 
     */
    private Byte status;

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
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * @return code 
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code 
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 
     * @return icon 
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon 
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 
     * @return system_id 
     */
    public Integer getSystemId() {
        return systemId;
    }

    /**
     * 
     * @param systemId 
     */
    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    /**
     * 
     * @return parent_id 
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 
     * @param parentId 
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return parent_ids 
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 
     * @param parentIds 
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * 
     * @return page_url 
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * 
     * @param pageUrl 
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    /**
     * 
     * @return actions 
     */
    public String getActions() {
        return actions;
    }

    /**
     * 
     * @param actions 
     */
    public void setActions(String actions) {
        this.actions = actions == null ? null : actions.trim();
    }

    /**
     * 
     * @return order_index 
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }

    /**
     * 
     * @param orderIndex 
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * 
     * @return is_public 
     */
    public Byte getIsPublic() {
        return isPublic;
    }

    /**
     * 
     * @param isPublic 
     */
    public void setIsPublic(Byte isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * 
     * @return is_external 
     */
    public Byte getIsExternal() {
        return isExternal;
    }

    /**
     * 
     * @param isExternal 
     */
    public void setIsExternal(Byte isExternal) {
        this.isExternal = isExternal;
    }

    /**
     * 
     * @return is_menu 
     */
    public Byte getIsMenu() {
        return isMenu;
    }

    /**
     * 
     * @param isMenu 
     */
    public void setIsMenu(Byte isMenu) {
        this.isMenu = isMenu;
    }

    /**
     * 
     * @return status 
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 
     * @param status 
     */
    public void setStatus(Byte status) {
        this.status = status;
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
}