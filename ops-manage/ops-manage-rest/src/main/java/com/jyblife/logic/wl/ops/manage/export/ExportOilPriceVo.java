package com.jyblife.logic.wl.ops.manage.export;

import java.util.Date;

import com.jyblife.logic.wl.ops.common.excel.DateFormat;
import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;

public class ExportOilPriceVo {

	@ExcelAttribute(name = "价格编号", column = "A")
	private String code;
	@ExcelAttribute(name = "价格状态", column = "B")
	private String status;
	@ExcelAttribute(name = "油站名称", column = "C")
	private String stationName;
	@ExcelAttribute(name = "油企名称", column = "D")
	private String companyName;
	@ExcelAttribute(name = "油品名称", column = "E")
	private String goodsName;
	@ExcelAttribute(name = "零售价/元", column = "F")
	private String retailPrice;
	@ExcelAttribute(name = "协议价/元", column = "G")
	private String agreedPrice;
	@ExcelAttribute(name = "优惠价/元", column = "H")
	private String discountPrice;
	@DateFormat(format = "yyyy-MM-dd HH:mm:ss")
	@ExcelAttribute(name = "生效时间", column = "I")
	private Date effectTime;
	@DateFormat(format = "yyyy-MM-dd HH:mm:ss")
	@ExcelAttribute(name = "失效时间", column = "J")
	private Date endTime;
	@ExcelAttribute(name = "创建人", column = "K")
	private String createUserName;

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

}
