package com.jyblife.logic.wl.ops.manage.dto;

public class OilCompanyListDto extends RequestPager {

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
	 * 搜索条件
	 */
	public class Search implements Searcher {

		private String name = "";
		private String status = "";

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}

}
