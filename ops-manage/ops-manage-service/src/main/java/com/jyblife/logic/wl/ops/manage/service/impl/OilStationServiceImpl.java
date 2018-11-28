package com.jyblife.logic.wl.ops.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jyblife.logic.wl.ops.entity.OilCompany;
import com.jyblife.logic.wl.ops.entity.OilStation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.entity.OilStationAttachment;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationDetailDto.File;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationListDto;
import com.jyblife.logic.wl.ops.manage.mapper.OilStationAttachmentMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilStationMapper;
import com.jyblife.logic.wl.ops.manage.service.OilStationService;

@Service
@Transactional
public class OilStationServiceImpl implements OilStationService {

    @Autowired
    private OilStationMapper oilStationMapper;
    @Autowired
    private OilStationAttachmentMapper attMapper;

    @Override
    public Page<RepOilStationListDto.OilStationItem> listPage(OilStationApplyListDto.Search search, int page, int pageSize) {
        Page<RepOilStationListDto.OilStationItem> pager = PageHelper.startPage(page, pageSize);
        oilStationMapper.listPage(search);
        return pager;
    }

    @Override
    public RespJson detail(Integer applyId) {

        RepOilStationDetailDto detailDto = oilStationMapper.selectByStationId(applyId);
        if (detailDto != null) {
            detailDto.setStatusName(StatusEnum.getText(detailDto.getStatus()));

            Map<String, Object> param = new HashMap<>();
            param.put("baseId", applyId);
            param.put("status", 1);
            List<OilStationAttachment> dataList = attMapper.list(OilStationAttachment.class, param);

            if (dataList != null && dataList.size() > 0) {
                for (OilStationAttachment at : dataList) {
                    RepOilStationDetailDto.File f = new File();
                    BeanUtils.copyProperties(at, f);
                    f.setUrl(at.getFileUrl());
                    detailDto.getFiles().add(f);
                }
            }
        }

        return RespJson.success(detailDto);
    }

    @Override
    public Map<String, OilStation> getAllOilStationMap(Map<String, OilCompany> oilCompanyMap) {
        Map<String, OilStation> oilStationMap = new HashMap<String, OilStation>();
        for (String key : oilCompanyMap.keySet()) {
            Map<String, Object> param = new HashMap<String, Object>();
            OilCompany oilCompany = oilCompanyMap.get(key);
            param.put("companyId", oilCompany.getCompanyId());
            List<OilStation> oilStations = oilStationMapper.list(OilStation.class, param);
            for (OilStation oilStation : oilStations) {
                oilStationMap.put(oilCompany.getName().trim() + "_" + oilStation.getName().trim(), oilStation);
            }
        }
        return oilStationMap;
    }

	@Override
	public RespJson oilStationList() {
		
		List<OilStation> oilStationList = oilStationMapper.oilStationList();
		return RespJson.success(oilStationList);
	}
}
