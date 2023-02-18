/**
 * 
 */
package com.jxsq.common.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jx
 * 正则工具
 */
public class RegUtil {
	
	/**
	 * 利用正则查找内容
	 * @param regStr
	 * @param content
	 * @return
	 */
	public static String search(String regStr, String content)
	{
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String matchContent = "";
		if (matcher.find()) {
			matchContent = matcher.group();
		}
		return matchContent;
	}
	
	/**
	 * 循环正则替换
	 * @param replaceRuleMap
	 * 0 -> 正则规则字符串
	 * 1 -> 要替换成的字符串
	 * @param content
	 * @return
	 */
	public static String replaceRecycleByList(List<String[]> replaceRuleList, String content)
	{
		String[] replaceRuleArray = null;
		String message = content;
		for (int i = 0; i < replaceRuleList.size(); i++) 
		{
			replaceRuleArray = replaceRuleList.get(i);
			message = message.replaceAll(replaceRuleArray[0], replaceRuleArray[1]);
		}
		return message;
	}
	
	public static String replaceSizePx(String message) {
		String messageTotal = "\\" + RegUtil.search("\\[size=\\d{1,5}px", message) + "\\]";
//		System.out.println(messageTotal);
		String pxValue = RegUtil.search("\\d{1,5}", messageTotal);
//		System.out.println(pxValue);
		return message.replaceAll(messageTotal, "<font style=\"font-size:" + pxValue + "px\">");
	}
	
	public static String replaceRecycleImg(String regStr, String content)
	{
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String imgReplacedPrefix = "";
		while (matcher.find()) {
			imgReplacedPrefix = "223" + StringUtil.getRandomStrByLength(5) + 
			"422";
			content = content.replaceFirst(regStr, imgReplacedPrefix);
			System.out.println(content);
		}
		return content;
	}
	
	/**
	 * 正则验证格式
	 * true -> 验证通过
	 * @param regStr
	 * @param content
	 * @return
	 */
	public static boolean regValidate(String regStr, String content)
	{
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		if (matcher.find())
			return true;
		else 
			return false;
	}
	
	public static void main(String[] args) {
//		String message = search("\\[size=\\d{1,5}px\\]", "[align=left][size=14px]  [/size][size=3]");
//		System.out.println(message);
//		message = search("\\d{1,5}", message);
//		System.out.println(message);
		
//		System.out.println(replaceSizePx("[align=left][size=14px]"));
		
//		System.out.println(replaceRecycleImg("\\[img\\]", "[img]static/image/hrline/2.gif[/img]"));
//		System.out.println(RegUtil.regValidate("^\\d{1,}$", "-1"));	}
		//正数，最多保留两位小数
//		System.out.println(RegUtil.regValidate("^\\d+(\\.\\d{1,2})?$", "288.00"));
//		System.out.println(RegUtil.regValidate("^\\d+$", "222"));
//		System.out.println(RegUtil.regValidate("^\\+[0-9]{1,20}$", "+77555555555555555529"));
		
//		System.out.println(RegUtil.regValidate("*.juxiuclub.com", "_21#!@.juxiuclub.com"));
		System.out.println(RegUtil.regValidate("^*.juxiuclub.com/*", "https://apptest.juxiuclub.com"));
	}

	
}
