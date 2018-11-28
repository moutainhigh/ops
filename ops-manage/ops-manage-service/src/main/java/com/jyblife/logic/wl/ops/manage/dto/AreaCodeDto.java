package com.jyblife.logic.wl.ops.manage.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/20
 **/
public class AreaCodeDto {
    private Integer id;
    private String name;
    private List<AreaCodeDto> children;

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

    public List<AreaCodeDto> getChildren() {
    	if(children == null) {
    		children = new ArrayList<>();
    	}
        return children;
    }

    public void setChildren(List<AreaCodeDto> children) {
        this.children = children;
    }
}
