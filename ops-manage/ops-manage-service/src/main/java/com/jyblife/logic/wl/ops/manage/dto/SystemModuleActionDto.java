package com.jyblife.logic.wl.ops.manage.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class SystemModuleActionDto  implements Serializable {

    private String name;
    @NotBlank(message = "操作码不能为空")
    private String code;

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
}
