package com.jyblife.logic.wl.ops.external.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilPriceAdjustMsgDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilPriceListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilPriceAdjustMsgListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilPriceListDto;
import com.jyblife.logic.wl.ops.external.mapper.OilPriceAdjustMsgMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilPriceMapper;
import com.jyblife.logic.wl.ops.external.service.OilPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Consumer;

@Service
@Transactional
public class OilPriceServiceImpl implements OilPriceService {

    private Logger logger = LoggerFactory.getLogger(OilPriceServiceImpl.class);

    @Autowired
    private OilPriceMapper priceMapper;

    @Autowired
    private OilPriceAdjustMsgMapper oilPriceAdjustMsgMapper;

    @Override
    public RespOilPriceListDto getOilPriceList(ReqOilPriceListDto dto) {
        BigDecimal b100 = new BigDecimal("100");
        Date cur_date = new Date();

        Page<RespOilPriceListDto.OilPriceItem> page = PageHelper.startPage(dto.getPage(), dto.getPageSize());
        priceMapper.getOilPriceList(dto.getSearch());

        page.getResult().stream().forEach(new Consumer<RespOilPriceListDto.OilPriceItem>() {
            @Override
            public void accept(RespOilPriceListDto.OilPriceItem item) {
                item.setAgreedPrice(new BigDecimal(item.getAgreedPrice()).divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
                item.setRetailPrice(new BigDecimal(item.getRetailPrice()).divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());

                Date effectTime = item.getEffectTime();
                Date endTime = item.getEndTime();
                if(effectTime != null && cur_date.before(effectTime) && StatusEnum.ENABLE.getValue().equals(StrUtils.str2Int(item.getStatus()))) {
                    item.setStatus("待生效");
                } else if(effectTime == null || endTime.before(cur_date) || StatusEnum.DISABLE.getValue().equals(StrUtils.str2Int(item.getStatus()))) {
                    item.setStatus("已失效");
                } else {
                    item.setStatus("已生效");
                }
            }
        });

        RespOilPriceListDto respDto = new RespOilPriceListDto();
        respDto.setData(page.getResult());
        respDto.setPage(dto.getPage());
        respDto.setPageSize(dto.getPageSize());
        respDto.setTotalPages(page.getPages());
        respDto.setTotalRows(page.getTotal());

        return respDto;
    }

    @Override
    public Page<RespOilPriceAdjustMsgListDto.OilPriceAdjustMsgItem> selectOilPriceAdjustMsgPageList(ReqOilPriceAdjustMsgDto dto) {
        Page<RespOilPriceAdjustMsgListDto.OilPriceAdjustMsgItem> page = PageHelper.startPage(dto.getPage(), dto.getPageSize());
        oilPriceAdjustMsgMapper.selectOilPriceAdjustMsgPageList(dto);
        return page;
    }
}
