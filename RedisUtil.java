/**
 * 
 */
package com.jxsq.common.util;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * @author JX
 * 【redis】工具
 */
public class RedisUtil {
	/**
	 * 日志
	 */
	private static final Logger log = Logger.getLogger(RedisUtil.class);
	
	/**
	 * 通过【缓存名称】获得redis缓存
	 * @param cacheName
	 * @return
	 */
	public static Cache getCache(String cacheName)
	{
		Cache cache = null;
		try {
			cache = Redis.use(cacheName);
		} 
		catch (Exception e) {
			log.error("【Redis】" + e.getMessage() + "，配置错误或redis服务已关闭");
			cache = null;
		}
		return cache;
	}
	
	/**
	 * 通过【key】【field】获得值
	 * @param key
	 * @param field
	 * @param cacheName
	 * @return
	 */
	public static String getCacheValue(String key, String field, String cacheName)
	{
		Cache cache = getCache(cacheName);
		if (cache != null)
			return cache.hget(key, field);
		return null;
	}
	
	/**
	 * 通过【key】【field】获得值
	 * @param key
	 * @param field
	 * @param cacheName
	 * @return
	 */
	public static String getCacheValueValidate(String key, String field, Cache cache)
	{
		try {
			if (cache != null && cache.exists(key))
				return cache.hget(key, field);
			return null;
		} catch (Exception e) {
			log.error("【获取缓存值(验证存在)】出现异常，异常信息：" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 通过【key】获得值
	 * 【默认redis对象】
	 * @param key
	 * @return
	 * 2017年4月14日 下午5:54:53
	 */
	public static String getCacheValue(String key)
	{
		Cache cache = getCache(PropertyUtil.getRedisName());
		try {
			if (cache != null)
				return cache.get(key);
		} catch (Exception e) {
			log.error("【redis获取值失败】异常信息：" + e.getMessage() + "【key】" + key);
		}
		return null;
	}
	
	/**
	 * 通过【key】获得【MAP】
	 * 【默认redis对象】
	 * @param key
	 * @return
	 * 2017年6月13日 下午4:18:03
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> hgetAllCacheValue(String key)
	{
		Cache cache = getCache(PropertyUtil.getRedisName());
		try {
			if (cache != null)
				return cache.hgetAll(key);
		} catch (Exception e) {
			log.error("【redis获取(MAP)值失败】异常信息：" + e.getMessage() + "【key】" + key);
		}
		return null;
	}
	
	/**
	 * 设置MAP缓存
	 * 【设置超时秒数】
	 * 【默认redis对象】
	 * @param key
	 * @param map
	 * @param timeOutSecond
	 * @return
	 * 2017年6月13日 下午5:43:33
	 */
	public static Map<String, String> hsetAllCacheValue(String key, Map<Object, Object> map, 
			int timeOutSecond)
	{
		Cache cache = getCache(PropertyUtil.getRedisName());
		try {
			if (cache != null)
			{
				cache.hmset(key, map);
				cache.expire(key, timeOutSecond);
			}
		} catch (Exception e) {
			log.error("【redis设置(MAP)值失败】异常信息：" + e.getMessage() + "【key】" + key);
		}
		return null;
	}
	
	/**
	 * 设置缓存值
	 * 【两String】
	 * 【默认redis对象】
	 * 【设置成功不重要】
	 * @param key
	 * @param value
	 * @return
	 * 2017年4月14日 下午5:54:53
	 */
	public static void setCacheValue(String key, String value)
	{
		Cache cache = getCache(PropertyUtil.getRedisName());
		try {
			if (cache != null)
				cache.set(key, value);
		} catch (Exception e) {
			log.error("【redis设置值失败】错误信息：" + e.getMessage() + "【key】" + key);
		}
	}
	
	/**
	 * 设置缓存值
	 * 【默认redis对象】
	 * @param key
	 * @param value 
	 * @param seconds 【过期秒数】
	 * @return
	 * 2017年4月14日 下午5:54:53
	 */
	public static void setCacheValue(String key, String value, int seconds)
	{
		Cache cache = getCache(PropertyUtil.getRedisName());
		try {
			if (cache != null)
			{
				cache.set(key, value);
				cache.expire(key, seconds);
			}
		} catch (Exception e) {
			log.error("【redis设置值失败】错误信息：" + e.getMessage() + "【key】" + key);
		}
	}
	
}
