package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilCompanyAttachment;

public interface OilCompanyAttachmentMapper extends BaseMapper<OilCompanyAttachment, Integer> {
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
    int insert(OilCompanyAttachment record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilCompanyAttachment record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return OilCompanyAttachment
     */
    OilCompanyAttachment selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilCompanyAttachment record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilCompanyAttachment record);
    
    /**
     * 新增返回主键
     * @param attachment
     * @return
     */
    int insertAttachment(OilCompanyAttachment attachment);
}