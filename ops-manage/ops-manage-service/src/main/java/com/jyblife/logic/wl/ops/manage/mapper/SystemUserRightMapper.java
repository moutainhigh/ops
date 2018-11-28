package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.SystemUserRight;

public interface SystemUserRightMapper {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param userId
     * @return int
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(SystemUserRight record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(SystemUserRight record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param userId
     * @return SystemUserRight
     */
    SystemUserRight selectByPrimaryKey(Integer userId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(SystemUserRight record);

    /**
     * updateByPrimaryKeyWithBLOBs
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(SystemUserRight record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(SystemUserRight record);
}