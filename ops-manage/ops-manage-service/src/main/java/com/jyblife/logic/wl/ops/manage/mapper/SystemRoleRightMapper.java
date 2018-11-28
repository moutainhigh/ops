package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.SystemRoleRight;

public interface SystemRoleRightMapper extends BaseMapper<SystemRoleRight,Integer> {
	
	/**
	 * 根据用户编号获取角色权限
	 * @return
	 */
	public List<SystemRoleRight> selectByUserId(Integer userId);

}