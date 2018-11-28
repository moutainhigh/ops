package com.jyblife.logic.wl.ops.manage.dto;

/**
 * @author yangcey
 * @date 2018/9/20
 **/
public class OilGoodsListDto  extends RequestPager {

    private Search search;

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    /**
     * 定义搜索条件
     */
    public class Search implements Searcher {

        private String name;
        private Integer status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

    }
}
