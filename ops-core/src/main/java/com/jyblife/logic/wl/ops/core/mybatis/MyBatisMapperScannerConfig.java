package com.jyblife.logic.wl.ops.core.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
@MapperScan(basePackages={"com.jyblife.logic.wl.**.mapper"})
public class MyBatisMapperScannerConfig {
	
    //采用java配置 此配置无效 注释掉 
	/*@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.jyblife.route.*.mapper,com.jyblife.route.*.*.mapper");
		return mapperScannerConfigurer;
	}*/

}