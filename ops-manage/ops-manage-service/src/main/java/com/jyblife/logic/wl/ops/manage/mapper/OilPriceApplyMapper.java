package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilPriceApply;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceAuditListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceAuditListDto;

import java.util.List;

public interface OilPriceApplyMapper extends BaseMapper<OilPriceApply,Integer> {
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
    int insert(OilPriceApply record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilPriceApply record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param applyId
     * @return OilPriceApply
     */
    OilPriceApply selectByPrimaryKey(Integer applyId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilPriceApply record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilPriceApply record);

    int insertPriceApply(OilPriceApply record);

    List<RespOilPriceApplyListDto.OilPriceApplyItem> selectPageList(OilPriceApplyListDto.Search search);
    
    List<RespOilPriceAuditListDto.OilPriceAuditItem> selectAuditList(OilPriceAuditListDto.Search search);
    
}