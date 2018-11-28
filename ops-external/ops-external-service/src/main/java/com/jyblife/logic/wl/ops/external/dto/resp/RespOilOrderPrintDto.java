package com.jyblife.logic.wl.ops.external.dto.resp;

import java.util.Date;

/**
 * @author wanyuexin
 * @date 2018/11/13
 **/
public class RespOilOrderPrintDto{

	private String ordId; //	订单号	
	private String oilCode; //油站编码
	private String oilName; // 油站名称	
	private String plateInfo; // 车牌号	
	private Date ordPayTime; // 交易日期
	private String oilType;// 油品类型	
	private String refuelNumber;// 加油升数	
	
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
	public String getOilName() {
		return oilName;
	}
	public void setOilName(String oilName) {
		this.oilName = oilName;
	}
	public String getPlateInfo() {
		return plateInfo;
	}
	public void setPlateInfo(String plateInfo) {
		this.plateInfo = plateInfo;
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


}
