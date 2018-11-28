package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.entity.LogisticsCompany;
import com.jyblife.logic.wl.ops.manage.dto.LogisticsCompanyListDto;

import java.util.List;

public interface LogisticsCompanyMapper  {
    /**
     * deleteByPrimaryKey
     *
     *
     * @param logisticsId
     * @return int
     */
    int deleteByPrimaryKey(Long logisticsId);

    /**
     * insert
     *
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
     * updateById
     *
     *
     * @param record
     * @return int
     */
    int updateById(LogisticsCompany record);
    /**
     * updateByPrimaryKey
     *
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogisticsCompany record);


    List<LogisticsCompany> listPage(LogisticsCompanyListDto.Search search);

}