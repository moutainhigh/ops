package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

public class RespOilPrintListDto extends RespPager<RespOilPrintListDto.PrintItem> {

    public static class PrintItem {
    	
        private String printId; 
        private String stationId; // 油站ID
        private String stationName; // 油站名称
        private String printSn; // 打印机编码
        private String printName; // 打印机名称
        private String simCard; // 流量卡号码
        private String status; // 在线状态 (0:正常,1:异常)
        private String toBePrint; // 待打印数量
        private String createUserId; 
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        private String updateUserId;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date updateTime;
        
		public String getPrintId() {
			return printId;
		}
		public void setPrintId(String printId) {
			this.printId = printId;
		}
		public String getStationId() {
			return stationId;
		}
		public void setStationId(String stationId) {
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getToBePrint() {
			return toBePrint;
		}
		public void setToBePrint(String toBePrint) {
			this.toBePrint = toBePrint;
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
        
    }
}
