package com.jyblife.logic.wl.ops.manage.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.utils.ExcelUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.manage.dto.req.ReqOilPriceListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceListDto.OilPriceItem;
import com.jyblife.logic.wl.ops.manage.export.ExportOilPriceVo;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.OilPriceService;

@RestController
@RequestMapping("/webAPI/oilPrice")
@SuppressWarnings("unchecked")
public class WebApiOilPriceController extends BaseController {

    @Autowired
    private OilPriceService oilPriceService;

    /**
     * 价格列表
     */
	@Permission(value = "oil-price-list")
    @RequestMapping("/list")
    @ResponseBody
    public RespJson list(@RequestBody(required = false) ReqOilPriceListDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            boolean isCanExecute = isHadPermission(sessionId, "oil-price-execute");
            BigDecimal b100 = new BigDecimal("100");
            Date cur_date = new Date();
            
            Page<RespOilPriceListDto.OilPriceItem> page = oilPriceService.selectPageList(dto.getSearch(), dto.getPage(), dto.getPageSize());
            page.getResult().stream().forEach(new Consumer<RespOilPriceListDto.OilPriceItem>() {
                @Override
                public void accept(RespOilPriceListDto.OilPriceItem item) {
                	item.setAgreedPrice(new BigDecimal(item.getAgreedPrice()).divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
                	item.setDiscountPrice(new BigDecimal(item.getDiscountPrice()).divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
                	item.setRetailPrice(new BigDecimal(item.getRetailPrice()).divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
                	
                	Date effectTime = item.getEffectTime();
                	Date endTime = item.getEndTime();
                	if(effectTime != null && cur_date.before(effectTime) && effectTime.before(endTime) && StatusEnum.ENABLE.getValue().equals(StrUtils.str2Int(item.getStatus()))) {
                		item.setStatus("待生效");
                		item.setIsCanExecute(isCanExecute);
                	} else if(effectTime == null || endTime.before(cur_date) || StatusEnum.DISABLE.getValue().equals(StrUtils.str2Int(item.getStatus()))) {
                		item.setStatus("已失效");
                	} else {
                		item.setStatus("已生效");
                	}
                }
            });

            RespOilPriceListDto respDto = new RespOilPriceListDto();
            respDto.setData(page.getResult());
            respDto.setPage(page.getPageNum());
            respDto.setPageSize(page.getPageSize());
            respDto.setSearchItems(dto.getSearch());
            respDto.setTotalPages(page.getPages());
            respDto.setTotalRows((int) page.getTotal());
            return RespJson.success(respDto);
        } catch (OpsException e) {
            throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw e;
        }
    }
	
	/**
	 * 立即执行
	 * @param priceId
	 * @return
	 */
	@Permission(value = "oil-price-execute")
    @RequestMapping("/execute")
    @ResponseBody
    public RespJson execute(@RequestParam("price_id") Integer priceId, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
        	Integer userId = getCurUserId(sessionId);
            return oilPriceService.execute(priceId, userId);
        } catch (Exception e) {
            logger.error("立即执行价格异常:{}", e.getMessage());
            throw e;
        }
    }
	
	/**
	 * 导出
	 * @param dto
	 * @param response
	 */
	@Permission(value="oil-price-export")
    @RequestMapping("/export")
    public void export(ReqOilPriceListDto dto, HttpServletResponse response) {
        try {
        	BigDecimal b100 = new BigDecimal("100");
            Date cur_date = new Date();
            Date effectTime = null;
            Date endTime = null;
            List<ExportOilPriceVo> expList = new ArrayList<>();
            
        	List<OilPriceItem> dataList = oilPriceService.export(dto.getSearch());
        	for(OilPriceItem item : dataList) {
        		ExportOilPriceVo vo = new ExportOilPriceVo();
        		BeanUtils.copyProperties(item, vo);
        		
        		vo.setAgreedPrice(new BigDecimal(item.getAgreedPrice()).divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
        		vo.setDiscountPrice(new BigDecimal(item.getDiscountPrice()).divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
        		vo.setRetailPrice(new BigDecimal(item.getRetailPrice()).divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
            	
            	effectTime = item.getEffectTime();
            	endTime = item.getEndTime();
            	if(effectTime != null && cur_date.before(effectTime) && StatusEnum.ENABLE.getValue().equals(StrUtils.str2Int(item.getStatus()))) {
            		vo.setStatus("待生效");
            	} else if(effectTime == null || endTime.before(cur_date) || StatusEnum.DISABLE.getValue().equals(StrUtils.str2Int(item.getStatus()))) {
            		vo.setStatus("已失效");
            	} else {
            		vo.setStatus("已生效");
            	}
            	
            	expList.add(vo);
        	}
        	
        	ExcelUtil<ExportOilPriceVo> eu = new ExcelUtil<ExportOilPriceVo>(ExportOilPriceVo.class);
            eu.exportExcel(expList, response);
            
        } catch (Exception e) {
            logger.error("导出价格列表出错 : {}", e.getMessage());
        }
    }
	
}
