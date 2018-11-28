package com.jyblife.logic.wl.ops.manage.dto;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public class LogisticsDailyQuotaLogListDto extends RequestPager {
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
    public static class Search implements Searcher {

        private String logisticsId;

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }
    }
}
