package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.ExcelUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.Driver;
import com.jyblife.logic.wl.ops.manage.dto.DriverListDto;
import com.jyblife.logic.wl.ops.manage.dto.DriverOpenLocationDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.export.ExportDriverVo;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.DriverMapper;
import com.jyblife.logic.wl.ops.manage.service.DriverService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Controller
@RequestMapping("/webAPI/driver")
public class WebApiDriverController extends BaseController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverMapper driverMapper;

    @Permission(value = "driver-detail")
    @RequestMapping("/detail")
    @ResponseBody
    public RespJson detail(@RequestParam(value = "customer_id") Integer customerId) {
        try {
            RespDriverDetailDto respDriverDetailDto = driverService.selectDetailById(customerId);
            if (respDriverDetailDto != null) {
                return RespJson.success(respDriverDetailDto);
            }
            return RespJson.error(ResultCodeEnum.DRIVER_NOT_EXIST);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value = "driver-list")
    @RequestMapping("/list")
    @ResponseBody
    public RespJson list(@RequestBody(required = false) DriverListDto driverListDto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (driverListDto == null) {
                driverListDto = new DriverListDto();
            }

            boolean isCanEdit = isHadPermission(sessionId, "driver-edit");
            boolean isCanView = isHadPermission(sessionId, "driver-detail");

            RespDriverListDto respDriverListDto = new RespDriverListDto();
            Page<RespDriverListDto.DriverItem> page = driverService.selectPageList(driverListDto.getSearch(), driverListDto.getPage(), driverListDto.getPageSize());
            page.getResult().stream().forEach(new Consumer<RespDriverListDto.DriverItem>() {
                @Override
                public void accept(RespDriverListDto.DriverItem driverItem) {
                    driverItem.setStatusName(StatusEnum.getText(StrUtils.str2Int(driverItem.getStatus())));
                    driverItem.setIsCanEdit(isCanEdit);
                    driverItem.setIsCanView(isCanView);
                }
            });
            respDriverListDto.setData(page.getResult());
            respDriverListDto.setPage(page.getPageNum());
            respDriverListDto.setPageSize(page.getPageSize());
            respDriverListDto.setTotalPages(page.getPages());
            respDriverListDto.setTotalRows((int) page.getTotal());
            respDriverListDto.setSearchItems(driverListDto.getSearch());

            return RespJson.success(respDriverListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    /**
     * 文件导出
     *
     * @param dto
     * @param response
     * @throws Exception
     */
    @Permission(value = "driver-export")
    @RequestMapping("/export")
    public void export(DriverListDto dto, HttpServletResponse response) {
        try {
            List<RespDriverListDto.DriverItem> driverItems = driverMapper.selectPageList(dto.getSearch());
            List<ExportDriverVo> resList = new ArrayList<ExportDriverVo>();
            for (RespDriverListDto.DriverItem driverItem : driverItems) {
                ExportDriverVo exportDriverVo = new ExportDriverVo();
                BeanUtils.copyProperties(driverItem, exportDriverVo);
                exportDriverVo.setStatus(StatusEnum.getText(StrUtils.str2Int(driverItem.getStatus())));
                resList.add(exportDriverVo);
            }
            ExcelUtil<ExportDriverVo> eu = new ExcelUtil<ExportDriverVo>(ExportDriverVo.class);
            eu.exportExcel(resList, response);
        } catch (Exception e) {
            logger.error("导出文件文件出错，:{}", e.getMessage());
        }

    }

    /**
     * 开启关闭司机定位功能
     */
    @Permission(value = "driver-edit")
    @RequestMapping("/openLocation")
    @ResponseBody
    public RespJson openLoacation(@RequestBody DriverOpenLocationDto dto) {
        try {
            driverService.updateOpenLocationById(dto.getDriverId(), dto.getOpenLocation());
            return RespJson.success();
        } catch (OpsException e) {
            logger.error("开启关闭司机定位功能，OpsException:{},{}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("开启关闭司机定位功能，Exception:{},{}", e.getMessage(), e);
            throw e;
        }
    }
}
