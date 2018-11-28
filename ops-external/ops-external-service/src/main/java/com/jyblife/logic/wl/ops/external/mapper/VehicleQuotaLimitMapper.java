package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;

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
     * 查询当天最新
     * @return
     */
    VehicleQuotaLimit selectCurrentDate();
    
    /**
     * 查询当前最新
     * @return
     */
    VehicleQuotaLimit selectCurrent();

}