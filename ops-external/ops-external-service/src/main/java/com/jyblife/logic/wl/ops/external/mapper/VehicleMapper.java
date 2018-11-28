package com.jyblife.logic.wl.ops.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyblife.logic.wl.ops.entity.Vehicle;
import com.jyblife.logic.wl.ops.external.dto.resp.RespDriverVehicleInfoDto;

public interface VehicleMapper {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param vehicleId
     * @return int
     */
    int deleteByPrimaryKey(Integer vehicleId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(Vehicle record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(Vehicle record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param vehicleId
     * @return Vehicle
     */
    Vehicle selectByPrimaryKey(Integer vehicleId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Vehicle record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Vehicle record);
    
    int insertVehicle(Vehicle record);
    
    Vehicle selectByNumber(String number);
    
    List<Vehicle> selectByIds(@Param("vehicleIds") List<Integer> vehicleIds);
    
    /**
     * 根据司机编号获取车辆信息
     * @param driverId
     * @return
     */
    List<RespDriverVehicleInfoDto.Vehicle> selectByDriverId(Integer driverId);


    /**
     * 根据司机编号获取车辆信息
     * @param driverId
     * @return
     */
    List<RespDriverVehicleInfoDto.Vehicle> selectByDriverIdAndState(@Param("driverId") Integer driverId,@Param("state")Integer state);

}