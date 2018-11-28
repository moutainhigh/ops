package com.jyblife.logic.wl.ops.manage.service.impl;

import com.jyblife.logic.wl.ops.entity.PhotoAttachment;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespFileItemDto;
import com.jyblife.logic.wl.ops.manage.mapper.PhotoAttachmentMapper;
import com.jyblife.logic.wl.ops.manage.service.PhotoAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
@Service
@Transactional
public class PhotoAttachmentServiceImpl implements PhotoAttachmentService {
    @Autowired
    private PhotoAttachmentMapper photoAttachmentMapper;
    @Override
    public List<PhotoAttachment> selectList(PhotoAttachment photoAttachment) {
        return photoAttachmentMapper.selectList(photoAttachment);
    }

    @Override
    public List<RespFileItemDto> selectFileItems(PhotoAttachment photoAttachment) {
        return photoAttachmentMapper.selectFileItems(photoAttachment);
    }

    @Override
    public List<RespFileItemDto> selectByBaseIdAndType(Integer baseId, Integer type) {
        PhotoAttachment photoAttachment = new PhotoAttachment();
        photoAttachment.setBaseId(baseId.longValue());
        photoAttachment.setType(type);
        return this.selectFileItems(photoAttachment);
    }
}
