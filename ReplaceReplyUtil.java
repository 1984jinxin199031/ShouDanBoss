/**
 * 
 */
package com.jxsq.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author JX
 * 替换回复工具
 */
public class ReplaceReplyUtil {
	/**
	 * 替换【所有开启标签】
	 * @param content
	 * @return
	 */
	private static String replaceStartTag(String content)
	{
		String regStr = "\\[[a-zA-Z]{1}";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) 
		{	
			content = content.substring(content.indexOf("]") + 1);
		}
		return content;
	}
	
	/**
	 * 替换【所有结束标签】
	 * @param content
	 * @return
	 */
	public static String replaceEndTag(String content)
	{
		String regStr = "\\[/[a-zA-Z]{1,}\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) 
		{	
			content = content.replaceFirst(regStr, "");
		}
		return content;
	}
	
	/**
	 * 替换【所有标签】
	 * @param content
	 * @return
	 */
	private static String replaceAllTag(String content)
	{
		content = replaceStartTag(content);
		return replaceEndTag(content);
	}
	
	/**
	 * 获得中间的内容
	 * 【格式】
	 * 引用回帖内容|回帖内容
	 * @param content
	 * @return
	 */
	private static String getMiddleContent(String content)
	{
		String[] contentArray = content.split("\\[/quote\\]");
		
		if (contentArray != null && contentArray.length == 2)
		{
			String contentMiddle = contentArray[0];
			contentMiddle = replaceAllTag(contentMiddle);
			contentArray[0] = contentMiddle;
			return contentMiddle + "|" + contentArray[1];
		}
		return null;//【日志】
	}
	
	/**
	 * 获得中间的内容
	 * 【格式】
	 * 引用回帖内容|回帖内容
	 * @param content
	 * @return
	 */
	private static String getReplyMsg(String content)
	{
		String[] contentArray = content.split("\\[/quote\\]");
		if (contentArray != null && contentArray.length == 2)
		{
			String contentMiddle = contentArray[0];
			contentMiddle = replaceAllTag(contentMiddle);
			return contentMiddle;
		}
		return null;//【日志】
	}
	
	/**
	 * 替换所有表情
	 * @param content
	 * @return
	 */
	public static String replaceFace(String content, String regStr)
	{
		
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) 
		{	
			content = content.replaceAll(regStr, "");
		}
		return content;
	}
	
	/**
	 * 复杂正则解释帖子
	 * @param content
	 */
	public static String replaceProcess(String content) {
		
		if (content.indexOf("[") == -1)
			return content;
		else if (content.indexOf("[quo") >= 0)
		{
			String authorContent = content.substring(0, content.indexOf("[/"));
			authorContent = authorContent.substring(authorContent.lastIndexOf(']') + 1);
			content = replaceStartTag(content);
			return authorContent + "|" + getMiddleContent(content);
		}
		else if (content.indexOf("[/i") >= 0)
			return content.substring(content.lastIndexOf("]") + 1);
		else
			return replaceAllTag(content);
	}

	/**
	 * 【我的回复】正则替换
	 * @param content
	 */
	public static String mineRepliesListReplaceProcess(String content) {
		content = content.replaceAll("\r\n", "");
		content = content.replaceAll("\n\n", "");
		content = content.replaceAll("\n", "");
		
		if (content.indexOf("[") == -1)
			return content;
		else if (content.indexOf("[quo") >= 0)
		{
			content = content.substring(content.indexOf("[/"));
			content = replaceStartTag(content);
			return getReplyMsg(content);
		}
		else if (content.indexOf("[/i") >= 0)
			return content.substring(content.lastIndexOf("]") + 1);
		else
			return replaceAllTag(content);
	}
	
	public static void main(String[] args) {
		// 复杂帖子格式
//		System.out.println(
//				replaceProcess("[quote][color=#999999]jack 发表于 2016-3-5 17:17[/color][color=#999999]买买买[/color][/quote]看看14年的股价，那时的经济可要比现在好得多。"
//						));
		
//		String content2 = "[quote][size=2][url=forum.php?mod=redirect&goto=findpost&pid=551&ptid=134][color=#999999]A兴创茗茶陈创伟 发表于 2016-3-11 10:11[/color][/url][/size]再讨论下去都筹满爆仓了。认筹已经百分之百了。[/quote]:lol那么你投了吗。";
//		System.out.println(replaceAllTag("[quote][color=#999999]jack 发表于 2016-3-5 17:17[/color][color=#999999]买买买[/color][/quote]看看14年的股价，那时的经济可要比现在好得多。"));
	}
}
