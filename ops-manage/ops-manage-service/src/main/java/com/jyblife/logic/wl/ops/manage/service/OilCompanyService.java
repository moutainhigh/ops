package com.jyblife.logic.wl.ops.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.entity.OilCompany;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanyListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanySaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilCompanyListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;

public interface OilCompanyService {

    Page<RepOilCompanyListDto.OilCompanyItem> listPage(OilCompanyListDto.Search search, int page, int pageSize);

    RespJson detail(Integer companyId);

    List<OilCompany> listAllEnable();

    RespJson save(OilCompanySaveDto dto, int userId) throws ManageServiceException;

    Map<String, OilCompany> getAllOilCompanyMap();
    
    RespJson delFile(int fileId, int userId) throws ManageServiceException;

}
