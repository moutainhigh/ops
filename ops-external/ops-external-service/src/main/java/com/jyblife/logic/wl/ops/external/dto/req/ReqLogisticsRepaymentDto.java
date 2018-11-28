package com.jyblife.logic.wl.ops.external.dto.req;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqLogisticsRepaymentDto {

	@NotNull(message = "物流企业id不能为空")
	private Integer logisticsId;
	@NotBlank(message = "交易流水号不能为空")
	private String txNo;
	@Min(value = 0, message = "还款金额不能小于0")
	@NotNull(message = "还款金额不能为空")
	private BigDecimal amount;
	
	private String remark;

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getTxNo() {
		return txNo;
	}

	public void setTxNo(String txNo) {
		this.txNo = txNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
