package com.jyblife.logic.wl.ops.manage.export;

import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ImportOilPriceVo {
	
    @NotEmpty(message = "油站名称不能为空")
    @ExcelAttribute(name = "油站名称", column = "A")
    private String oilStationName;
    @NotEmpty(message = "油企名称不能为空")
    @ExcelAttribute(name = "油企名称", column = "B")
    private String oilCompanyName;
    @NotEmpty(message = "油品名称不能为空")
    @ExcelAttribute(name = "油品名称", column = "C")
    private String goodsName;
    @Min(value=(long) 0.01, message = "零售价必须大于0元")
    @NotNull(message = "零售价不能为空")
    @ExcelAttribute(name = "零售价", column = "D")
    private BigDecimal retailPrice;
    @Min(value=(long) 0.01, message = "优惠价必须大于0元")
    @NotNull(message = "优惠价不能为空")
    @ExcelAttribute(name = "优惠价", column = "E")
    private BigDecimal discountPrice;
    @Min(value=(long) 0.01, message = "协议价必须大于0元")
    @NotNull(message = "协议价不能为空")
    @ExcelAttribute(name = "协议价", column = "F")
    private BigDecimal agreedPrice;
    @ExcelAttribute(name = "备注", column = "G")
    private String remark;

    public String getOilStationName() {
        return oilStationName;
    }

    public void setOilStationName(String oilStationName) {
        this.oilStationName = oilStationName;
    }

    public String getOilCompanyName() {
        return oilCompanyName;
    }

    public void setOilCompanyName(String oilCompanyName) {
        this.oilCompanyName = oilCompanyName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getAgreedPrice() {
        return agreedPrice;
    }

    public void setAgreedPrice(BigDecimal agreedPrice) {
        this.agreedPrice = agreedPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
