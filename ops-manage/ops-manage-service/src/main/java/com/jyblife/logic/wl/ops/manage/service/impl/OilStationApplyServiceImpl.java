package com.jyblife.logic.wl.ops.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.OilStationApplyStatusEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.AreaCode;
import com.jyblife.logic.wl.ops.entity.OilCompany;
import com.jyblife.logic.wl.ops.entity.OilStation;
import com.jyblife.logic.wl.ops.entity.OilStationApply;
import com.jyblife.logic.wl.ops.entity.OilStationApplyAttachment;
import com.jyblife.logic.wl.ops.entity.OilStationAttachment;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplySaveDto;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplySaveDto.file;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyDetailDto.File;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.mapper.AreaCodeMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilCompanyMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilStationApplyAttachmentMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilStationApplyMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilStationAttachmentMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilStationMapper;
import com.jyblife.logic.wl.ops.manage.service.OilStationApplyService;
import com.jyblife.logic.wl.ops.message.dto.PushOilStationDto;
import com.jyblife.logic.wl.ops.message.mq.contants.MqContant;
import com.jyblife.logic.wl.ops.message.mq.entity.MqMessage;
import com.jyblife.logic.wl.ops.message.mq.producer.MqProducer;

@Service
@Transactional
public class OilStationApplyServiceImpl implements OilStationApplyService {

	protected Logger logger = LoggerFactory.getLogger(OilStationApplyServiceImpl.class);
	
	@Autowired
	private OilStationApplyMapper mapper;
	@Autowired
	private OilStationApplyAttachmentMapper applyAttMapper;
	@Autowired
	private OilStationMapper oilStationMapper;
	@Autowired
	private OilStationAttachmentMapper attMapper;
	@Autowired
	private AreaCodeMapper areaCodeMapper;
	@Autowired
	private OilCompanyMapper oilCompanyMapper;
	@Autowired
	private MqProducer mqProducer;
	
	@Override
	public Page<RepOilStationApplyListDto.OilStationApplyItem> listPage(OilStationApplyListDto.Search search, int page, int pageSize) {
		Page<RepOilStationApplyListDto.OilStationApplyItem> pager = PageHelper.startPage(page, pageSize);
        mapper.listPage(search);
        return pager;
	}

