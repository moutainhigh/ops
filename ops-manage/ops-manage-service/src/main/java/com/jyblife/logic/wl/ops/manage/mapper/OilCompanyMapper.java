package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.OilCompany;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilCompanyDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilCompanyListDto;

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
     * 列表分页查询
     * @param search
     * @return
     */
    List<RepOilCompanyListDto.OilCompanyItem> listPage(OilCompanyListDto.Search search);
    
    /**
     * 查看
     * @return
     */
    RepOilCompanyDetailDto selectByCompanyId(Integer companyId);
    
    /**
     * 新增返回主键
     * @param record
     * @return
     */
    int insertOilCompany(OilCompany record);


    /**
     * 查询企业
     * @return
     */
    List<OilCompany> selectCompanyByStatus(Integer status);

    /**
     *
     * @param record
     * @return
     */

    List<OilCompany> selectExistCompany(OilCompany record);
}