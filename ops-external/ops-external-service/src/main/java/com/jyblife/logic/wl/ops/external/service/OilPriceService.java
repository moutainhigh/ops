package com.jyblife.logic.wl.ops.external.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilPriceAdjustMsgDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilPriceListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilPriceAdjustMsgListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilPriceListDto;

public interface OilPriceService {

    /**
     * 油价列表
     * @param dto
     * @return
     */
    RespOilPriceListDto getOilPriceList(ReqOilPriceListDto dto);

    Page<RespOilPriceAdjustMsgListDto.OilPriceAdjustMsgItem> selectOilPriceAdjustMsgPageList(ReqOilPriceAdjustMsgDto dto);

}
