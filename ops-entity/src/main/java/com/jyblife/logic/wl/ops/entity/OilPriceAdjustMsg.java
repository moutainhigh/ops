package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_oil_price_adjust_msg")
public class OilPriceAdjustMsg {
    /**
     * 
     */
    @Id
    private Integer id;

    /**
     * 油价调整的内容
     */
    private String content;

    /**
     * 关联t_oil_price表字段price_id
     */
    private Integer priceId;

    /**
     * 油企id
     */
    private Integer companyId;

    /**
     * 油站id
     */
    private Integer stationId;

    /**
     * 生效时间
     */
    private Date effectTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 油价调整的内容
     * @return content 油价调整的内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 油价调整的内容
     * @param content 油价调整的内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 关联t_oil_price表字段price_id
     * @return price_id 关联t_oil_price表字段price_id
     */
    public Integer getPriceId() {
        return priceId;
    }

    /**
     * 关联t_oil_price表字段price_id
     * @param priceId 关联t_oil_price表字段price_id
     */
    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    /**
     * 油企id
     * @return company_id 油企id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 油企id
     * @param companyId 油企id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 油站id
     * @return station_id 油站id
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * 油站id
     * @param stationId 油站id
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
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
}