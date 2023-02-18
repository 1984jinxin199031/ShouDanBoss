/**
 * 
 */
package com.jxsq.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jxsq.common.constant.HomeNotificationConstant;
import com.jxsq.common.po.member.HomeNotification;
import com.jxsq.common.po.member.Member;
import com.jxsq.common.service.factory.CommonFactory;

/**
 * @author JX 
 * 替换【通知】工具
 */
public class ReplaceNotificationUtil {
	
	/**
	 * 替换【通知信息】
	 * @param homeNotification
	 * @return
	 */
	public static String replaceMsg(HomeNotification homeNotification)
	{
		String content = homeNotification.getNote();
		String type = homeNotification.getType();
		if (type.equals(HomeNotificationConstant.POST) || type.equals(HomeNotificationConstant.GOODS))
			return replaceGoodsAndPostMsg(content);
		else if (type.equals(HomeNotificationConstant.SYSTEM))//【还有五种系统提示信息】
			return replaceRegisterWelcomeMsg(homeNotification);
		else if (type.equals(HomeNotificationConstant.QUESTION) || 
			type.equals(HomeNotificationConstant.REWARD) || 
			type.equals(HomeNotificationConstant.REPLY))
			return content;
		else 
			return "";
	}
	
	/**
	 * 替换【注册的欢迎信息】
	 * @param homeNotification
	 * @return
	 * 2016年12月29日 上午10:42:19
	 */
	public static String replaceRegisterWelcomeMsg(HomeNotification homeNotification)
	{
		String fromIdType = homeNotification.getFromIdtype();
		if (!ValidateUtil.validateStrNullValue(fromIdType) && 
				fromIdType.equals(HomeNotificationConstant.WELCOME_MSG))
		{
			Member member = CommonFactory.getMemberService().findByUid(homeNotification.getUid().toString());
			StringBuilder stringBuilder = new StringBuilder("尊敬的：");
			stringBuilder.append(member.getUsername());
			stringBuilder.append("，您已经注册成为聚秀的会员，请您在发表言论时，遵守当地法律法规。\n如果您有什么疑问可以联系管理员，Email:547961634@qq.com。");
			return stringBuilder.toString();
		}
		return "";
	}
	
	/**
	 * 替换【电商】和【帖子】信息
	 * @param content
	 * @return
	 */
	public static String replaceGoodsAndPostMsg(String content)
	{
		String regStr = "<a href";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String replaceStr = "";
		while (matcher.find()) 
		{
			replaceStr = content.substring(content.indexOf("<a href=\""), content.indexOf(">") + 1);
			content = content.replace(replaceStr, "");
			content = content.replace("</a>", "");
		}
		content = content.replace("&nbsp;", " ");
		content = content.replace("&rsaquo;", "").trim();
		content = content.substring(0, content.length() - 2);
		return content;
	}
	
	/**
	 * 替换【电商】信息
	 * @param content
	 * @return
	 */
	public static String getTidFromContent(HomeNotification homeNotification)
	{
		//note,  d, type, new, author, from_idtype
		String content = homeNotification.getNote();
		String fromIdType = homeNotification.getFromIdtype();
		if (!ValidateUtil.validateStrNull(fromIdType))
		{
			if (fromIdType.equals(HomeNotificationConstant.THREAD_REPLY))
			{
				return homeNotification.getFromId().toString();
			}
			else if (fromIdType.equals(HomeNotificationConstant.REPLY_REPLY))
			{
				return getTidFromUrl(content);
			}
			else 
				return "";
		}
		else 
			return "";
	}

	public static String getTidFromUrl(String msg)
	{
		if (msg.indexOf("ptid=") >= 0)
		{
			return msg.substring(msg.indexOf("ptid=") + 5, msg.indexOf("&pid"));
		}
		return "";
	}
	
	public static void main(String[] args) {
		String content = "<a href=\"home.php?mod=space&uid=6\">一起说悄悄话</a> 回复了您的帖子 <a href=\"forum.php?mod=redirect&goto=findpost&ptid=19&pid=41\" target=\"_blank\">哈，股权众筹，分享几点吧</a> &nbsp; <a href=\"forum.php?mod=redirect&goto=findpost&pid=41&ptid=19\" target=\"_blank\" class=\"lit\">查看</a>";
//		System.out.println(getTidFromUrl(content));
		System.out.println(replaceGoodsAndPostMsg(content));
	}
	
	
}