/**
 * 
 */
package com.jxsq.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jfplugin.mail.MailKit;

/**
 * @author JX
 * 邮箱工具
 * 2016年11月29日 下午5:52:03
 */
public class MailUtil {
	private static final Logger log = Logger.getLogger(MailUtil.class);
	
	/**
	 * 发送邮件
	 * @param subject
	 * @param content
	 * 2016年11月29日 下午5:53:10
	 */
	public static void sendExceptionMail(String subject, String content)
	{
		try {
			MailKit.send(PropertyUtil.getToMail(), transferCopyTo(), subject, content);
		} catch (Exception e) {
			log.error("【出异常时发送邮件】出现异常【标题】" + subject + "【内容】" + content, e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 转换【抄送】
	 * @return
	 * 2016年11月30日 下午3:58:21
	 */
	private static List<String> transferCopyTo()
	{
		String copyTo = PropertyUtil.getMailCopyTo();
		if (!ValidateUtil.validateStrNullValue(copyTo))
		{
			String[] emailArray = copyTo.split("\\|");
			List<String> emailList = new ArrayList<String>();
			for (int i = 0; i < emailArray.length; i++) {
				emailList.add(emailArray[i]);
			}
			return emailList;
		}
		return null;
	}
	
	public static void main(String[] args) {
		String[] emailArray = "2052164630@qq.com|".split("\\|");
		System.out.println(emailArray.length);
		System.out.println(emailArray[0]);
//		System.out.println(emailArray[1]);
	}
	
}
