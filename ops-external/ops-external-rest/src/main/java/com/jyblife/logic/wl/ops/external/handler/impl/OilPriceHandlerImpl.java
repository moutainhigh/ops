package com.jyblife.logic.wl.ops.external.handler.impl;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilPriceAdjustMsgDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilPriceListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilPriceAdjustMsgListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilPriceListDto;
import com.jyblife.logic.wl.ops.external.exception.RestException;
import com.jyblife.logic.wl.ops.external.handler.AbstractHandler;
import com.jyblife.logic.wl.ops.external.handler.OilPriceHandler;
import com.jyblife.logic.wl.ops.external.service.OilPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OilPriceHandlerImpl extends AbstractHandler implements OilPriceHandler {

    @Autowired
    private OilPriceService priceSerice;

    @Override
    public OutRespJson getOilPriceList(ReqOilPriceListDto dto) throws RestException {
        if (dto.getSearch() == null || dto.getSearch().getStationId() == null) {
            return OutRespJson.paramError("加油站编号stationId不能为空");
        }

        RespOilPriceListDto listDto = priceSerice.getOilPriceList(dto);
        return OutRespJson.success(listDto);
    }

    @Override
    public OutRespJson getOilPriceAdjustMsgPageList(ReqOilPriceAdjustMsgDto dto) throws RestException {
        try {
            RespOilPriceAdjustMsgListDto respDto = new RespOilPriceAdjustMsgListDto();
            Page<RespOilPriceAdjustMsgListDto.OilPriceAdjustMsgItem> page = priceSerice.selectOilPriceAdjustMsgPageList(dto);
            respDto.setPage(page.getPageNum());
            respDto.setData(page.getResult());
            respDto.setPageSize(page.getPageSize());
            respDto.setTotalPages(page.getPages());
            respDto.setTotalRows(page.getTotal());
            return OutRespJson.success(respDto);
        } catch (OpsException e) {
            logger.error("异常：{},{}", e, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("异常：{}", e);
            throw e;
        }
    }
}
