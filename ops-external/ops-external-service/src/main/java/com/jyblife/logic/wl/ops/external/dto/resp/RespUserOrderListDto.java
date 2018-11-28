package com.jyblife.logic.wl.ops.external.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class RespUserOrderListDto {

	private Integer pageCount;
	private Integer page;
	private Long total;
	private List<?> rows;
	private Object monthlySum;

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Object getMonthlySum() {
		return monthlySum;
	}

	public void setMonthlySum(Object monthlySum) {
		this.monthlySum = monthlySum;
	}

	public static class OrderItem {
		private String orderId;// 订单id/订单编号,
		private String status;// 订单状态,
		private String statusDesc;// 订单状态描述,
		private String quantity;// 升数,
		private String sellAmount;// 油品总销售价，单位：分,
		private String buyAmount;// 油品总采购价，单位：分,
		private String retailPrice;// 零售价，单位：分,
		private String agreedPrice;// 协议价，单位：分,
		private String discountPrice;// 优惠价，单位：分,
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		private Date createTime;// 订单提交时间，格式：yyyy-mm-dd HH;//MM;//SS,
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		private Date effectTime;// 订单生效时间，格式：yyyy-mm-dd HH;//MM;//SS,
		private String failedReason;// 失败原因,
		private String goodsId;// 油品id,
		private String goodsName;// 油品名,
		private String customerId;// 司机id,
		private String customerName;// 司机名称,
		private String customerPhone;// 手机号码,
		private String logisticsId;// 物流公司id,
		private String logisticsName;// 物流公司名称
		private String vehicleId;// 车辆id,
		private String vehicleNumber;// 车牌号,
		private String vehicleModel;// 车型,
		private String oilStationId;// 油站id,
		private String oilStationName;// 油站名,
		private String oilStationAddress;// 油站地址

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStatusDesc() {
			return statusDesc;
		}

		public void setStatusDesc(String statusDesc) {
			this.statusDesc = statusDesc;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		public String getSellAmount() {
			return sellAmount;
		}

		public void setSellAmount(String sellAmount) {
			this.sellAmount = sellAmount;
		}

		public String getBuyAmount() {
			return buyAmount;
		}

		public void setBuyAmount(String buyAmount) {
			this.buyAmount = buyAmount;
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

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getEffectTime() {
			return effectTime;
		}

		public void setEffectTime(Date effectTime) {
			this.effectTime = effectTime;
		}

		public String getFailedReason() {
			return failedReason;
		}

		public void setFailedReason(String failedReason) {
			this.failedReason = failedReason;
		}

		public String getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
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

		public String getLogisticsId() {
			return logisticsId;
		}

		public void setLogisticsId(String logisticsId) {
			this.logisticsId = logisticsId;
		}

		public String getLogisticsName() {
			return logisticsName;
		}

		public void setLogisticsName(String logisticsName) {
			this.logisticsName = logisticsName;
		}

		public String getVehicleId() {
			return vehicleId;
		}

		public void setVehicleId(String vehicleId) {
			this.vehicleId = vehicleId;
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

		public String getOilStationId() {
			return oilStationId;
		}

		public void setOilStationId(String oilStationId) {
			this.oilStationId = oilStationId;
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
	}
}
