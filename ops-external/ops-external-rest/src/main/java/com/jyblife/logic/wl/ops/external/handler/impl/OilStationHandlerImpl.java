package com.jyblife.logic.wl.ops.external.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.ReqEffectOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqNearOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilStationInfoDto;
import com.jyblife.logic.wl.ops.external.dto.resp.AreaCodeDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespEffectOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespNearOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilStationInfoDto;
import com.jyblife.logic.wl.ops.external.handler.AbstractHandler;
import com.jyblife.logic.wl.ops.external.handler.OilStationHandler;
import com.jyblife.logic.wl.ops.external.service.OilStationService;

@Service
public class OilStationHandlerImpl extends AbstractHandler implements OilStationHandler {

	@Autowired
	private OilStationService oilStationService;
	
	@Override
	public OutRespJson getNearOilStationList(ReqNearOilStationListDto dto) {
		try {
			RespNearOilStationListDto listDto = oilStationService.getNearOilStationList(dto);
            return OutRespJson.success(listDto);
		} catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
	}
	
	@Override
	public OutRespJson getOilStationInfo(ReqOilStationInfoDto dto) {
		try {
			RespOilStationInfoDto stationInfo = oilStationService.getOilStationInfo(dto);
			if(stationInfo == null || stationInfo.getStatus().equals(StatusEnum.DISABLE.getValue())) {
				return OutRespJson.error("当前油站已禁用");
			}
			
            return OutRespJson.success(stationInfo);
		} catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
	}

	@Override
	public OutRespJson getEffectOilStationList(ReqEffectOilStationListDto dto) {
		try {
			RespEffectOilStationListDto effectOilStationList = oilStationService.getEffectOilStationList(dto);
            return OutRespJson.success(effectOilStationList);
		} catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
	}

	@Override
	public OutRespJson getAreaDict() {
		try {
			AreaCodeDto codeDto = oilStationService.getAreaDict();
            return OutRespJson.success(codeDto);
		} catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
	}

}
