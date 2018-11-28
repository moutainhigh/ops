package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.entity.LogisticsCompany;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsCompanyListDto;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public interface LogisticsCompanyService {

    void updateLogisticsCompany(LogisticsCompany logisticsCompany);
    
    int updateLogisticsName(LogisticsCompany logisticsCompany);

    Page<LogisticsCompany> listPage(LogisticsCompanyListDto.Search search, Integer page, Integer pageSize);

    LogisticsCompany selectById(Long logisticsId);
}
