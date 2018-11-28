package com.jyblife.logic.wl.ops.external.dto.resp;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("rawtypes")
public class RespOilPriceListDto extends RespPager {

    public static class OilPriceItem {
    	
        private String code;
        private String status;
        private String goodsName;
        private String retailPrice;
        private String agreedPrice;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date effectTime;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date endTime;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public String getRetailPrice() {
			return retailPrice;
		}

		public void setRetailPrice(String retailPrice) {
			this.retailPrice = retailPrice;
		}

		public String getAgreedPrice() {
			return agreedPrice;
		}

		public void setAgreedPrice(String agreedPrice) {
			this.agreedPrice = agreedPrice;
		}

		public Date getEffectTime() {
			return effectTime;
		}

		public void setEffectTime(Date effectTime) {
			this.effectTime = effectTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

    }
}
