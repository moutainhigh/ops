package com.jyblife.logic.wl.ops.manage.dto.resp;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public class RespDriverListDto extends RespPager<RespDriverListDto.DriverItem> {

    public static class DriverItem {
        private String driverId;
        private String customerId;
        private String logisticsId;
        private String name;
        private String status;
        private String phone;
        private String logisticsName;
        private String statusName;
        private String number;
        private String openLocation;
        private boolean isCanView;
        private boolean isCanEdit;

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public boolean getIsCanView() {
            return isCanView;
        }

        public void setIsCanView(boolean canView) {
            isCanView = canView;
        }

        public boolean getIsCanEdit() {
            return isCanEdit;
        }

        public void setIsCanEdit(boolean canEdit) {
            isCanEdit = canEdit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

        public String getOpenLocation() {
            return openLocation;
        }

        public void setOpenLocation(String openLocation) {
            this.openLocation = openLocation;
        }
    }
}
