package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.manage.dto.OilPrintListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPrintSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPrintListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;

public interface OilPrintService {

	Page<RespOilPrintListDto.PrintItem> listPage(OilPrintListDto.Search search, int page, int pageSize);
	
	RespJson detail(String id);
	
	RespJson save(OilPrintSaveDto dto, int userId) throws ManageServiceException;
	
    void delete(Integer printId);
	
}
