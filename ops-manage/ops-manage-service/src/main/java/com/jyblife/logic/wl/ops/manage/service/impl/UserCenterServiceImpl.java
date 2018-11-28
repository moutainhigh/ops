package com.jyblife.logic.wl.ops.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.EncryptUtil;
import com.jyblife.logic.wl.ops.common.utils.HttpUtils;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.config.UserCenterConfig;
import com.jyblife.logic.wl.ops.manage.mapper.SystemUserMapper;
import com.jyblife.logic.wl.ops.manage.service.UserCenterService;

@Service
@Transactional
public class UserCenterServiceImpl implements UserCenterService {

	public static Logger logger = LoggerFactory.getLogger(UserCenterServiceImpl.class);
	
	@Autowired
	private SystemUserMapper systemUserMapper;
	
	@Autowired
	private UserCenterConfig config;
	
	@Override
	public void synUser() {
		
		JSONObject params = new JSONObject();
		params.put("cmd", config.getSynCmd());
		JSONObject param = new JSONObject();
		param.put("client_id", config.getClientId());
		param.put("secret_key", EncryptUtil.encryptTo32Md5(config.getClientId()+"-"+config.getClientSecret()));
		params.put("data", param);
		String response = HttpUtils.postJson(config.getApi(), JSON.toJSONString(params));
		
		if(StringUtils.isNotBlank(response)) {
			JSONObject responseData = JSON.parseObject(response);
			if("0".equals(responseData.getString("code")) && responseData.containsKey("data")) {
				JSONArray userData = responseData.getJSONArray("data");
				
				List<SystemUser> allUser = systemUserMapper.selectUserCenterUsers();
				List<Integer> userIds = new ArrayList<>();
				Map<String, Object> ids = new HashMap<>();
				for(SystemUser user : allUser) {
					userIds.add(user.getUserCenterId());
					ids.put(String.valueOf(user.getUserCenterId()), user.getUserId());
				}
				
				SystemUser user = null;
				for(int i=0; i<userData.size(); i++) {
					user = new SystemUser();
					user.setEmail(userData.getJSONObject(i).getString("email"));
					user.setPhone(userData.getJSONObject(i).getString("phone"));
					user.setName(userData.getJSONObject(i).getString("name"));
					user.setRemark(userData.getJSONObject(i).getString("remark"));
					user.setUserName(userData.getJSONObject(i).getString("user_name"));
					user.setUpdateTime(new Date());
					user.setUserCenterId(userData.getJSONObject(i).getIntValue("user_id"));
					
					if(userIds.contains(user.getUserCenterId())) {
						user.setUserId(Integer.valueOf(ids.get(String.valueOf(user.getUserCenterId())).toString()));
						user.setUpdateTime(new Date());
						systemUserMapper.updateWithoutNull(user);
						userIds.remove(user.getUserCenterId());
					} else {
						user.setCreateTime(new Date());
						user.setStatus(1); //暂时默认都是启用的
						systemUserMapper.insertOne(user);
					}
				}
				
				//删除无用的用户数据
				if(userIds.size() > 0) {
					systemUserMapper.deleteUsersByUserCenterIds(userIds);
				}
			}
		} else {
			logger.error("===========同步用户中心数据，请求用户中心失败===============");
		}
		
	}

	@Override
	public RespJson verifyUsernamePassword(String userName, String password) {
		
		JSONObject params = new JSONObject();
		params.put("cmd", config.getLoginCmd());
		JSONObject param = new JSONObject();
		param.put("client_id", config.getClientId());
		param.put("username", userName);
		param.put("password", password.toUpperCase());
		param.put("secret_key", EncryptUtil.encryptTo32Md5(userName+"-"+password+"-"+config.getClientId()+"-"+config.getClientSecret()));
		params.put("data", param);
		String response = HttpUtils.postJson(config.getApi(), params.toJSONString());
		
		if(StringUtils.isBlank(response)) {
			return RespJson.error(ResultCodeEnum.REQUEST_USER_CENTER_ERROR);
		}
		
		JSONObject resJson = JSON.parseObject(response);
		if ("0".equals(resJson.getString("code"))) {
			JSONObject json = resJson.getJSONObject("data");
			return RespJson.success(json);
		} else {
			return RespJson.error(resJson.getString("msg"));
		}
		
	}

}
