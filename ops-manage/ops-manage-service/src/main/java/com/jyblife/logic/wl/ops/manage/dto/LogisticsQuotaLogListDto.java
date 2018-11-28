package com.jyblife.logic.wl.ops.manage.dto;

import javax.validation.constraints.NotNull;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public class LogisticsQuotaLogListDto  extends RequestPager {
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

        private String category;
        private String createTimeEnd;
        private String createTimeStart;
        @NotNull(message = "物流企业id不能为空")
        private String logisticsId;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCreateTimeEnd() {
            return createTimeEnd;
        }

        public void setCreateTimeEnd(String createTimeEnd) {
            this.createTimeEnd = createTimeEnd;
        }

        public String getCreateTimeStart() {
            return createTimeStart;
        }

        public void setCreateTimeStart(String createTimeStart) {
            this.createTimeStart = createTimeStart;
        }

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }
    }
}