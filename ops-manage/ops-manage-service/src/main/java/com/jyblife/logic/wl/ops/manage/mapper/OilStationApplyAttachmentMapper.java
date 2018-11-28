package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilStationApplyAttachment;

public interface OilStationApplyAttachmentMapper extends BaseMapper<OilStationApplyAttachment, Integer>{
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
    int insert(OilStationApplyAttachment record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilStationApplyAttachment record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return OilStationApplyAttachment
     */
    OilStationApplyAttachment selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilStationApplyAttachment record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilStationApplyAttachment record);
    
    /**
     * 新增返回主键
     * @param attachment
     * @return
     */
    int insertAttachment(OilStationApplyAttachment attachment);
    
}