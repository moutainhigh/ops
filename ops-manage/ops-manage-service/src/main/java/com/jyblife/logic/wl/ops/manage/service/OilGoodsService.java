package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.entity.OilGoods;
import com.jyblife.logic.wl.ops.manage.dto.OilGoodsListDto;

import java.util.Map;

/**
 * @author yangcey
 * @date 2018/9/20
 **/
public interface OilGoodsService {

    void saveOilGoods(OilGoods oilGoods);

    void updateOilGoods(OilGoods oilGoods);

    OilGoods selectById(Integer goodsId);

    Page<OilGoods> listOilPage(OilGoodsListDto.Search search, int page, int pageSize);

    void deleteOilGoods(Integer goodsId);

    Map<String, OilGoods> getAllOilGoodsMap();
}
