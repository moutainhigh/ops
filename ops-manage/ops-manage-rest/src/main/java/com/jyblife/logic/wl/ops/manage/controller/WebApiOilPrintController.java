package com.jyblife.logic.wl.ops.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.PrintUtils;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.manage.dto.OilPrintListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPrintSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPrintListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPrintListDto.PrintItem;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.OilPrintService;

@RestController
@RequestMapping("/webAPI/oilPrint")
public class WebApiOilPrintController extends BaseController {
	
	@Autowired
	private OilPrintService oilPrintServiceImpl;
	
	@Permission(value="oil-print-list")
	@RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) OilPrintListDto dto) {
		if (dto == null) {
			dto = new OilPrintListDto();
        }
		
		try {
			Page<PrintItem> page = oilPrintServiceImpl.listPage(dto.getSearch(), dto.getPage(), dto.getPageSize());
			RespOilPrintListDto respData = new RespOilPrintListDto();
			List<RespOilPrintListDto.PrintItem> items = page.getResult();
			for(RespOilPrintListDto.PrintItem item : items) {
				item.setToBePrint(PrintUtils.printNums(item.getPrintSn()));
				item.setStatus(PrintUtils.printStatus(item.getPrintSn()));
			}			
	
			respData.setData(items);
			respData.setPage(page.getPageNum());
			respData.setPageSize(page.getPageSize());
			respData.setTotalPages(page.getPages());
			respData.setTotalRows((int) page.getTotal());
			respData.setSearchItems(dto.getSearch());
			return RespJson.success(respData);
		} catch (Exception e) {
			logger.error("打印机添加异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	@Permission(value="oil-print-detail")
	@RequestMapping("/detail")
	public RespJson detail(@RequestParam(value = "print_id") String printId) {
		try {
			return oilPrintServiceImpl.detail(printId);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	

	@Permission(value="oil-print-save")
	@RequestMapping("/save")
	public RespJson save(@RequestBody @Validated OilPrintSaveDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
		
		try {
			Integer userId = getCurUserId(sessionId);
			return oilPrintServiceImpl.save(dto, userId);
		} catch (Exception e) {
			logger.error("打印机数据变更异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	

	@Permission(value="oil-print-delete")
	@RequestMapping("/delete")
	public RespJson delete(@RequestParam(value = "print_id") String printId) {
		
		try {
			oilPrintServiceImpl.delete(StrUtils.str2Int(printId));
            return RespJson.success();
		} catch (Exception e) {
			logger.error("打印机删除异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
}
