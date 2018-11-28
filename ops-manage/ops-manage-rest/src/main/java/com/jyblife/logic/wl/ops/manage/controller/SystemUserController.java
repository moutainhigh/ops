package com.jyblife.logic.wl.ops.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.EncryptUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.*;
import com.jyblife.logic.wl.ops.manage.dto.SystemUserRightDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemUserSaveDto;
import com.jyblife.logic.wl.ops.manage.dto.SytemUserListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespSystemRoleDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespSystemUserDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespSystemUserListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.SystemRoleService;
import com.jyblife.logic.wl.ops.manage.service.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统用户控制器
 *
 * @Author yangcey
 */

@RestController
@RequestMapping("/admin/user")
public class SystemUserController extends BaseController {

    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemRoleService systemRoleService;

    @Permission(value="system-user-save")
    @RequestMapping("/save")
    public RespJson saveSystemUser(@RequestBody @Validated SystemUserSaveDto systemUserSaveDto,
                                   @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {

        try {
            SystemUser systemUser = new SystemUser();
            BeanUtils.copyProperties(systemUserSaveDto, systemUser);
            if(StringUtils.isNotBlank(systemUser.getPassword())) {
            	systemUser.setPassword(EncryptUtil.encryptTo32Md5(systemUser.getPassword()));
            }
            
            if (systemUserSaveDto.getRoleArray() != null && systemUserSaveDto.getRoleArray().size() > 0) {
                StringBuffer roleIds = new StringBuffer();
                for (SystemUserSaveDto.SystemRoleInner systemRoleInner : systemUserSaveDto.getRoleArray()) {
                    roleIds.append(",").append(systemRoleInner.getId());
                }
                systemUser.setRoleIds(roleIds.toString().substring(1));
            } else {
                systemUser.setRoleIds("");
            }
            SystemUser currentUser = this.getCurSystemUser(sessionId);
            if (systemUser.getUserId() == null) {
            	if(StringUtils.isBlank(systemUser.getPassword())) {
            		RespJson.error("密码不能为空");
            	}
            	systemUser.setUserCenterId(0);
            	systemUser.setLoginCount(0);
                systemUser.setCreateTime(new Date());
                systemUser.setUpdateTime(new Date());
                if (systemUser != null) {
                    systemUser.setUpdateUserId(currentUser.getUserId());
                    systemUser.setCreateUserId(currentUser.getUserId());
                }
                systemUserService.saveSystemUser(systemUser);
            } else {
                if (systemUser != null) {
                    systemUser.setUpdateUserId(currentUser.getUserId());
                }
                if(StringUtils.isBlank(systemUser.getPassword())) {
                	systemUser.setPassword(null);
                }
                systemUser.setUpdateTime(new Date());
                systemUserService.updateSystemUser(systemUser);
            }
            return RespJson.success(systemUserSaveDto);
        } catch (OpsException e) {
            logger.error("异常：{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("异常：{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }


    }

    @Permission(value="system-user-del")
    @RequestMapping("/del")
    public RespJson delSystemUser(@RequestParam("user_id") Integer id) {
        try {
            systemUserService.deleteSystemUser(id);
            return RespJson.success();
        } catch (Exception e) {
            logger.error("异常：{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="system-user-detail")
    @RequestMapping("/detail")
    public RespJson detailSystemUser(@RequestParam("user_id") Integer id) {
        try {
            SystemUser systemUser = systemUserService.selectByPrimaryKey(id);
            if (systemUser != null) {
                RespSystemUserDetailDto systemUserDetailDto = new RespSystemUserDetailDto();
                BeanUtils.copyProperties(systemUser, systemUserDetailDto);
                systemUserDetailDto.setMainRoleId(StrUtils.int2Str(systemUser.getMainRoleId()));
                systemUserDetailDto.setStatus(StrUtils.int2Str(systemUser.getStatus()));
                //查询mainrolename
                SystemRole systemRole = systemRoleService.selectById(systemUser.getMainRoleId());
                if (systemRole != null) {
                    systemUserDetailDto.setMainRoleName(systemRole.getName());
                }

                //查询roleids
                List<SystemUserSaveDto.SystemRoleInner> roleInners = new ArrayList<SystemUserSaveDto.SystemRoleInner>();
                if (StringUtils.isNotBlank(systemUser.getRoleIds())) {
                    String[] roleIds = systemUser.getRoleIds().split(",");
                    List<Integer> rIds = new ArrayList<Integer>();
                    for (String roleId : roleIds) {
                        if (roleId.isEmpty()) {
                            continue;
                        }
                        rIds.add(StrUtils.str2Int(roleId));
                    }

                    List<SystemRole> roles = systemRoleService.selectRoleListByIds(rIds);
                    for (SystemRole role : roles) {
                        SystemUserSaveDto.SystemRoleInner roleInner = new SystemUserSaveDto.SystemRoleInner();
                        roleInner.setId(StrUtils.int2Str(role.getRoleId()));
                        roleInner.setName(role.getName());
                        roleInners.add(roleInner);
                    }

                }
                systemUserDetailDto.setRoleArray(roleInners);
                systemUserDetailDto.setIsRightRole(StrUtils.int2Str(StrUtils.byte2Int(systemUser.getIsRightRole())));

                return RespJson.success(systemUserDetailDto);
            }
            return RespJson.error(ResultCodeEnum.USER_NOT_EXIST);
        } catch (Exception e) {
            logger.error("异常：{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="system-user-list")
    @RequestMapping("/list")
    public RespJson listSystemUser(@RequestBody(required = false) SytemUserListDto sytemUserListDto, 
    							   @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (sytemUserListDto == null) {
                sytemUserListDto = new SytemUserListDto();
            }
            
            boolean isCanAuth = isHadPermission(sessionId, "system-user-saveRight");
            boolean isCanDel = isHadPermission(sessionId, "system-user-del");
            boolean isCanEdit = isHadPermission(sessionId, "system-user-edit");
            boolean isCanView = isHadPermission(sessionId, "system-user-detail");
            Page<RespSystemUserListDto.UserItem> page = systemUserService.listPageUser((SytemUserListDto.Search) sytemUserListDto.getSearch(), sytemUserListDto.getPage(), sytemUserListDto.getPageSize());
            for (RespSystemUserListDto.UserItem user : page.getResult()) {
                user.setCanAuth(isCanAuth);
                user.setCanDel(isCanDel);
                user.setCanEdit(isCanEdit);
                user.setCanView(isCanView);
            }
            
            RespSystemUserListDto respSystemUserListDto = new RespSystemUserListDto();
            respSystemUserListDto.setData(page.getResult());
            respSystemUserListDto.setPage(page.getPageNum());
            respSystemUserListDto.setPageSize(page.getPageSize());
            respSystemUserListDto.setTotalPages(page.getPages());
            respSystemUserListDto.setTotalRows((int) page.getTotal());
            respSystemUserListDto.setSearchItems(sytemUserListDto.getSearch());
            
            return RespJson.success(respSystemUserListDto);
        } catch (Exception e) {
            logger.error("异常：{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    //@Permission(value="system-user-getRoles")
    @RequestMapping("/getRoles")
    public RespJson getRoles() {
        try {
            List<SystemRole> list = systemRoleService.listAllRoles(new SystemRole());
            List<RespSystemRoleDto> dtos = new ArrayList<RespSystemRoleDto>();
            if (list != null && list.size() > 0) {
                for (SystemRole systemRole : list) {
                    RespSystemRoleDto dto = new RespSystemRoleDto();
                    BeanUtils.copyProperties(systemRole, dto);
                    dto.setRoleId(StrUtils.int2Str(systemRole.getRoleId()));
                    dto.setOrderIndex(StrUtils.int2Str(systemRole.getOrderIndex()));
                    dto.setStatus(StrUtils.int2Str(systemRole.getStatus()));
                    dto.setType(StrUtils.int2Str(systemRole.getType()));
                    dtos.add(dto);
                }
            }
            return RespJson.success(dtos);
        } catch (Exception e) {
            logger.error("异常：{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="system-user-saveRight")
    @RequestMapping("/saveRight")
    public RespJson saveUserRight(@RequestBody @Validated SystemUserRightDto systemUserRightDto,
                                  @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {

            SystemUserRight systemUserRight = new SystemUserRight();
            systemUserRight.setUserId(StrUtils.str2Int(systemUserRightDto.getUserId()));
            String rightCodes = JSONArray.toJSONString(systemUserRightDto.getUserRight());
            systemUserRight.setRightCodes(rightCodes);
            systemUserRight.setCreateTime(new Date());
            systemUserRight.setUpdateTime(new Date());
            SystemUser currentUser = this.getCurSystemUser(sessionId);
            if(currentUser!=null){
                systemUserRight.setUpdateUserId(currentUser.getUserId());
                systemUserRight.setCreateUserId(currentUser.getUserId());
            }

            systemUserService.saveUserRight(systemUserRight);

            return RespJson.success();
        } catch (Exception e) {
            logger.error("异常：{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="system-user-userRight")
    @RequestMapping("/userRight")
    public RespJson getUserRight(@RequestParam("user_id") Integer userId) {
        try {
        	SystemUserRightDto systemUserRightDto = new SystemUserRightDto();
        	systemUserRightDto.setUserId(StrUtils.int2Str(userId));
            SystemUserRight systemUserRight = systemUserService.selectByUserId(userId);
            if (systemUserRight != null) {
                List<SystemUserRightDto.UserRight> userRights = JSONArray.parseArray(systemUserRight.getRightCodes(), SystemUserRightDto.UserRight.class);
                systemUserRightDto.setUserRight(userRights);
            }
            return RespJson.success(systemUserRightDto);
        } catch (Exception e) {
            logger.error("异常：{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

}
