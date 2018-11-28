package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.VehicleDailyQuota;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VehicleDailyQuotaMapper extends BaseMapper<VehicleDailyQuota, Integer> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(VehicleDailyQuota record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(VehicleDailyQuota record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return VehicleDailyQuota
     */
    VehicleDailyQuota selectByPrimaryKey(Long id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(VehicleDailyQuota record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(VehicleDailyQuota record);
    
    /**
     * 根据vehicleId获取当天消费情况
     * @param vehicleId
     * @return
     */
    VehicleDailyQuota selectCurDateByVehicleId(Integer vehicleId);
    
    int insertVehicleDailyQuota(VehicleDailyQuota record);
    
}