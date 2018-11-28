package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.entity.OilStationApplyAttachment;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplySaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;

public interface OilStationApplyService {

	Page<RepOilStationApplyListDto.OilStationApplyItem> listPage(OilStationApplyListDto.Search search, int page, int pageSize);
	
	RespJson save(OilStationApplySaveDto dto, int userId) throws ManageServiceException;
	
	RespJson detail(Integer applyId);
	
	RespJson delFile(int fileId, int userId) throws ManageServiceException;
	
	OilStationApplyAttachment selectAttachmentByFileId(int fileId);
	
    int insertAttachment(OilStationApplyAttachment attachment);
	
}
