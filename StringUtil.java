 /**
 * 
 */
package com.jxsq.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author JX
 * 字符串相关工具
 */
public class StringUtil {
	/**
	 * 生成流水号（唯一）
	 * 【29位】
	 * @return
	 */
	public static String getSerilNum() {
		String serlNum = "";
		int random = (int) (Math.random() * 9) + 10;
		serlNum = String.valueOf(Math.abs(UUID.randomUUID().toString()
				.hashCode()));
		String str = "0000000000";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsssss");
		String nowStr = sdf.format(new Date());
		if (serlNum.length() > 10) {
			serlNum = serlNum.substring(0, 10);
		} else if (serlNum.length() < 10) {
			serlNum = serlNum + str.substring(serlNum.length());
		}
		serlNum = nowStr + random + serlNum;
		return serlNum;
	}
	
	/**
	 * 生成流水号（唯一）
	 * 【年月日时分秒】+【uuid(位数不够，随机数字字母补位)】
	 * 【至少大于十四位】
	 * @return
	 */
	public static String getSerilNumByLength(int length) {
		if (length <= 14) return null;
		String serlNum = "";
		serlNum = String.valueOf(Math.abs(UUID.randomUUID().toString().hashCode()));
		//【14位】
		String nowStr = DateUtil.getTodayStrUseFormat(DateUtil.TOTAL_DATE_TIME_SIMPLE);
		String strTemp = nowStr + serlNum;
		int strTempLength = strTemp.length();
		if (strTempLength >= length) 
			serlNum = strTemp.substring(0, length);
		else if (strTempLength < length) 
			serlNum = strTemp + getStringRandom(length - strTempLength); 
		return serlNum;
	}
	
	/**
	 * 生成随机数字和字母
	 * @param length
	 * @return
	 * 2016年11月2日 下午4:13:39
	 */
	public static String getStringRandom(int length) {
		String val = "";
		Random random = new Random();
		
		for(int i = 0; i < length; i++) 
		{
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if("char".equalsIgnoreCase(charOrNum) )
			{
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char)(random.nextInt(26) + temp);
			}
			else if( "num".equalsIgnoreCase(charOrNum) ) 
			{
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
	
	/**
	 * 字符串前补零直到最大长度
	 * @param timeStr
	 * @return
	 */
	public static String addZeroToStr(String str, int totalLength)
	{
		StringBuilder stringBuilder = new StringBuilder();
		int recycleCount = totalLength - str.length();
		for (int i = 0; i < recycleCount; i++) {
			stringBuilder.append(0);
		}
		return stringBuilder.toString() + str;
	}
	
	/**
	 * 获得【指定长度的】随机数字符串
	 * @param length
	 * @return
	 */
	public static String getRandomStrByLength(int length)
	{
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			stringBuilder.append(random.nextInt(10));
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 裁剪内容
	 * @param content
	 * @param cutLength
	 * @return
	 * 2017年3月2日 下午3:17:05
	 */
	public static String cutContent(String content, int cutLength)
	{
		if (cutLength >= content.length()) 
			return content;
		else
			return content.substring(0, cutLength) + "...";
	}
	
	/**
	 * 获得参数map
	 * @param queryStr
	 * @return
	 * 2017年5月16日 上午11:14:04
	 */
	public static Map<String, String> getQueryStrMap(String queryStr)
	{
		String[] paramArrayInside = null;
		Map<String, String> paramMap = new HashMap<String, String>();
		if (queryStr.indexOf("&") >= 0)
		{
			String[] paramArray = queryStr.split("&");
			String param = "";
			for (int i = 0; i < paramArray.length; i++) {
				param = paramArray[i];
				if (param.indexOf("=") >= 0)
				{
					paramArrayInside = param.split("=");
					if (paramArrayInside != null && paramArrayInside.length == 2)
						paramMap.put(paramArrayInside[0], paramArrayInside[1]);
				}
			}
		}
		else
		{
			if (queryStr.indexOf("=") >= 0)
			{
				String[] paramArray= queryStr.split("=");
				if (paramArray != null && paramArray.length == 2)
					paramMap.put(paramArray[0], paramArray[1]);
			}
		}
		return paramMap;
	}
	
	public static void main(String[] args) {
//		System.out.println(addZeroToStr("28936", 9));
		
//		System.out.println(StringUtil.getStringRandom(4));
		
//		System.out.println(getRandomStrByLength(6));
//		System.out.println("334bac4b8de7fba9c1bd3feb98c9ac".length());
//		System.out.println(getStringRandom(10));
//		System.out.println(getSerilNum().length());
//		System.out.println(getSerilNum());
//		String c = getSerilNumByLength(35);
//		System.out.println(c);
//		System.out.println(c.length());
//		System.out.println(getStringRandom(20));
//		System.out.println(getRandomStrByLength(32).length());
//		System.out.println(cutContent("今日市场再度大跌，短期均线已经对市场构成明显压制，继续控制好仓位，切忌盲目抄底，等待市场缩量企稳。", 20));
//		System.out.println(StringUtil.getStringRandom(29));
		System.out.println(getSerilNumByLength(25));
//		System.out.println(cutContent("问题这sss是问题", 30));
	}
}
