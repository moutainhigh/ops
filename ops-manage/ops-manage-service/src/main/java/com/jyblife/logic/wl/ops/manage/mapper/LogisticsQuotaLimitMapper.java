package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsLimitListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsLimitDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsLimitListDto;
import java.util.List;

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


    List<RespLogisticsLimitListDto.LogisticsLimitItem> selectPageList(LogisticsLimitListDto.Search search);


    RespLogisticsLimitDetailDto selectCurrentDetail();
    
    /**
     * 查询当前最新
     * @return
     */
    LogisticsQuotaLimit selectCurrent();
}