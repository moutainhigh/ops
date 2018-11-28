package com.jyblife.logic.wl.ops.manage.mapper;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.UserRoleRelation;
import org.apache.ibatis.annotations.Param;

public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation,Integer> {

    int deleteByRoleId(@Param("roleId") Integer roleId);

    int deleteByUserId(@Param("userId") Integer userId);
}