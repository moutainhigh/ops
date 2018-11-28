package com.jyblife.logic.wl.ops.common.excel;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yangcey
 */
@Component
public class ExcelBeanPostProcesser implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {

        Class<?>[] clazzs = bean.getClass().getInterfaces();
        for (Class<?> clazz : clazzs) {
            ExcelService excelService = clazz.getAnnotation(ExcelService.class);
            if (excelService != null) {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    ExcelMethod excelMethod = method.getAnnotation(ExcelMethod.class);
                    if (excelMethod != null) {
                        InvokerHolder.put(clazz.getName(),method.getName(),new Invoker(bean,method));
                    }
                }
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}
