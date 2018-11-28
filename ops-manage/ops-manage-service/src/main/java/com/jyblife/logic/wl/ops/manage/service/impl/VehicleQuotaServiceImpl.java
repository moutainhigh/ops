package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleDailyQuotaLogMapper;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleDailyQuotaMapper;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleQuotaLimitMapper;
import com.jyblife.logic.wl.ops.manage.service.VehicleQuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.function.Consumer;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
@Service
@Transactional
public class VehicleQuotaServiceImpl implements VehicleQuotaService {
    @Autowired
    private VehicleQuotaLimitMapper vehicleQuotaLimitMapper;
    @Autowired
    private VehicleDailyQuotaMapper vehicleDailyQuotaMapper;
    @Autowired
    private VehicleDailyQuotaLogMapper vehicleDailyQuotaLogMapper;

    @Override
    public Page<RespVehicleQuotaListDto.VehicleQuotaItem> selectPageList(VehicleQuotaListDto.Search search, int page, int pageSize) {
        VehicleQuotaLimit vehicleQuotaLimit = vehicleQuotaLimitMapper.selectCurrent();
        BigDecimal rate = (vehicleQuotaLimit == null) ? new BigDecimal("1.00") : vehicleQuotaLimit.getRate();
        
        Page<RespVehicleQuotaListDto.VehicleQuotaItem> pager = PageHelper.startPage(page, pageSize);
        vehicleDailyQuotaMapper.selectPageList(search);
        pager.getResult().stream().forEach(new Consumer<RespVehicleQuotaListDto.VehicleQuotaItem>() {
            @Override
            public void accept(RespVehicleQuotaListDto.VehicleQuotaItem vehicleQuotaItem) {
            	BigDecimal dailyCapacity = rate.multiply(new BigDecimal(vehicleQuotaItem.getCapacity()));
            	BigDecimal dailyAvailableCapacity = dailyCapacity.subtract(new BigDecimal(vehicleQuotaItem.getUsedQuota()))
                											.subtract(new BigDecimal(vehicleQuotaItem.getFrozenQuota()));
                
                vehicleQuotaItem.setRate(rate.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                vehicleQuotaItem.setDailyCapacity(dailyCapacity.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                vehicleQuotaItem.setDailyAvailableCapacity(dailyAvailableCapacity.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                vehicleQuotaItem.setStateName(StatusEnum.getText(vehicleQuotaItem.getState()));
            }
        });

        return pager;
    }

    @Override
    public Page<RespVehicleQuotaLogListDto.VehicleQuotaLogItem> selectQuotaLogPageList(VehicleQuotaLogListDto.Search search, int page, int pageSize) {
        Page<RespVehicleQuotaLogListDto.VehicleQuotaLogItem> pager = PageHelper.startPage(page, pageSize);
        vehicleDailyQuotaLogMapper.selectQuotaLogPageList(search);
        return pager;
    }
}
