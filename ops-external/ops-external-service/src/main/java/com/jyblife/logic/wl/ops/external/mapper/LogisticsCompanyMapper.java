package com.jyblife.logic.wl.ops.external.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.LogisticsCompany;

public interface LogisticsCompanyMapper extends BaseMapper<LogisticsCompany, Long> {

    /**
     * deleteByPrimaryKey
     *
     * @param logisticsId
     * @return int
     */
    int deleteByPrimaryKey(Long logisticsId);

    /**
     * insert
     *
     * @param record
     * @return int
     */
    int insert(LogisticsCompany record);

    /**
     * insertSelective
     *
     *
     * @param record
     * @return int
     */
    int insertSelective(LogisticsCompany record);

    /**
     * selectByPrimaryKey
     *
     *
     * @param logisticsId
     * @return LogisticsCompany
     */
    LogisticsCompany selectByPrimaryKey(Long logisticsId);

    /**
     * updateByPrimaryKeySelective
     *
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(LogisticsCompany record);

    /**
     * updateByPrimaryKey
     *
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsCompany record);
	
	public int insertLogisticsCompany(LogisticsCompany company);
	
	public LogisticsCompany selectByIdentity(String outIdentity);
	
}