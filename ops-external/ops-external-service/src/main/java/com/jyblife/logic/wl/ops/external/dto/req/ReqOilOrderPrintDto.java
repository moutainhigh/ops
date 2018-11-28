package com.jyblife.logic.wl.ops.external.dto.req;

/**
 * @author wanyuexin
 * @date 2018/11/13
 **/
public class ReqOilOrderPrintDto {
    
    private String ordId; //订单号
    private String printer; //打印人
    
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getPrinter() {
		return printer;
	}
	public void setPrinter(String printer) {
		this.printer = printer;
	}

}
