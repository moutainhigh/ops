package com.jyblife.logic.wl.ops.external.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author yangcey
 * @date 2018/11/1
 **/
public class ReqOilCompanyOrderListDto extends ReqPager {
    @NotBlank(message = "油站编码不能为空")
    private String oilCode;//油站编码
    private String ordId;//订单号
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$",message = "时间格式不正确")
    private String ordCreateTime;//订单创建开始时间
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$",message = "时间格式不正确")
    private String ordCreateTimeEnd;//订单创建结束时间
    private String logiCode;//	物流企业编码
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$",message = "时间格式不正确")
    private String ordPayTime;//	订单交易开始时间
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$",message = "时间格式不正确")
    private String ordPayTimeEnd;//	订单交易结束时间
    private String plateInfo;//车牌号

    public String getOilCode() {
        return oilCode;
    }

    public void setOilCode(String oilCode) {
        this.oilCode = oilCode;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getOrdCreateTime() {
        return ordCreateTime;
    }

    public void setOrdCreateTime(String ordCreateTime) {
        this.ordCreateTime = ordCreateTime;
    }

    public String getOrdCreateTimeEnd() {
        return ordCreateTimeEnd;
    }

    public void setOrdCreateTimeEnd(String ordCreateTimeEnd) {
        this.ordCreateTimeEnd = ordCreateTimeEnd;
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
}
