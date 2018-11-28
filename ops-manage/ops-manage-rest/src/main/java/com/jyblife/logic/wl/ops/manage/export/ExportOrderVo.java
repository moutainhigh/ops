package com.jyblife.logic.wl.ops.manage.export;

import com.jyblife.logic.wl.ops.common.excel.DateFormat;
import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;

import java.util.Date;

public class ExportOrderVo {

	@ExcelAttribute(name = "订单交易日期", column = "A")
	@DateFormat(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@ExcelAttribute(name = "订单编号", column = "B")
	private String code;
	@ExcelAttribute(name = "车牌号", column = "C")
	private String vehicleNumber;
	@ExcelAttribute(name = "手机号码", column = "D")
	private String customerPhone;
	@ExcelAttribute(name = "司机姓名", column = "E")
	private String customerName;
	@ExcelAttribute(name = "物流公司", column = "F")
	private String logisticsName;
	@ExcelAttribute(name = "油品", column = "G")
	private String goodsName;
	@ExcelAttribute(name = "升数", column = "H")
	private String quantity;
	@ExcelAttribute(name = "优惠单价", column = "I")
	private String priceSell;
	@ExcelAttribute(name = "油品总价", column = "J")
	private String sellAmount;
	@ExcelAttribute(name = "订单状态", column = "K")
	private String statusName;
	@ExcelAttribute(name = "加油站地址", column = "L")
	private String oilStationAddress;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPriceSell() {
		return priceSell;
	}

	public void setPriceSell(String priceSell) {
		this.priceSell = priceSell;
	}

	public String getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(String sellAmount) {
		this.sellAmount = sellAmount;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOilStationAddress() {
		return oilStationAddress;
	}

	public void setOilStationAddress(String oilStationAddress) {
		this.oilStationAddress = oilStationAddress;
	}

}
