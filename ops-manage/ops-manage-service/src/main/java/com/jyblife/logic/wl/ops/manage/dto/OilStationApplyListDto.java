package com.jyblife.logic.wl.ops.manage.dto;

public class OilStationApplyListDto extends RequestPager {

	private Search search;

	public Search getSearch() {
		if(search == null) {
			search = new Search();
		}
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	/**
	 * 定义搜索条件
	 */
	public class Search implements Searcher {

		private String name = "";
		private Integer companyId;
		private Integer companyStatus;
		private Integer stationId;
		private Integer status;
		

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getCompanyId() {
			return companyId;
		}

		public void setCompanyId(Integer companyId) {
			this.companyId = companyId;
		}

		public Integer getCompanyStatus() {
			return companyStatus;
		}

		public void setCompanyStatus(Integer companyStatus) {
			this.companyStatus = companyStatus;
		}

		public Integer getStationId() {
			return stationId;
		}

		public void setStationId(Integer stationId) {
			this.stationId = stationId;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

	}

}
