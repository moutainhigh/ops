package com.jyblife.logic.wl.ops.manage.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.SystemModule;
import com.jyblife.logic.wl.ops.manage.dto.SysModuleIndexDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemModuleActionDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemModuleSaveDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.mapper.SystemModuleMapper;
import com.jyblife.logic.wl.ops.manage.service.SystemModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangcey
 */
@Service
@Transactional
public class SystemModuleServiceImpl implements SystemModuleService {
    @Autowired
    private SystemModuleMapper systemModuleMapper;


    @Override
    public void saveSystemModule(SystemModule systemModule) {
        if (this.selectByCode(systemModule.getCode()) != null) {
            throw new ManageServiceException(ResultCodeEnum.MODULE_CODE_EXIST);
        }
        systemModuleMapper.insertOne(systemModule);
    }

    @Override
    public void updateSystemModule(SystemModule systemModule) {
        SystemModule systemModule1 = this.selectByCode(systemModule.getCode());
        if (systemModule1 != null && systemModule1.getId().intValue() != systemModule.getId().intValue()) {
            throw new ManageServiceException(ResultCodeEnum.MODULE_CODE_EXIST);
        }
        systemModuleMapper.updateWithoutNull(systemModule);
    }

    @Override
    public List<SysModuleIndexDto> selectAllTreeMenus() {
        List<SysModuleIndexDto> dtoMenus = new ArrayList<SysModuleIndexDto>();
        //查询所有顶层菜单
        List<SystemModule> topMenus = this.selectTopMenus();
        for (SystemModule systemModule : topMenus) {
            SysModuleIndexDto sysModuleIndexDto = this.createDtoChildModule(systemModule);
            dtoMenus.add(sysModuleIndexDto);
        }

        return dtoMenus;
    }

    private SysModuleIndexDto createDtoChildModule(SystemModule systemModule) {

        SysModuleIndexDto sysModuleIndexDto = new SysModuleIndexDto();

        //查询子菜单
        List<SystemModule> children = this.selectChildMenus(systemModule.getId(), null);
        List<SysModuleIndexDto> indexChildren = new ArrayList<SysModuleIndexDto>();
        for (SystemModule systemModule1 : children) {
            indexChildren.add(this.createDtoChildModule(systemModule1));
        }
        sysModuleIndexDto.setChildren(indexChildren);

        //设置菜单其他属性
        sysModuleIndexDto.setCode(systemModule.getCode());
        sysModuleIndexDto.setId(String.valueOf(systemModule.getId()));
        sysModuleIndexDto.setLabel(systemModule.getName());
        sysModuleIndexDto.setParentId(String.valueOf(systemModule.getParentId()));
        sysModuleIndexDto.setStatus(String.valueOf(systemModule.getStatus()));

        //json装换
        List<SystemModuleActionDto> dtoActions = JSONArray.parseArray(systemModule.getActions(), SystemModuleActionDto.class);
        sysModuleIndexDto.setActions(dtoActions);

        return sysModuleIndexDto;
    }

    @Override
    public List<SystemModule> selectTopMenus() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", 0);
        return systemModuleMapper.list(SystemModule.class, map);
    }

    @Override
    public List<SystemModule> selectChildMenus(Integer menuId, Integer isMenu) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", menuId);
        if (isMenu != null) {
            //菜单
            map.put("isMenu", isMenu);
        }
        return systemModuleMapper.list(SystemModule.class, map);
    }

    @Override
    public SystemModule selectParentMenu(Integer menuId) {
        SystemModule systemModule = this.systemModuleMapper.get(SystemModule.class, menuId);
        SystemModule parentModule = null;
        if (systemModule != null) {
            parentModule = this.systemModuleMapper.get(SystemModule.class, systemModule.getParentId());
        }
        return parentModule;
    }

    @Override
    public void deleteSystemModule(Integer menuId) {
        List<SystemModule> list = this.selectChildMenus(menuId, null);
        if (list != null && list.size() > 0) {
            throw new ManageServiceException(ResultCodeEnum.MODULE_DEL_HAS_CHILD_TIP);
        }

        this.systemModuleMapper.delete(SystemModule.class, menuId);
    }

    @Override
    public SystemModuleSaveDto selectModuleDto(Integer id) {
        SystemModuleSaveDto systemModuleSaveDto = null;
        SystemModule systemModule = this.systemModuleMapper.get(SystemModule.class, id);

        if (systemModule != null) {
            systemModuleSaveDto = new SystemModuleSaveDto();
            //json装换
            List<SystemModuleActionDto> dtoActions = JSONArray.parseArray(systemModule.getActions(), SystemModuleActionDto.class);

            systemModuleSaveDto.setActions(dtoActions);

            BeanUtils.copyProperties(systemModule, systemModuleSaveDto);

            if (systemModule.getIsMenu() != null) {
                systemModuleSaveDto.setIsMenu(StrUtils.int2Str(systemModule.getIsMenu().intValue()));
            }
            if (systemModule.getStatus() != null) {
                systemModuleSaveDto.setStatus(StrUtils.int2Str(systemModule.getStatus().intValue()));
            }
            if (systemModule.getIsPublic() != null) {
                systemModuleSaveDto.setIsPublic(StrUtils.int2Str(systemModule.getIsPublic().intValue()));
            }
            if (systemModule.getIsExternal() != null) {
                systemModuleSaveDto.setIsExternal(StrUtils.int2Str(systemModule.getIsExternal().intValue()));
            }

            systemModuleSaveDto.setId(StrUtils.int2Str(systemModule.getId()));

            SystemModule parentModule = this.systemModuleMapper.get(SystemModule.class, systemModule.getParentId());
            if (parentModule != null) {
                systemModuleSaveDto.setParentName(parentModule.getName());
            }
        }

        return systemModuleSaveDto;

    }

    @Override
    public List<SystemModule> selectByModuleIds(List<Integer> moduleIds) {
        return systemModuleMapper.selectByModuleIds(moduleIds);
    }

    @Override
    public SystemModule selectByCode(String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);

        List<SystemModule> list = systemModuleMapper.list(SystemModule.class, map);

        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
