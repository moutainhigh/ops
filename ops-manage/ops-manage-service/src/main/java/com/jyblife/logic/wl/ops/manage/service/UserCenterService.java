package com.jyblife.logic.wl.ops.manage.service;

import com.jyblife.logic.wl.ops.common.result.RespJson;

/**
 * 同步用户中心服务
 */
public interface UserCenterService {

	/**
	 * 同步用户数据
	 */
	public void synUser();
	
	/**
	 * 登录校验用户名密码
	 * @param userName
	 * @param password
	 * @return
	 */
	public RespJson verifyUsernamePassword(String userName, String password);
	
}
