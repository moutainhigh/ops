package com.jyblife.logic.wl.ops.manage.dto.resp;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("rawtypes")
public class RespOilPriceListDto extends RespPager {

    public static class OilPriceItem {
    	
    	private Integer applyId;
        private Integer priceId;
        private String code;
        private String status;
        private String stationName;
        private String companyName;
        private String goodsName;
        private String retailPrice;
        private String agreedPrice;
        private String discountPrice;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date effectTime;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date endTime;
        private String createUserName;

        private Boolean isCanExecute = false;

		public Integer getApplyId() {
			return applyId;
		}

		public void setApplyId(Integer applyId) {
			this.applyId = applyId;
		}

		public Integer getPriceId() {
			return priceId;
		}

		public void setPriceId(Integer priceId) {
			this.priceId = priceId;
		}

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

		public String getStationName() {
			return stationName;
		}

		public void setStationName(String stationName) {
			this.stationName = stationName;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
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

		public String getDiscountPrice() {
			return discountPrice;
		}

		public void setDiscountPrice(String discountPrice) {
			this.discountPrice = discountPrice;
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

		public String getCreateUserName() {
			return createUserName;
		}

		public void setCreateUserName(String createUserName) {
			this.createUserName = createUserName;
		}

		public Boolean getIsCanExecute() {
			return isCanExecute;
		}

		public void setIsCanExecute(Boolean isCanExecute) {
			this.isCanExecute = isCanExecute;
		}
       
    }
}
