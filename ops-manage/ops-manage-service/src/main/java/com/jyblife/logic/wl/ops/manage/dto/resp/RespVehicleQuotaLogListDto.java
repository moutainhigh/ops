package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public class RespVehicleQuotaLogListDto extends RespPager<RespVehicleQuotaLogListDto.VehicleQuotaLogItem> {

    public static class VehicleQuotaLogItem {
        private String relationId;
        private String quota;
        private Date createTime;
        private String orderCode;

        public String getRelationId() {
            return relationId;
        }

        public void setRelationId(String relationId) {
            this.relationId = relationId;
        }

        public String getQuota() {
            return quota;
        }

        public void setQuota(String quota) {
            this.quota = quota;
        }

        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

		public String getOrderCode() {
			return orderCode;
		}

		public void setOrderCode(String orderCode) {
			this.orderCode = orderCode;
		}
        
    }
}
