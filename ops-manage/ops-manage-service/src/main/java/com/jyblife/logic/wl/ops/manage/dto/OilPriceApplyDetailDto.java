package com.jyblife.logic.wl.ops.manage.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.jyblife.logic.wl.ops.common.excel.ExcelAttribute;
import com.jyblife.logic.wl.ops.common.json.FieldThousandsSerializer;
import com.jyblife.logic.wl.ops.entity.OilPriceApprovalLog;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/10/29
 **/
public class OilPriceApplyDetailDto {

    //价格列表
    List<OilPriceItem> priceItems;

    //审批记录
    List<OilPriceApproval> priceApprovalItems;

    public List<OilPriceItem> getPriceItems() {
        return priceItems;
    }

    public void setPriceItems(List<OilPriceItem> priceItems) {
        this.priceItems = priceItems;
    }

    public List<OilPriceApproval> getPriceApprovalItems() {
        return priceApprovalItems;
    }

    public void setPriceApprovalItems(List<OilPriceApproval> priceApprovalItems) {
        this.priceApprovalItems = priceApprovalItems;
    }

    public static class OilPriceItem{
        private Integer applyId;

        private String oilStationName;

        private String oilCompanyName;

        private String goodsName;
        @JSONField(serializeUsing = FieldThousandsSerializer.class)
        private BigDecimal retailPrice;
        @JSONField(serializeUsing = FieldThousandsSerializer.class)
        private BigDecimal discountPrice;
        @JSONField(serializeUsing = FieldThousandsSerializer.class)
        private BigDecimal agreedPrice;

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

        public Integer getApplyId() {
            return applyId;
        }

        public void setApplyId(Integer applyId) {
            this.applyId = applyId;
        }
    }

    public static class OilPriceApproval{
        private Integer id;

        /**
         * 申请id
         */
        private Integer applyId;

        /**
         * 审批人id
         */
        private Integer operatorId;

        /**
         * 审批人姓名
         */
        private String operatorName;

        /**
         * 审批时间
         */
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;

        /**
         * 审批动作：7-同意，3-驳回
         */
        private Integer action;

        private String actionName;

        /**
         * 审批建议
         */
        private String remark;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getApplyId() {
            return applyId;
        }

        public void setApplyId(Integer applyId) {
            this.applyId = applyId;
        }

        public Integer getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(Integer operatorId) {
            this.operatorId = operatorId;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Integer getAction() {
            return action;
        }

        public void setAction(Integer action) {
            this.action = action;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }
    }
}
