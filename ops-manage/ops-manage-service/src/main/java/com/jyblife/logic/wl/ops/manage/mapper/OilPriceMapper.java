package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;
import java.util.Map;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilPrice;
import com.jyblife.logic.wl.ops.manage.dto.req.ReqOilPriceListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceListDto;

public interface OilPriceMapper extends BaseMapper<OilPrice,Integer> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param priceId
     * @return int
     */
    int deleteByPrimaryKey(Integer priceId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilPrice record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilPrice record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param priceId
     * @return OilPrice
     */
    OilPrice selectByPrimaryKey(Integer priceId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilPrice record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilPrice record);

    OilPrice selectByGoodsId(Integer goodsId);
    
    int updateEndTimeByStationIdGoodsId(OilPrice record);
    
    int updateUneffectByStationIdGoodsId(OilPrice record);
    
    /**
     * 获取该油站油品得最新有效价格
     * @param param
     * @return
     */
    OilPrice getEffectByStationIdAndGoodsId(Map<String, Object> param);
    
    /**
     * 列表查询
     * @param search
     * @return
     */
    List<RespOilPriceListDto.OilPriceItem> selectPageList(ReqOilPriceListDto.Search search);
    
    int insertOilPriceBatch(List<OilPrice> list);
    
    /**
     * 获取当前价格调整得最新信息
     * @return
     */
    Map<String, Object> getCurStationGoodsPriceInfo(OilPrice record);
    
}