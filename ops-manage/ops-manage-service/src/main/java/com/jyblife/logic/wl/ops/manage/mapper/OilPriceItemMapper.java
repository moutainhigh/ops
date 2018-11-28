package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilPriceItem;

import java.util.List;

public interface OilPriceItemMapper extends BaseMapper<OilPriceItem,Integer> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param itemId
     * @return int
     */
    int deleteByPrimaryKey(Integer itemId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilPriceItem record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilPriceItem record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param itemId
     * @return OilPriceItem
     */
    OilPriceItem selectByPrimaryKey(Integer itemId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilPriceItem record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilPriceItem record);
    
    OilPriceItem selectByOilPriceItem(OilPriceItem record);

    List<OilPriceItem> selectByApplyId(Integer applyId);

    void deleteByApplyId(Integer applyId);
}