package com.jyblife.logic.wl.ops.manage.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/18
 **/
public class RoleRightDto implements Serializable {

    private Integer id;
    private String name;
    private String code;
    private Integer parentId;
    private List<SystemModuleActionDto> actions;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<SystemModuleActionDto> getActions() {
        return actions;
    }

    public void setActions(List<SystemModuleActionDto> actions) {
        this.actions = actions;
    }
}
