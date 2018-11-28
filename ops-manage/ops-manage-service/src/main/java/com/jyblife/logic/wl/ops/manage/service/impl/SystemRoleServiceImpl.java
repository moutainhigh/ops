package com.jyblife.logic.wl.ops.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.entity.SystemRole;
import com.jyblife.logic.wl.ops.entity.SystemRoleRight;
import com.jyblife.logic.wl.ops.manage.dto.SystemRoleListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.mapper.SystemRoleMapper;
import com.jyblife.logic.wl.ops.manage.mapper.SystemRoleRightMapper;
import com.jyblife.logic.wl.ops.manage.mapper.UserRoleRelationMapper;
import com.jyblife.logic.wl.ops.manage.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangcey
 * @date 2018/9/18
 **/
@Service
@Transactional
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleMapper systemRoleMapper;
    @Autowired
    private SystemRoleRightMapper systemRoleRightMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public void saveRole(SystemRole systemRole) {
        SystemRole systemRole1 = this.selectByName(systemRole.getName());
        if (systemRole1 != null) {
            throw new ManageServiceException(ResultCodeEnum.ROLE_NAME_EXIST);
        }
        systemRoleMapper.insertOne(systemRole);
    }

    @Override
    public void updateRole(SystemRole systemRole) {
        SystemRole systemRole1 = this.selectByName(systemRole.getName());
        if (systemRole1 != null && systemRole.getRoleId().intValue() != systemRole1.getRoleId().intValue()) {
            throw new ManageServiceException(ResultCodeEnum.ROLE_NAME_EXIST);
        }
        systemRoleMapper.updateWithoutNull(systemRole);
    }

    @Override
    public void delRole(Integer roleId) {
        systemRoleMapper.delete(SystemRole.class, roleId);
        systemRoleRightMapper.delete(SystemRoleRight.class, roleId);
        userRoleRelationMapper.deleteByRoleId(roleId);
    }

    @Override
    public Page<SystemRole> listPageRole(SystemRoleListDto.Search search, int page, int pageSize) {
        Page<SystemRole> pager = PageHelper.startPage(page, pageSize);
        systemRoleMapper.selectBySearch(search);
        return pager;
    }

    @Override
    public SystemRole detailRole(Integer id) {
        return systemRoleMapper.get(SystemRole.class, id);
    }

    @Override
    public SystemRoleRight selectRoleRight(Integer id) {
        return systemRoleRightMapper.get(SystemRoleRight.class, id);
    }

    @Override
    public void saveRoleRight(SystemRoleRight systemRoleRight) {
        //先删除
        systemRoleRightMapper.delete(SystemRoleRight.class, systemRoleRight.getRoleId());
        //再插入
        systemRoleRightMapper.insertOne(systemRoleRight);
    }

    @Override
    public SystemRole selectByName(String name) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);

        List<SystemRole> list = systemRoleMapper.list(SystemRole.class, map);

        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

	@Override
	public List<SystemRole> selectRoleListByIds(List<Integer> roleIds) {
		if(roleIds == null || roleIds.isEmpty()) {
			return null;
		}
		return systemRoleMapper.selectRoleListByIds(roleIds);
	}

	@Override
	public List<SystemRole> selectByUserId(Integer userId) {
		return systemRoleMapper.selectByUserId(userId);
	}

	@Override
	public List<SystemRoleRight> selectRoleRightByUserId(Integer userId) {
		return systemRoleRightMapper.selectByUserId(userId);
	}

    @Override
    public SystemRole selectById(Integer roleId) {
        return systemRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<SystemRole> listAllRoles(SystemRole systemRole) {
        return systemRoleMapper.selectSelective(systemRole);
    }
}
