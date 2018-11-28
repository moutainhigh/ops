package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.manage.dto.OilAttendantListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilAttendantDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilAttendantListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.OilAttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.function.Consumer;

/**
 * @author yangcey
 * @date 2018/11/27
 **/
@RestController
@RequestMapping("/webAPI/oilAttendant")
public class WebApiOilAttendantController extends BaseController {
    @Autowired
    private OilAttendantService oilAttendantService;

    @Permission(value = "attendant-detail")
    @RequestMapping("/detail")
    @ResponseBody
    public RespJson detail(@RequestParam(value = "id") Integer id) {
        try {
            RespOilAttendantDto oilAttendantDto = oilAttendantService.selectDetailById(id);
            if (oilAttendantDto != null) {
                return RespJson.success(oilAttendantDto);
            }
            return RespJson.error(ResultCodeEnum.OILATTENDANT_NOT_EXIST);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value = "attendant-list")
    @RequestMapping("/list")
    @ResponseBody
    public RespJson list(@RequestBody(required = false) OilAttendantListDto attendantListDto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (attendantListDto == null) {
                attendantListDto = new OilAttendantListDto();
            }

            boolean isCanView = isHadPermission(sessionId, "attendant-detail");

            RespOilAttendantListDto respOilAttendantListDto = new RespOilAttendantListDto();
            Page<RespOilAttendantListDto.OilAttendantItem> page = oilAttendantService.selectPageList(attendantListDto.getSearch(), attendantListDto.getPage(), attendantListDto.getPageSize());
            page.getResult().stream().forEach(new Consumer<RespOilAttendantListDto.OilAttendantItem>() {
                @Override
                public void accept(RespOilAttendantListDto.OilAttendantItem oilAttendantItem) {
                    oilAttendantItem.setStatusName(StatusEnum.getText(StrUtils.byte2Int(oilAttendantItem.getStatus())));
                    oilAttendantItem.setIsCanView(isCanView);
                }
            });
            respOilAttendantListDto.setData(page.getResult());
            respOilAttendantListDto.setPage(page.getPageNum());
            respOilAttendantListDto.setPageSize(page.getPageSize());
            respOilAttendantListDto.setTotalPages(page.getPages());
            respOilAttendantListDto.setTotalRows((int) page.getTotal());
            respOilAttendantListDto.setSearchItems(attendantListDto.getSearch());

            return RespJson.success(respOilAttendantListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
}
