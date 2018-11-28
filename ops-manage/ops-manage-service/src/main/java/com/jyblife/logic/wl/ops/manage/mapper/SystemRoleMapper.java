package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.SystemRole;
import com.jyblife.logic.wl.ops.manage.dto.SystemRoleListDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleMapper extends BaseMapper<SystemRole,Integer> {

	List<SystemRole> selectRoleListByIds(@Param("roleIds") List<Integer> roleIds);

	List<SystemRole> selectByUserId(Integer userId);
    /**
     * 条件查询
     * @param systemRole
     * @return
     */
    List<SystemRole> selectSelective(SystemRole systemRole);

    /**
     * 条件查询
     * @param search
     * @return
     */
    List<SystemRole> selectBySearch(SystemRoleListDto.Search search);
    
    SystemRole selectByPrimaryKey(@Param("roleId") Integer roleId);

}