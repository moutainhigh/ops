package com.jyblife.logic.wl.ops.common.excel;

import java.lang.reflect.Method;


public class Invoker {
    Object target;
    Method method;

    public Invoker(Object target, Method method) {
        this.target = target;
        this.method = method;
    }


    public Object invoke() throws Throwable {
        return method.invoke(target, null);
    }
}
