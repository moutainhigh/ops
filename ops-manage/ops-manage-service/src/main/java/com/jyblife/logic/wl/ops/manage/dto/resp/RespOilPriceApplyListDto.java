package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/10/29
 **/
public class RespOilPriceApplyListDto extends RespPager {

    public static class OilPriceApplyItem{
        private Integer applyId;
        private String code;
        private Integer status;
        private String statusName;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        private String createUserId;
        private String createUserName;

        private Boolean isCanEdit = false;
        private Boolean isCanView = true;


        public Integer getApplyId() {
            return applyId;
        }

        public void setApplyId(Integer applyId) {
            this.applyId = applyId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

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

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public Boolean getIsCanEdit() {
            return isCanEdit;
        }

        public void setIsCanEdit(Boolean canEdit) {
            isCanEdit = canEdit;
        }

        public Boolean getIsCanView() {
            return isCanView;
        }

        public void setIsCanView(Boolean canView) {
            isCanView = canView;
        }
    }
}
