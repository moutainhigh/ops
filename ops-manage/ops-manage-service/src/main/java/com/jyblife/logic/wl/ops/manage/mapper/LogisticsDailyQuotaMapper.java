package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.LogisticsDailyQuota;

public interface LogisticsDailyQuotaMapper {
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
    int insert(LogisticsDailyQuota record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(LogisticsDailyQuota record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return LogisticsDailyQuota
     */
    LogisticsDailyQuota selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(LogisticsDailyQuota record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsDailyQuota record);
    
    /**
     * 根据物流企业编号获取当天额度信息
     * @param logisticsId
     * @return
     */
    LogisticsDailyQuota selectCurdateByLogisticsId(Integer logisticsId);
    
    /**
     * 新增并返回主键
     * @param record
     * @return
     */
    int insertLogisticsDailyQuota(LogisticsDailyQuota record);
    
}