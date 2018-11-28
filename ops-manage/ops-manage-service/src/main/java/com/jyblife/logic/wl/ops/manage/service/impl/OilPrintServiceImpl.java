package com.jyblife.logic.wl.ops.manage.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.entity.OilPrint;
import com.jyblife.logic.wl.ops.entity.OilStation;
import com.jyblife.logic.wl.ops.manage.dto.OilPrintListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPrintSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPrintListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.mapper.OilPrintMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilStationMapper;
import com.jyblife.logic.wl.ops.manage.service.OilPrintService;

@Service
@Transactional
public class OilPrintServiceImpl implements OilPrintService {

	protected Logger logger = LoggerFactory.getLogger(OilPrintServiceImpl.class);

	@Autowired
	private OilPrintMapper mapper;
	
	@Autowired
	private OilStationMapper oilStatioMapper;

	@Override
	public Page<RespOilPrintListDto.PrintItem> listPage(OilPrintListDto.Search search, int page, int pageSize) {
		Page<RespOilPrintListDto.PrintItem> pager = PageHelper.startPage(page, pageSize);
		mapper.listPage(search);
		return pager;
	}

	@Override
	public RespJson detail(String printId) {
		OilPrint oilPrint = mapper.selectByPrintId(printId);
		return RespJson.success(oilPrint);
	}


	@Override
	public RespJson save(OilPrintSaveDto dto, int userId) throws ManageServiceException {
		OilPrint oilPrint = new OilPrint();
		BeanUtils.copyProperties(dto, oilPrint);
		Date curDate = new Date();
		oilPrint.setUpdateUserId(userId);
		oilPrint.setUpdateTime(curDate);

		OilStation oilStation = oilStatioMapper.selectByPrimaryKey(dto.getStationId());
		String oilStationName = oilStation.getName();
		oilPrint.setStationName(oilStationName);
		
		//保存申请记录
		if(oilPrint.getPrintId() == null) {
			Integer printSn = mapper.selectByPrintSn(dto.getPrintSn());
			if(null != printSn && printSn > 0) {
				return RespJson.error(ResultCodeEnum.OILSTATION_PRINTSN_EXIST);
			}

			oilPrint.setCreateUserId(userId);
			oilPrint.setCreateTime(curDate);
			mapper.insert(oilPrint);
		} else {
			mapper.updateByPrimaryKeySelective(oilPrint);
		}

		return RespJson.success(oilPrint.getPrintId());
	}

	@Override
	public void delete(Integer printId) {
		mapper.deleteByPrimaryKey(printId);
	}

}
