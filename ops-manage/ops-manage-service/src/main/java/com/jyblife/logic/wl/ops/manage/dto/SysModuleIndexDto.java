package com.jyblife.logic.wl.ops.manage.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/18
 **/
public class SysModuleIndexDto implements Serializable {
    private List<SysModuleIndexDto> children;
    private String id;
    private String label;
    private String code;
    private String parentId;
    private List<SystemModuleActionDto> actions;
    private String status;

    public List<SysModuleIndexDto> getChildren() {
        return children;
    }

    public void setChildren(List<SysModuleIndexDto> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<SystemModuleActionDto> getActions() {
        return actions;
    }

    public void setActions(List<SystemModuleActionDto> actions) {
        this.actions = actions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
