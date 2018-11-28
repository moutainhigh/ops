package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsLimitDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsQuotaListDto;
import com.jyblife.logic.wl.ops.manage.mapper.LogisticsQuotaLimitMapper;
import com.jyblife.logic.wl.ops.manage.mapper.LogisticsQuotaMapper;
import com.jyblife.logic.wl.ops.manage.service.LogisticsQuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
@Service
@Transactional
public class LogisticsQuotaServiceImpl implements LogisticsQuotaService {
    @Autowired
    private LogisticsQuotaMapper logisticsQuotaMapper;
    @Autowired
    private LogisticsQuotaLimitMapper logisticsQuotaLimitMapper;

    @Override
    public Page<RespLogisticsQuotaListDto.LogisticsQuotaItem> selectPageList(LogisticsQuotaListDto.Search search, int page, int pageSize) {

        RespLogisticsLimitDetailDto respLogisticsLimitDetailDto = logisticsQuotaLimitMapper.selectCurrentDetail();
        if(respLogisticsLimitDetailDto == null) {
    		respLogisticsLimitDetailDto = new RespLogisticsLimitDetailDto();
    		respLogisticsLimitDetailDto.setRate("1");
    	}

        Page<RespLogisticsQuotaListDto.LogisticsQuotaItem> pager = PageHelper.startPage(page, pageSize);
        logisticsQuotaMapper.selectPageList(search);
        this.adapterList(respLogisticsLimitDetailDto, pager.getResult());
        return pager;
    }

    @Override
    public List<RespLogisticsQuotaListDto.LogisticsQuotaItem> selectExportVoList(LogisticsQuotaListDto.Search search) {

        List<RespLogisticsQuotaListDto.LogisticsQuotaItem> list = logisticsQuotaMapper.selectPageList(search);
        RespLogisticsLimitDetailDto respLogisticsLimitDetailDto = logisticsQuotaLimitMapper.selectCurrentDetail();
        this.adapterList(respLogisticsLimitDetailDto, list);
        return list;
    }

    private void adapterList(RespLogisticsLimitDetailDto respLogisticsLimitDetailDto, List<RespLogisticsQuotaListDto.LogisticsQuotaItem> list) {
    	Date curDate =DateUtil.parse(DateUtil.format(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
    	DecimalFormat df = new DecimalFormat("#,##0.00");
    	BigDecimal rate = new BigDecimal(respLogisticsLimitDetailDto.getRate());
    	BigDecimal b100 = new BigDecimal("100");
    	
        list.stream().forEach(new Consumer<RespLogisticsQuotaListDto.LogisticsQuotaItem>() {
            @Override
            public void accept(RespLogisticsQuotaListDto.LogisticsQuotaItem logisticsQuotaItem) {
            	if(logisticsQuotaItem.getStartDate().compareTo(curDate) <= 0 && logisticsQuotaItem.getEndDate().compareTo(curDate) >= 0) {
            		logisticsQuotaItem.setStatusName("正常");
            	} else {
            		logisticsQuotaItem.setStatusName("过期");
            	}
            	
                if (respLogisticsLimitDetailDto != null) {
                    BigDecimal creditQuota = new BigDecimal(logisticsQuotaItem.getCreditQuota()).divide(b100);
                    BigDecimal frozenQuota = new BigDecimal(logisticsQuotaItem.getFrozenQuota()).divide(b100);
                    BigDecimal usedQuota = new BigDecimal(logisticsQuotaItem.getUsedQuota()).divide(b100);
                    BigDecimal availableQuota = creditQuota.subtract(frozenQuota).subtract(usedQuota);

                    logisticsQuotaItem.setRate(respLogisticsLimitDetailDto.getRate());
                    logisticsQuotaItem.setCreditQuota(df.format(creditQuota.setScale(2, BigDecimal.ROUND_HALF_UP)));
                    logisticsQuotaItem.setDailyCreditQuota(df.format(rate.multiply(creditQuota).setScale(2, BigDecimal.ROUND_HALF_UP)));
                    logisticsQuotaItem.setAvailableQuota(df.format(availableQuota.setScale(2, BigDecimal.ROUND_HALF_UP)));

                    BigDecimal dayFrozenQuota = new BigDecimal(logisticsQuotaItem.getDayFrozenQuota()).divide(b100);
                    BigDecimal dayUsedQuota = new BigDecimal(logisticsQuotaItem.getDayUsedQuota()).divide(b100);
                    BigDecimal dailyAvailableQuota = rate.multiply(creditQuota).subtract(dayFrozenQuota).subtract(dayUsedQuota);
                    
                    logisticsQuotaItem.setDailyAvailableQuota(df.format(dailyAvailableQuota.setScale(2, BigDecimal.ROUND_HALF_UP)));
                    logisticsQuotaItem.setDayFrozenQuota(df.format(dayFrozenQuota.setScale(2, BigDecimal.ROUND_HALF_UP)));
                    logisticsQuotaItem.setDayUsedQuota(df.format(dayUsedQuota.setScale(2, BigDecimal.ROUND_HALF_UP)));
                }
            }
        });
    }
    
}
