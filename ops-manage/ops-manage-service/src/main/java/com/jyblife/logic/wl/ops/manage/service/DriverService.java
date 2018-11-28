package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.manage.dto.DriverListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespDriverListDto;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public interface DriverService {
    RespDriverDetailDto selectDetailById(Integer id);

    Page<RespDriverListDto.DriverItem> selectPageList(DriverListDto.Search search,int page,int pageSize);

    int updateOpenLocationById(Integer driverId,Byte openLocation);
}
