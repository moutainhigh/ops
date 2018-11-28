package com.jyblife.logic.wl.ops.manage.dto;

public class VehicleQuotaLogListDto extends RequestPager {
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
        private String vehicleId;
        private String createTimeStart;
        private String createTimeEnd;

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getCreateTimeStart() {
            return createTimeStart;
        }

        public void setCreateTimeStart(String createTimeStart) {
            this.createTimeStart = createTimeStart;
        }

        public String getCreateTimeEnd() {
            return createTimeEnd;
        }

        public void setCreateTimeEnd(String createTimeEnd) {
            this.createTimeEnd = createTimeEnd;
        }
    }
}