/**
 * 
 */
package com.jxsq.common.util;

/**
 * @author JX
 * 分页工具
 */
public class PageUtil {
	/**
	 * 获得【MYSQL】分页开始序号
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public static int getPageIndexFromMysql(int pageNo, int pageNum)
	{
		return (pageNo - 1) * pageNum;
	}
}
