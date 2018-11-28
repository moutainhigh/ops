package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.Driver;
import com.jyblife.logic.wl.ops.manage.dto.DriverListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverListDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverMapper {
    /**
     * deleteByPrimaryKey
     *
     * @param driverId
     * @return int
     */
    int deleteByPrimaryKey(Integer driverId);

    /**
     * insert
     *
     * @param record
     * @return int
     */
    int insert(Driver record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(Driver record);

    /**
     * selectByPrimaryKey
     *
     * @param driverId
     * @return Driver
     */
    Driver selectByPrimaryKey(Integer driverId);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Driver record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Driver record);


    RespDriverDetailDto selectDetailByCustomerId(Integer customerId);

    List<RespDriverListDto.DriverItem> selectPageList(DriverListDto.Search search);
    
    Driver selectByCustomerId(Integer customerId);


    int updateOpenLocationById(@Param("driverId") Integer driverId, @Param("openLocation") Byte openLocation);
}