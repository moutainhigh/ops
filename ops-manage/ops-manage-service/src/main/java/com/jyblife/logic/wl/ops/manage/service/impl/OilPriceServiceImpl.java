package com.jyblife.logic.wl.ops.manage.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.utils.PoolUtils;
import com.jyblife.logic.wl.ops.entity.OilContact;
import com.jyblife.logic.wl.ops.entity.OilGoods;
import com.jyblife.logic.wl.ops.entity.OilPrice;
import com.jyblife.logic.wl.ops.entity.OilPriceAdjustMsg;
import com.jyblife.logic.wl.ops.entity.OilPriceApply;
import com.jyblife.logic.wl.ops.manage.dto.req.ReqOilPriceListDto.Search;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceListDto.OilPriceItem;
import com.jyblife.logic.wl.ops.manage.mapper.OilContactMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilGoodsMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilPriceAdjustMsgMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilPriceApplyMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilPriceMapper;
import com.jyblife.logic.wl.ops.manage.service.OilPriceService;
import com.jyblife.logic.wl.ops.message.constants.MessageTplEnum;
import com.jyblife.logic.wl.ops.message.dto.OilPriceSmsDto;
import com.jyblife.logic.wl.ops.message.task.SmsMessageTask;

@Service
@Transactional
public class OilPriceServiceImpl implements OilPriceService {

	public static Logger logger = LoggerFactory.getLogger(OilPriceServiceImpl.class);
	
	@Autowired
	private OilPriceMapper priceMapper;
	@Autowired
	private OilPriceAdjustMsgMapper msgMapper;
	@Autowired
	private OilContactMapper oilContactMapper;
	@Autowired
    private OilPriceApplyMapper oilPriceApplyMapper;
	@Autowired
    private OilGoodsMapper goodsMapper;
	
	@Override
	public Page<OilPriceItem> selectPageList(Search search, int pageNum, int pageSize) {
		Page<RespOilPriceListDto.OilPriceItem> page = PageHelper.startPage(pageNum, pageSize);
		priceMapper.selectPageList(search);
		return page;
	}

	@Override
	public RespJson execute(Integer priceId, Integer updateUserId) {
		Date cur_date = new Date();
		OilPrice oilPrice = priceMapper.selectByPrimaryKey(priceId);
		if(oilPrice == null) {
			return RespJson.paramError("价格信息不存在");
		} else if(oilPrice.getEffectTime() == null 
					|| cur_date.compareTo(oilPrice.getEffectTime()) >= 0 
					|| oilPrice.getEffectTime().compareTo(oilPrice.getEndTime()) >= 0) {
			return RespJson.paramError("价格信息非待生效状态");
		}
		
		OilPrice updateOilPrice = new OilPrice();
		updateOilPrice.setPriceId(priceId);
		updateOilPrice.setUpdateTime(cur_date);
		updateOilPrice.setUpdateUserId(updateUserId);
		
		BigDecimal zero = new BigDecimal("0");
		if (oilPrice.getAgreedPrice().compareTo(zero) > 0 && oilPrice.getDiscountPrice().compareTo(zero) > 0
                && oilPrice.getRetailPrice().compareTo(zero) > 0) {
			updateOilPrice.setEffectTime(cur_date);
			
			//先更新原生效价格失效
			OilPrice updateEndTime = new OilPrice();
			updateEndTime.setUpdateTime(cur_date);
			updateEndTime.setEndTime(cur_date);
			updateEndTime.setGoodsId(oilPrice.getGoodsId());
			updateEndTime.setStationId(oilPrice.getStationId());
			priceMapper.updateEndTimeByStationIdGoodsId(updateEndTime);
        } else {
        	updateOilPrice.setEndTime(cur_date);
		}
		priceMapper.updateByPrimaryKeySelective(updateOilPrice);
		
		//更新价格调整系统提示信息
		OilPriceAdjustMsg record = new OilPriceAdjustMsg();
		record.setEffectTime(cur_date);
		record.setPriceId(priceId);
		msgMapper.executeByPriceId(record);
		
		//发送价格生效短信提醒
        try {
        	OilContact contact = new OilContact();
        	contact.setStatus(1);
        	contact.setType(2);
        	contact.setCompanyId(oilPrice.getCompanyId());
    		contact.setStationId(oilPrice.getStationId());
    		OilContact oilContact = oilContactMapper.getByStationIdAndType(contact);
    		
    		if(oilContact != null && StringUtils.isNotBlank(oilContact.getPhone())) {
    			BigDecimal b100 = new BigDecimal("100");
    			OilPriceSmsDto sms = new OilPriceSmsDto();
    			
    			OilPriceApply oilPriceApply = oilPriceApplyMapper.selectByPrimaryKey(oilPrice.getApplyId());
    			sms.setPrice_code(oilPriceApply.getCode());
        		sms.setAgreed_price(oilPrice.getAgreedPrice().divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
        		
        		OilGoods oilGoods = goodsMapper.selectByPrimaryKey(oilPrice.getGoodsId());
        		if(oilGoods != null) {
        			sms.setGoods_name(oilGoods.getName());
        			PoolUtils.pool.execute(new SmsMessageTask(oilContact.getPhone(), (JSONObject)JSON.toJSON(sms), MessageTplEnum.OIL_PRICE_EXECUTED_IMMEDIATELY_TEMPATE));
        		}
    		}
		} catch (Exception e) {
			logger.error("发送价格生效短信提醒信息异常：" + e.getMessage());
		}
		
		return RespJson.success();
	}

	@Override
	public List<OilPriceItem> export(Search search) {
		return priceMapper.selectPageList(search);
	}
	
}
