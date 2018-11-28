package com.jyblife.logic.wl.ops.manage.dto.resp;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public class RespVehicleQuotaListDto extends RespPager<RespVehicleQuotaListDto.VehicleQuotaItem> {

    public static class VehicleQuotaItem {
        private String vehicleId;
        private String logisticsName;
        private String number;
        private String capacity;
        private String usedQuota;
        private String frozenQuota;
        private String rate;
        private String dailyCapacity;
        private String dailyAvailableCapacity;
        private String stateName;
        private Integer state;

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
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

        public String getUsedQuota() {
            return usedQuota;
        }

        public void setUsedQuota(String usedQuota) {
            this.usedQuota = usedQuota;
        }

        public String getFrozenQuota() {
            return frozenQuota;
        }

        public void setFrozenQuota(String frozenQuota) {
            this.frozenQuota = frozenQuota;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDailyCapacity() {
            return dailyCapacity;
        }

        public void setDailyCapacity(String dailyCapacity) {
            this.dailyCapacity = dailyCapacity;
        }

        public String getDailyAvailableCapacity() {
            return dailyAvailableCapacity;
        }

        public void setDailyAvailableCapacity(String dailyAvailableCapacity) {
            this.dailyAvailableCapacity = dailyAvailableCapacity;
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
