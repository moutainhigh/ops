package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.manage.dto.OilAttendantListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilAttendantDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilAttendantListDto;
import com.jyblife.logic.wl.ops.manage.mapper.OilAttendantMapper;
import com.jyblife.logic.wl.ops.manage.service.OilAttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangcey
 * @date 2018/11/27
 **/
@Service
@Transactional
public class OilAttendantServiceImpl implements OilAttendantService {

    @Autowired
    private OilAttendantMapper oilAttendantMapper;

    @Override
    public RespOilAttendantDto selectDetailById(Integer id) {
        RespOilAttendantDto respOilAttendantDto = oilAttendantMapper.selectDetailById(id);        ;
        respOilAttendantDto.setStatusName(StatusEnum.getText(StrUtils.byte2Int(respOilAttendantDto.getStatus())));
        return respOilAttendantDto;
    }

    @Override
    public Page<RespOilAttendantListDto.OilAttendantItem> selectPageList(OilAttendantListDto.Search search, int page, int pageSize) {
        Page<RespOilAttendantListDto.OilAttendantItem> pager = PageHelper.startPage(page,pageSize);
        oilAttendantMapper.selectPageList(search);
        return pager;
    }
}
