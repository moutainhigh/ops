package com.jyblife.logic.wl.ops.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.OilPriceApplyStatusEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.PoolUtils;
import com.jyblife.logic.wl.ops.entity.*;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceApplyDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceAuditListDto;
import com.jyblife.logic.wl.ops.manage.dto.req.ReqOilPriceAuditDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceAuditListDto;
import com.jyblife.logic.wl.ops.manage.mapper.*;

import com.jyblife.logic.wl.ops.manage.service.OilPriceApplyService;
import com.jyblife.logic.wl.ops.message.constants.MessageTplEnum;
import com.jyblife.logic.wl.ops.message.dto.OilPriceSmsDto;
import com.jyblife.logic.wl.ops.message.task.SmsMessageTask;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


@Service
@Transactional
public class OilPriceApplyServiceImpl implements OilPriceApplyService {
	
	public static Logger logger = LoggerFactory.getLogger(OilPriceApplyServiceImpl.class);
	
    @Autowired
    private OilPriceApplyMapper oilPriceApplyMapper;
    @Autowired
    private OilPriceItemMapper oilPriceItemMapper;
    @Autowired
    private OilPriceMapper oilPriceMapper;
    @Autowired
    private OilPriceApplyAttachmentMapper applyAttachmentMapper;
    @Autowired
    private OilGoodsMapper oilGoodsMapper;
    @Autowired
    private OilCompanyMapper oilCompanyMapper;
    @Autowired
    private OilStationMapper oilStationMapper;
    @Autowired
    private OilPriceApprovalLogMapper oilPriceApprovalLogMapper;
    @Autowired
    private OilGoodsMapper goodsMapper;
    @Autowired
	private OilContactMapper oilContactMapper;
    @Autowired
    private OilPriceAdjustMsgMapper oilPriceAdjustMsgMapper;

    @Override
    public int insertImportPrices(List<OilPriceItem> importList, OilPriceApply oilPriceApply, OilPriceApplyAttachment applyAttachment) {
        int i = oilPriceApplyMapper.insertPriceApply(oilPriceApply);
        importList.stream().forEach(new Consumer<OilPriceItem>() {
            @Override
            public void accept(OilPriceItem oilPriceItem) {
                oilPriceItem.setApplyId(oilPriceApply.getApplyId());
            }
        });
        oilPriceItemMapper.insertBatch(importList);

        applyAttachment.setBaseId(oilPriceApply.getApplyId());
        applyAttachmentMapper.updateByPrimaryKeySelective(applyAttachment);
        
        return i;
    }

    @Override
    public Page<RespOilPriceApplyListDto.OilPriceApplyItem> selectPageList(OilPriceApplyListDto.Search search, int page, int pageSize) {
        Page<RespOilPriceApplyListDto.OilPriceApplyItem> pager = PageHelper.startPage(page,pageSize);
        oilPriceApplyMapper.selectPageList(search);

        pager.getResult().stream().forEach(new Consumer<RespOilPriceApplyListDto.OilPriceApplyItem>() {
            @Override
            public void accept(RespOilPriceApplyListDto.OilPriceApplyItem oilPriceApplyItem) {
                oilPriceApplyItem.setStatusName(OilPriceApplyStatusEnum.getText(oilPriceApplyItem.getStatus()));
            }
        });
        return pager;
    }


