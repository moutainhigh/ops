package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsLimitListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsLimitListDto;

/**
 * 企业每日限额业务
 * @author yangcey
 * @date 2018/9/25
 **/
public interface LogisticsLimitService {

    Page<RespLogisticsLimitListDto.LogisticsLimitItem> selectPageList(LogisticsLimitListDto.Search search, int page, int pageSize);

    void save(LogisticsQuotaLimit logisticsQuotaLimit);
}
