/**
 * 
 */
package com.jxsq.common.util;

import org.apache.log4j.Logger;

import com.jxsq.common.po.common.AppEncrypt;

/**
 * @author JX
 * 帖子工具
 */
public class PostUtil {
	private static final Logger log = Logger.getLogger(PostUtil.class);
	/**
	 * 压缩帖子的长度
	 */
	private static final int SHRINK_LENGTH = 80;
	
	/**
	 * 压缩【帖子内容】到【固定长度】
	 * @param content
	 * @return
	 */
	public static String shrinkPostContent(String content)
	{
		if (content.length() <= SHRINK_LENGTH)
			return content;
		else
			return content.substring(0, SHRINK_LENGTH) + "......";
	}
	
	/**
	 * 获得帖子明细的http地址
	 * @param tid
	 * @return
	 */
	public static String getPostDetailHttpUrl(String tid, boolean httpFlag)
	{
		StringBuilder stringBuilder = new StringBuilder(PropertyUtil.getServerLocalUrl(httpFlag));
		stringBuilder.append("post/postDetail/");
		stringBuilder.append(tid);
		stringBuilder.append("?timestamp=").append(DateUtil.getTodayStrUseFormat(DateUtil.TOTAL_DATE_TIME_SIMPLE));
		return  stringBuilder.toString();
	}
	
	/**
	 * 获得帖子明细的http地址
	 * @param tid
	 * @return
	 */
	public static String getPostDetailNotifyHttpUrl(String tid, boolean httpFlag,
			String nid)
	{
		StringBuilder stringBuilder = new StringBuilder(PropertyUtil.getServerLocalUrl(httpFlag));
		stringBuilder.append("post/postDetail/");
		stringBuilder.append(tid);
		stringBuilder.append("?timestamp=").append(DateUtil.getTodayStrUseFormat(DateUtil.TOTAL_DATE_TIME_SIMPLE));
		stringBuilder.append("&nid=").append(nid).append("&newNotify=0");
		return  stringBuilder.toString();
	}
	
	/**
	 * 获得帖子【分享】明细的地址
	 * @param tid
	 * @return
	 */
	public static String getPostShareDetailUrl(String tid)
	{
		StringBuilder stringBuilder = new StringBuilder(PropertyUtil.getShareHttpsUrl());
		stringBuilder.append("post/postShare/");
		stringBuilder.append(tid);
		stringBuilder.append("?timestamp=").append(DateUtil.getTodayStrUseFormat(DateUtil.TOTAL_DATE_TIME_SIMPLE));
		return  stringBuilder.toString();
	}

	/**
	 * 获得帖子明细的地址
	 * @param content
	 * @param forumName
	 * @param tid
	 * @param httpFlag
	 * @return
	 * 2016年11月22日 下午7:15:13
	 */
	public static String getPostDetailHttpUrl(String content, String forumName, String tid,
			boolean httpFlag)
	{
		if (forumName.equals("AD"))
			return content;
		else if (forumName.equals("QUESTION"))
		{
			return QuestionUtil.getQuestionDetailUrlNoUid(tid);
		}
		else
			return getPostDetailHttpUrl(tid, httpFlag);
	}
	
	/**
	 * 获得帖子明细的http地址
	 * @param tid
	 * @param httpFlag
	 * @param appId
	 * @return
	 */
	public static String getPostDetailUrlByEncrypt(String tid, boolean httpFlag, AppEncrypt appEncrypt)
	{
		StringBuilder stringBuilder = new StringBuilder(PropertyUtil.getServerLocalUrl(httpFlag));
		stringBuilder.append("postOut/postOutDetail");
		//AppEncrypt appEncrypt = CommonFactory.getAppEncryptService().findByAppId(appId);

		stringBuilder.append("?tid=").append(tid);
		stringBuilder.append("&appId=").append(appEncrypt.getAppId());
		//stringBuilder.append("?time=").append(DateUtil.getNowSecond());
		return  stringBuilder.toString();
	}
	
}