    @Override
    public OilPriceApplyDetailDto selectDetailByApplyId(Integer applyId) {
        OilPriceApplyDetailDto oilPriceApplyDetailDto = new OilPriceApplyDetailDto();
        List<OilPriceItem> oilPriceItems = oilPriceItemMapper.selectByApplyId(applyId);

        List<OilPriceApplyDetailDto.OilPriceItem> priceItems = new ArrayList<OilPriceApplyDetailDto.OilPriceItem>();
        for (OilPriceItem item : oilPriceItems) {
            OilPriceApplyDetailDto.OilPriceItem temp = new OilPriceApplyDetailDto.OilPriceItem();
            BeanUtils.copyProperties(item, temp);
            //转换元
            if(item.getAgreedPrice()!=null){
				temp.setAgreedPrice(item.getAgreedPrice().divide(new BigDecimal(100)));
			}
			if(item.getDiscountPrice()!=null){
				temp.setDiscountPrice(item.getDiscountPrice().divide(new BigDecimal(100)));
			}
			if(item.getRetailPrice()!=null){
				temp.setRetailPrice(item.getRetailPrice().divide(new BigDecimal(100)));
			}

            OilGoods oilGoods = oilGoodsMapper.selectByPrimaryKey(item.getGoodsId());
            if (oilGoods != null) {
                temp.setGoodsName(oilGoods.getName());
            }
            OilCompany oilCompany = oilCompanyMapper.selectByPrimaryKey(item.getCompanyId());
            if (oilCompany != null) {
                temp.setOilCompanyName(oilCompany.getName());
            }
            OilStation oilStation = oilStationMapper.selectByPrimaryKey(item.getStationId());
            if (oilStation != null) {
                temp.setOilStationName(oilStation.getName());
            }
            priceItems.add(temp);
        }
        oilPriceApplyDetailDto.setPriceItems(priceItems);

        //设置审批记录
        List<OilPriceApplyDetailDto.OilPriceApproval> priceApprovalItems = new ArrayList<OilPriceApplyDetailDto.OilPriceApproval>();
        List<OilPriceApprovalLog> oilPriceApprovalLogs = oilPriceApprovalLogMapper.selectByApplyId(applyId);
        for (OilPriceApprovalLog item : oilPriceApprovalLogs) {
            OilPriceApplyDetailDto.OilPriceApproval temp = new OilPriceApplyDetailDto.OilPriceApproval();
            temp.setActionName(item.getAction().intValue() == OilPriceApplyStatusEnum.APPROVED.getValue().intValue() ? "同意" : "驳回");
            BeanUtils.copyProperties(item, temp);
            priceApprovalItems.add(temp);
        }

        oilPriceApplyDetailDto.setPriceApprovalItems(priceApprovalItems);

        return oilPriceApplyDetailDto;
    }

	@Override
	public Page<RespOilPriceAuditListDto.OilPriceAuditItem> selectAuditList(OilPriceAuditListDto.Search search, int pageNum, int pageSize) {
		Page<RespOilPriceAuditListDto.OilPriceAuditItem> page = PageHelper.startPage(pageNum, pageSize);
        oilPriceApplyMapper.selectAuditList(search);
        return page;
	}

    @Override
    public OilPriceApply selectByApplyId(Integer applyId) {
        return oilPriceApplyMapper.selectByPrimaryKey(applyId);
    }

    @Override
    public int updateOilPriceApply(List<OilPriceItem> importList, OilPriceApply oilPriceApply, OilPriceApplyAttachment applyAttachment) {

        Integer applyId = oilPriceApply.getApplyId();

        //删除原有的priceItem
        oilPriceItemMapper.deleteByApplyId(applyId);

        //将原有的附件设置为删除状态-1
        OilPriceApplyAttachment oldAttachment = new OilPriceApplyAttachment();
        oldAttachment.setStatus(-1);
        oldAttachment.setBaseId(applyId);
        oldAttachment.setUpdateTime(applyAttachment.getUpdateTime());
        oldAttachment.setUpdateUserId(applyAttachment.getUpdateUserId());
        applyAttachmentMapper.updateStatusByApplyId(oldAttachment);

        //插入新的priceItems
        importList.stream().forEach(new Consumer<OilPriceItem>() {
            @Override
            public void accept(OilPriceItem oilPriceItem) {
                oilPriceItem.setApplyId(oilPriceApply.getApplyId());
            }
        });
        oilPriceItemMapper.insertBatch(importList);

        //更新priceApply
        oilPriceApplyMapper.updateByPrimaryKeySelective(oilPriceApply);

        //更新 新的applyattachement
        applyAttachment.setBaseId(applyId);
        applyAttachmentMapper.updateByPrimaryKeySelective(applyAttachment);

        return 0;
    }

