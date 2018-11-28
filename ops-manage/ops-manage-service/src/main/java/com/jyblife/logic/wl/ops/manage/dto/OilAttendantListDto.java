package com.jyblife.logic.wl.ops.manage.dto;

/**
 * @author yangcey
 * @date 2018/11/27
 **/
public class OilAttendantListDto  extends RequestPager {
    private Search search;

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public static class Search implements Searcher{

        /**
         * 姓名
         */
        private String name;

        /**
         * 手机号
         */
        private String phone;

        /**
         * 是否有效：1,有效；0,无效
         */
        private Byte status;

        /**
         * 加油站id
         */
        private Integer stationId;

        private String stationName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Byte getStatus() {
            return status;
        }

        public void setStatus(Byte status) {
            this.status = status;
        }

        public Integer getStationId() {
            return stationId;
        }

        public void setStationId(Integer stationId) {
            this.stationId = stationId;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }
    }
}
