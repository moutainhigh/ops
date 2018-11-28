package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsQuotaListDto;

import java.util.List;

/**
 * 企业限额业务
 *
 * @author yangcey
 * @date 2018/9/26
 **/
public interface LogisticsQuotaService {

    Page<RespLogisticsQuotaListDto.LogisticsQuotaItem> selectPageList(LogisticsQuotaListDto.Search search, int page, int pageSize);

    List<RespLogisticsQuotaListDto.LogisticsQuotaItem> selectExportVoList(LogisticsQuotaListDto.Search search);
}
