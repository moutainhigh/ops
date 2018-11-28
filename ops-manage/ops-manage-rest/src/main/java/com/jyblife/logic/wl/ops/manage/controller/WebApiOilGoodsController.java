package com.jyblife.logic.wl.ops.manage.controller;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.OilGoods;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.OilGoodsListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilGoodsSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilGoodsDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilGoodsListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.OilGoodsService;
import com.jyblife.logic.wl.ops.manage.service.SystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/20
 **/
@RestController
@RequestMapping("/webAPI/oilGoods")
public class WebApiOilGoodsController extends BaseController {

    @Autowired
    private OilGoodsService oilGoodsService;
    @Autowired
    private SystemUserService systemUserService;

    @Permission(value="oil-goods-detail")
    @RequestMapping("/detail")
    public RespJson detail(@RequestParam(value = "goods_id") Integer goodsId) {
        try {
            OilGoods oilGoods = oilGoodsService.selectById(goodsId);
            if (oilGoods != null) {
                RespOilGoodsDetailDto respOilGoodsDetailDto = new RespOilGoodsDetailDto();
                BeanUtils.copyProperties(oilGoods, respOilGoodsDetailDto);
                respOilGoodsDetailDto.setSort(StrUtils.int2Str(oilGoods.getOrderIndex()));
                respOilGoodsDetailDto.setStatusName(StatusEnum.getText(oilGoods.getStatus()));

                SystemUser updater = systemUserService.selectByPrimaryKey(oilGoods.getUpdateUserId());
                SystemUser creator = systemUserService.selectByPrimaryKey(oilGoods.getCreateUserId());

                if (updater != null) {
                    respOilGoodsDetailDto.setUpdateUserName(updater.getUserName());
                }
                if (creator != null) {
                    respOilGoodsDetailDto.setCreateUserName(creator.getUserName());
                }
                return RespJson.success(respOilGoodsDetailDto);
            }
            return RespJson.error(ResultCodeEnum.OILGOODS_NOT_EXIST);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="oil-goods-list")
    @RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) OilGoodsListDto oilGoodsListDto, 
    					 @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (oilGoodsListDto == null) {
                oilGoodsListDto = new OilGoodsListDto();
            }
            
            Page<OilGoods> page = oilGoodsService.listOilPage(oilGoodsListDto.getSearch(), oilGoodsListDto.getPage(), oilGoodsListDto.getPageSize());
            RespOilGoodsListDto respOilGoodsListDto = new RespOilGoodsListDto();

            List<RespOilGoodsListDto.GoodsItem> goodsItems = new ArrayList<RespOilGoodsListDto.GoodsItem>();
            for (OilGoods oilGoods : page.getResult()) {
                RespOilGoodsListDto.GoodsItem goodsItem = new RespOilGoodsListDto.GoodsItem();
                goodsItem.setGoodsId(StrUtils.int2Str(oilGoods.getGoodsId()));
                goodsItem.setName(oilGoods.getName());
                goodsItem.setCode(oilGoods.getCode());
                goodsItem.setSort(StrUtils.int2Str(oilGoods.getOrderIndex()));
                goodsItem.setRemark(oilGoods.getRemark());
                goodsItem.setStatus(StrUtils.int2Str(oilGoods.getStatus()));
                goodsItem.setStatusName(StatusEnum.getText(oilGoods.getStatus()));
                
                goodsItem.setIsCanEdit(isHadPermission(sessionId, "oil-goods-edit"));
                goodsItem.setIsCanView(isHadPermission(sessionId, "oil-goods-detail"));
                goodsItems.add(goodsItem);
            }

            respOilGoodsListDto.setData(goodsItems);
            respOilGoodsListDto.setPage(page.getPageNum());
            respOilGoodsListDto.setPageSize(page.getPageSize());
            respOilGoodsListDto.setTotalPages(page.getPages());
            respOilGoodsListDto.setTotalRows((int) page.getTotal());
            respOilGoodsListDto.setSearchItems(oilGoodsListDto.getSearch());
            return RespJson.success(respOilGoodsListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="oil-goods-save")
    @RequestMapping("/save")
    public RespJson save(@RequestBody @Validated OilGoodsSaveDto oilGoodsSaveDto,
                         @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {

            OilGoods oilGoods = new OilGoods();
            BeanUtils.copyProperties(oilGoodsSaveDto, oilGoods);
            if(oilGoodsSaveDto.getSort() != null) {
            	oilGoods.setOrderIndex(oilGoodsSaveDto.getSort());
            }
            
            SystemUser currentUser = this.getCurSystemUser(sessionId);
            if (oilGoodsSaveDto.getGoodsId() == null) {
                oilGoods.setCreateTime(new Date());
                oilGoods.setUpdateTime(new Date());
                if (currentUser != null) {
                    oilGoods.setUpdateUserId(currentUser.getUserId());
                    oilGoods.setCreateUserId(currentUser.getUserId());
                }
                oilGoodsService.saveOilGoods(oilGoods);
            } else {
                if (currentUser != null) {
                    oilGoods.setUpdateUserId(currentUser.getUserId());
                }
                oilGoods.setUpdateTime(new Date());

                oilGoodsService.updateOilGoods(oilGoods);
            }

            return RespJson.success();
        } catch (OpsException e) {
            logger.error("异常:{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }
}
