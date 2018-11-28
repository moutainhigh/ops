package com.jyblife.logic.wl.ops.manage.dto;

import org.apache.commons.lang3.StringUtils;

import com.jyblife.logic.wl.ops.common.utils.DateUtil;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public class OrderListDto extends RequestPager {

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

        private String code;//订单编号
        private String createEndTime;//
        private String createStartTime;//
        private String customerName;//司机姓名
        private String customerPhone;//司机电话号码
        private String logisticsName;//物流公司
        private String status;//状态 "-1":交易失败，0:新建，10:成功
        private String vehicleNumber;//车牌号

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreateEndTime() {
        	if(StringUtils.isNoneBlank(createEndTime)) {
        		createEndTime = createEndTime + DateUtil.END_TIME;
        	}
            return createEndTime;
        }

        public void setCreateEndTime(String createEndTime) {
            this.createEndTime = createEndTime;
        }

        public String getCreateStartTime() {
        	if(StringUtils.isNoneBlank(createStartTime)) {
        		createStartTime = createStartTime + DateUtil.START_TIME;
        	}
            return createStartTime;
        }

        public void setCreateStartTime(String createStartTime) {
            this.createStartTime = createStartTime;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }
    }
}