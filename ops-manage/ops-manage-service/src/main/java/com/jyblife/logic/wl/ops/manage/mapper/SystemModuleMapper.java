package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.SystemModule;

/**
 * @Author yangcey
 */
public interface SystemModuleMapper extends BaseMapper<SystemModule,Integer> {

	List<SystemModule> selectByModuleIds(@Param("moduleIds") List<Integer> moduleIds);
	
}