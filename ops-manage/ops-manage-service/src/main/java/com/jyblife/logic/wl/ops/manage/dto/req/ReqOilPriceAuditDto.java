package com.jyblife.logic.wl.ops.manage.dto.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqOilPriceAuditDto {
	
	@NotNull(message = "油价申请编号不能为空")
    private Integer applyId;

	@NotNull(message = "action不能为空")
    private Integer action;
	
    @NotBlank(message = "审批意见opinion不能为空")
    private String opinion;

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
    
}
