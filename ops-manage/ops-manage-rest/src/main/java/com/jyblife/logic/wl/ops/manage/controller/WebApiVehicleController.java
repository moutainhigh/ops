package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.manage.dto.VehicleListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.VehicleService;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
@RestController
@RequestMapping("/webAPI/vehicle")
public class WebApiVehicleController extends BaseController {
    @Autowired
    private VehicleService vehicleService;

    @Permission(value="vehicleData-detail")
    @RequestMapping("/detail")
    public RespJson detail(@RequestParam(value = "vehicle_id") Integer vehicleId) {
        try {
            RespVehicleDetailDto respVehicleDetailDto = vehicleService.selectDetailById(vehicleId);
            if (respVehicleDetailDto != null) {
                return RespJson.success(respVehicleDetailDto);
            }
            return RespJson.error(ResultCodeEnum.VEHICLE_NOT_EXIST);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="vehicleData-list")
    @RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) VehicleListDto vehicleListDto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (vehicleListDto == null) {
                vehicleListDto = new VehicleListDto();
            }
            
            boolean isCanEdit = isHadPermission(sessionId, "vehicleData-edit");
            boolean isCanView = isHadPermission(sessionId, "vehicleData-detail");
            
            RespVehicleListDto respVehicleListDto = new RespVehicleListDto();
            Page<RespVehicleListDto.VehicleItem> page = vehicleService.selectPageList(vehicleListDto.getSearch(), vehicleListDto.getPage(), vehicleListDto.getPageSize());
            page.getResult().stream().forEach(new Consumer<RespVehicleListDto.VehicleItem>() {
                @Override
                public void accept(RespVehicleListDto.VehicleItem item) {
                	item.setIsCanEdit(isCanEdit);
                	item.setIsCanView(isCanView);
                	item.setStateName(StatusEnum.getText(item.getState()));
                }
            });
            
            respVehicleListDto.setData(page.getResult());
            respVehicleListDto.setPage(page.getPageNum());
            respVehicleListDto.setPageSize(page.getPageSize());
            respVehicleListDto.setTotalPages(page.getPages());
            respVehicleListDto.setTotalRows((int) page.getTotal());
            respVehicleListDto.setSearchItems(vehicleListDto.getSearch());

            return RespJson.success(respVehicleListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
}
