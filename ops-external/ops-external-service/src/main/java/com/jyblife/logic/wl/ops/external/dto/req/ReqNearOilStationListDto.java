package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReqNearOilStationListDto {

	@Min(value = 0, message="请填写正确的userId")
	@NotNull(message = "userId不能为空")
	private Integer userId;
	@NotNull(message = "longitude不能为空")
	private Double longitude;
	@NotNull(message = "latitude不能为空")
	private Double latitude;
	private String name;
	@Max(value=100, message="最远距离不能超过100千米")
	private Double maxDistance = 100d; // 最远距离（单位千米）
	private Integer page = 1;
	private Integer pageSize = 20;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(Double maxDistance) {
		this.maxDistance = maxDistance;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
