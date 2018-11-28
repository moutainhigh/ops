package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsQuotaLogListDto;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public interface LogisticsQuotaLogService {

    Page<RespLogisticsQuotaLogListDto.LogisticsQuotaLogItem> selectPageList(LogisticsQuotaLogListDto.Search search, int page, int pageSize);
}
