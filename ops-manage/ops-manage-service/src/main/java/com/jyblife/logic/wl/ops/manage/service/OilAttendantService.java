package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.manage.dto.OilAttendantListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilAttendantDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilAttendantListDto;

/**
 * @author yangcey
 * @date 2018/11/27
 **/
public interface OilAttendantService {

    RespOilAttendantDto selectDetailById(Integer id);


    Page<RespOilAttendantListDto.OilAttendantItem>selectPageList(OilAttendantListDto.Search search, int page, int pageSize);

}
