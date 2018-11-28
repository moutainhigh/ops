package com.jyblife.logic.wl.ops.core.log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.JmfReportUtils;
import com.alibaba.dubbo.rpc.RpcContext;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;

public class OpsReportLogAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	@SuppressWarnings("rawtypes")
	protected LayoutWrappingEncoder encoder;

	private Boolean logLineAndMethod = false;

	@Override
	public void start() {
	    super.start();
	}

	@Override
	public void stop() {
		try {
			super.stop();
		} finally {

		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void append(ILoggingEvent event) {
		Layout layout = encoder.getLayout();
		final Map<String, Object> data = new HashMap<String, Object>();
		data.put("log_level", event.getLevel().toString().toLowerCase());
		String addr = RpcContext.getContext() != null ? RpcContext.getContext().getLocalHost() : ConfigUtils.getProperty(Constants.APPLICATION_REPORT_HOST, "0.0.0.0");
		data.put("svr_addr", addr);
		data.put("msg", layout.doLayout(event));
		data.put("logger", event.getLoggerName());
		data.put("thread", event.getThreadName());
		data.put("ts", event.getTimeStamp());

		if (logLineAndMethod) {
			try {
				if (layout instanceof PatternLayout) {
					PatternLayout patternLayout = (PatternLayout) layout;
					Map<String, String> defaultConverterMap = patternLayout.getDefaultConverterMap();
					if (defaultConverterMap.containsKey("line")) {
						Class clz = Class.forName(defaultConverterMap.get("line"));
						Object obj = clz.newInstance();
						Method[] methods = clz.getMethods();
						for (Method method : methods) {
							if ("convert".equals(method.getName())) {
								data.put("line", method.invoke(obj, event));
							}
						}
					}
					if (defaultConverterMap.containsKey("method")) {
						Class clz = Class.forName(defaultConverterMap.get("method"));
						Object obj = clz.newInstance();
						Method[] methods = clz.getMethods();
						for (Method method : methods) {
							if ("convert".equals(method.getName())) {
								data.put("func", method.invoke(obj, event));
							}
						}
					}
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {

			}
		}

		JmfReportUtils.report("log", data);
	}

	@SuppressWarnings("rawtypes")
	public LayoutWrappingEncoder getEncoder() {
		return encoder;
	}

	@SuppressWarnings("rawtypes")
	public void setEncoder(LayoutWrappingEncoder encoder) {
		this.encoder = encoder;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setLayout(Layout layout) {
		LayoutWrappingEncoder layoutWrappingEncoder = new LayoutWrappingEncoder();
		layoutWrappingEncoder.setLayout(layout);
		this.encoder = layoutWrappingEncoder;
	}

	public Boolean getLogLineAndMethod() {
		return logLineAndMethod;
	}

	public void setLogLineAndMethod(Boolean logLineAndMethod) {
		this.logLineAndMethod = logLineAndMethod;
	}
	
}
