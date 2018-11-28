package com.jyblife.logic.wl.ops.manage.dto;


import javax.validation.constraints.NotNull;

public class OilStationApplyDelFileDto {
	
	@NotNull(message = "附件编号不能为空")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
