package com.jyblife.logic.wl.ops.external.service;

import com.jyblife.logic.wl.ops.external.dto.req.ReqEffectOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqNearOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilStationInfoDto;
import com.jyblife.logic.wl.ops.external.dto.resp.AreaCodeDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespEffectOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespNearOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilStationInfoDto;

public interface OilStationService {
	
	AreaCodeDto getAreaDict();
	
	RespOilStationInfoDto getOilStationInfo(ReqOilStationInfoDto dto);
	
	RespEffectOilStationListDto getEffectOilStationList(ReqEffectOilStationListDto dto);
	
	RespNearOilStationListDto getNearOilStationList(ReqNearOilStationListDto dto);

}
