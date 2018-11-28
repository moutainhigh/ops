package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;

import com.jyblife.logic.wl.ops.entity.OilPrint;
import com.jyblife.logic.wl.ops.manage.dto.OilPrintListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPrintListDto;

public interface OilPrintMapper {

	/**
     * deleteByPrimaryKey
     *
     * 
     * @param printId
     * @return int
     */
    int deleteByPrimaryKey(Integer printId);

    /**
     * insert
     *
     * 
     * @param record
     * @return int
     */
    int insert(OilPrint record);

    /**
     * insertSelective
     *
     * 
     * @param record
     * @return int
     */
    int insertSelective(OilPrint record);

    /**
     * selectByPrimaryKey
     *
     * 
     * @param printId
     * @return OilPrint
     */
    OilPrint selectByPrimaryKey(Integer printId);

    /**
     * updateByPrimaryKeySelective
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(OilPrint record);

    /**
     * updateByPrimaryKey
     *
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(OilPrint record);
	
	
	/**
	 * 列表分页查询
	 * @param search
	 * @return
	 */
	List<RespOilPrintListDto.PrintItem> listPage(OilPrintListDto.Search search);
	
	/**
	 *  打印机SN查询
	 * @param search
	 * @return
	 */
	int selectByPrintSn(String printSn);
	
	/**
	 * 打印机ID查询
	 */
	OilPrint selectByPrintId(String id);

}