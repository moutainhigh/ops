package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.Vehicle;
import com.jyblife.logic.wl.ops.manage.dto.VehicleListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleListDto;

import java.util.List;

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


    List<RespVehicleListDto.VehicleItem> selectPageList(VehicleListDto.Search search);


    RespVehicleDetailDto selectDetailById(Integer vehicleId);
}