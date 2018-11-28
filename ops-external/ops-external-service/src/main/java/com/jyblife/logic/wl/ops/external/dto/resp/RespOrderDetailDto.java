package com.jyblife.logic.wl.ops.external.dto.resp;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author yangcey
 * @date 2018/9/28
 **/
public class RespOrderDetailDto {
    private String orderId;
    private String code;
    private String status;
    private String statusDesc;
    //油品总销售价
    private Integer sellAmount;
    //油品总采购价
    private Integer buyAmount;
    private String quantity;
    private String retailPrice;
    private String agreedPrice;
    private String discountPrice;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectTime;
    private String failedReason;
    private String orderType;
    private String remark;

    private GoodsItem goods;
    private CustomerItem customer;
    private LogisticsItem logistics;
    private VehicleItem vehicle;
    private OilStationItem oilStation;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(Integer sellAmount) {
        this.sellAmount = sellAmount;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getAgreedPrice() {
        return agreedPrice;
    }

    public void setAgreedPrice(String agreedPrice) {
        this.agreedPrice = agreedPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public GoodsItem getGoods() {
        return goods;
    }

    public void setGoods(GoodsItem goods) {
        this.goods = goods;
    }

    public CustomerItem getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerItem customer) {
        this.customer = customer;
    }

    public LogisticsItem getLogistics() {
        return logistics;
    }

    public void setLogistics(LogisticsItem logistics) {
        this.logistics = logistics;
    }

    public VehicleItem getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleItem vehicle) {
        this.vehicle = vehicle;
    }

    public OilStationItem getOilStation() {
        return oilStation;
    }

    public void setOilStation(OilStationItem oilStation) {
        this.oilStation = oilStation;
    }

    public static class GoodsItem {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CustomerItem {
        private String id;
        private String name;
        private String phone;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LogisticsItem {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class VehicleItem {
        private String id;
        private String number;
        private String model;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    }

    public static class OilStationItem {
        private String id;
        private String name;
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
