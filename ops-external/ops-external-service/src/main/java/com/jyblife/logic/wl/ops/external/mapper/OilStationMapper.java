package com.jyblife.logic.wl.ops.external.mapper;

import java.util.List;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilStation;
import com.jyblife.logic.wl.ops.external.dto.resp.RespEffectOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilStationInfoDto;

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
     * 获取油站信息
     * 
     * @param stationId
     * @return
     */
    RespOilStationInfoDto getOilStationInfoById(Integer stationId);
    
    /**
     * 获取所有有效的油站列表
     * @param record
     * @return
     */
    List<RespEffectOilStationListDto.OilStationItem> getEffectOilStationList(OilStation record);
    
    /**
     * 获取最常访问的油站
     * @param customerId
     * @return
     */
    OilStation getMostVisitStationByCustId(Integer customerId);
    
}