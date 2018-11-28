package com.jyblife.logic.wl.ops.external.handler;

import com.jyblife.logic.wl.ops.common.command.annotation.CommandMethod;
import com.jyblife.logic.wl.ops.common.command.annotation.CommandService;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilPriceAdjustMsgDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilPriceListDto;

/**
 * @author yangcey
 * @date 2018/10/31
 **/
@CommandService("910500")
public interface OilPriceHandler {

    /**
     * 获取油价列表
     * @param dto
     * @return
     */
    @CommandMethod("01")
    OutRespJson getOilPriceList(ReqOilPriceListDto dto);

    /**
     * 价格变动消息查询
     * @param dto
     * @return
     */
    @CommandMethod("02")
    OutRespJson getOilPriceAdjustMsgPageList(ReqOilPriceAdjustMsgDto dto);


}
