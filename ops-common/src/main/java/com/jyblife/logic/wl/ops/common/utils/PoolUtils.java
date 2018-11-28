package com.jyblife.logic.wl.ops.common.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程工具
 */
public class PoolUtils {

	public static final Executor pool = Executors.newFixedThreadPool(20);
	
}
