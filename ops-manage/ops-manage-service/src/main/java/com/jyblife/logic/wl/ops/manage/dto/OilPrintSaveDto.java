package com.jyblife.logic.wl.ops.manage.dto;

import javax.validation.constraints.NotNull;

public class OilPrintSaveDto {

	private Integer printId; //标识
	@NotNull(message = "油站编码不能为空")
    private Integer stationId; // 油站编码
    private String stationName; // 油站名称
	@NotNull(message = "打印机编码不能为空")
    private String printSn; // 打印机编码
    private String printName; // 打印机名称
    private String simCard; // 流量卡号码
    
	public Integer getPrintId() {
		return printId;
	}
	public void setPrintId(Integer printId) {
		this.printId = printId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getPrintSn() {
		return printSn;
	}
	public void setPrintSn(String printSn) {
		this.printSn = printSn;
	}
	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public String getSimCard() {
		return simCard;
	}
	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}

    

}
