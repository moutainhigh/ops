package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.ExcelUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.Vehicle;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.export.ExportVehicleQuotaVo;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleDailyQuotaMapper;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleMapper;
import com.jyblife.logic.wl.ops.manage.mapper.VehicleQuotaLimitMapper;
import com.jyblife.logic.wl.ops.manage.service.VehicleQuotaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
@RestController
@RequestMapping("/webAPI/vehicleQuota")
public class WebApiVehicleQuotaController extends BaseController {
    @Autowired
    private VehicleQuotaService vehicleQuotaService;
    @Autowired
    private VehicleDailyQuotaMapper vehicleDailyQuotaMapper;
    @Autowired
    private VehicleQuotaLimitMapper vehicleQuotaLimitMapper;
    @Autowired
    private VehicleMapper vehicleMapper;

    @Permission(value="VehicleQuota-list")
    @RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) VehicleQuotaListDto vehicleQuotaListDto) {
        try {
            if (vehicleQuotaListDto == null) {
                vehicleQuotaListDto = new VehicleQuotaListDto();
            }
            RespVehicleQuotaListDto respVehicleQuotaListDto = new RespVehicleQuotaListDto();
            Page<RespVehicleQuotaListDto.VehicleQuotaItem> page = vehicleQuotaService.selectPageList(vehicleQuotaListDto.getSearch(), vehicleQuotaListDto.getPage(), vehicleQuotaListDto.getPageSize());
            respVehicleQuotaListDto.setData(page.getResult());
            respVehicleQuotaListDto.setPage(page.getPageNum());
            respVehicleQuotaListDto.setPageSize(page.getPageSize());
            respVehicleQuotaListDto.setTotalPages(page.getPages());
            respVehicleQuotaListDto.setTotalRows((int) page.getTotal());
            respVehicleQuotaListDto.setSearchItems(vehicleQuotaListDto.getSearch());

            return RespJson.success(respVehicleQuotaListDto);
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
    @Permission(value="VehicleQuota-export")
    @RequestMapping("/export")
    public void export(VehicleQuotaListDto dto, HttpServletResponse response) {
        try {
        	DecimalFormat df = new DecimalFormat("0.00%");
            List<RespVehicleQuotaListDto.VehicleQuotaItem> driverItems = vehicleDailyQuotaMapper.selectPageList(dto.getSearch());
            VehicleQuotaLimit vehicleQuotaLimit = vehicleQuotaLimitMapper.selectCurrent();

            List<ExportVehicleQuotaVo> resList = new ArrayList<ExportVehicleQuotaVo>();
            for (RespVehicleQuotaListDto.VehicleQuotaItem driverItem : driverItems) {
            	BigDecimal rate = vehicleQuotaLimit == null ? new BigDecimal("1.00") : vehicleQuotaLimit.getRate();
                String dailyCapacity = rate.multiply(new BigDecimal(driverItem.getCapacity())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                String dailyAvailableCapacity = dailyCapacity;
                driverItem.setRate(df.format(rate));
                driverItem.setDailyCapacity(dailyCapacity);
                driverItem.setDailyAvailableCapacity(dailyAvailableCapacity);
                
                ExportVehicleQuotaVo exportDriverVo = new ExportVehicleQuotaVo();
                BeanUtils.copyProperties(driverItem, exportDriverVo);
                resList.add(exportDriverVo);
            }
            ExcelUtil<ExportVehicleQuotaVo> eu = new ExcelUtil<ExportVehicleQuotaVo>(ExportVehicleQuotaVo.class);
            eu.exportExcel(resList, response);
        } catch (Exception e) {
            logger.error("导出文件文件出错，:{}", e.getMessage());
        }

    }

    @Permission(value="VehicleQuota-getDailyQuotaLog")
    @RequestMapping("/getDailyQuotaLog")
    public RespJson getDailyQuotaLog(@RequestBody VehicleQuotaLogListDto vehicleQuotaLogListDto) {
        if (vehicleQuotaLogListDto.getSearch() == null) {
            throw new RestException(ResultCodeEnum.PARAM_ERROR);
        }
        if (vehicleQuotaLogListDto.getSearch().getVehicleId() == null) {
            throw new RestException(ResultCodeEnum.PARAM_ERROR);
        }
        try {
            RespVehicleQuotaLogListDto respVehicleQuotaLogListDto = new RespVehicleQuotaLogListDto();
            Page<RespVehicleQuotaLogListDto.VehicleQuotaLogItem> page = vehicleQuotaService.selectQuotaLogPageList(vehicleQuotaLogListDto.getSearch(), vehicleQuotaLogListDto.getPage(), vehicleQuotaLogListDto.getPageSize());
            respVehicleQuotaLogListDto.setData(page.getResult());
            respVehicleQuotaLogListDto.setPage(page.getPageNum());
            respVehicleQuotaLogListDto.setPageSize(page.getPageSize());
            respVehicleQuotaLogListDto.setTotalPages(page.getPages());
            respVehicleQuotaLogListDto.setTotalRows((int) page.getTotal());
            respVehicleQuotaLogListDto.setSearchItems(vehicleQuotaLogListDto.getSearch());

            Vehicle vehicle = vehicleMapper.selectByPrimaryKey(StrUtils.str2Int(vehicleQuotaLogListDto.getSearch().getVehicleId()));
            Map<String, String> extra = new HashMap<String, String>();
            extra.put("number", vehicle.getNumber());
            
            RespJson respJson = RespJson.success(respVehicleQuotaLogListDto);
            respJson.put("extra", extra);
            
            return respJson;
        } catch (Exception e) {
            logger.error("异常: " + e.getMessage(), e);
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }


}
