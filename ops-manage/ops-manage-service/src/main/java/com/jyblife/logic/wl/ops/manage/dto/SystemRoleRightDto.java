package com.jyblife.logic.wl.ops.manage.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/19
 **/
public class SystemRoleRightDto implements Serializable {
    @NotBlank(message = "角色id不能为空")
    private String roleId;

    @NotEmpty(message = "权限不能为空")
    private List<RoleRight> roleRight = new ArrayList<RoleRight>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<RoleRight> getRoleRight() {
        return roleRight;
    }

    public void setRoleRight(List<RoleRight> roleRight) {
        this.roleRight = roleRight;
    }

    public void newRoleRight(String id, String name, String code, List<ModuleAction> actions) {
        this.roleRight.add(new RoleRight(id, name, code, actions));
    }


    public static class RoleRight {
        private String id;
        private String name;
        private String code;
        private String parentId;
        private transient String label;
        private List<ModuleAction> actions;

        public RoleRight() {

        }

        public RoleRight(String id, String name, String code, List<ModuleAction> actions) {
            this.id = id;
            this.name = name;
            this.code = code;
            this.actions = actions;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public List<ModuleAction> getActions() {
            return actions;
        }

        public void setActions(List<ModuleAction> actions) {
            this.actions = actions;
        }

        public String getLabel() {
            return this.name;
        }

        public void setLabel(String label) {
            this.name = label;
        }
    }

    public static class ModuleAction {
        private String name;
        private String code;

        public ModuleAction() {

        }

        public ModuleAction(String name, String code) {
            this.name = name;
            this.code = code;
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
    }

}
