package com.jyblife.logic.wl.ops.manage.dto;

public class DriverListDto  extends RequestPager  {
    private Search search;

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public static class Search implements Searcher {
        private String driverName;
        private Integer status;
        private String logisticsName;
        private String number;
        private Integer logisticsId;


        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

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

		public Integer getLogisticsId() {
			return logisticsId;
		}

		public void setLogisticsId(Integer logisticsId) {
			this.logisticsId = logisticsId;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

        //以下用于导出接收的数据属性 存在下划线转换的时候,应为是get queryString请求
        public String getDriver_name() {
            return driverName;
        }

        public void setDriver_name(String driver_name) {
            this.driverName = driver_name;
        }

        public String getLogistics_name() {
            return logisticsName;
        }

        public void setLogistics_name(String logistics_name) {
            this.logisticsName = logistics_name;
        }
    }
}
