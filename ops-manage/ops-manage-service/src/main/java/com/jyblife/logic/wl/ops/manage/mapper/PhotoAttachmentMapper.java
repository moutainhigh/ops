package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.PhotoAttachment;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespFileItemDto;

import java.util.List;

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


    List<PhotoAttachment> selectList(PhotoAttachment photoAttachment);

    List<RespFileItemDto> selectFileItems(PhotoAttachment photoAttachment);

}