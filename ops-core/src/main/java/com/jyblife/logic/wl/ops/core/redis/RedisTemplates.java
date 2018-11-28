package com.jyblife.logic.wl.ops.core.redis;

import org.redisson.api.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

//@Component
//@ConditionalOnBean(RedissonProperties.class)
public class RedisTemplates {

	//@Autowired
	private RedissonClient redissonClient;

	public RedissonClient getRedissonClient() {
		return redissonClient;
	}

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	@PostConstruct
	public void init() {

	}
	
	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, String value) {
		try {
			redissonClient.getBucket(key).set(value);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	/**
	 * 写入缓存设置时效时间
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, String value, Long expireTime) {
		try {
			redissonClient.getBucket(key).set(value, expireTime, TimeUnit.SECONDS);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
	 redissonClient.getKeys().deleteByPattern(pattern);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		redissonClient.getKeys().delete(key);
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redissonClient.getKeys().countExists(key)>0;
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		RBucket<String> bucket = redissonClient.getBucket(key);
		return bucket.get();
	}

	/**
	 * 哈希 添加
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void hmSet(String key, String hashKey, String value) {
		RMap<String, String> rMap = redissonClient.getMap(key);
		rMap.put(hashKey,value);
	}

	/**
	 * 哈希获取数据
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public String hmGet(String key, String hashKey) {
		RMap<String, String> rMap = redissonClient.getMap(key);
		return rMap.get(hashKey);
	}

	/**
	 * 列表添加
	 * 
	 * @param k
	 * @param v
	 */
	public void lPush(String k, String v) {
		RList<String> rList= redissonClient.getList(k);
		rList.add(v);
	}

	/**
	 * 列表获取
	 * 
	 * @param k
	 * @param l
	 * @param l1
	 * @return
	 */
	public List<String> lRange(String k, long l, long l1) {
		RList<String> rList= redissonClient.getList(k);
		return rList.subList((int)l,(int)l1).readAll();
	}

	/**
	 * 集合添加
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, String value) {
		RSet<String> rSet=redissonClient.getSet(key);
		rSet.add(value);
	}

	/**
	 * 集合获取
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> getMembers(String key) {
		RSet<String> rSet=redissonClient.getSet(key);
		return rSet.readAll();
	}

	/**
	 * 有序集合添加
	 * 
	 * @param key
	 * @param value
	 * @param scoure
	 */
	public void zAdd(String key, String value, double scoure) {
		RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		rScoredSortedSet.add(scoure,value);

	}



	/**
	 * 有序集合获取
	 * 
	 * @param key
	 * @param scoure
	 * @param scoure1
	 * @return
	 */
	public Set<String> rangeByScore(String key, double scoure, double scoure1) {
		Set<String> set = new HashSet<String>();
		RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		Collection<String> collection = rScoredSortedSet.valueRange(scoure,true,scoure1,true);
		collection.stream().forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				set.add(s);
			}
		});

		return set;
	}

	/**
	 * 处理分布式锁用
	 * 网络差情况下请不要随意使用
	 * @param key
	 * @param value
	 * @param timeout 超时时间,秒为单位
	 * @return
	 */
	public boolean setNX(final String key,final String value,long timeout) {
		RBucket<String> bucket = redissonClient.getBucket(key);
		return bucket.trySet(value,timeout,TimeUnit.SECONDS);
	}
	
	/**
	 * 是否重复
	 * @param key
	 * @param timeout 失效时间（单位/秒）
	 * @return
	 */
	public boolean ifRedo(String key,long timeout) {
		try {
			boolean isExist = this.exists(key);
			if(!isExist) {
				return this.set(key,"",timeout);
			}
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * 增加并获取返回值
	 * @param key
	 * @param value
	 * @param expirSeconds
	 * @return
	 */
	public Long incrementAndGet(String key,Integer value,Long expirSeconds){
		RAtomicLong rAtomicLong = redissonClient.getAtomicLong(key);
		rAtomicLong.expire(expirSeconds,TimeUnit.SECONDS);
		return rAtomicLong.addAndGet(value);
	}
	
}
