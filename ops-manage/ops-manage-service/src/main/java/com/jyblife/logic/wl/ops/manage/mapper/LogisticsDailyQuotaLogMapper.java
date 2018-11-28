package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.LogisticsDailyQuotaLog;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsDailyQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsDailyQuotaLogListDto;

import java.util.List;

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


    List<RespLogisticsDailyQuotaLogListDto.LogisticsDailyQuotaItem> selectPageList(LogisticsDailyQuotaLogListDto.Search search);
}