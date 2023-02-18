/**
 * 
 */
package com.jxsq.common.util;

import java.math.BigDecimal;

/**
 * @author JX
 * BigDecimal工具
 */
public class BigDecimalUtil {
	/**
	 * 减【一字符串一BigDecimal】
	 * 返回【字符串】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static String subtract(String valueOne, BigDecimal valueTwo)
	{
		return new BigDecimal(valueOne).subtract(valueTwo).toString();
	}
	
	/**
	 * 减【两BigDecimal】
	 * 返回【long】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static Long subtract(BigDecimal valueOne, BigDecimal valueTwo)
	{
		return valueOne.subtract(valueTwo).longValue();
	}
	
	/**
	 * 减【一个Decimal一个字符串】
	 * 返回【long】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static Long subtractReturnLong(BigDecimal valueOne, String valueTwo)
	{
		return valueOne.subtract(new BigDecimal(valueTwo)).longValue();
	}
	
	/**
	 * 减【一个Decimal一个字符串】
	 * 返回【double】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static Double subtractReturnDouble(BigDecimal valueOne, String valueTwo)
	{
		return valueOne.subtract(new BigDecimal(valueTwo)).doubleValue();
	}
	
	/**
	 * 减【两字符串】
	 * 返回【字符串】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static String subtract(String valueOne, String valueTwo)
	{
		return new BigDecimal(valueOne).subtract(new BigDecimal(valueTwo)).toString();
	}
	
	/**
	 * 减【一个decimal一个字符串】
	 * 返回【字符串】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static String subtract(BigDecimal valueOne, String valueTwo)
	{
		return valueOne.subtract(new BigDecimal(valueTwo)).toString();
	}
	
	/**
	 * 减【一个字符串一个decimal】
	 * 返回【decimal】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static BigDecimal subtractReturnDecimal(String valueOne, BigDecimal valueTwo)
	{
		return new BigDecimal(valueOne).subtract(valueTwo);
	}
	
	/**
	 * 乘【一个decimal一个字符串】
	 * 返回【字符串】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static String multiply(BigDecimal valueOne, String valueTwo)
	{
		return valueOne.multiply(new BigDecimal(valueTwo)).toString();
	}
	
	/**
	 * 乘【两个字符串】
	 * 返回【decimal】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static BigDecimal multiplyReturnDecimal(String valueOne, String valueTwo)
	{
		return new BigDecimal(valueOne).multiply(new BigDecimal(valueTwo));
	}
	
	/**
	 * 乘【一个decimal一个字符串】
	 * 返回【decimal】对象
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static BigDecimal multiplyReturnDecimal(BigDecimal valueOne, String valueTwo)
	{
		return valueOne.multiply(new BigDecimal(valueTwo));
	}
	
	/**
	 * 加【两字符串】
	 * 返回【字符串】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static String add(String valueOne, String valueTwo)
	{
		return new BigDecimal(valueOne).add(new BigDecimal(valueTwo)).toString();
	}
	
	/**
	 * 加【两decimal】
	 * 返回【字符串】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static String add(BigDecimal valueOne, BigDecimal valueTwo)
	{
		return valueOne.add(valueTwo).toString();
	}
	
	/**
	 * 加【一个decimal一个字符串】
	 * 返回【字符串】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static String add(BigDecimal valueOne, String valueTwo)
	{
		return valueOne.add(new BigDecimal(valueTwo)).toString();
	}
	
	/**
	 * 加【一个decimal一个字符串】
	 * 返回【decimal】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static BigDecimal addReturnDecimal(BigDecimal valueOne, String valueTwo)
	{
		return valueOne.add(new BigDecimal(valueTwo));
	}
	
	/**
	 * 除【两个字符串】
	 * 返回【字符串】
	 * 设置保留位数
	 * @param valueOne
	 * @param valueTwo
	 * @param stayNumber
	 * @return
	 * 2017年1月3日 下午6:02:07
	 */
	public static String divide(String valueOne, String valueTwo,
			int stayNumber)
	{
		return new BigDecimal(valueOne).divide(new BigDecimal(valueTwo))
			.setScale(stayNumber, BigDecimal.ROUND_HALF_UP).toString(); 
	}
	
	/**
	 * 获得分的价钱
	 * 【必须有.】
	 * @param amount
	 * @return
	 * 2016年11月18日 上午10:31:11
	 */
	public static String getPointAmount(BigDecimal amount)
	{
		String amountStr = amount.toString();
		if (amountStr.indexOf('.') > 0)
		{
			amountStr = amount.toString().replace(".", "");
			return Integer.parseInt(amountStr) + "";
		}
		return amountStr;
	}
	
	/**
	 * 获得分的价钱
	 * 【必须有.】
	 * @param amount
	 * @return
	 * 2016年11月18日 上午10:31:11
	 */
	public static String getPointAmount(String amountStr)
	{
		if (amountStr.indexOf('.') > 0)
		{
			amountStr = amountStr.replace(".", "");
			return Integer.parseInt(amountStr) + "";
		}
		return amountStr;
	}
	
	/**
	 * 比较【两字符串】
	 * 返回【int】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static int compare(String valueOne, String valueTwo)
	{
		return new BigDecimal(valueOne).compareTo(new BigDecimal(valueTwo));
	}
	
	/**
	 * 比较【一decimaly一字符串】
	 * 返回【int】
	 * @param valueOne
	 * @param valueTwo
	 * @return
	 */
	public static int compare(BigDecimal valueOne, String valueTwo)
	{
		return valueOne.compareTo(new BigDecimal(valueTwo));
	}
	
	/**
	 * 截取金额
	 * 【四舍五入】
	 * @param money
	 * @param cutLength 【保留的长度】
	 * @return
	 * 2017年5月2日 下午3:47:08
	 */
	public static String cutMoney(BigDecimal money, int cutLength)
	{
		return money.setScale(cutLength, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public static void main(String[] args) {
//		System.out.println(getPointAmount(new BigDecimal("24.00")));
//		System.out.println(getPointAmount(new BigDecimal("24.25")));
//		System.out.println(getPointAmount(new BigDecimal("24.00")));
//		System.out.println(getPointAmount(new BigDecimal("24.00")));
//		System.out.println(getPointAmount(new BigDecimal("24.02")));
//		System.out.println(getPointAmount(new BigDecimal("0.02")));
//		System.out.println(getPointAmount(new BigDecimal("1535.00")));
//		System.out.println(divide("153555", "100", 2));
//		System.out.println(add("-1", "5"));
//		System.out.println(add("10", "0.12"));
		System.out.println(cutMoney(new BigDecimal(0.1250), 2));
	}
	
}
