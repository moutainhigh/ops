package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilStation;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationDetailDto;

public interface OilStationMapper extends BaseMapper<OilStation,Integer> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param stationId
     * @return int
     */
    int deleteByPrimaryKey(Integer stationId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilStation record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilStation record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param stationId
     * @return OilStation
     */
    OilStation selectByPrimaryKey(Integer stationId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilStation record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilStation record);
    
    /**
     * 列表分页查询
     * @param search
     * @return
     */
    List<RepOilStationApplyListDto.OilStationApplyItem> listPage(OilStationApplyListDto.Search search);
    
    /**
     * 查看
     * @return
     */
    RepOilStationDetailDto selectByStationId(Integer stationId);
    
    /**
     * 油站下拉list
     */
    List<OilStation> oilStationList();
}