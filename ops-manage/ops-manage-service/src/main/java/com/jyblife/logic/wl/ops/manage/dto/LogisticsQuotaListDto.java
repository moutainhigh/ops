package com.jyblife.logic.wl.ops.manage.dto;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public class LogisticsQuotaListDto extends RequestPager {
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

        private Integer status;
        private String logisticsName;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
        }
        //用于导出
        public String getLogistics_name() {
            return logisticsName;
        }

        public void setLogistics_name(String logistics_name) {
            this.logisticsName = logistics_name;
        }
    }
}
