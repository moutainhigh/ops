package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.OilPhone;

public interface OilPhoneMapper {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param phoneId
     * @return int
     */
    int deleteByPrimaryKey(Integer phoneId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilPhone record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilPhone record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param phoneId
     * @return OilPhone
     */
    OilPhone selectByPrimaryKey(Integer phoneId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilPhone record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilPhone record);
}