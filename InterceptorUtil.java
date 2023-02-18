/**
 * 
 */
package com.jxsq.common.util;

import org.apache.log4j.Logger;

/**
 * @author Jx
 * 过滤
 * 2017年4月18日 上午11:33:35
 */
public class InterceptorUtil {
	
	private static final Logger log = Logger.getLogger(InterceptorUtil.class);
	
	/**
	 * 来源主机验证
	 * @param sourceUrl
	 * @return
	 * 2017年4月18日 上午11:58:37
	 */
	public static boolean sourceHostValidate(String sourceUrl)
	{
		if (ValidateUtil.validateStrNullValue(sourceUrl)) return false;
		String host = PropertyUtil.getInterceptorHost();
		String[] urlArray = host.split("\\|\\|");
		for (int i = 0; i < urlArray.length; i++) {
			if (RegUtil.regValidate(urlArray[i], sourceUrl))
				return true;
		}
		log.error("【来源验证】异常的来源地址：" + sourceUrl);
		return false;
	}
	
	
}
