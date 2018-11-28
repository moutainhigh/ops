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
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.core.spring.ApplicationContextHolder;

/**
 * 接口登录验证拦截器
 * 如果需要拦截认证在接口方法名上加上 ：@Authentication 即可
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	// 在调用方法之前执行拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		Authentication access = method.getAnnotation(Authentication.class);
		if (access == null) {
			return true;
		}
		
		Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
        	for(Cookie cookie : cookies) {
                if(Constants.USER_LOGIN_COOKIE_KEY.equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())) {
                	RedisTemplates redis = ApplicationContextHolder.getBean(RedisTemplates.class);
        			String user = redis.get(Constants.USER_LOGIN_TOKEN_REDIS_KEY_+cookie.getValue());
        			if(StringUtils.isNotBlank(user)) {
        				return true;
        			}
                }
            }
        }
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(RespJson.error(ResultCodeEnum.LOGIN_TIME_OUT)));
		out.close();
		
		return false;
	}

}
