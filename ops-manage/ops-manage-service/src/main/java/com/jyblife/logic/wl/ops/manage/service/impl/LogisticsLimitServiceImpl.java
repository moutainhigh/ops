package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsLimitListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsLimitListDto;
import com.jyblife.logic.wl.ops.manage.mapper.LogisticsQuotaLimitMapper;
import com.jyblife.logic.wl.ops.manage.service.LogisticsLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
@Service
@Transactional
public class LogisticsLimitServiceImpl implements LogisticsLimitService {
    @Autowired
    private LogisticsQuotaLimitMapper logisticsQuotaLimitMapper;

    @Override
    public Page<RespLogisticsLimitListDto.LogisticsLimitItem> selectPageList(LogisticsLimitListDto.Search search, int page, int pageSize) {
        Page<RespLogisticsLimitListDto.LogisticsLimitItem> pager = PageHelper.startPage(page, pageSize);
        logisticsQuotaLimitMapper.selectPageList(search);
        return pager;
    }

    @Override
    public void save(LogisticsQuotaLimit logisticsQuotaLimit) {
        logisticsQuotaLimitMapper.insert(logisticsQuotaLimit);
    }
}
