package com.jyblife.logic.wl.ops.core.log;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.fluentd.logger.FluentLogger;

import ch.qos.logback.classic.pattern.CallerDataConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class OpsReportFluentdAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    public static final String DEFAULT_TAG_PREFIX = "monitor";

    private FluentLogger fluentLogger;

    private String tagPrefix;

    private String tag;

    private String host;

    private int port;


    @Override
    public void start() {
        super.start();
        String prefix = tagPrefix != null ? tagPrefix : OpsReportFluentdAppender.DEFAULT_TAG_PREFIX;
        if (host == null || StringUtils.isBlank(host) || port <= 0) {
            throw new RuntimeException("host or port not found for JmfFluentdAppender!");
        }
        fluentLogger = FluentLogger.getLogger(prefix, host, port);
    }

    @Override
    public void stop() {
        try {
            super.stop();
        } finally {
            if (fluentLogger != null) {
                fluentLogger.close();
            }
        }
    }

    protected void append(ILoggingEvent event) {
        final Map<String, Object> data = new HashMap<String, Object>();
        data.put("msg", event.getFormattedMessage());
        data.put("logger", event.getLoggerName());
        data.put("thread", event.getThreadName());
        data.put("level", event.getLevel().toString().toLowerCase());
        data.put("ts", event.getTimeStamp());
        if (event.getMarker() != null) {
            data.put("marker", event.getMarker());
        }
        if (event.hasCallerData()) {
            data.put("caller", new CallerDataConverter().convert(event));
        }
        if (event.getThrowableProxy() != null) {
            data.put("throwable", ThrowableProxyUtil.asString(event.getThrowableProxy()));
        }

        for (Entry<String, String> entry : event.getMDCPropertyMap().entrySet()) {
            data.put(entry.getKey(), entry.getValue());
        }

        if (tag == null) {
            tag = event.getLevel().toString().toLowerCase();
        } else {
            tag = tag + "." + event.getLevel().toString().toLowerCase();
        }

        fluentLogger.log(tag, data);
    }

    public FluentLogger getFluentLogger() {
        return fluentLogger;
    }

    public void setFluentLogger(FluentLogger fluentLogger) {
        this.fluentLogger = fluentLogger;
    }

    public String getTagPrefix() {
        return tagPrefix;
    }

    public void setTagPrefix(String tagPrefix) {
        this.tagPrefix = tagPrefix;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
