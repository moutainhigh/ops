package com.jyblife.logic.wl.ops.external.mapper;

import org.apache.ibatis.annotations.Param;

import com.jyblife.logic.wl.ops.entity.CustomerWxRelation;

public interface CustomerWxRelationMapper {
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
    int insert(CustomerWxRelation record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(CustomerWxRelation record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return CustomerWxRelation
     */
    CustomerWxRelation selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CustomerWxRelation record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CustomerWxRelation record);

    Integer getCustomerIdByOpenId(String openId);
    
    CustomerWxRelation selectByCustomerId(@Param("customerId") Integer customerId);
    
}