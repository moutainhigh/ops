package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.entity.OilGoods;
import com.jyblife.logic.wl.ops.manage.dto.OilGoodsListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.mapper.OilGoodsMapper;
import com.jyblife.logic.wl.ops.manage.service.OilGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangcey
 * @date 2018/9/20
 **/
@Service
@Transactional
public class OilGoodsServiceImpl implements OilGoodsService {
    @Autowired
    private OilGoodsMapper oilGoodsMapper;

    @Override
    public void saveOilGoods(OilGoods oilGoods) {
        if (oilGoodsMapper.selectByName(oilGoods.getName()) != null) {
            throw new ManageServiceException(ResultCodeEnum.OILGOODS_NAME_EXIST);
        }
        oilGoodsMapper.insert(oilGoods);
    }

    @Override
    public void updateOilGoods(OilGoods oilGoods) {
        OilGoods temp = oilGoodsMapper.selectByName(oilGoods.getName());
        if (temp != null && oilGoods.getGoodsId().intValue() != temp.getGoodsId().intValue()) {
            throw new ManageServiceException(ResultCodeEnum.OILGOODS_NAME_EXIST);
        }
        oilGoodsMapper.updateByPrimaryKeySelective(oilGoods);
    }

    @Override
    public OilGoods selectById(Integer goodsId) {
        return oilGoodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public Page<OilGoods> listOilPage(OilGoodsListDto.Search search, int page, int pageSize) {
        Page<OilGoods> pager = PageHelper.startPage(page, pageSize);
        oilGoodsMapper.listOilPage(search);
        return pager;
    }

    @Override
    public void deleteOilGoods(Integer goodsId) {
        oilGoodsMapper.deleteByPrimaryKey(goodsId);
    }

    @Override
    public Map<String, OilGoods> getAllOilGoodsMap() {
        List<OilGoods> list = oilGoodsMapper.list(OilGoods.class, new HashMap<String, Object>(0));
        Map<String, OilGoods> oilGoodsMap = new HashMap<String, OilGoods>();
        for (OilGoods oilGoods : list) {
            oilGoodsMap.put(oilGoods.getName().trim(), oilGoods);
        }
        return oilGoodsMap;
    }
}
