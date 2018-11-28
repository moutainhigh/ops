package com.jyblife.logic.wl.ops.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.entity.LogisticsCompany;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsCompanyListDto;
import com.jyblife.logic.wl.ops.manage.mapper.LogisticsCompanyMapper;
import com.jyblife.logic.wl.ops.manage.service.LogisticsCompanyService;
import com.jyblife.logic.wl.ops.message.dto.PushLogisticsCompanyDto;
import com.jyblife.logic.wl.ops.message.mq.contants.MqContant;
import com.jyblife.logic.wl.ops.message.mq.entity.MqMessage;
import com.jyblife.logic.wl.ops.message.mq.producer.MqProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
@Service
@Transactional
public class LogisticsCompanyServiceImpl implements LogisticsCompanyService {
	
	protected Logger logger = LoggerFactory.getLogger(LogisticsCompanyServiceImpl.class);
	
    @Autowired
    private LogisticsCompanyMapper logisticsCompanyMapper;
    @Autowired
	private MqProducer mqProducer;
    
    @Override
    public void updateLogisticsCompany(LogisticsCompany logisticsCompany) {
        logisticsCompanyMapper.updateByPrimaryKeySelective(logisticsCompany);
        
        try {
        	//推送物流企业信息到财务系统
        	LogisticsCompany company = logisticsCompanyMapper.selectByPrimaryKey(logisticsCompany.getLogisticsId());
        	PushLogisticsCompanyDto req = new PushLogisticsCompanyDto();
        	req.setLogi_code(company.getLogisticsId().toString());
        	req.setLogistics_name(company.getName());
        	req.setStatus(logisticsCompany.getStatus() == 1 ? "1" : "2");
			
			MqMessage mqMessage = new MqMessage();
			mqMessage.setRetryTime(3000l);
			mqMessage.setContent(JSON.toJSONString(req));
			mqMessage.setExchange(MqContant.LOGISTICS_COMPANY_EXCHANGE);
			mqMessage.setRoutingKey(MqContant.LOGISTICS_COMPANY_ROUTINGKEY);
			mqProducer.sendDelayMessage(mqMessage);
		} catch (Exception e) {
			logger.error("发送推送物流企业mq信息异常：" + e.getMessage());
		}
    }

    @Override
    public int updateLogisticsName(LogisticsCompany logisticsCompany) {
    	// TODO Auto-generated method stub
    	Integer re =  logisticsCompanyMapper.updateById(logisticsCompany);
    	return re;
    }
    
    @Override
    public Page<LogisticsCompany> listPage(LogisticsCompanyListDto.Search search, Integer page, Integer pageSize) {
        Page<LogisticsCompany> pager = PageHelper.startPage(page, pageSize);
        logisticsCompanyMapper.listPage(search);
        return pager;
    }

    @Override
    public LogisticsCompany selectById(Long logisticsId) {
        return logisticsCompanyMapper.selectByPrimaryKey(logisticsId);
    }
}
