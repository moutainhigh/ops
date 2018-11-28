package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

public class RespOrderListDto extends RespPager<RespOrderListDto.OrderItem> {

    public static class OrderItem {
        private String orderId;
        private String code;
        private String customerId;
        private String vehicleId;
        private String stationId;
        private String goodsId;
        private String quantity;
        private String priceBuy;
        private String priceSell;
        private String priceRetail;
        private String oilCompanyId;
        private String logisticsId;
        private String failedReason;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date effectTime;
        private String status;
        private String statusName;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date statusTime;
        private String remark;
        private String createUserId;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        private String updateUserId;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date updateTime;
        private String goodsName;
        private String customerName;
        private String customerPhone;
        private String logisticsName;
        private String vehicleNumber;
        private String vehicleModel;
        private String oilStationName;
        private String oilStationAddress;
        //油品总销售价
        private Long sellAmount;
        //油品总采购价
        private Long buyAmount;
        private boolean isCanView = true;
        private boolean isCanEdit = false;
        //订单类型
        private String orderType;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getPriceBuy() {
            return priceBuy;
        }

        public void setPriceBuy(String priceBuy) {
            this.priceBuy = priceBuy;
        }

        public String getPriceSell() {
            return priceSell;
        }

        public void setPriceSell(String priceSell) {
            this.priceSell = priceSell;
        }

        public String getPriceRetail() {
            return priceRetail;
        }

        public void setPriceRetail(String priceRetail) {
            this.priceRetail = priceRetail;
        }

        public String getOilCompanyId() {
            return oilCompanyId;
        }

        public void setOilCompanyId(String oilCompanyId) {
            this.oilCompanyId = oilCompanyId;
        }

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }

        public String getFailedReason() {
            return failedReason;
        }

        public void setFailedReason(String failedReason) {
            this.failedReason = failedReason;
        }

        public Date getEffectTime() {
            return effectTime;
        }

        public void setEffectTime(Date effectTime) {
            this.effectTime = effectTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Date getStatusTime() {
            return statusTime;
        }

        public void setStatusTime(Date statusTime) {
            this.statusTime = statusTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public String getVehicleModel() {
            return vehicleModel;
        }

        public void setVehicleModel(String vehicleModel) {
            this.vehicleModel = vehicleModel;
        }

        public String getOilStationName() {
            return oilStationName;
        }

        public void setOilStationName(String oilStationName) {
            this.oilStationName = oilStationName;
        }

        public String getOilStationAddress() {
            return oilStationAddress;
        }

        public void setOilStationAddress(String oilStationAddress) {
            this.oilStationAddress = oilStationAddress;
        }

        public Long getSellAmount() {
            return sellAmount;
        }

        public void setSellAmount(Long sellAmount) {
            this.sellAmount = sellAmount;
        }

        public Long getBuyAmount() {
            return buyAmount;
        }

        public void setBuyAmount(Long buyAmount) {
            this.buyAmount = buyAmount;
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

		public String getStatusName() {
			return statusName;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}

		public String getOrderType() {
			return orderType;
		}

		public void setOrderType(String orderType) {
			this.orderType = orderType;
		}
        
    }
}
