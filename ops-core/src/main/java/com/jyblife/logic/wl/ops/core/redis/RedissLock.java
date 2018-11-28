package com.jyblife.logic.wl.ops.core.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * redis分布式锁
 * 
 */
//@Component
//@ConditionalOnBean(RedissonClient.class)
public class RedissLock {

	//@Autowired
	private RedissonClient redissonClient;
	
    public RedissLock(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	/**
     * 加锁
     * @param lockKey
     * @return
     */
    public RLock lock(String lockKey) {
    	RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public void unlock(String lockKey) {
    	RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }
    
    /**
     * 释放锁
     * @param lock
     */
    public void unlock(RLock lock) {
    	lock.unlock();
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public RLock lock(String lockKey, int timeout) {
    	RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, TimeUnit.SECONDS);
        return lock;
    }
    
    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public RLock lock(String lockKey, TimeUnit unit ,int timeout) {
    	RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }
    
    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }
    
    /**
     * 尝试获取锁
     * @param lockKey
     * @param unit 时间单位
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
    	 RLock lock = redissonClient.getLock(lockKey);
         try {
             return lock.tryLock(waitTime, leaseTime, unit);
         } catch (InterruptedException e) {
             return false;
         }
    }
	
}
