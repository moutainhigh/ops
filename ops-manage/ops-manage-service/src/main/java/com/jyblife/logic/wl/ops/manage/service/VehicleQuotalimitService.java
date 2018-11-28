package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotalimitListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotalimitListDto;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public interface VehicleQuotalimitService {
    Page<RespVehicleQuotalimitListDto.QuotalimitItem> selectPageList(VehicleQuotalimitListDto.Search search, int page, int pageSize);

    void save(VehicleQuotaLimit vehicleQuotaLimit);
}
