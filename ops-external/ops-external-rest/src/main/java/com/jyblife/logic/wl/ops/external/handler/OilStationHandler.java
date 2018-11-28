package com.jyblife.logic.wl.ops.external.handler;

import com.jyblife.logic.wl.ops.common.command.annotation.CommandMethod;
import com.jyblife.logic.wl.ops.common.command.annotation.CommandService;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.ReqEffectOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqNearOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilStationInfoDto;

@CommandService("910300")
public interface OilStationHandler {

	/**
     * 获取附近油站列表
     * 
     * @param dto
     * @return
     */
    @CommandMethod("01")
    OutRespJson getNearOilStationList(ReqNearOilStationListDto dto);
	
	/**
     * 油站信息
     * 
     * @param dto
     * @return
     */
    @CommandMethod("02")
    OutRespJson getOilStationInfo(ReqOilStationInfoDto dto);
    
    /**
     * 获取所有有效油站列表
     * 
     * @param dto
     * @return
     */
    @CommandMethod("03")
    OutRespJson getEffectOilStationList(ReqEffectOilStationListDto dto);
    
    /**
     * 获取城市字典树
     * 
     * @return
     */
    @CommandMethod("04")
    OutRespJson getAreaDict();
	
}
