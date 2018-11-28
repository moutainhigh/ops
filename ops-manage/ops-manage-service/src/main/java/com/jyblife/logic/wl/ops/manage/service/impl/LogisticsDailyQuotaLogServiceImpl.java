package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.LogisticsQuotaLogCategoryEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsDailyQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsDailyQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.mapper.LogisticsDailyQuotaLogMapper;
import com.jyblife.logic.wl.ops.manage.service.LogisticsDailyQuotaLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.Consumer;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
@Service
@Transactional
public class LogisticsDailyQuotaLogServiceImpl implements LogisticsDailyQuotaLogService {
    @Autowired
    private LogisticsDailyQuotaLogMapper logisticsDailyQuotaLogMapper;

    @Override
    public Page<RespLogisticsDailyQuotaLogListDto.LogisticsDailyQuotaItem> selectPageList(LogisticsDailyQuotaLogListDto.Search search, int page, int pageSize) {
        Page<RespLogisticsDailyQuotaLogListDto.LogisticsDailyQuotaItem> pager = PageHelper.startPage(page, pageSize);
        logisticsDailyQuotaLogMapper.selectPageList(search);
        
        DecimalFormat df = new DecimalFormat("#,##0.00");
    	BigDecimal b100 = new BigDecimal("100");
        pager.getResult().stream().forEach(new Consumer<RespLogisticsDailyQuotaLogListDto.LogisticsDailyQuotaItem>() {
            @Override
            public void accept(RespLogisticsDailyQuotaLogListDto.LogisticsDailyQuotaItem logisticsDailyQuotaItem) {
                logisticsDailyQuotaItem.setCategoryName(LogisticsQuotaLogCategoryEnum.getText(StrUtils.str2Int(logisticsDailyQuotaItem.getCategory())));
                boolean flag = LogisticsQuotaLogCategoryEnum.ORDER_PAYMENT.getValue().intValue() == StrUtils.str2Int(logisticsDailyQuotaItem.getCategory()).intValue();
                logisticsDailyQuotaItem.setQuota((flag ? "-" : "") + df.format(new BigDecimal(logisticsDailyQuotaItem.getQuota()).divide(b100, 2, BigDecimal.ROUND_HALF_UP)));
            }
        });
        return pager;
    }
}
