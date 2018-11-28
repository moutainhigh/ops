package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;

import com.jyblife.logic.wl.ops.entity.AreaCode;
import com.jyblife.logic.wl.ops.manage.dto.AreaCodeDto;

public interface AreaCodeMapper {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param areaCode
     * @return int
     */
    int deleteByPrimaryKey(Integer areaCode);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(AreaCode record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(AreaCode record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param areaCode
     * @return AreaCode
     */
    AreaCode selectByPrimaryKey(Integer areaCode);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(AreaCode record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(AreaCode record);


    AreaCodeDto selectTreeCode(Integer areaCode);
    
    List<AreaCode> selectAll();
}