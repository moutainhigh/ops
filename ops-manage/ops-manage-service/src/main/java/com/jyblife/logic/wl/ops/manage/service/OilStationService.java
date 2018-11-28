package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.entity.OilCompany;
import com.jyblife.logic.wl.ops.entity.OilStation;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationListDto;

import java.util.Map;

public interface OilStationService {

	Page<RepOilStationListDto.OilStationItem> listPage(OilStationApplyListDto.Search search, int page, int pageSize);
	
	RespJson detail(Integer applyId);

	Map<String, OilStation> getAllOilStationMap(Map<String, OilCompany> oilCompanyMap);
	
	RespJson oilStationList();
}