	@Override
	public RespJson audit(ReqOilPriceAuditDto dto, SystemUser user) {
		Date cur_date = new Date();
		int action = dto.getAction().intValue();
		if(action != OilPriceApplyStatusEnum.APPROVED.getValue().intValue() && action != OilPriceApplyStatusEnum.REJECTED.getValue().intValue()) {
			return RespJson.paramError("无效得action参数");
		}
		OilPriceApply oilPriceApply = oilPriceApplyMapper.selectByPrimaryKey(dto.getApplyId());
		if(oilPriceApply == null) {
			return RespJson.paramError("油价申请信息不存在");
		} else if(oilPriceApply.getStatus().intValue() != OilPriceApplyStatusEnum.SUBMITED.getValue().intValue()) {
			return RespJson.paramError("当前油价申请信息非待审核状态");
		}
		
		Integer applyUserId = oilPriceApply.getCreateUserId();
		oilPriceApply = new OilPriceApply();
		oilPriceApply.setApplyId(dto.getApplyId());
		oilPriceApply.setStatus(action);
		oilPriceApply.setUpdateTime(cur_date);
		oilPriceApply.setUpdateUserId(user.getUserId());
		oilPriceApplyMapper.updateByPrimaryKeySelective(oilPriceApply);
		
		OilPriceApprovalLog log = new OilPriceApprovalLog();
		log.setAction(action);
		log.setApplyId(dto.getApplyId());
		log.setCreateTime(new Date());
		log.setOperatorId(user.getUserId());
		log.setOperatorName(user.getUserName());
		log.setRemark(dto.getOpinion());
		oilPriceApprovalLogMapper.insertSelective(log);
		
		//审批通过添加油价记录
		if(action == OilPriceApplyStatusEnum.APPROVED.getValue().intValue()) {
			OilPrice updatePrice =  new OilPrice();
	        BigDecimal zero = new BigDecimal("0");
	        List<OilPrice> oilPrices = new ArrayList<OilPrice>();
	        
	        List<OilPriceItem> priceItems = oilPriceItemMapper.selectByApplyId(dto.getApplyId());
	        for (OilPriceItem item : priceItems) {
	            OilPrice oilPrice = new OilPrice();
	            BeanUtils.copyProperties(item, oilPrice);
	            oilPrice.setStatusTime(cur_date);
	            oilPrice.setStatus(StatusEnum.ENABLE.getValue());
	            oilPrice.setCreateTime(cur_date);
	            oilPrice.setUpdateTime(cur_date);
	            oilPrice.setCreateUserId(applyUserId);
	            oilPrice.setUpdateUserId(user.getUserId());
	            
	            if (oilPrice.getAgreedPrice().compareTo(zero) > 0 && oilPrice.getDiscountPrice().compareTo(zero) > 0
	                    && oilPrice.getRetailPrice().compareTo(zero) > 0) {
	                oilPrice.setEffectTime(DateUtil.getBeginDayOfTomorrow());
	                oilPrice.setEndTime(DateUtil.getForeverTime());
	            } else {
	            	oilPrice.setEffectTime(null);
	            	oilPrice.setEndTime(DateUtil.getDayEnd());
				}
	            oilPrices.add(oilPrice);

	            //更新已生效数据
	            updatePrice.setStationId(oilPrice.getStationId());
	            updatePrice.setGoodsId(oilPrice.getGoodsId());
	            updatePrice.setEndTime(DateUtil.getDayEnd());
	            updatePrice.setUpdateTime(cur_date);
	            oilPriceMapper.updateEndTimeByStationIdGoodsId(updatePrice);
	            
	            //更新待生效数据
	            oilPriceMapper.updateUneffectByStationIdGoodsId(updatePrice);
	        }
	        oilPriceMapper.insertOilPriceBatch(oilPrices);
	        
	        BigDecimal b100 = new BigDecimal("100");
	        //添加系统消息提醒记录
	        //xxx油站xx油品零售价由xx上调或者下降xx，优惠价由xx上调或下降xx
	        try {
	        	String msg = "";
	        	boolean flag = false;
	        	List<OilPriceAdjustMsg> msgList = new ArrayList<>();
	        	for(OilPrice item : oilPrices) {
	        		flag = false;
	        		if(item.getEffectTime() == null) {
	        			continue;
	        		}
	        		
	        		Map<String, Object> info = oilPriceMapper.getCurStationGoodsPriceInfo(item);
	        		if(info == null) {
	        			continue;
	        		}
	        		BigDecimal retailPrice = new BigDecimal(info.get("retailPrice").toString());
	        		BigDecimal discountPrice = new BigDecimal(info.get("discountPrice").toString());
	        		
	        		msg = info.get("stationName").toString() + info.get("goodsName").toString();
	        		if(item.getRetailPrice().compareTo(retailPrice) != 0) {
	        			flag = true;
	        			msg = msg + "零售价由" + retailPrice.divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString();
	        			msg = msg + (item.getRetailPrice().compareTo(retailPrice) > 0 ? "上调" : "下降") + item.getRetailPrice().subtract(retailPrice).abs().divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString();
	        		}
	        		if(item.getDiscountPrice().compareTo(discountPrice) != 0) {
	        			msg = msg + (flag ? "，" : "") + "优惠价由" + discountPrice.divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString();
	        			msg = msg + (item.getDiscountPrice().compareTo(discountPrice) > 0 ? "上调" : "下降") + item.getDiscountPrice().subtract(discountPrice).abs().divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString();
	        			flag = true;
	        		}
	        		
	        		if(flag) {
	        			OilPriceAdjustMsg record = new OilPriceAdjustMsg();
		        		record.setCompanyId(item.getCompanyId());
		        		record.setContent(msg);
		        		record.setCreateTime(cur_date);
		        		record.setEffectTime(item.getEffectTime());
		        		record.setPriceId(item.getPriceId());
		        		record.setStationId(item.getStationId());
	        			msgList.add(record);
	        		}
	        	}
	        	if(msgList.size() > 0) {
	        		oilPriceAdjustMsgMapper.insertBatch(msgList);
	        	}
	        } catch (Exception e) {
				logger.error("添加油价调整系统消息提醒记录异常：" + e.getMessage());
			}
	        
	        //发送价格审批通过提醒
	        try {
	        	OilContact record = new OilContact();
	        	record.setStatus(1);
	        	record.setType(2);
    			
	        	for(OilPrice price : oilPrices) {
	        		OilPriceSmsDto sms = new OilPriceSmsDto();
	        		sms.setPrice_code(oilPriceApply.getCode());
	        		sms.setAgreed_price(price.getAgreedPrice().divide(b100, 2, BigDecimal.ROUND_HALF_UP).toString());
	        		
	        		record.setCompanyId(price.getCompanyId());
	    			record.setStationId(price.getStationId());
	    			
	        		OilGoods oilGoods = goodsMapper.selectByPrimaryKey(price.getGoodsId());
	        		if(oilGoods != null) {
	        			sms.setGoods_name(oilGoods.getName());
	        			
	        			OilContact oilContact = oilContactMapper.getByStationIdAndType(record);
	        			if(oilContact != null && StringUtils.isNotBlank(oilContact.getPhone())) {
	        				PoolUtils.pool.execute(new SmsMessageTask(oilContact.getPhone(), (JSONObject)JSON.toJSON(sms), MessageTplEnum.OIL_PRICE_APPROVE_TEMPATE));
	        			}
	        		}
	        	}
			} catch (Exception e) {
				logger.error("发送油品价格审批通过提醒短信信息异常：" + e.getMessage());
			}
	        
		}
		
		return RespJson.success();
	}
	
}