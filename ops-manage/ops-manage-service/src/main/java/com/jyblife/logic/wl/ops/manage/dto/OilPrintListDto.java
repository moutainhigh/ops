package com.jyblife.logic.wl.ops.manage.dto;

public class OilPrintListDto extends RequestPager {

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

		private String printSn;
		
		public String getPrintSn() {
			return printSn;
		}
		public void setPrintSn(String printSn) {
			this.printSn = printSn;
		}

	}

}
