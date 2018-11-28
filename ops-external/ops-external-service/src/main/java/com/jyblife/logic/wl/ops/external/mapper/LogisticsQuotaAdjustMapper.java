package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaAdjust;

public interface LogisticsQuotaAdjustMapper extends BaseMapper<LogisticsQuotaAdjust,Integer> {
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
    int insert(LogisticsQuotaAdjust record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(LogisticsQuotaAdjust record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return LogisticsQuotaAdjust
     */
    LogisticsQuotaAdjust selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(LogisticsQuotaAdjust record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsQuotaAdjust record);
}