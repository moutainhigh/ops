package com.jyblife.logic.wl.ops.external.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.utils.DistanceUtils;
import com.jyblife.logic.wl.ops.entity.AreaCode;
import com.jyblife.logic.wl.ops.entity.OilStation;
import com.jyblife.logic.wl.ops.external.common.AreaCache;
import com.jyblife.logic.wl.ops.external.dto.req.ReqEffectOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqNearOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilStationInfoDto;
import com.jyblife.logic.wl.ops.external.dto.resp.AreaCodeDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespEffectOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespNearOilStationListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilStationInfoDto;
import com.jyblife.logic.wl.ops.external.mapper.AreaCodeMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilPriceMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilStationMapper;
import com.jyblife.logic.wl.ops.external.service.OilStationService;

@Service
@Transactional
public class OilStationServiceImpl implements OilStationService {

	@Autowired
	private AreaCodeMapper areaCodeMapper;
	@Autowired
	private OilStationMapper oilStationMapper;
	@Autowired
	private OilPriceMapper oilPriceMapper;
	
	@Override
	public AreaCodeDto getAreaDict() {
		if (AreaCache.areaCodeCache == null) {
        	List<AreaCode> all = areaCodeMapper.selectAll();
        	//List<AreaCode> all2 = all;
        	AreaCodeDto dto = new AreaCodeDto();
        	List<AreaCodeDto> level_1 = new ArrayList<>();
        	Map<Integer, List<AreaCodeDto>> level_2 = new HashMap<Integer, List<AreaCodeDto>>();
        	
        	for(int i=0; i<all.size(); i++) {
        		AreaCodeDto d = new AreaCodeDto();
    			d.setId(all.get(i).getAreaCode());
    			d.setName(all.get(i).getAreaName());
        		if (all.get(i).getLevel().intValue() == 0) {
        			dto = d;
        		} else if(all.get(i).getLevel().intValue() == 1) {
        			level_1.add(d);
        			level_2.put(all.get(i).getAreaCode(), new ArrayList<AreaCodeDto>());
        		} else if(all.get(i).getLevel().intValue() == 2) {
        			//暂时不需要县级别
        			/*for(int j=0; j<all2.size(); j++) {
        				if(all2.get(j).getpAreaCode().intValue() == d.getId().intValue()) {
        					AreaCodeDto area = new AreaCodeDto();
        					area.setId(all2.get(j).getAreaCode());
        					area.setName(all2.get(j).getAreaName());
        					d.getChildren().add(area);
        				}
        			}*/
        			level_2.get(all.get(i).getpAreaCode()).add(d);
        		}
        	}
        	
        	for(AreaCodeDto acd : level_1) {
        		acd.setChildren(level_2.get(acd.getId()));
        	}
        	dto.setChildren(level_1);
        	
            AreaCache.areaCodeCache = dto;
        }
		
		return AreaCache.areaCodeCache;
	}

	@Override
	public RespOilStationInfoDto getOilStationInfo(ReqOilStationInfoDto dto) {
		RespOilStationInfoDto infoDto = oilStationMapper.getOilStationInfoById(dto.getStationId());
		if(infoDto == null || infoDto.getStatus().equals(StatusEnum.DISABLE.getValue())) {
			return infoDto;
		}
		
		double distance = DistanceUtils.getDistance(dto.getLongitude(), dto.getLatitude(), Double.valueOf(infoDto.getLongitude()), Double.valueOf(infoDto.getLatitude()));
		infoDto.setDistance(distance);
		
		List<RespOilStationInfoDto.Goods> list = oilPriceMapper.getOilPriceInfoByStationId(dto.getStationId());
		infoDto.setGoods(list);
		
		return infoDto;
	}

	@Override
	public RespEffectOilStationListDto getEffectOilStationList(ReqEffectOilStationListDto dto) {
		
		Page<RespEffectOilStationListDto.OilStationItem> page = PageHelper.startPage(dto.getPage(), dto.getPageSize());
		OilStation os = new OilStation();
		os.setCityId(dto.getCityId());
		os.setProvinceId(dto.getProvinceId());
		os.setName(dto.getName());
		oilStationMapper.getEffectOilStationList(os);
		
		RespEffectOilStationListDto resp = new RespEffectOilStationListDto();
		resp.setData(page.getResult());
		resp.setPage(page.getPageNum());
		resp.setPageSize(page.getPageSize());
		resp.setTotalPages(page.getPages());
		resp.setTotalRows(page.getTotal());
		
		return resp;
	}

	@Override
	public RespNearOilStationListDto getNearOilStationList(ReqNearOilStationListDto dto) {
		OilStation os = new OilStation();
		os.setName(dto.getName());
		List<RespEffectOilStationListDto.OilStationItem> stationList = oilStationMapper.getEffectOilStationList(os);
		
		OilStation mostVisitStation = null;
		if(stationList != null) {
			mostVisitStation = oilStationMapper.getMostVisitStationByCustId(dto.getUserId());
		}
		
		List<RespNearOilStationListDto.OilStationItem> dataList = new ArrayList<RespNearOilStationListDto.OilStationItem>();
		RespNearOilStationListDto.OilStationItem nearStation = null;
		for(RespEffectOilStationListDto.OilStationItem item : stationList) {
			nearStation = new RespNearOilStationListDto.OilStationItem();
			BeanUtils.copyProperties(item, nearStation);
			
			nearStation.setMostVisit((mostVisitStation != null && String.valueOf(mostVisitStation.getStationId()).equals(nearStation.getStationId())) ? 1 : 2);
			
			double distance = DistanceUtils.getDistance(dto.getLongitude(), dto.getLatitude(), Double.valueOf(item.getLongitude()), Double.valueOf(item.getLatitude()));
			nearStation.setDistance(String.valueOf(distance));
			
			if(dto.getMaxDistance() == null || dto.getMaxDistance().doubleValue() >= distance) {
				dataList.add(nearStation);
			}
		}
		
		//排序 判断最快到达
		int size = dataList.size();
		if(size > 0) {
			Collections.sort(dataList);
			for(int i=0; i<size; i++) {
				dataList.get(i).setClosest(i == 0 ? 1 : 2);
				String d = dataList.get(i).getDistance();
				dataList.get(i).setDistance(new BigDecimal(d).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
			}
		}
		
		RespNearOilStationListDto respDto = new RespNearOilStationListDto();
		respDto.setData(dataList);
		respDto.setPage(dto.getPage());
		respDto.setPageSize(dto.getPageSize());
		int totalPages = ((dataList.size()%dto.getPageSize()) > 0) ? dataList.size()/dto.getPageSize() + 1 : dataList.size()/dto.getPageSize();
		respDto.setTotalPages(totalPages);
		respDto.setTotalRows(Long.valueOf(dataList.size()));
		
		return respDto;
	}

}
