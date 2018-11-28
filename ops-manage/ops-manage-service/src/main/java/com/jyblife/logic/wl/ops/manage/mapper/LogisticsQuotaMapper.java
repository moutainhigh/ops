package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.LogisticsQuota;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsQuotaListDto;

import java.util.List;

public interface LogisticsQuotaMapper {
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


    List<RespLogisticsQuotaListDto.LogisticsQuotaItem> selectPageList(LogisticsQuotaListDto.Search search);
    
    LogisticsQuota selectByLogisticsId(Integer logisticsId);
}