package com.jyblife.logic.wl.ops.common.excel;

import java.util.HashMap;
import java.util.Map;


public class InvokerHolder {

    private static Map<String, Map<String, Invoker>> invokerMap = new HashMap<String, Map<String, Invoker>>();

    public static void put(String serviceName, String methodName, Invoker object) {
        Map<String, Invoker> cmap = invokerMap.get(serviceName);
        if (cmap == null) {
            cmap = new HashMap<String, Invoker>();
            cmap.put(methodName, object);

            invokerMap.put(serviceName, cmap);
        } else {
            cmap.put(methodName, object);
        }
    }

    public static Invoker get(String serviceName, String methodName) {
        Map<String, Invoker> cmap = invokerMap.get(serviceName);
        if (cmap == null) {
            return null;
        } else {
            return cmap.get(methodName);
        }
    }
}
