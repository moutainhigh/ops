package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaLog;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsQuotaListDto;

import java.util.List;

public interface LogisticsQuotaLogMapper extends BaseMapper<LogisticsQuotaLog, Long> {
    /**
     * deleteByPrimaryKey
     *
     * @param logId
     * @return int
     */
    int deleteByPrimaryKey(Long logId);

    /**
     * insert
     *
     * @param record
     * @return int
     */
    int insert(LogisticsQuotaLog record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(LogisticsQuotaLog record);

    /**
     * selectByPrimaryKey
     *
     * @param logId
     * @return LogisticsQuotaLog
     */
    LogisticsQuotaLog selectByPrimaryKey(Long logId);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(LogisticsQuotaLog record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsQuotaLog record);


    List<RespLogisticsQuotaListDto.LogisticsQuotaItem> selectPageList(LogisticsQuotaLogListDto.Search search);
}