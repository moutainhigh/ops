package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.DriverVehicleRelation;

import java.util.List;
import java.util.Map;

public interface DriverVehicleRelationMapper extends BaseMapper<DriverVehicleRelation, Integer> {
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
    int insert(DriverVehicleRelation record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(DriverVehicleRelation record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return DriverVehicleRelation
     */
    DriverVehicleRelation selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(DriverVehicleRelation record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(DriverVehicleRelation record);

    /**
     *
     * @param driverId
     * @return
     */
    List<Integer> selectVehicleIdByDriverId(Integer driverId);
    
    void deleteByDriverId(Integer driverId);
    
    /**
     * 根据车辆id及司机id获取
     * @param params
     * @return
     */
    DriverVehicleRelation getByVehicleIdAndDriverId(Map<String, Object> params);
}