package com.jyblife.logic.wl.ops.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.SystemRole;
import com.jyblife.logic.wl.ops.entity.SystemRoleRight;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.SystemRoleDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemRoleListDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemRoleRightDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemRoleSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespSystemRoleListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.SystemUserMapper;
import com.jyblife.logic.wl.ops.manage.service.SystemRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/18
 **/
@RestController
@RequestMapping("/admin/role")
public class SystemRoleController extends BaseController {

    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 角色信息修改保存
     *
     * @return
     */
    @Permission(value="system-role-save")
    @RequestMapping("/save")
    public RespJson saveRole(@RequestBody @Validated SystemRoleSaveDto systemRoleSaveDto,
                             @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            SystemRole systemRole = new SystemRole();
            BeanUtils.copyProperties(systemRoleSaveDto, systemRole);
            SystemUser systemUser = this.getCurSystemUser(sessionId);
            if (systemRoleSaveDto.getRoleId() == null) {
                systemRole.setCreateTime(new Date());
                systemRole.setUpdateTime(new Date());
                if (systemUser != null) {
                    systemRole.setUpdateUserId(systemUser.getUserId());
                    systemRole.setCreateUserId(systemUser.getUserId());
                }
                systemRoleService.saveRole(systemRole);
            } else {
                if (systemUser != null) {
                    systemRole.setUpdateUserId(systemUser.getUserId());
                }
                systemRole.setUpdateTime(new Date());
                systemRoleService.updateRole(systemRole);
            }
            return RespJson.success();
        } catch (ManageServiceException e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(e);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    /**
     * 角色信息删除
     *
     * @param roleId
     * @return
     */
    @Permission(value="system-role-del")
    @RequestMapping("/del")
    public RespJson delRole(@RequestParam(value = "role_id") Integer roleId) {
        try {
            systemRoleService.delRole(roleId);
            return RespJson.success();
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    /**
     * 系统角色列表
     *
     * @param systemRoleListDto
     * @return
     */
    @Permission(value="system-role-list")
    @RequestMapping("/list")
    public RespJson listRole(@RequestBody(required = false) SystemRoleListDto systemRoleListDto, 
    						 @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (systemRoleListDto == null) {
                systemRoleListDto = new SystemRoleListDto();
            }

            Page<SystemRole> pager = systemRoleService.listPageRole(systemRoleListDto.getSearch(), systemRoleListDto.getPage(), systemRoleListDto.getPageSize());

            RespSystemRoleListDto respSystemRoleListDto = new RespSystemRoleListDto();
            respSystemRoleListDto.setSearchItems(systemRoleListDto.getSearch());
            respSystemRoleListDto.setPage(pager.getPageNum());
            respSystemRoleListDto.setPageSize(pager.getPageSize());
            respSystemRoleListDto.setTotalPages(pager.getPages());
            respSystemRoleListDto.setTotalRows((int) pager.getTotal());
            
            boolean isCanAuth = isHadPermission(sessionId, "system-role-saveRight");
            boolean isCanDel = isHadPermission(sessionId, "system-role-del");
            boolean isCanEdit = isHadPermission(sessionId, "system-role-edit");
            boolean isCanView = isHadPermission(sessionId, "system-role-detail");
            List<RespSystemRoleListDto.RoleItem> roleItems = new ArrayList<RespSystemRoleListDto.RoleItem>();
            for (SystemRole systemRole1 : pager.getResult()) {
                RespSystemRoleListDto.RoleItem roleItem = new RespSystemRoleListDto.RoleItem();
                roleItem.setRoleId(StrUtils.int2Str(systemRole1.getRoleId()));
                roleItem.setCreateTime(systemRole1.getCreateTime());
                roleItem.setUpdateTime(systemRole1.getUpdateTime());
                roleItem.setCreateUserId(StrUtils.int2Str(systemRole1.getCreateUserId()));
                roleItem.setName(systemRole1.getName());
                roleItem.setRemark(systemRole1.getRemark());
                roleItem.setRightCodes(systemRole1.getRightCodes());
                roleItem.setStatus(StrUtils.int2Str(systemRole1.getStatus()));
                roleItem.setType(StrUtils.int2Str(systemRole1.getType()));
                roleItem.setUpdateUserId(StrUtils.int2Str(systemRole1.getUpdateUserId()));
                
                roleItem.setIsCanAuth(isCanAuth);
                roleItem.setIsCanDel(isCanDel);
                roleItem.setIsCanEdit(isCanEdit);
                roleItem.setIsCanView(isCanView);
                roleItems.add(roleItem);
            }
            respSystemRoleListDto.setData(roleItems);
            return RespJson.success(respSystemRoleListDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    /**
     * 角色详情
     *
     * @param roleId
     * @return
     */
    @Permission(value="system-role-detail")
    @RequestMapping("/detail")
    public RespJson detailRole(@RequestParam(value = "role_id") Integer roleId) {

        try {
            SystemRole systemRole = systemRoleService.selectById(roleId);
            if (systemRole != null) {
                SystemRoleDetailDto systemRoleDetailDto = new SystemRoleDetailDto();
                SystemUser createUser = systemRole.getCreateUserId() == null ? null : systemUserMapper.get(SystemUser.class, systemRole.getCreateUserId());
                SystemUser updateUser = systemRole.getUpdateUserId() == null ? null : systemUserMapper.get(SystemUser.class, systemRole.getUpdateUserId());
                BeanUtils.copyProperties(systemRole, systemRoleDetailDto);
                systemRoleDetailDto.setRoleId(StrUtils.int2Str(systemRole.getRoleId()));
                systemRoleDetailDto.setRightCodes(systemRole.getRightCodes());
                systemRoleDetailDto.setOrderIndex(StrUtils.int2Str(systemRole.getOrderIndex()));
                systemRoleDetailDto.setType(StrUtils.int2Str(systemRole.getType()));
                systemRoleDetailDto.setStatus(StrUtils.int2Str(systemRole.getStatus()));

                if (createUser != null) {
                    systemRoleDetailDto.newCreateUser(StrUtils.int2Str(createUser.getUserId()), createUser.getUserName());
                }
                if (updateUser != null) {
                    systemRoleDetailDto.newUpdateUser(StrUtils.int2Str(updateUser.getUserId()), updateUser.getUserName());
                }

                return RespJson.success(systemRoleDetailDto);
            } else {
                return RespJson.error(ResultCodeEnum.FAIL);
            }
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }

    }

    /**
     * 获取角色权限
     *
     * @param roleId
     * @return
     */
    @Permission(value="system-role-roleRight")
    @RequestMapping("/roleRight")
    public RespJson roleRight(@RequestParam(value = "role_id") Integer roleId) {
        try {
            SystemRoleRight systemRoleRight = systemRoleService.selectRoleRight(roleId);
            SystemRoleRightDto systemRoleRightDto = new SystemRoleRightDto();
            systemRoleRightDto.setRoleId(StrUtils.int2Str(roleId));
            if (systemRoleRight != null) {
                List<SystemRoleRightDto.RoleRight> roleRights = JSONArray.parseArray(systemRoleRight.getRightCodes(), SystemRoleRightDto.RoleRight.class);
                systemRoleRightDto.setRoleRight(roleRights);
            }
            return RespJson.success(systemRoleRightDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    /**
     * 保存角色权限
     *
     * @param systemRoleRightDto
     * @return
     */
    @Permission(value="system-role-saveRight")
    @RequestMapping("/saveRight")
    public RespJson saveRight(@RequestBody @Validated SystemRoleRightDto systemRoleRightDto,
                              @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            SystemRoleRight systemRoleRight = new SystemRoleRight();
            systemRoleRight.setRoleId(StrUtils.str2Int(systemRoleRightDto.getRoleId()));
            String rightCodes = JSONArray.toJSONString(systemRoleRightDto.getRoleRight());
            systemRoleRight.setRightCodes(rightCodes);
            systemRoleRight.setCreateTime(new Date());
            systemRoleRight.setUpdateTime(new Date());
            SystemUser currentUser = this.getCurSystemUser(sessionId);
            if(currentUser!=null){
                systemRoleRight.setUpdateUserId(currentUser.getUserId());
                systemRoleRight.setCreateUserId(currentUser.getUserId());
            }
            systemRoleService.saveRoleRight(systemRoleRight);

            return RespJson.success();
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }


}
