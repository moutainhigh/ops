package com.jyblife.logic.wl.ops.external.mapper;

import java.util.List;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.PhotoAttachment;
import com.jyblife.logic.wl.ops.external.dto.resp.RespFileItemDto;

public interface PhotoAttachmentMapper extends BaseMapper<PhotoAttachment,Integer> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(PhotoAttachment record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(PhotoAttachment record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param id
     * @return PhotoAttachment
     */
    PhotoAttachment selectByPrimaryKey(Long id);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(PhotoAttachment record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(PhotoAttachment record);
    
    List<RespFileItemDto> selectFileItems(PhotoAttachment photoAttachment);
    
    int deleteByBaseIdAndType(PhotoAttachment record);

}