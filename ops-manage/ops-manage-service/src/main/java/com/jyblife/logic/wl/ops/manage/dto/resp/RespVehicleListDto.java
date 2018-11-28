package com.jyblife.logic.wl.ops.manage.dto.resp;

public class RespVehicleListDto extends RespPager<RespVehicleListDto.VehicleItem> {

    public static class VehicleItem {
        private String vehicleId;
        private String logisticsId;
        private String number;
        private String model;
        private String capacity;
        private String logisticsName;
        private String stateName;
        private Integer state;
        private boolean isCanView = true;
        private boolean isCanEdit = false;

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
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

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }
    }
}
