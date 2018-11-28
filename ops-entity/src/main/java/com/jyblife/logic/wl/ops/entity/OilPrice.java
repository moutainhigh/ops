package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;
@Table("t_oil_price")
public class OilPrice {
    /**
     * 标识
     */
    @Id
    private Integer priceId;

    /**
     * 申请id
     */
    private Integer applyId;

    /**
     * 申请item
     */
    private Integer itemId;

    /**
     * 油企
     */
    private Integer companyId;

    /**
     * 油站
     */
    private Integer stationId;

    /**
     * 油品
     */
    private Integer goodsId;

    /**
     * 零售价
     */
    private BigDecimal retailPrice;

    /**
     * 协议价
     */
    private BigDecimal agreedPrice;

    /**
     * 优惠价
     */
    private BigDecimal discountPrice;

    /**
     * 生效时间
     */
    private Date effectTime;

    /**
     * 失效时间
     */
    private Date endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 状态时间
     */
    private Date statusTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户
     */
    private Integer updateUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建用户
     */
    private Integer createUserId;

    /**
     * 标识
     * @return price_id 标识
     */
    public Integer getPriceId() {
        return priceId;
    }

    /**
     * 标识
     * @param priceId 标识
     */
    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    /**
     * 申请id
     * @return apply_id 申请id
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * 申请id
     * @param applyId 申请id
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 申请item
     * @return item_id 申请item
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 申请item
     * @param itemId 申请item
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 油企
     * @return company_id 油企
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 油企
     * @param companyId 油企
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 油站
     * @return station_id 油站
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * 油站
     * @param stationId 油站
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * 油品
     * @return goods_id 油品
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 油品
     * @param goodsId 油品
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 零售价
     * @return retail_price 零售价
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 零售价
     * @param retailPrice 零售价
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 协议价
     * @return agreed_price 协议价
     */
    public BigDecimal getAgreedPrice() {
        return agreedPrice;
    }

    /**
     * 协议价
     * @param agreedPrice 协议价
     */
    public void setAgreedPrice(BigDecimal agreedPrice) {
        this.agreedPrice = agreedPrice;
    }

    /**
     * 优惠价
     * @return discount_price 优惠价
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 优惠价
     * @param discountPrice 优惠价
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 生效时间
     * @return effect_time 生效时间
     */
    public Date getEffectTime() {
        return effectTime;
    }

    /**
     * 生效时间
     * @param effectTime 生效时间
     */
    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    /**
     * 失效时间
     * @return end_time 失效时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 失效时间
     * @param endTime 失效时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 状态时间
     * @return status_time 状态时间
     */
    public Date getStatusTime() {
        return statusTime;
    }

    /**
     * 状态时间
     * @param statusTime 状态时间
     */
    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新用户
     * @return update_user_id 更新用户
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新用户
     * @param updateUserId 更新用户
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建用户
     * @return create_user_id 创建用户
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建用户
     * @param createUserId 创建用户
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}