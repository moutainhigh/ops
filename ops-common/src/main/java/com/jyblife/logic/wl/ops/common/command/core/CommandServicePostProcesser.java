package com.jyblife.logic.wl.ops.common.command.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.jyblife.logic.wl.ops.common.command.annotation.CommandMethod;
import com.jyblife.logic.wl.ops.common.command.annotation.CommandService;

import java.lang.reflect.Method;

@Component
public class CommandServicePostProcesser implements BeanPostProcessor {
	
    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        Class<?>[] clazzs = bean.getClass().getInterfaces();
        for (Class<?> clazz : clazzs) {
            CommandService commandService = clazz.getAnnotation(CommandService.class);
            if (commandService != null) {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    CommandMethod commandMethod = method.getAnnotation(CommandMethod.class);
                    if (commandMethod != null) {
                       CommandHolder.put(commandService.value(),commandMethod.value(),new CommandInvoker(bean,method));
                    }
                }
            }
        }

        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}
