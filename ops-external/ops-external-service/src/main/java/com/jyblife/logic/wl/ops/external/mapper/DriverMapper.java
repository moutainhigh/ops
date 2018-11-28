package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.entity.Driver;

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
    
    Driver selectByPhone(String phone);
    
    Driver selectByCustomerId(Integer customerId);
    
    /**
     * 新增返回主键
     *
     * @param record
     * @return int
     */
    int insertDriver(Driver record);
    
    int updateDriverByCustomerId(Driver driver);

}