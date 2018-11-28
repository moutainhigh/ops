package com.jyblife.logic.wl.ops.manage.service;

import com.jyblife.logic.wl.ops.entity.PhotoAttachment;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespFileItemDto;

import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public interface PhotoAttachmentService {

    List<PhotoAttachment> selectList(PhotoAttachment photoAttachment);

    List<RespFileItemDto> selectFileItems(PhotoAttachment photoAttachment);

    List<RespFileItemDto> selectByBaseIdAndType(Integer baseId,Integer type);
}
