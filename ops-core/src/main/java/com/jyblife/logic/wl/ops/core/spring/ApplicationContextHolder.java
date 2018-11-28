package com.jyblife.logic.wl.ops.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * applicaton context holder
 * @Author yangcey
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware, EnvironmentAware {

    private static ApplicationContext applicationContext = null;
    private static Environment environment = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext1) throws BeansException {
        applicationContext = applicationContext1;
    }

    @Override
    public void setEnvironment(Environment environment1) {
        environment = environment1;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Environment getEnvironment() {
        return environment;
    }

    @SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    public static String getProperty(String key) {
        return environment.getProperty(key);
    }


}
