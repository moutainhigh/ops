package com.jyblife.logic.wl.ops.manage.dto.resp;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public class RespLogisticsDailyQuotaLogListDto extends RespPager {

    public static class LogisticsDailyQuotaItem {
        private String relationId;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        private String quota;
        private String category;
        private String orderCode;
        private String categoryName;
        private boolean isCanView = true;
        private boolean isCanEdit = true;

        public String getRelationId() {
            return relationId;
        }

        public void setRelationId(String relationId) {
            this.relationId = relationId;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public String getQuota() {
            return quota;
        }

        public void setQuota(String quota) {
            this.quota = quota;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
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