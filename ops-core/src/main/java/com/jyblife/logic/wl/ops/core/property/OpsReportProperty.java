package com.jyblife.logic.wl.ops.core.property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.alibaba.dubbo.common.utils.ConfigUtils;

/**
 * 加载上报日记配置
 */
public class OpsReportProperty {

	public static String APPLICATION_PROPERTIES_NAME = "application-${active}.properties";
	public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
	
    static {
        System.out.println("开始加载properties文件内容...........");
        Properties prop = new Properties();
        InputStream in = null;
        String active = null; // 不同环境-默认dev
        
        try {
        	//第一种，通过类加载器进行获取properties文件流
        	ClassLoader loader = OpsReportProperty.class.getClassLoader();
            in = loader.getResourceAsStream("application.properties");
            //第二种，通过类进行获取properties文件流
            //in = PropertyUtil.class.getResourceAsStream("/application.properties");
            prop.load(in);
            active = prop.getProperty(SPRING_PROFILES_ACTIVE, "dev");
            
            if (in != null) {
                in.close();
            }
            
            APPLICATION_PROPERTIES_NAME = APPLICATION_PROPERTIES_NAME.replace("${active}", active);
            in = loader.getResourceAsStream(APPLICATION_PROPERTIES_NAME);
            prop.load(in);
            
            ConfigUtils.addProperties(prop);
        } catch (FileNotFoundException e) {
        	System.out.println("properties文件未找到...........");
        } catch (IOException e) {
        	System.out.println("出现IOException...........");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
            	System.out.println("properties文件流关闭出现异常...........");
            }
        }
        
        System.out.println("加载properties配置内容完成...........");
    }
    
}
