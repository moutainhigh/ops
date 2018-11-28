package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilGoods;


public interface OilGoodsMapper extends BaseMapper<OilGoods,Integer> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param goodsId
     * @return int
     */
    int deleteByPrimaryKey(Integer goodsId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilGoods record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilGoods record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param goodsId
     * @return OilGoods
     */
    OilGoods selectByPrimaryKey(Integer goodsId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilGoods record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilGoods record);

    
}