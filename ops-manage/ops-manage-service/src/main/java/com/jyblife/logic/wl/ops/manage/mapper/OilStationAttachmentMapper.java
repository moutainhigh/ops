package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilStationAttachment;

public interface OilStationAttachmentMapper extends BaseMapper<OilStationAttachment, Integer> {
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
    int insert(OilStationAttachment record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilStationAttachment record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return OilStationAttachment
     */
    OilStationAttachment selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilStationAttachment record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilStationAttachment record);
}