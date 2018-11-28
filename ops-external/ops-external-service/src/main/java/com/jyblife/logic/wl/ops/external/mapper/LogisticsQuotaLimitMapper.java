package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaLimit;

public interface LogisticsQuotaLimitMapper extends BaseMapper<LogisticsQuotaLimit,Integer> {
    /**
     * deleteByPrimaryKey
     *
     * @param limitId
     * @return int
     */
    int deleteByPrimaryKey(Integer limitId);

    /**
     * insert
     *
     * @param record
     * @return int
     */
    int insert(LogisticsQuotaLimit record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(LogisticsQuotaLimit record);

    /**
     * selectByPrimaryKey
     *
     * @param limitId
     * @return LogisticsQuotaLimit
     */
    LogisticsQuotaLimit selectByPrimaryKey(Integer limitId);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(LogisticsQuotaLimit record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsQuotaLimit record);

    /**
     * 查询当前最新
     * @return
     */
    LogisticsQuotaLimit selectCurrent();
    
}