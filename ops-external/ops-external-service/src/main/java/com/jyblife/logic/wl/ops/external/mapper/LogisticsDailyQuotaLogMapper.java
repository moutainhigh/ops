package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.entity.LogisticsDailyQuotaLog;

public interface LogisticsDailyQuotaLogMapper {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param logId
     * @return int
     */
    int deleteByPrimaryKey(Long logId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(LogisticsDailyQuotaLog record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(LogisticsDailyQuotaLog record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param logId
     * @return LogisticsDailyQuotaLog
     */
    LogisticsDailyQuotaLog selectByPrimaryKey(Long logId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(LogisticsDailyQuotaLog record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsDailyQuotaLog record);

}