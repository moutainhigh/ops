package com.jyblife.logic.wl.ops.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.common.utils.ValidatorUtil;
import com.jyblife.logic.wl.ops.entity.SystemModule;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.SysModuleIndexDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemModuleActionDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemModuleSaveDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.service.SystemModuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.*;

/**
 * @ahthor yangcey
 */
@RestController
@RequestMapping("/admin/module")
public class SystemModuleController extends BaseController {


    @Autowired
    private SystemModuleService systemModuleService;

    @Permission(value="system-module-save")
    @RequestMapping("/save")
    public RespJson saveSystemModule(@RequestBody @Validated SystemModuleSaveDto systemModuleSaveDto,
                                     @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            //验证action
            String action = "";
            if (systemModuleSaveDto.getActions() != null) {
                for (SystemModuleActionDto actionDto : systemModuleSaveDto.getActions()) {
                    List<String> errorResult = ValidatorUtil.validate(actionDto);
                    if (!errorResult.isEmpty()) {
                        throw new RestException(ResultCodeEnum.PARAM_ERROR.getCode(), errorResult.get(0));
                    }
                }
                action = JSONArray.toJSONString(systemModuleSaveDto.getActions());
            }

            SystemModule systemModule = new SystemModule();
            systemModule.setId(StrUtils.str2Int(systemModuleSaveDto.getId()));
            BeanUtils.copyProperties(systemModuleSaveDto, systemModule);
            systemModule.setActions(action);
            systemModule.setParentId(StrUtils.str2Int(systemModuleSaveDto.getParentId()));
            systemModule.setSystemId(StrUtils.str2Int(systemModuleSaveDto.getSystemId()));
            systemModule.setIsMenu(StrUtils.int2Byte(StrUtils.str2Int(systemModuleSaveDto.getIsMenu())));
            systemModule.setStatus(StrUtils.int2Byte(StrUtils.str2Int(systemModuleSaveDto.getStatus())));
            systemModule.setIsPublic(StrUtils.int2Byte(StrUtils.str2Int(systemModuleSaveDto.getIsPublic())));
            systemModule.setIsExternal(StrUtils.int2Byte(StrUtils.str2Int(systemModuleSaveDto.getIsExternal())));

            SystemUser systemUser = this.getCurSystemUser(sessionId);
            if (StringUtils.isBlank(systemModuleSaveDto.getId())) {
                if (systemUser != null) {
                    systemModule.setUpdateUserId(systemUser.getUserId());
                    systemModule.setCreateUserId(systemUser.getUserId());
                }
                systemModule.setUpdateTime(new Date());
                systemModule.setCreateTime(new Date());
                systemModuleService.saveSystemModule(systemModule);
            } else {
                if (systemUser != null) {
                    systemModule.setUpdateUserId(systemUser.getUserId());
                }
                systemModule.setUpdateTime(new Date());
                systemModuleService.updateSystemModule(systemModule);
            }
            return RespJson.success();
        }catch (OpsException e) {
            logger.error("saveSystemModule异常: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("saveSystemModule异常: {}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="system-module-index")
    @RequestMapping("/index")
    public RespJson indexSystemModule() {
        try {
            List<SysModuleIndexDto> children = this.systemModuleService.selectAllTreeMenus();
            Map<String, List<SysModuleIndexDto>> map = new HashMap<String, List<SysModuleIndexDto>>();
            map.put("children", children);
            return RespJson.success(map);
        } catch (RestException e) {
            logger.error("indexSystemModule异常: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("indexSystemModule异常: {}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="system-module-del")
    @RequestMapping("/del")
    public RespJson delSystemModule(@RequestParam(value = "id") String id) {
        try {
            this.systemModuleService.deleteSystemModule(StrUtils.str2Int(id));
            return RespJson.success();
        } catch (ManageServiceException e) {
            logger.error("delSystemModule 异常: {}", e.getMessage());
            throw new RestException(e);
        } catch (Exception e) {
            logger.error("delSystemModule 异常: {}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

    @Permission(value="system-module-detail")
    @RequestMapping("/detail")
    public RespJson detailSystemModule(@RequestParam(value = "id") String id) {
        try {
            SystemModuleSaveDto systemModuleSaveDto = this.systemModuleService.selectModuleDto(StrUtils.str2Int(id));
            return RespJson.success(systemModuleSaveDto);
        } catch (ManageServiceException e) {
            logger.error("detailSystemModule 异常: {}", e.getMessage());
            throw new RestException(e);
        } catch (Exception e) {
            logger.error("detailSystemModule 异常: {}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }

}
