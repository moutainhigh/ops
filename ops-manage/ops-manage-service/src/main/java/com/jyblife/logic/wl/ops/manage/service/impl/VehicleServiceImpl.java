package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.PhotoAttachTypeEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.enums.VehicleApproveStatusEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.Vehicle;
import com.jyblife.logic.wl.ops.entity.VehicleDailyQuota;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.VehicleListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespFileItemDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleListDto;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleDailyQuotaMapper;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleMapper;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleQuotaLimitMapper;
import com.jyblife.logic.wl.ops.manage.service.PhotoAttachmentService;
import com.jyblife.logic.wl.ops.manage.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private PhotoAttachmentService photoAttachmentService;
    @Autowired
	private VehicleDailyQuotaMapper vDailyQuotaMapper;
	@Autowired
	private VehicleQuotaLimitMapper vQuotaLimitMapper;

    @Override
    public Vehicle selectById(Integer vehicleId) {
        return vehicleMapper.selectByPrimaryKey(vehicleId);
    }

    @Override
    public Page<RespVehicleListDto.VehicleItem> selectPageList(VehicleListDto.Search search, int page, int pageSize) {
        Page<RespVehicleListDto.VehicleItem> pager = PageHelper.startPage(page, pageSize);
        vehicleMapper.selectPageList(search);
        return pager;
    }

    @Override
    public RespVehicleDetailDto selectDetailById(Integer vehicleId) {
        RespVehicleDetailDto respVehicleDetailDto = vehicleMapper.selectDetailById(vehicleId);
        if (respVehicleDetailDto != null) {
            respVehicleDetailDto.setStatusName(VehicleApproveStatusEnum.getText(StrUtils.str2Int(respVehicleDetailDto.getStatus())));
            //查询图片
            List<RespFileItemDto> fileItems = photoAttachmentService.selectByBaseIdAndType(vehicleId,PhotoAttachTypeEnum.VEHICLE.getValue());
            //setter 图片
            respVehicleDetailDto.setFiles(fileItems);
            respVehicleDetailDto.setStateName(StatusEnum.getText(respVehicleDetailDto.getState()));
            //计算每日额度
            VehicleQuotaLimit quotaLimit = vQuotaLimitMapper.selectCurrent();
    		BigDecimal dailyLimit = (quotaLimit == null) ? new BigDecimal("1.00") : quotaLimit.getRate();
    		VehicleDailyQuota dailyQuota = vDailyQuotaMapper.selectCurDateByVehicleId(vehicleId);
			if(dailyQuota == null) {
				dailyQuota = new VehicleDailyQuota();
				Date curDate = new Date();
				dailyQuota.setCreateTime(curDate);
				dailyQuota.setUpdateTime(curDate);
				dailyQuota.setCurrentDate(curDate);
				dailyQuota.setFrozenQuota(new BigDecimal("0"));
				dailyQuota.setUsedQuota(new BigDecimal("0"));
				dailyQuota.setVehicleId(vehicleId);
				vDailyQuotaMapper.insertOne(dailyQuota);
			}
			BigDecimal dayCapacity = new BigDecimal(respVehicleDetailDto.getCapacity()).multiply(dailyLimit);
			BigDecimal balanceCapacity = dayCapacity.subtract(dailyQuota.getFrozenQuota()).subtract(dailyQuota.getUsedQuota());
			
			respVehicleDetailDto.setDayCapacity(dayCapacity.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			respVehicleDetailDto.setBalanceCapacity(balanceCapacity.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        }
        return respVehicleDetailDto;
    }

    @Override
    public List<RespVehicleDetailDto> selectDetailByIds(List<Integer> vehicleIds) {
        List<RespVehicleDetailDto> list = new ArrayList<RespVehicleDetailDto>();
        for (Integer vehicleId : vehicleIds) {
            list.add(this.selectDetailById(vehicleId));
        }
        return list;
    }
}
