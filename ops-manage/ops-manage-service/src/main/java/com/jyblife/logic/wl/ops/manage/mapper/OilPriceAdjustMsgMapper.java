package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilPriceAdjustMsg;

public interface OilPriceAdjustMsgMapper extends BaseMapper<OilPriceAdjustMsg,Integer> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilPriceAdjustMsg record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilPriceAdjustMsg record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return OilPriceAdjustMsg
     */
    OilPriceAdjustMsg selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilPriceAdjustMsg record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilPriceAdjustMsg record);
    
    /**
     * 立即执行价格
     * @param priceId
     * @return
     */
    int executeByPriceId(OilPriceAdjustMsg record);
}