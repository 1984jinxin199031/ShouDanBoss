/**
 * 
 */
package com.jxsq.common.util;

import java.util.List;

/**
 * @author jinxin
 * 验证工具
 */
public class ValidateUtil {
	/**
	 * 验证字符串是否为空
	 * @param str
	 * @return 【true】表示为空
	 */
	public static boolean validateStrNull(String str)
	{
		if (str != null && str.length() != 0)
			return false;
		return true;
	}
	
	/**
	 * 验证字符串是否为空
	 * 1、null
	 * 2、空串
	 * 3、null字符串
	 * @param str
	 * @return 【true】表示为空
	 */
	public static boolean validateStrNullValue(String str)
	{
		if (str != null && str.length() != 0 && !str.equalsIgnoreCase("null"))
			return false;
		return true;
	}
	
	/**
	 * 验证字符串对象【假设】是否为空
	 * @param str
	 * @return
	 */
	public static boolean validateStrObjectNull(Object strObject)
	{
		if (strObject == null)
			return true;
		else 
		{
			String str = (String)strObject;
			if (str.equals(""))
				return true;
			else 
				return false;
		}
	}
	
	/**
	 * 验证集合是否为空
	 * 1、null
	 * 2、size为0的集合
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean validateListNull(List list)
	{
		if (list != null && list.size() != 0)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 若【字符串】为【null】返回0
	 * 否则返回原来的字符串
	 * @param str
	 * @return
	 */
	public static String getValidateStr(String str)
	{
		if (str == null) return "0";
		else return str; 
	}
	
	/**
	 * 若【字符串】为【null字符串】返回0
	 * 否则返回原来的字符串
	 * @param str
	 * @return
	 */
	public static String getStrongerStrReturn0(String str)
	{
		if (str != null && str.equalsIgnoreCase("null")) return "0";
		else return str; 
	}
	
	/**
	 * 若【字符串】为【null字符串】返回空
	 * 否则返回原来的字符串
	 * @param str
	 * @return
	 */
	public static String getStrongerStrReturnBlank(String str)
	{
		if (str != null && str.equalsIgnoreCase("null")) return "";
		else return str; 
	}
	
	public static void main(String[] args) {
//		System.out.println(validateStrNull("."));
		System.out.println(validateStrNullValue("."));
	}
	
}
