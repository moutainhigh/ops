package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

public class RespSystemRoleListDto extends RespPager<RespSystemRoleListDto.RoleItem> {


    public static class RoleItem {
        private String roleId;
        private String name;
        private String rightCodes;
        private String orderIndex;
        private String type;
        private String status;
        private String remark;
        private Date createTime;
        private String createUserId;
        private String updateUserId;
        private Date updateTime;
        private boolean isCanDel;
        private boolean isCanAuth;
        private boolean isCanEdit;
        private boolean isCanView;

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRightCodes() {
            return rightCodes;
        }

        public void setRightCodes(String rightCodes) {
            this.rightCodes = rightCodes;
        }

        public String getOrderIndex() {
            return orderIndex;
        }

        public void setOrderIndex(String orderIndex) {
            this.orderIndex = orderIndex;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public boolean getIsCanDel() {
            return isCanDel;
        }

        public void setIsCanDel(boolean canDel) {
            isCanDel = canDel;
        }

        public boolean getIsCanAuth() {
            return isCanAuth;
        }

        public void setIsCanAuth(boolean canAuth) {
            isCanAuth = canAuth;
        }

        public boolean getIsCanEdit() {
            return isCanEdit;
        }

        public void setIsCanEdit(boolean canEdit) {
            isCanEdit = canEdit;
        }

        public boolean getIsCanView() {
            return isCanView;
        }

        public void setIsCanView(boolean canView) {
            isCanView = canView;
        }
    }

}
