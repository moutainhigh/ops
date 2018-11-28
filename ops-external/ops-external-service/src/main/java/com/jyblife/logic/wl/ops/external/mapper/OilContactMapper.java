package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.entity.OilContact;

public interface OilContactMapper {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param contactId
     * @return int
     */
    int deleteByPrimaryKey(Integer contactId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilContact record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilContact record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param contactId
     * @return OilContact
     */
    OilContact selectByPrimaryKey(Integer contactId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilContact record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilContact record);
    
    /**
     * 获取油站联系人
     * @param record
     * @return
     */
    OilContact getByStationIdAndType(OilContact record);
    
}