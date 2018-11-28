package com.jyblife.logic.wl.ops.manage.mapper;

import java.util.List;

import com.jyblife.logic.wl.ops.core.mybatis.mapper.BaseMapper;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.SytemUserListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespSystemUserListDto;

public interface SystemUserMapper extends BaseMapper<SystemUser, Integer> {

	/**
	 * 获取全部用户中心用户数据
	 * @return
	 */
	public List<SystemUser> selectUserCenterUsers();
	
	/**
	 * 根据用户中心编号批量删除
	 * @param userIds
	 */
	public void deleteUsersByUserCenterIds(List<Integer> userIds);
	
	/**
	 * 更新密码
	 * @param user
	 * @return
	 */
	public int updatePwd(SystemUser user);
	
	/**
	 * 更新登录信息
	 * @param userId
	 */
	public void updateUserLoginInfo(Integer userId);

	/**
	 * 新增用户，返回用户的userid
	 * @param systemUser
	 * @return
	 */
	public int insertSystemUser(SystemUser systemUser);

	/**
	 * 查询列表
	 * @return
	 */
	List<RespSystemUserListDto.UserItem> listPageUser(SytemUserListDto.Search search);
}
