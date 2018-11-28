package com.jyblife.logic.wl.ops.manage.intercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	
	public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
		 registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**");
	}
	
}
