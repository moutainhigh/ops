package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public class RespLogisticsQuotaListDto extends RespPager<RespLogisticsQuotaListDto.LogisticsQuotaItem> {

    public static class LogisticsQuotaItem{
        private String id;
        private String logisticsId;
        private String logisticsName;
        private String usedQuota;
        private String frozenQuota;
        private String creditQuota;
        private Date startDate;
        private Date  endDate;
        private Integer status;
        private String statusName;
        private String rate;
        private String dayUsedQuota;
        private String dayFrozenQuota;
        private String availableQuota;
        private String dailyCreditQuota;
        private String dailyAvailableQuota;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
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

        public String getCreditQuota() {
            return creditQuota;
        }

        public void setCreditQuota(String creditQuota) {
            this.creditQuota = creditQuota;
        }
        @JSONField(format = "yyyy-MM-dd")
        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
        @JSONField(format = "yyyy-MM-dd")
        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public String getDayUsedQuota() {
            return dayUsedQuota;
        }

        public void setDayUsedQuota(String dayUsedQuota) {
            this.dayUsedQuota = dayUsedQuota;
        }

        public String getDayFrozenQuota() {
            return dayFrozenQuota;
        }

        public void setDayFrozenQuota(String dayFrozenQuota) {
            this.dayFrozenQuota = dayFrozenQuota;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getAvailableQuota() {
            return availableQuota;
        }

        public void setAvailableQuota(String availableQuota) {
            this.availableQuota = availableQuota;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDailyCreditQuota() {
            return dailyCreditQuota;
        }

        public void setDailyCreditQuota(String dailyCreditQuota) {
            this.dailyCreditQuota = dailyCreditQuota;
        }

        public String getDailyAvailableQuota() {
            return dailyAvailableQuota;
        }

        public void setDailyAvailableQuota(String dailyAvailableQuota) {
            this.dailyAvailableQuota = dailyAvailableQuota;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }
}
