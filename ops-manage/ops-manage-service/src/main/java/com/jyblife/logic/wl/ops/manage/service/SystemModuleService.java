package com.jyblife.logic.wl.ops.manage.service;

import com.jyblife.logic.wl.ops.entity.SystemModule;
import com.jyblife.logic.wl.ops.manage.dto.SysModuleIndexDto;
import com.jyblife.logic.wl.ops.manage.dto.SystemModuleSaveDto;

import java.util.List;

public interface SystemModuleService {

    /**
     * 新增菜单模块
     *
     * @param systemModule
     */
    void saveSystemModule(SystemModule systemModule);

    /**
     * 更新菜单模块
     *
     * @param systemModule
     */
    void updateSystemModule(SystemModule systemModule);

    /**
     * 查询所有的树形结构菜单
     *
     * @return
     */
    List<SysModuleIndexDto> selectAllTreeMenus();

    /**
     * 查询顶层菜单
     *
     * @return
     */
    List<SystemModule> selectTopMenus();


    /**
     * 查询直接子节点菜单
     *
     * @param menuId
     * @param isMenu
     * @return
     */
    List<SystemModule> selectChildMenus(Integer menuId,Integer isMenu);

    /**
     * 查询直接父节点菜单
     *
     * @param menuId
     * @return
     */
    SystemModule selectParentMenu(Integer menuId);

    /**
     * 删除子模块
     * @param menuId
     */
    void deleteSystemModule(Integer menuId);

    /**
     * 根据id查询模块详情
     * @param id
     * @return
     */
    SystemModuleSaveDto selectModuleDto(Integer id);
    
    /**
     * 根据主键集合批量获取
     * @param moduleIds
     * @return
     */
    List<SystemModule> selectByModuleIds(List<Integer> moduleIds);

    /**
     * 查询模块
     * @param code
     * @return
     */
    SystemModule selectByCode(String code);
}
