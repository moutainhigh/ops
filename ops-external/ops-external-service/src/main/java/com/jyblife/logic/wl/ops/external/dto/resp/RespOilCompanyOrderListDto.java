package com.jyblife.logic.wl.ops.external.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/11/1
 **/
public class RespOilCompanyOrderListDto extends RespPager<RespOilCompanyOrderListDto.OilCompanyOrderItem> {


    public static class OilCompanyOrderItem {
        //private String id;
        private String ordId;//	订单号	varchar
        private String oilCode;
        private String logiCode;
        private String plateInfo;//	车牌号	varchar
        //@JSONField(format = "yyyyMMdd")
        private Date ordCreateTime;//1532880000",
        @JSONField(format = "yyyyMMdd")
        private Date ordPayDate;//20180730",
        //@JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date ordPayTime;//1532880000",
        private String oilType;//	油品类型	varchar
        private String refuelNumber;//	加油升数	decimal
        private String retailPrice;//	零售单价	decimal
        private String logiPrice;//	优惠单价	给物流公司的价格
        private String oilsPrice;//协议价 给油企的价格
        private String logiSettleAmount;//logiPrice*refuelNumber
        private String oilsSettleAmount;//oils_price*refuelNumber
        private String oilName;//	油站名称	varchar
        private String logisticsName;//物流企业名称
        private String type;
        private String remark;

       /* 以下字段是无法获取的字段
        "id": "907030934",
        "bill_time": "1534262400",
        "final_repayment_time": "1534521600",
        "overdue_amt_time": "1537113600",
        "type": "1",
        "status": "1",
        "oils_daily_code": "",
        "logi_daily_code": "",
        "refund_id": null,
        "refund_time": null,
        "examine_status": "1",
        "created_time": "1532880000",
        "oil_parent_code": null,
        "oil_parent_name": null,*/

        public String getOrdId() {
            return ordId;
        }

        public void setOrdId(String ordId) {
            this.ordId = ordId;
        }

        public String getOilCode() {
            return oilCode;
        }

        public void setOilCode(String oilCode) {
            this.oilCode = oilCode;
        }

        public String getLogiCode() {
            return logiCode;
        }

        public void setLogiCode(String logiCode) {
            this.logiCode = logiCode;
        }

        public String getPlateInfo() {
            return plateInfo;
        }

        public void setPlateInfo(String plateInfo) {
            this.plateInfo = plateInfo;
        }

        public Date getOrdCreateTime() {
            return ordCreateTime;
        }

        public void setOrdCreateTime(Date ordCreateTime) {
            this.ordCreateTime = ordCreateTime;
        }

        public Date getOrdPayDate() {
            return ordPayDate;
        }

        public void setOrdPayDate(Date ordPayDate) {
            this.ordPayDate = ordPayDate;
        }

        public Date getOrdPayTime() {
            return ordPayTime;
        }

        public void setOrdPayTime(Date ordPayTime) {
            this.ordPayTime = ordPayTime;
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

        public String getOilsPrice() {
            return oilsPrice;
        }

        public void setOilsPrice(String oilsPrice) {
            this.oilsPrice = oilsPrice;
        }

        public String getLogiSettleAmount() {
            return logiSettleAmount;
        }

        public void setLogiSettleAmount(String logiSettleAmount) {
            this.logiSettleAmount = logiSettleAmount;
        }

        public String getOilsSettleAmount() {
            return oilsSettleAmount;
        }

        public void setOilsSettleAmount(String oilsSettleAmount) {
            this.oilsSettleAmount = oilsSettleAmount;
        }

        public String getOilName() {
            return oilName;
        }

        public void setOilName(String oilName) {
            this.oilName = oilName;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
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
