/**
 * 
 */
package com.jxsq.common.util;

import java.util.HashMap;

/**
 * @author JX
 * 【验证码】和【校验码】会话
 */
public class CheckCodeSession {
	/**
	 * 验证码MAP
	 */
	private static HashMap<String, String> codeMap = new HashMap<String, String>();
	
	public static void put(String key, String value)
	{
		codeMap.put(key, value);
	}
	
	public static String get(String key)
	{
		return codeMap.get(key);
	}
	
	public static String remove(String key)
	{
		return codeMap.remove(key);
	}
}
