package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.PhotoAttachTypeEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.Driver;
import com.jyblife.logic.wl.ops.manage.dto.DriverListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespFileItemDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleDetailDto;
import com.jyblife.logic.wl.ops.manage.mapper.DriverMapper;
import com.jyblife.logic.wl.ops.manage.mapper.DriverVehicleRelationMapper;
import com.jyblife.logic.wl.ops.manage.service.DriverService;
import com.jyblife.logic.wl.ops.manage.service.PhotoAttachmentService;
import com.jyblife.logic.wl.ops.manage.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {
	
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private PhotoAttachmentService photoAttachmentService;
    @Autowired
    private DriverVehicleRelationMapper driverVehicleRelationMapper;

    @Override
    public RespDriverDetailDto selectDetailById(Integer customerId) {
        //查询司机
        RespDriverDetailDto respDriverDetailDto = driverMapper.selectDetailByCustomerId(customerId);
        if (respDriverDetailDto != null) {
            respDriverDetailDto.setStatusName(StatusEnum.getText(StrUtils.str2Int(respDriverDetailDto.getStatus())));
            Integer driverId = StrUtils.str2Int(respDriverDetailDto.getId());
            //查司机图片
            List<RespFileItemDto> fileItems = photoAttachmentService.selectByBaseIdAndType(driverId, PhotoAttachTypeEnum.DRIVER.getValue());
            respDriverDetailDto.setFiles(fileItems);
            //查询司机关联的车辆id
            List<Integer> vehicleIds = driverVehicleRelationMapper.selectVehicleIdByDriverId(driverId);
            //查询车辆
            List<RespVehicleDetailDto> respVehicleDetailDtos = this.vehicleService.selectDetailByIds(vehicleIds);
            //查询车辆图片
            if(respVehicleDetailDtos != null) {
            	for(RespVehicleDetailDto dto : respVehicleDetailDtos) {
            		List<RespFileItemDto> items = photoAttachmentService.selectByBaseIdAndType(driverId, PhotoAttachTypeEnum.VEHICLE.getValue());
            		dto.setFiles(items);
            	}
            }
            respDriverDetailDto.setVehicles(respVehicleDetailDtos);
        }
        return respDriverDetailDto;
    }

    @Override
    public Page<RespDriverListDto.DriverItem> selectPageList(DriverListDto.Search search, int page, int pageSize) {
        Page<RespDriverListDto.DriverItem> pager = PageHelper.startPage(page,pageSize);
        driverMapper.selectPageList(search);
        return pager;
    }


    @Override
    public int updateOpenLocationById(Integer driverId, Byte openLocation) {
        Driver driver = driverMapper.selectByPrimaryKey(driverId);
        if(driver == null){
            throw new OpsException(ResultCodeEnum.DRIVER_NOT_EXIST);
        }
        return driverMapper.updateOpenLocationById(driverId,openLocation);
    }
}
