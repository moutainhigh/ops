package com.jyblife.logic.wl.ops.manage.controller;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.OilPriceApplyStatusEnum;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceApplyDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceAuditListDto;
import com.jyblife.logic.wl.ops.manage.dto.req.ReqOilPriceAuditDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceAuditListDto;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.OilPriceApplyService;

/**
 * 油价审核类
 */
@RestController
@RequestMapping("/webAPI/oilPriceAudit")
@SuppressWarnings("unchecked")
public class WebApiOilPriceAuditController extends BaseController {

    @Autowired
    private OilPriceApplyService oilPriceApplyService;

    /**
     * 价格审核列表
     */
	@Permission(value = "oil-price-audit-list")
    @RequestMapping("/list")
    @ResponseBody
    public RespJson list(@RequestBody(required = false) OilPriceAuditListDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            boolean isCanCheck = isHadPermission(sessionId, "oil-price-audit-check");
            boolean isCanView = isHadPermission(sessionId, "oil-price-audit-detail");
            
            Page<RespOilPriceAuditListDto.OilPriceAuditItem> page = oilPriceApplyService.selectAuditList(dto.getSearch(), dto.getPage(), dto.getPageSize());
            page.getResult().stream().forEach(new Consumer<RespOilPriceAuditListDto.OilPriceAuditItem>() {
                @Override
                public void accept(RespOilPriceAuditListDto.OilPriceAuditItem item) {
                	item.setStatusName(OilPriceApplyStatusEnum.getText(item.getStatus()));
                	if(isCanCheck && item.getStatus().intValue() == OilPriceApplyStatusEnum.SUBMITED.getValue().intValue()) {
                		item.setIsCanCheck(isCanCheck);
                	}
                	item.setIsCanView(isCanView);
                }
            });

            RespOilPriceAuditListDto respDto = new RespOilPriceAuditListDto();
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
	 * 审核详情
	 * @param applyId
	 * @return
	 */
	@Permission(value = "oil-price-audit-detail")
    @RequestMapping("/detail")
    @ResponseBody
    public RespJson detail(@RequestParam("apply_id") Integer applyId) {
        try {
            OilPriceApplyDetailDto oilPriceApplyDetailDto = oilPriceApplyService.selectDetailByApplyId(applyId);
            return RespJson.success(oilPriceApplyDetailDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw e;
        }
    }
	
	/**
	 * 价格审核
	 * @param applyId
	 * @return
	 */
	@Permission(value = "oil-price-audit-check")
    @RequestMapping("/audit")
    @ResponseBody
    public RespJson audit(@RequestBody @Validated ReqOilPriceAuditDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
        	SystemUser user = getCurSystemUser(sessionId);
            return oilPriceApplyService.audit(dto, user);
        } catch (Exception e) {
            logger.error("价格审核异常:{}", e.getMessage());
            throw e;
        }
    }
	
}
