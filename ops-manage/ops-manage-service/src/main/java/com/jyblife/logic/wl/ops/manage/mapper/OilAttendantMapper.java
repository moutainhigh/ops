package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilAttendant;
import com.jyblife.logic.wl.ops.manage.dto.OilAttendantListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilAttendantDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilAttendantListDto;

import java.util.List;

public interface OilAttendantMapper extends BaseMapper<OilAttendant,Integer> {
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
    int insert(OilAttendant record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilAttendant record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return OilAttendant
     */
    OilAttendant selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilAttendant record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilAttendant record);


    RespOilAttendantDto selectDetailById(Integer id);


    List<RespOilAttendantListDto.OilAttendantItem> selectPageList(OilAttendantListDto.Search search);
}