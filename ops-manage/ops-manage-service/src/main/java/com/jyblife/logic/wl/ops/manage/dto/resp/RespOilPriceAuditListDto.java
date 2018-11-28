package com.jyblife.logic.wl.ops.manage.dto.resp;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("rawtypes")
public class RespOilPriceAuditListDto extends RespPager {

    public static class OilPriceAuditItem {
    	
        private Integer applyId;
        private String code;
        private Integer status;
        private String statusName;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        private String createUserName;

        private Boolean isCanCheck = false;
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

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public Boolean getIsCanCheck() {
			return isCanCheck;
		}

		public void setIsCanCheck(Boolean isCanCheck) {
			this.isCanCheck = isCanCheck;
		}

		public Boolean getIsCanView() {
            return isCanView;
        }

        public void setIsCanView(Boolean canView) {
            isCanView = canView;
        }
    }
}
