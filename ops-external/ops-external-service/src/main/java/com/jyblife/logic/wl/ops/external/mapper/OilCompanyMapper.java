package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilCompany;

public interface OilCompanyMapper extends BaseMapper<OilCompany, Integer> {
    /**
     * deleteByPrimaryKey
     *
     * 
     * @param companyId
     * @return int
     */
    int deleteByPrimaryKey(Integer companyId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilCompany record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilCompany record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param companyId
     * @return OilCompany
     */
    OilCompany selectByPrimaryKey(Integer companyId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilCompany record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilCompany record);
    
    /**
     * 新增返回主键
     * @param record
     * @return
     */
    int insertOilCompany(OilCompany record);
    
}