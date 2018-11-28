package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilGoods;
import com.jyblife.logic.wl.ops.manage.dto.OilGoodsListDto;

import java.util.List;

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

    /**
     * 条件查询
     * @param search
     * @return
     */
    List<OilGoods> listOilPage(OilGoodsListDto.Search search);

    /**
     *
     */
    OilGoods selectByName(String name);
}