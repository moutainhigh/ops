package com.jyblife.logic.wl.ops.manage.intercept;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.core.spring.ApplicationContextHolder;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.resp.UserInfoDto;

/**
 * 接口权限验证拦截器
 * 如果需要拦截认证在接口方法名上加上 ：@Permission 即可
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	
	// 在调用方法之前执行拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		Permission permission = method.getAnnotation(Permission.class);
		if (permission == null) {
			return true;
		}
		
		String right = permission.value();
		if(StringUtils.isBlank(right)) {
			return true;
		}
		
		RedisTemplates redis = null;
		SystemUser user = null;
		Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
        	for(Cookie cookie : cookies) {
                if(Constants.USER_LOGIN_COOKIE_KEY.equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())) {
                	redis = ApplicationContextHolder.getBean(RedisTemplates.class);
        			String userStr = redis.get(Constants.USER_LOGIN_TOKEN_REDIS_KEY_+cookie.getValue());
        			if(StringUtils.isNotBlank(userStr)) {
        				user = JSON.parseObject(userStr, SystemUser.class);
        				break;
        			}
                }
            }
        }
		
        if (user != null) {
        	String userInfoStr = redis.get(Constants.USER_INFO_REDIS_KEY_+user.getUserId());
        	if(StringUtils.isNotBlank(userInfoStr)) {
    			UserInfoDto userInfo = JSON.parseObject(userInfoStr, UserInfoDto.class);
    			JSONArray userRight = userInfo.getUserRight();
    			
    			String code = "";
    			String action = "";
    			JSONArray actions = null;
				for(int i=0; i<userRight.size(); i++) {
					code = userRight.getJSONObject(i).getString("code");
					actions = userRight.getJSONObject(i).getJSONArray("actions");
					for(int j=0; j<actions.size(); j++) {
						action = actions.getJSONObject(j).getString("code");
						if(right.equals(code + "-" + action)) {
							return true;
						}
					}
				}
				
    		}
        }
        
        response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(RespJson.error(user != null ? ResultCodeEnum.PERMISSION_ERROR : ResultCodeEnum.LOGIN_TIME_OUT)));
		out.close();
		
		return false;
	}

}
