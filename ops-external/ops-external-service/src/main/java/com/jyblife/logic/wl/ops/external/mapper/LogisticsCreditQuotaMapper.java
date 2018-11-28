package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.entity.LogisticsCreditQuota;

public interface LogisticsCreditQuotaMapper {
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
    int insert(LogisticsCreditQuota record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(LogisticsCreditQuota record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return LogisticsCreditQuota
     */
    LogisticsCreditQuota selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(LogisticsCreditQuota record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsCreditQuota record);

    LogisticsCreditQuota selectByLogisticsId(Integer logisticsId);
}