/**
 * 
 */
package com.jxsq.common.util;

/**
 * @author Jx
 * 问题工具
 * 2017年4月27日 下午3:07:44
 */
public class QuestionUtil {
	/**
	 * 问答明细地址
	 * @param questionId
	 * @param mapUid
	 * @return
	 * 2017年4月27日 下午3:13:17
	 */
	public static String getQuestionDetailUrl(String questionId, String mapUid)
	{
		StringBuilder stringBuilder = new StringBuilder(PropertyUtil.getServerLocalUrl(true));
		stringBuilder.append("question/detail/");
		stringBuilder.append(questionId).append("?timestamp=").append(DateUtil.getTodayStrUseFormat(DateUtil.TOTAL_DATE_TIME_SIMPLE));
		if (!ValidateUtil.validateStrNullValue(mapUid))
			stringBuilder.append("&uid=").append(mapUid);
		return  stringBuilder.toString();
	}

	/**
	 * 问答明细地址【更新系统通知】
	 * @param questionId
	 * @param nid
	 * @return
	 * 2017年5月12日 上午11:59:09
	 */
	public static String getQuestionDetailUrlUpdateNotify(String questionId, String nid)
	{
		StringBuilder stringBuilder = new StringBuilder(PropertyUtil.getServerLocalUrl(true));
		stringBuilder.append("question/detail/");
		stringBuilder.append(questionId).append("?timestamp=").append(DateUtil.getTodayStrUseFormat(DateUtil.TOTAL_DATE_TIME_SIMPLE));
		stringBuilder.append("&nid=").append(nid).append("&newNotify=0");
		return  stringBuilder.toString();
	}
	
	/**
	 * 问答明细地址【没有uid】
	 * @param questionId
	 * @return
	 * 2017年5月11日 下午5:31:17
	 */
	public static String getQuestionDetailUrlNoUid(String questionId)
	{
		StringBuilder stringBuilder = new StringBuilder(PropertyUtil.getServerLocalUrl(true));
		stringBuilder.append("question/detail/").append(questionId);
		stringBuilder.append("?timestamp=").append(DateUtil.getTodayStrUseFormat(DateUtil.TOTAL_DATE_TIME_SIMPLE));
		return  stringBuilder.toString();
	}
	
	/**
	 * 【中划线分隔】
	 * 从订单的【扩展字段1】获得【答案打赏】的参数数组
	 * 0：questionId
	 * 1：answerId
	 * @param reserved1
	 * @return
	 * 2017年5月26日 上午11:34:18
	 */
	public static String[] getAnswerRewardMsgByReserved1(String reserved1)
	{
		return reserved1.split("\\|");
	}
	
	
	
}
