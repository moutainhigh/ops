package com.jyblife.logic.wl.ops.common.command.core;

import com.jyblife.logic.wl.ops.common.exception.OpsException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommandInvoker {
	
    Object target;
    Method method;

    public CommandInvoker(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public Object invoke(Object... paramValues) throws Exception {
        try {
            return method.invoke(target, paramValues);
        } catch (IllegalAccessException | IllegalArgumentException|InvocationTargetException e) {
            //抛出自定义业务异常
            if(e.getCause() instanceof OpsException){
                throw (OpsException)e.getCause();
            }
        }
        return null;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
