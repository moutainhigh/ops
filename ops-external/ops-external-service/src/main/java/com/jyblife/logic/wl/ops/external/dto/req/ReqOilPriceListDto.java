package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

public class ReqOilPriceListDto extends ReqPager {

	private ReqOilPriceListDto.Search search;

	public ReqOilPriceListDto.Search getSearch() {
		return search;
	}

	public void setSearch(ReqOilPriceListDto.Search search) {
		this.search = search;
	}

	public class Search {

		@NotNull(message = "加油站编号stationId不能为空")
		private Integer stationId; //油站编号
		private String status; // 价格状态 1-待生效，2-已生效，3已失效
		private String effectStartTime; // 启用时间
		private String effectEndTime; // 启用时间
		private String unEffectStartTime; // 失效时间
		private String unEffectEndTime; // 失效时间

		public Integer getStationId() {
			return stationId;
		}

		public void setStationId(Integer stationId) {
			this.stationId = stationId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getEffectStartTime() {
			return effectStartTime;
		}

		public void setEffectStartTime(String effectStartTime) {
			this.effectStartTime = effectStartTime;
		}

		public String getEffectEndTime() {
			return effectEndTime;
		}

		public void setEffectEndTime(String effectEndTime) {
			this.effectEndTime = effectEndTime;
		}

		public String getUnEffectStartTime() {
			return unEffectStartTime;
		}

		public void setUnEffectStartTime(String unEffectStartTime) {
			this.unEffectStartTime = unEffectStartTime;
		}

		public String getUnEffectEndTime() {
			return unEffectEndTime;
		}

		public void setUnEffectEndTime(String unEffectEndTime) {
			this.unEffectEndTime = unEffectEndTime;
		}

	}
}
