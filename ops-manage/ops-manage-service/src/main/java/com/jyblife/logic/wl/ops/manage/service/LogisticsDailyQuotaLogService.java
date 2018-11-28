package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsDailyQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsDailyQuotaLogListDto;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public interface LogisticsDailyQuotaLogService {

    Page<RespLogisticsDailyQuotaLogListDto.LogisticsDailyQuotaItem> selectPageList(LogisticsDailyQuotaLogListDto.Search search, int page, int pageSize);

}
