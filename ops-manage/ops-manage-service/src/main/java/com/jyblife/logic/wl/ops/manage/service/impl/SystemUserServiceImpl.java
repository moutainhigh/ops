package com.jyblife.logic.wl.ops.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.*;
import com.jyblife.logic.wl.ops.manage.dto.SytemUserListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespSystemUserListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.UserMenuDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.mapper.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyblife.logic.wl.ops.manage.service.SystemUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemUserRightMapper userRightMapper;
    @Autowired
    private SystemModuleMapper systemModuleMapper;
    @Autowired
    private SystemRoleRightMapper roleRightMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public SystemUser selectByUserName(String userName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        List<SystemUser> list = systemUserMapper.list(SystemUser.class, map);

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public RespJson updatePwd(SystemUser user) throws ManageServiceException {
        systemUserMapper.updatePwd(user);
        return RespJson.success();
    }

    @Override
    public SystemUser selectByPrimaryKey(Integer userId) {
        if (userId == null) {
            return null;
        }

        return systemUserMapper.get(SystemUser.class, userId);
    }

    @Override
    public RespJson getUserMenuByUserRight(Integer userId) throws ManageServiceException {
        if (userId == null) {
            return RespJson.error();
        }

        SystemUserRight userRight = userRightMapper.selectByPrimaryKey(userId);
        if(userRight != null) {
        	JSONArray rightCodes = JSON.parseArray(userRight.getRightCodes());

            List<Integer> moduleIds = new ArrayList<>();
            for (int i = 0; i < rightCodes.size(); i++) {
                moduleIds.add(rightCodes.getJSONObject(i).getIntValue("id"));
            }

            List<SystemModule> dataList = systemModuleMapper.selectByModuleIds(moduleIds);
            if (!dataList.isEmpty() && dataList.size() > 0) {
                //拼装顶部菜单
                List<UserMenuDto> topMenus = getTopMenuList(dataList);
                //添加子菜单
                topMenus = packageChildrenMenu(topMenus, dataList);

                return RespJson.success(topMenus);
            }
        }

        return RespJson.success();
    }

    @Override
    public RespJson getUserMenuByRoleRight(Integer userId) {
        if (userId == null) {
            return RespJson.error();
        }

        List<SystemRoleRight> roleRightList = roleRightMapper.selectByUserId(userId);
        List<Integer> moduleIds = new ArrayList<>();
        JSONArray rightCodes = null;
        int moduleId = 0;
        //获取全部菜单id
        for (SystemRoleRight srr : roleRightList) {
            rightCodes = JSON.parseArray(srr.getRightCodes());
            for (int i = 0; i < rightCodes.size(); i++) {
                moduleId = rightCodes.getJSONObject(i).getIntValue("id");
                if (!moduleIds.contains(moduleId)) {
                    moduleIds.add(moduleId);
                }
            }
        }

        List<SystemModule> dataList = systemModuleMapper.selectByModuleIds(moduleIds);
        if (!dataList.isEmpty() && dataList.size() > 0) {
            //拼装顶部菜单
            List<UserMenuDto> topMenus = getTopMenuList(dataList);
            //添加子菜单
            topMenus = packageChildrenMenu(topMenus, dataList);

            return RespJson.success(topMenus);
        }

        return RespJson.success();
    }

    /**
     * 获取顶部菜单
     *
     * @param dataList
     * @return
     */
    private List<UserMenuDto> getTopMenuList(List<SystemModule> dataList) {
        List<UserMenuDto> topMenus = new ArrayList<>();
        UserMenuDto umd = null;
        List<SystemModule> removeList = new ArrayList<>();
        for (SystemModule sm : dataList) {
            if (sm.getParentId() == 0) {
                umd = new UserMenuDto();
                BeanUtils.copyProperties(sm, umd);
                topMenus.add(umd);
                removeList.add(sm);
            }
        }

        dataList.removeAll(removeList);
        return topMenus;
    }

    /**
     * 组装子菜单
     *
     * @param topMenuList
     * @param menuList
     * @return
     */
    private List<UserMenuDto> packageChildrenMenu(List<UserMenuDto> topMenuList, List<SystemModule> menuList) {
        UserMenuDto menu = null;
        for (UserMenuDto umd : topMenuList) {
            temp:
            for (SystemModule sm : menuList) {
                if (umd.getId().equals(sm.getParentId())) {
                    for (UserMenuDto u : umd.getChildren()) {
                        if (u.getCode().equals(sm.getCode())) {
                            continue temp;
                        }
                    }
                    menu = new UserMenuDto();
                    BeanUtils.copyProperties(sm, menu);
                    umd.getChildren().add(menu);
                    this.packageChildrenMenu(umd.getChildren(), menuList);
                }
            }
        }

        return topMenuList;
    }

    @Override
    public SystemUserRight getUserRightByUserId(Integer userId) {
        return userRightMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUserLoginInfo(Integer userId) {
        systemUserMapper.updateUserLoginInfo(userId);
    }

    @Override
    public void saveSystemUser(SystemUser systemUser) {
        if (this.selectByUserName(systemUser.getUserName()) != null) {
            throw new ManageServiceException(ResultCodeEnum.USER_NAME_EXIST);
        }

        systemUserMapper.insertSystemUser(systemUser);
        insertRoleRelations(systemUser);
    }

    @Override
    public void updateSystemUser(SystemUser systemUser) {
        SystemUser systemUser1 = this.selectByUserName(systemUser.getUserName());
        if (systemUser1 != null && systemUser1.getUserId().intValue() != systemUser.getUserId().intValue()) {
            throw new ManageServiceException(ResultCodeEnum.USER_NAME_EXIST);
        }

        systemUserMapper.updateWithoutNull(systemUser);
        //delete
        userRoleRelationMapper.deleteByUserId(systemUser.getUserId());
        //insert
        insertRoleRelations(systemUser);

    }

    private int insertRoleRelations(SystemUser systemUser) {
        if (StringUtils.isNoneBlank(systemUser.getRoleIds())) {
            String[] roleIds = systemUser.getRoleIds().split(",");
            List<UserRoleRelation> userRoleRelations = new ArrayList<UserRoleRelation>();
            for (String roleId : roleIds) {
                if (!roleId.isEmpty()) {
                    UserRoleRelation userRoleRelation = new UserRoleRelation();
                    userRoleRelation.setUserId(systemUser.getUserId());
                    userRoleRelation.setRoleId(StrUtils.str2Int(roleId));
                    userRoleRelation.setUpdateTime(systemUser.getUpdateTime());
                    userRoleRelations.add(userRoleRelation);

                }
            }
            if (userRoleRelations.size() > 0) {
                return userRoleRelationMapper.insertBatch(userRoleRelations);
            }
        }
        return 0;
    }

    @Override
    public void deleteSystemUser(Integer userId) {
        systemUserMapper.delete(SystemUser.class, userId);
        userRoleRelationMapper.deleteByUserId(userId);
    }


    @Override
    public Page<RespSystemUserListDto.UserItem> listPageUser(SytemUserListDto.Search search, Integer page, Integer pageSize) {
        Page<RespSystemUserListDto.UserItem> pager = PageHelper.startPage(page, pageSize);
        systemUserMapper.listPageUser(search);
        return pager;
    }

    @Override
    public void saveUserRight(SystemUserRight systemUserRight) {
        userRightMapper.deleteByPrimaryKey(systemUserRight.getUserId());
        userRightMapper.insert(systemUserRight);
    }

    @Override
    public SystemUserRight selectByUserId(Integer userId) {
        return userRightMapper.selectByPrimaryKey(userId);
    }
}
