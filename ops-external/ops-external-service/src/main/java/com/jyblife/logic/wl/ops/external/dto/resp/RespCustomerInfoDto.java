package com.jyblife.logic.wl.ops.external.dto.resp;

/**
 * @author yangcey
 * @date 2018/9/28
 **/
public class RespCustomerInfoDto {
    private CustomerItem customer;
    private CompanyItem company;

    public CustomerItem getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerItem customer) {
        this.customer = customer;
    }

    public CompanyItem getCompany() {
        return company;
    }

    public void setCompany(CompanyItem company) {
        this.company = company;
    }

    public static class CustomerItem {
        private String name;
        private String customerId;
        private String phone;
        private String status;
        private Integer openLocation;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getOpenLocation() {
            return openLocation;
        }

        public void setOpenLocation(Integer openLocation) {
            this.openLocation = openLocation;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class CompanyItem {
        private String logisticsId;
        private String name;
        private String status;

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
