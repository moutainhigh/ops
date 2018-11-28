package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.Customer;

public interface CustomerMapper {
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
    int insert(Customer record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(Customer record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return Customer
     */
    Customer selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Customer record);
}