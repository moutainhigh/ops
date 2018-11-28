package com.jyblife.logic.wl.ops.common.command.core;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {

    private static Map<String, Map<String, CommandInvoker>> invokerMap = new HashMap<String, Map<String, CommandInvoker>>();

    public static void put(String serviceName, String methodName, CommandInvoker object) {
        Map<String, CommandInvoker> cmap = invokerMap.get(serviceName);
        if (cmap == null) {
            cmap = new HashMap<String, CommandInvoker>();
            cmap.put(methodName, object);

            invokerMap.put(serviceName, cmap);
        } else {
            cmap.put(methodName, object);
        }
    }

    public static CommandInvoker get(String serviceName, String methodName) {
        Map<String, CommandInvoker> cmap = invokerMap.get(serviceName);
        if (cmap == null) {
            return null;
        } else {
            return cmap.get(methodName);
        }
    }
}
