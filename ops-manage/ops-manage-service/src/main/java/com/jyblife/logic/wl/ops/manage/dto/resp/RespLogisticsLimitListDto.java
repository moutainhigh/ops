package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public class RespLogisticsLimitListDto extends RespPager<RespLogisticsLimitListDto.LogisticsLimitItem> {

    public static class LogisticsLimitItem {
        private String limitId;
        private String code;
        private String rate;
        private String createUserId;
        private String createUserName;
        private Date createTime;
        private boolean isCanView = false;
        private boolean isCanEdit = false;

        public String getLimitId() {
            return limitId;
        }

        public void setLimitId(String limitId) {
            this.limitId = limitId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
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

        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public boolean getIsCanView() {
            return isCanView;
        }

        public void setIsCanView(boolean canView) {
            isCanView = canView;
        }

        public boolean getIsCanEdit() {
            return isCanEdit;
        }

        public void setIsCanEdit(boolean canEdit) {
            isCanEdit = canEdit;
        }

    }
}
