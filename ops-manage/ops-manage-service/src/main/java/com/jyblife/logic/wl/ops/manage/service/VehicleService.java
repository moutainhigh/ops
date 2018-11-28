package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.entity.Vehicle;
import com.jyblife.logic.wl.ops.manage.dto.VehicleListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleListDto;

import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public interface VehicleService {
    Vehicle selectById(Integer vehicleId);

    Page<RespVehicleListDto.VehicleItem> selectPageList(VehicleListDto.Search search, int page, int pageSize);

    RespVehicleDetailDto selectDetailById(Integer vehicleId);

    List<RespVehicleDetailDto> selectDetailByIds(List<Integer> vehicleIds);
}
