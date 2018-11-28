package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.entity.LogisticsQuota;

public interface LogisticsQuotaMapper {
    /**
     * deleteByPrimaryKey
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
    int insert(LogisticsQuota record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(LogisticsQuota record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return LogisticsQuota
     */
    LogisticsQuota selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(LogisticsQuota record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsQuota record);

    LogisticsQuota selectByLogisticsId(Integer logisticsId);
    
}