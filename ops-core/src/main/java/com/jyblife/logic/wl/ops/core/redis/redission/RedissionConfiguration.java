package com.jyblife.logic.wl.ops.core.redis.redission;

import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.core.redis.RedissLock;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissionConfiguration {

    @Autowired
    private RedissonProperties redssionProperties;
    
    /**
     * 哨兵模式
     *
     * @return
     */
    @Bean("redissonClient")
    @ConditionalOnProperty(name = "redisson.masterName")
    public RedissonClient redissonSentinel() {
        Config config = new Config();
        SentinelServersConfig serverConfig = config.useSentinelServers()
                .addSentinelAddress(redssionProperties.getSentinelAddresses())
                .setMasterName(redssionProperties.getMasterName())
                .setTimeout(redssionProperties.getTimeout())
                .setMasterConnectionPoolSize(redssionProperties.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redssionProperties.getSlaveConnectionPoolSize());

        if (StringUtils.isNotBlank(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }
        
        return Redisson.create(config);
    }

    /**
     * 单机模式
     *
     * @return
     */
    @Bean("redissonClient")
    @ConditionalOnProperty(name = "redisson.address")
    public RedissonClient redissonSingle(RedissonProperties redssionProperties) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redssionProperties.getAddress())
                .setTimeout(redssionProperties.getTimeout())
                .setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize());

        if (StringUtils.isNotBlank(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }

        return Redisson.create(config);
    }

    @Bean
    @DependsOn("redissonClient")
    @ConditionalOnBean(RedissonClient.class)
    public RedisTemplates createRedisTemplates(RedissonClient redissonClient){
        RedisTemplates redisTemplates = new RedisTemplates();
        redisTemplates.setRedissonClient(redissonClient);
        return redisTemplates;
    }

    @Bean
    @DependsOn("redissonClient")
    @ConditionalOnBean(RedissonClient.class)
    public RedissLock createRedissLock(RedissonClient redissonClient){
        return new RedissLock(redissonClient);
    }
    
}