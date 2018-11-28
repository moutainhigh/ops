package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.ExcelUtil;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsDailyQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsDailyQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsQuotaListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespLogisticsQuotaLogListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.export.ExportLogisticsQuotaVo;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.LogisticsDailyQuotaLogService;
import com.jyblife.logic.wl.ops.manage.service.LogisticsQuotaLogService;
import com.jyblife.logic.wl.ops.manage.service.LogisticsQuotaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 企业额度控制器
 *
 **/
@RestController
@RequestMapping("/webAPI/logisticsQuota")
public class WebApiLogisticsQuotaController extends BaseController {
    @Autowired
    private LogisticsQuotaService logisticsQuotaService;
    @Autowired
    private LogisticsQuotaLogService logisticsQuotaLogService;
    @Autowired
    private LogisticsDailyQuotaLogService logisticsDailyQuotaLogService;

    @Permission(value="enterpriseQuota-list")
    @RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) LogisticsQuotaListDto logisticsQuotaListDto) {
        try {
            if (logisticsQuotaListDto == null) {
                logisticsQuotaListDto = new LogisticsQuotaListDto();
            }
            RespLogisticsQuotaListDto respLogisticsQuotaListDto = new RespLogisticsQuotaListDto();
            Page<RespLogisticsQuotaListDto.LogisticsQuotaItem> page = logisticsQuotaService.selectPageList(logisticsQuotaListDto.getSearch(), logisticsQuotaListDto.getPage(), logisticsQuotaListDto.getPageSize());
            respLogisticsQuotaListDto.setData(page.getResult());
            respLogisticsQuotaListDto.setPage(page.getPageNum());
            respLogisticsQuotaListDto.setPageSize(page.getPageSize());
            respLogisticsQuotaListDto.setTotalPages(page.getPages());
            respLogisticsQuotaListDto.setTotalRows((int) page.getTotal());
            respLogisticsQuotaListDto.setSearchItems(logisticsQuotaListDto.getSearch());

            return RespJson.success(respLogisticsQuotaListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="enterpriseQuota-getLogisticsQuotaLog")
    @RequestMapping("/getLogisticsQuotaLog")
    public RespJson getLogisticsQuotaLog(@RequestBody(required = false) LogisticsQuotaLogListDto logisticsQuotaLogListDto) {
        if (logisticsQuotaLogListDto == null
                || logisticsQuotaLogListDto.getSearch() == null
                || StringUtils.isBlank(logisticsQuotaLogListDto.getSearch().getLogisticsId())) {
            throw new RestException(ResultCodeEnum.PARAM_ERROR);
        }
        try {
            RespLogisticsQuotaLogListDto respLogisticsQuotaLogListDto = new RespLogisticsQuotaLogListDto();
            Page<RespLogisticsQuotaLogListDto.LogisticsQuotaLogItem> page = logisticsQuotaLogService.selectPageList(logisticsQuotaLogListDto.getSearch(), logisticsQuotaLogListDto.getPage(), logisticsQuotaLogListDto.getPageSize());
            respLogisticsQuotaLogListDto.setData(page.getResult());
            respLogisticsQuotaLogListDto.setPage(page.getPageNum());
            respLogisticsQuotaLogListDto.setPageSize(page.getPageSize());
            respLogisticsQuotaLogListDto.setTotalPages(page.getPages());
            respLogisticsQuotaLogListDto.setTotalRows((int) page.getTotal());
            respLogisticsQuotaLogListDto.setSearchItems(logisticsQuotaLogListDto.getSearch());

            return RespJson.success(respLogisticsQuotaLogListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="enterpriseQuota-getLogisticsDailyQuotaLog")
    @RequestMapping("/getLogisticsDailyQuotaLog")
    public RespJson getLogisticsDailyQuotaLog(@RequestBody(required = false) LogisticsDailyQuotaLogListDto logisticsDailyQuotaLogListDto) {
        if (logisticsDailyQuotaLogListDto == null
                || logisticsDailyQuotaLogListDto.getSearch() == null
                || StringUtils.isBlank(logisticsDailyQuotaLogListDto.getSearch().getLogisticsId())) {
            throw new RestException(ResultCodeEnum.PARAM_ERROR);
        }
        try {

            RespLogisticsDailyQuotaLogListDto respLogisticsDailyQuotaListDto = new RespLogisticsDailyQuotaLogListDto();
            Page<RespLogisticsDailyQuotaLogListDto.LogisticsDailyQuotaItem> page = logisticsDailyQuotaLogService.selectPageList(logisticsDailyQuotaLogListDto.getSearch(), logisticsDailyQuotaLogListDto.getPage(), logisticsDailyQuotaLogListDto.getPageSize());
            respLogisticsDailyQuotaListDto.setData(page.getResult());
            respLogisticsDailyQuotaListDto.setPage(page.getPageNum());
            respLogisticsDailyQuotaListDto.setPageSize(page.getPageSize());
            respLogisticsDailyQuotaListDto.setTotalPages(page.getPages());
            respLogisticsDailyQuotaListDto.setTotalRows((int) page.getTotal());
            respLogisticsDailyQuotaListDto.setSearchItems(logisticsDailyQuotaLogListDto.getSearch());

            return RespJson.success(respLogisticsDailyQuotaListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="enterpriseQuota-export")
    @RequestMapping("/export")
    public void export(LogisticsQuotaListDto logisticsQuotaListDto, HttpServletResponse response) {
        try {
            List<RespLogisticsQuotaListDto.LogisticsQuotaItem> items = logisticsQuotaService.selectExportVoList(logisticsQuotaListDto.getSearch());
            List<ExportLogisticsQuotaVo> resList = new ArrayList<ExportLogisticsQuotaVo>();
            for (RespLogisticsQuotaListDto.LogisticsQuotaItem item : items) {
                ExportLogisticsQuotaVo quotaVo = new ExportLogisticsQuotaVo();
                BeanUtils.copyProperties(item, quotaVo);
                resList.add(quotaVo);
            }
            ExcelUtil<ExportLogisticsQuotaVo> eu = new ExcelUtil<ExportLogisticsQuotaVo>(ExportLogisticsQuotaVo.class);
            eu.exportExcel(resList, response);
        } catch (Exception e) {
            logger.error("导出文件文件出错，:{}", e.getMessage());
        }

    }

}
