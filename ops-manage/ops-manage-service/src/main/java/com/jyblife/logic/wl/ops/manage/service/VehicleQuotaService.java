package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaLogListDto;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public interface VehicleQuotaService {

    Page<RespVehicleQuotaListDto.VehicleQuotaItem> selectPageList(VehicleQuotaListDto.Search search, int page, int pageSize);

    Page<RespVehicleQuotaLogListDto.VehicleQuotaLogItem> selectQuotaLogPageList(VehicleQuotaLogListDto.Search search, int page, int pageSize);
}
