package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilPriceApplyAttachment;

public interface OilPriceApplyAttachmentMapper extends BaseMapper<OilPriceApplyAttachment,Integer> {
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
    int insert(OilPriceApplyAttachment record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilPriceApplyAttachment record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return OilPriceApplyAttachment
     */
    OilPriceApplyAttachment selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilPriceApplyAttachment record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilPriceApplyAttachment record);


    int insertAttachment(OilPriceApplyAttachment record);

    void updateStatusByApplyId(OilPriceApplyAttachment record);
}