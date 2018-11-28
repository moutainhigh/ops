package com.jyblife.logic.wl.ops.manage.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.manage.dto.req.ReqOilPriceListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceListDto;

public interface OilPriceService {

	Page<RespOilPriceListDto.OilPriceItem> selectPageList(ReqOilPriceListDto.Search search, int pageNum, int pageSize);
	
	RespJson execute(Integer priceId, Integer updateUserId);
	
	List<RespOilPriceListDto.OilPriceItem> export(ReqOilPriceListDto.Search search);
	
}
