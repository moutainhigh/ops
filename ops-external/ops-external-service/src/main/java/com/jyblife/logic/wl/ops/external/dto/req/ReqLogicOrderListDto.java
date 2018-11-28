package com.jyblife.logic.wl.ops.external.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author yangcey
 * @date 2018/10/30
 **/
public class ReqLogicOrderListDto extends ReqPager {
    private String ordId;
    @NotBlank(message = "物流企业编码不能为空")
    private String logiCode;//	物流企业编码	varchar
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$",message = "时间格式不正确")
    private String ordPayTime;//	订单交易开始时间	datetime
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$",message = "时间格式不正确")
    private String ordPayTimeEnd;//	订单交易结束时间	datetime
    private String plateInfo;//	车牌号	varchar
    private String customerName;//	司机名称	varchar
    private String carModel;//	车型	varchar

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getLogiCode() {
        return logiCode;
    }

    public void setLogiCode(String logiCode) {
        this.logiCode = logiCode;
    }

    public String getOrdPayTime() {
        return ordPayTime;
    }

    public void setOrdPayTime(String ordPayTime) {
        this.ordPayTime = ordPayTime;
    }

    public String getOrdPayTimeEnd() {
        return ordPayTimeEnd;
    }

    public void setOrdPayTimeEnd(String ordPayTimeEnd) {
        this.ordPayTimeEnd = ordPayTimeEnd;
    }

    public String getPlateInfo() {
        return plateInfo;
    }

    public void setPlateInfo(String plateInfo) {
        this.plateInfo = plateInfo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
