package com.jyblife.logic.wl.ops.external;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jyblife.logic.wl.ops.core.property.OpsReportProperty;
import com.jyblife.logic.wl.ops.external.json.JSONNameFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 启动服务
 */
@SpringBootApplication(scanBasePackages="com.jyblife.logic.wl")
public class RestApplication {
	
	public static final OpsReportProperty rp = new OpsReportProperty();
	
    public static Logger logger = LoggerFactory.getLogger(RestApplication.class);

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializeConfig serializeConfig = fastJsonConfig.getSerializeConfig();
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setSerializeFilters(new JSONNameFilter());
        fastConverter.setFastJsonConfig(fastJsonConfig);
        
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);//设定json格式且编码为UTF-8
        fastConverter.setSupportedMediaTypes(mediaTypes);
        
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
    
    public static void main(String[] args) {
        
        SpringApplication.run(RestApplication.class, args);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        logger.info("===========================================================");
        logger.info("============ ops-external-rest start  success =============");
        logger.info("===========================================================");
        
        try {
            countDownLatch.await();
        } catch (Throwable e) {
            logger.error("ops-external-rest 启动异常: " + e.getMessage());
        }
        
    }
    
}
