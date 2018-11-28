package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyblife.logic.wl.ops.entity.OilStationApply;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyListDto;

public interface OilStationApplyMapper {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param applyId
     * @return int
     */
    int deleteByPrimaryKey(Integer applyId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilStationApply record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilStationApply record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param applyId
     * @return OilStationApply
     */
    OilStationApply selectByPrimaryKey(Integer applyId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilStationApply record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilStationApply record);
    
    /**
     * 列表分页查询
     * @param search
     * @return
     */
    List<RepOilStationApplyListDto.OilStationApplyItem> listPage(OilStationApplyListDto.Search search);
    
    /**
     * 保存并返回主键
     * @param record
     * @return
     */
    int insertStationApply(OilStationApply record);
    
    /**
     * 查看
     * @return
     */
    RepOilStationApplyDetailDto selectByApplyId(Integer applyId);
    
    List<OilStationApply> selectByName(@Param("name") String name, @Param("companyId") Integer companyId);
    
}