package com.jyblife.logic.wl.ops.manage.mapper;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;
import com.jyblife.logic.wl.ops.manage.dto.VehicleQuotalimitListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotaLimitDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespVehicleQuotalimitListDto;

public interface VehicleQuotaLimitMapper extends BaseMapper<VehicleQuotaLimit, Integer> {
    /**
     * deleteByPrimaryKey
     *
     * @param limitId
     * @return int
     */
    int deleteByPrimaryKey(Integer limitId);

    /**
     * insert
     *
     * @param record
     * @return int
     */
    int insert(VehicleQuotaLimit record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(VehicleQuotaLimit record);

    /**
     * selectByPrimaryKey
     *
     * @param limitId
     * @return VehicleQuotaLimit
     */
    VehicleQuotaLimit selectByPrimaryKey(Integer limitId);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(VehicleQuotaLimit record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(VehicleQuotaLimit record);



    /**
     * 查询最新的
     * @return
     */
    VehicleQuotaLimit selectCurrent();


    Page<RespVehicleQuotalimitListDto.QuotalimitItem> selectPageList(VehicleQuotalimitListDto.Search search);


    RespVehicleQuotaLimitDetailDto selectCurrentDetail();
}