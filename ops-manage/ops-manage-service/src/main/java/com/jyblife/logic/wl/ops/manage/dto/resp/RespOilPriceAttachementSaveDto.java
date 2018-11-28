package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;
import com.jyblife.logic.wl.ops.common.json.FieldThousandsSerializer;
import com.jyblife.logic.wl.ops.entity.OilPriceItem;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public class RespOilPriceAttachementSaveDto {
    private String id;
    private String type;
    private String name;
    private Integer status;
    private String url;

    private List<RespOilPriceItem> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<RespOilPriceItem> getData() {
        return data;
    }

    public void setData(List<RespOilPriceItem> data) {
        this.data = data;
    }

    public static class RespOilPriceItem{

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
        @JSONField(serializeUsing = FieldThousandsSerializer.class)
        private BigDecimal retailPrice;
        @Min(value=(long) 0.01, message = "优惠价必须大于0元")
        @NotNull(message = "优惠价不能为空")
        @ExcelAttribute(name = "优惠价", column = "E")
        @JSONField(serializeUsing = FieldThousandsSerializer.class)
        private BigDecimal discountPrice;
        @Min(value=(long) 0.01, message = "协议价必须大于0元")
        @NotNull(message = "协议价不能为空")
        @ExcelAttribute(name = "协议价", column = "F")
        @JSONField(serializeUsing = FieldThousandsSerializer.class)
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
}
