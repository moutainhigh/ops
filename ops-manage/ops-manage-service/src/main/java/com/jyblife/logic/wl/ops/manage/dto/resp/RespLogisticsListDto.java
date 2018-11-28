package com.jyblife.logic.wl.ops.manage.dto.resp;

public class RespLogisticsListDto extends RespPager<RespLogisticsListDto.LogisticsItem> {

    public static class LogisticsItem {
        private String logisticsId;
        private String outStatus;
        private String status;
        private String statusName;
        private String name;
        private String outStatusName;
        private boolean isCanView;
        private boolean isCanEdit;

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }

        public String getOutStatus() {
            return outStatus;
        }

        public void setOutStatus(String outStatus) {
            this.outStatus = outStatus;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOutStatusName() {
            return outStatusName;
        }

        public void setOutStatusName(String outStatusName) {
            this.outStatusName = outStatusName;
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

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }
}