	@Override
	public RespJson save(OilStationApplySaveDto dto, int userId) throws ManageServiceException {
		OilStationApply apply = new OilStationApply();
		BeanUtils.copyProperties(dto, apply);
		Date curDate = new Date();
		apply.setUpdateUserId(userId);
		apply.setUpdateTime(curDate);
		
		//保存申请记录
		if(dto.getApplyId() == null) {
			List<OilStationApply> list = mapper.selectByName(dto.getName(), null);
			if(list != null && list.size() > 0) {
				return RespJson.error(ResultCodeEnum.OILSTATION_EXIST);
			}
			
			apply.setStatus(dto.getIsSubmit() ? OilStationApplyStatusEnum.APPROVED.getValue() : OilStationApplyStatusEnum.SAVED.getValue());
			apply.setCreateUserId(userId);
			apply.setCreateTime(curDate);
			apply.setStatusTime(curDate);
			apply.setEffectTime(curDate);
			mapper.insertStationApply(apply);
		} else {
			List<OilStationApply> list = mapper.selectByName(dto.getName(), dto.getCompanyId());
			if(list != null && list.size() > 0) {
				for(OilStationApply oa : list) {
					if(!oa.getApplyId().equals(dto.getApplyId())) {
						return RespJson.error(ResultCodeEnum.OILSTATION_COMPANY_EXIST);
					}
				}
			}
			mapper.updateByPrimaryKeySelective(apply);
		}
		
		int applyId = apply.getApplyId();
		//保存申请附件记录
		OilStationApplyAttachment applyAttachment = null;
		List<file> files = dto.getFiles();
		if(files != null && files.size() > 0) {
			for(file f : files) {
				applyAttachment = new OilStationApplyAttachment();
				BeanUtils.copyProperties(f, applyAttachment);
				applyAttachment.setBaseId(applyId);
				applyAttachment.setStatus("success".equals(f.getStatus()) ? 1 : -1);
				applyAttachment.setFileUrl(f.getUrl());
				applyAttMapper.updateByPrimaryKeySelective(applyAttachment);
			}
		}
		
		//保存油站数据
		if(dto.getIsSubmit()) {
			OilStation oilStation = new OilStation();
			BeanUtils.copyProperties(apply, oilStation);
			oilStation.setApplyId(applyId);
			oilStation.setStationId(applyId);
			oilStation.setStatus(StatusEnum.ENABLE.getValue());
			if(dto.getApplyId() == null) {
				oilStation.setStatusTime(curDate);
				oilStation.setEffectTime(curDate);
				oilStationMapper.insertSelective(oilStation);
			} else {
				oilStationMapper.updateByPrimaryKeySelective(oilStation);
			}
			
			//保存油站附件
			Map<String, Object> param = new HashMap<>();
			param.put("baseId", applyId);
			param.put("status", 1);
			List<OilStationApplyAttachment> dataList = applyAttMapper.list(OilStationApplyAttachment.class, param);
			if(dataList != null && dataList.size() > 0) {
				//删除原有
				param.clear();
				param.put("baseId", applyId);
				attMapper.deleteWithField(OilStationAttachment.class, param);
				
				OilStationAttachment attachment = null;
				List<OilStationAttachment> insertList = new ArrayList<>();
				for(OilStationApplyAttachment at : dataList) {
					attachment = new OilStationAttachment();
					BeanUtils.copyProperties(at, attachment);
					attachment.setId(null);
					insertList.add(attachment);
				}
				
				attMapper.insertBatch(insertList);
			}
			
			try {
				//推送油站信息到财务系统
				PushOilStationDto p = new PushOilStationDto();
				p.setOil_code(StrUtils.int2Str(oilStation.getStationId()));
				p.setOil_name(oilStation.getName());
				p.setOil_parent_code(StrUtils.int2Str(dto.getCompanyId()));
				p.setAddress(dto.getAddress());
				p.setStatus(StrUtils.int2Str(oilStation.getStatus()));
				p.setPayment_fee_rate(dto.getRate().toString());
				
				OilCompany oilCompany = oilCompanyMapper.selectByPrimaryKey(dto.getCompanyId());
				p.setOil_parent_name(oilCompany.getName());
				
				AreaCode areaCode = areaCodeMapper.selectByPrimaryKey(dto.getCityId());
				p.setCity(areaCode.getAreaName());
				
				MqMessage mqMessage = new MqMessage();
				mqMessage.setRetryTime(3000l);
				mqMessage.setContent(JSON.toJSONString(p));
				mqMessage.setExchange(MqContant.OIL_STATION_EXCHANGE);
				mqMessage.setRoutingKey(MqContant.OIL_STATION_ROUTINGKEY);
				mqProducer.sendDelayMessage(mqMessage);
			} catch (Exception e) {
				logger.error("发送推送油站mq信息异常：" + e.getMessage());
			}
			
		}
		
		return RespJson.success(applyId);
	}

	@Override
	public RespJson detail(Integer applyId) {
		
		RepOilStationApplyDetailDto detailDto = mapper.selectByApplyId(applyId);
		if(detailDto != null) {
			detailDto.setStatusName(OilStationApplyStatusEnum.getText(detailDto.getStatus()));
			
			Map<String, Object> param = new HashMap<>();
			param.put("baseId", applyId);
			param.put("status", 1);
			List<OilStationApplyAttachment> dataList = applyAttMapper.list(OilStationApplyAttachment.class, param);
			
			if(dataList != null && dataList.size() > 0) {
				for(OilStationApplyAttachment at : dataList) {
					RepOilStationApplyDetailDto.File f = new File();
					BeanUtils.copyProperties(at, f);
					f.setUrl(at.getFileUrl());
					detailDto.getFiles().add(f);
				}
				
			}
		}
		
		return RespJson.success(detailDto);
	}

	@Override
	public RespJson delFile(int fileId, int userId) throws ManageServiceException {
		OilStationApplyAttachment att = new OilStationApplyAttachment();
		att.setUpdateUserId(userId);
		att.setUpdateTime(new Date());
		att.setId(fileId);
		att.setStatus(-1);
		applyAttMapper.updateByPrimaryKeySelective(att);
		return RespJson.success();
	}

	@Override
	public OilStationApplyAttachment selectAttachmentByFileId(int fileId) {
		return applyAttMapper.get(OilStationApplyAttachment.class, fileId);
	}

	@Override
	public int insertAttachment(OilStationApplyAttachment attachment) {
		return applyAttMapper.insertAttachment(attachment);
	}

}
