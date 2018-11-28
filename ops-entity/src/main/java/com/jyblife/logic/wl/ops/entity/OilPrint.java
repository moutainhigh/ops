package com.jyblife.logic.wl.ops.entity;

import java.util.Date;

public class OilPrint {
   
	/**
	 * 标识
	 */
    private Integer printId;

    /**
	 * 油站编号
	 */
    private Integer stationId;

    /**
	 * 油站名称
	 */
    private String stationName;

    /**
	 * 打印机编号
	 */
    private String printSn;

    /**
	 * 打印机名称
	 */
    private String printName;

    /**
	 * 流量卡号
	 */
    private String simCard;

    private Date createTime;

    private Integer createUserId;

    private Integer updateUserId;

    private Date updateTime;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    
    
    
}