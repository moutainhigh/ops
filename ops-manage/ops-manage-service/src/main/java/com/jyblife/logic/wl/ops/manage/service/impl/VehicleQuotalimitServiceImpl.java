package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotalimitListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotalimitListDto;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleQuotaLimitMapper;
import com.jyblife.logic.wl.ops.manage.service.VehicleQuotalimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
@Service
@Transactional
public class VehicleQuotalimitServiceImpl implements VehicleQuotalimitService {
    @Autowired
    private VehicleQuotaLimitMapper vehicleQuotaLimitMapper;

    @Override
    public Page<RespVehicleQuotalimitListDto.QuotalimitItem> selectPageList(VehicleQuotalimitListDto.Search search, int page, int pageSize) {
        Page<RespVehicleQuotalimitListDto.QuotalimitItem> pager = PageHelper.startPage(page, pageSize);
        vehicleQuotaLimitMapper.selectPageList(search);
        return pager;
    }

    @Override
    public void save(VehicleQuotaLimit vehicleQuotaLimit) {
        vehicleQuotaLimitMapper.insert(vehicleQuotaLimit);
    }
}
