/**
 * 
 */
package com.jxsq.common.util;

/**
 * @author JX
 * 附件工具
 */
public class AttachUtil {
	/**
	 * 获得附件KEY【加密验证用】
	 * @param aid
	 * @param time
	 * @param uid
	 * @return
	 * 2016年8月29日 下午8:04:15
	 */
	public static String getAttachKey(String aid, String time, String uid)
	{
		return (MD5Util.md5(aid + MD5Util.md5(PropertyUtil.getAttachKey()) + time 
				+ uid)).substring(0,8);
	}
}
