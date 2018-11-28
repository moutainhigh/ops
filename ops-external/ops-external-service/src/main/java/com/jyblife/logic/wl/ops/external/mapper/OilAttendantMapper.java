package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilAttendant;

public interface OilAttendantMapper extends BaseMapper<OilAttendant,Integer> {
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
    int insert(OilAttendant record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilAttendant record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return OilAttendant
     */
    OilAttendant selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilAttendant record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilAttendant record);
}