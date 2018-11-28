package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.VehicleDailyQuotaLog;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaLogListDto;

import java.util.List;

public interface VehicleDailyQuotaLogMapper extends BaseMapper<VehicleDailyQuotaLog,Long> {
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
    int insert(VehicleDailyQuotaLog record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(VehicleDailyQuotaLog record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param logId
     * @return VehicleDailyQuotaLog
     */
    VehicleDailyQuotaLog selectByPrimaryKey(Long logId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(VehicleDailyQuotaLog record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(VehicleDailyQuotaLog record);


    List<RespVehicleQuotaLogListDto.VehicleQuotaLogItem> selectQuotaLogPageList(VehicleQuotaLogListDto.Search search);
}