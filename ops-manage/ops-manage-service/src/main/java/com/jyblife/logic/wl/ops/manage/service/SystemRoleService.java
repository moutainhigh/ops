package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.entity.SystemRole;
import com.jyblife.logic.wl.ops.entity.SystemRoleRight;
import com.jyblife.logic.wl.ops.manage.dto.SystemRoleListDto;

import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/18
 **/
public interface SystemRoleService {

    /**
     * 保存角色
     *
     * @param systemRole
     */
    void saveRole(SystemRole systemRole);

    /**
     * 修改角色
     *
     * @param systemRole
     */
    void updateRole(SystemRole systemRole);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void delRole(Integer roleId);

    /**
     * 分页查询角色
     */
    Page<SystemRole> listPageRole(SystemRoleListDto.Search search, int page, int pageSize);

    /**
     * 查看角色详情
     *
     * @param id
     */
    SystemRole detailRole(Integer id);

    /**
     * 获取角色权限
     *
     * @param id
     */
    SystemRoleRight selectRoleRight(Integer id);

    /**
     * 保存角色权限
     *
     * @param systemRoleRight
     */
    void saveRoleRight(SystemRoleRight systemRoleRight);

    /**
     * 查询角色
     * @param name
     * @return
     */
    SystemRole selectByName(String name);
    
    /**
     * 批量查询
     * @return
     */
    List<SystemRole> selectRoleListByIds(List<Integer> roleIds);
    
    /**
     * 更加用户id查询
     * @param userId
     * @return
     */
    List<SystemRole> selectByUserId(Integer userId);
    
    /**
     * 角色权限
     * @param userId
     * @return
     */
    List<SystemRoleRight> selectRoleRightByUserId(Integer userId);

    /**
     * 查询角色
     * @param roleId
     * @return
     */
    SystemRole selectById(Integer roleId);

    /**
     * 查询所有角色
     * @return
     */
    List<SystemRole> listAllRoles(SystemRole systemRole);

}
