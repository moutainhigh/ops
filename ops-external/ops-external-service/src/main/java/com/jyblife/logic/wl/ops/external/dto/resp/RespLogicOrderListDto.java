package com.jyblife.logic.wl.ops.external.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/10/30
 **/
public class RespLogicOrderListDto extends RespPager<RespLogicOrderListDto.OrderItem> {


    public static class OrderItem {
        private String ordId;//	订单号	varchar
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date ordPayTime;//	订单交易时间	datetime
        private String customerName;//	司机姓名	varchar
        private String plateInfo;//	车牌号	varchar
        private String carModel;//	车型	varchar
        private String oilName;//	油站名称	varchar
        private String oilAdress;//	油站地址	varchar
        private String oilType;//	油品类型	varchar
        private String refuelNumber;//	加油升数	decimal
        private String retailPrice;//	零售单价	decimal
        private String logiPrice;//	优惠单价	decimal
        private String logiSettleAmount;//	加油金额	decimal
        private String type;//	订单类型 (正常、退单)	varchar
        private String remark; //备注

        public String getOrdId() {
            return ordId;
        }

        public void setOrdId(String ordId) {
            this.ordId = ordId;
        }

        public Date getOrdPayTime() {
            return ordPayTime;
        }

        public void setOrdPayTime(Date ordPayTime) {
            this.ordPayTime = ordPayTime;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getPlateInfo() {
            return plateInfo;
        }

        public void setPlateInfo(String plateInfo) {
            this.plateInfo = plateInfo;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getOilName() {
            return oilName;
        }

        public void setOilName(String oilName) {
            this.oilName = oilName;
        }

        public String getOilAdress() {
            return oilAdress;
        }

        public void setOilAdress(String oilAdress) {
            this.oilAdress = oilAdress;
        }

        public String getOilType() {
            return oilType;
        }

        public void setOilType(String oilType) {
            this.oilType = oilType;
        }

        public String getRefuelNumber() {
            return refuelNumber;
        }

        public void setRefuelNumber(String refuelNumber) {
            this.refuelNumber = refuelNumber;
        }

        public String getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(String retailPrice) {
            this.retailPrice = retailPrice;
        }

        public String getLogiPrice() {
            return logiPrice;
        }

        public void setLogiPrice(String logiPrice) {
            this.logiPrice = logiPrice;
        }

        public String getLogiSettleAmount() {
            return logiSettleAmount;
        }

        public void setLogiSettleAmount(String logiSettleAmount) {
            this.logiSettleAmount = logiSettleAmount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
        
    }
}
