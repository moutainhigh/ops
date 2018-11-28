package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.LogisticsQuotaLogCategoryEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.mapper.LogisticsQuotaLogMapper;
import com.jyblife.logic.wl.ops.manage.service.LogisticsQuotaLogService;
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
public class LogisticsQuotaLogServiceImpl implements LogisticsQuotaLogService {
    @Autowired
    private LogisticsQuotaLogMapper logisticsQuotaLogMapper;

    @Override
    public Page<RespLogisticsQuotaLogListDto.LogisticsQuotaLogItem> selectPageList(LogisticsQuotaLogListDto.Search search, int page, int pageSize) {
        Page<RespLogisticsQuotaLogListDto.LogisticsQuotaLogItem> pager = PageHelper.startPage(page, pageSize);
        logisticsQuotaLogMapper.selectPageList(search);
        
        DecimalFormat df = new DecimalFormat("#,##0.00");
    	BigDecimal b100 = new BigDecimal("100");
        pager.getResult().stream().forEach(new Consumer<RespLogisticsQuotaLogListDto.LogisticsQuotaLogItem>() {
            @Override
            public void accept(RespLogisticsQuotaLogListDto.LogisticsQuotaLogItem logisticsQuotaLogItem) {
                logisticsQuotaLogItem.setCategoryName(LogisticsQuotaLogCategoryEnum.getText(StrUtils.str2Int(logisticsQuotaLogItem.getCategory())));
                boolean flag = LogisticsQuotaLogCategoryEnum.ORDER_PAYMENT.getValue().intValue() == StrUtils.str2Int(logisticsQuotaLogItem.getCategory()).intValue();
                logisticsQuotaLogItem.setQuota((flag ? "-" : "") + df.format(new BigDecimal(logisticsQuotaLogItem.getQuota()).divide(b100, 2, BigDecimal.ROUND_HALF_UP)));
            }
        });
        return pager;
    }
}
