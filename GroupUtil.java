/**
 * 
 */
package com.jxsq.common.util;

/**
 * @author JX
 * 用户组工具
 */
public class GroupUtil {
	/**
	 * 拆分【扩展用户组数组】
	 * 空格
	 * @param extGroupStr
	 * @return
	 * 2016年8月26日 下午2:40:06
	 */
	public static String[] splitExtGroup(String extGroupStr)
	{
		extGroupStr = extGroupStr.trim();
		return extGroupStr.split("\t");
	}
	
}
