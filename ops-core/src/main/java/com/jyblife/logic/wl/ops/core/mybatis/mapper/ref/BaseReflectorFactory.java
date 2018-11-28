package com.jyblife.logic.wl.ops.core.mybatis.mapper.ref;

import java.util.Hashtable;
import java.util.Map;

public class BaseReflectorFactory {

	private BaseReflectorFactory() {
	};

	/**
	 * 缓存reflector
	 */
	private static Map<Class<?>, BaseReflector> cacheReflectorMap = new Hashtable<Class<?>, BaseReflector>();

	public static BaseReflector getReflector(Class<?> cls) {
		BaseReflector ref = cacheReflectorMap.get(cls);
		if (ref == null) {
			// 初始化
			synchronized (cls) {
				ref = new BaseReflector(cls);
				cacheReflectorMap.put(cls, ref);
			}
		}

		return ref;
	}

}
