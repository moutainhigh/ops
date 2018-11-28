package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.entity.SystemUserRight;
import com.jyblife.logic.wl.ops.manage.dto.SytemUserListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespSystemUserListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;


/**
 * 系统用户服务类
 */
public interface SystemUserService {

    SystemUser selectByUserName(String userName);

    RespJson updatePwd(SystemUser user) throws ManageServiceException;

    SystemUser selectByPrimaryKey(Integer userId);

    /**
     * 根据用户权限获取权限树
     *
     * @param userId
     * @return
     */
    RespJson getUserMenuByUserRight(Integer userId);

    /**
     * 根据角色权限获取用户权限树
     *
     * @param userId
     * @return
     */
    RespJson getUserMenuByRoleRight(Integer userId);

    /**
     * 用户权限
     *
     * @param userId
     * @return
     */
    SystemUserRight getUserRightByUserId(Integer userId);

    /**
     * 更新登录信息
     *
     * @param userId
     */
    void updateUserLoginInfo(Integer userId);

    /**
     * 新增
     *
     * @param systemUser
     */
    void saveSystemUser(SystemUser systemUser);

    /**
     * 修改
     *
     * @param systemUser
     */
    void updateSystemUser(SystemUser systemUser);

    /**
     * 删除
     *
     * @param userId
     */
    void deleteSystemUser(Integer userId);

    /**
     *分页查询用户列表
     */
    Page<RespSystemUserListDto.UserItem> listPageUser(SytemUserListDto.Search search, Integer page, Integer pageSize);

    /**
     * 保存更新用户权限
     * @param systemUserRight
     */
    void saveUserRight(SystemUserRight systemUserRight);

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    SystemUserRight selectByUserId(Integer userId);
}
