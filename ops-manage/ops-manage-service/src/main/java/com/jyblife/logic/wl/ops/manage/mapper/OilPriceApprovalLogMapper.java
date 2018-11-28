package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilPriceApprovalLog;

import java.util.List;

public interface OilPriceApprovalLogMapper extends BaseMapper<OilPriceApprovalLog,Integer> {
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
    int insert(OilPriceApprovalLog record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilPriceApprovalLog record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return OilPriceApprovalLog
     */
    OilPriceApprovalLog selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilPriceApprovalLog record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilPriceApprovalLog record);


    List<OilPriceApprovalLog> selectByApplyId(Integer applyId);
}