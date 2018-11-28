package com.jyblife.logic.wl.ops.manage.listener;

import com.jyblife.logic.wl.ops.core.spring.ApplicationContextHolder;
import com.jyblife.logic.wl.ops.manage.common.AreaCache;
import com.jyblife.logic.wl.ops.manage.mapper.AreaCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    protected Logger logger = LoggerFactory.getLogger(ApplicationRefreshListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
           // AreaCodeMapper areaCodeMapper = ApplicationContextHolder.getBean(AreaCodeMapper.class);
            logger.info("============= 查询area缓存 ==============");
            //看是否需要加入到redis缓存中
            /*try {
                AreaCache.areaCodeCache = areaCodeMapper.selectTreeCode(0);
            }catch (Exception e){
                logger.error("查询area缓存异常:{}",e.getMessage());
            }*/
            logger.info("============= 查询area缓存 end ==============");
        }
    }
}
