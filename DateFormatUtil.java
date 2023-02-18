/**
 * 
 */
package com.jxsq.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 正常日期格式【格式化】
 * 超过三天返回正常日期
 * @author JX
 *
 */
public class DateFormatUtil {
	private static final Logger log = Logger.getLogger(DateFormatUtil.class);
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String ALL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss SSS";
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";

    public static void main(String[] args) throws ParseException {
       System.out.println(dateFormat("2017-03-12 11:09:49"));
    }

    /**
     * 日期格式化
     * @param dateStr
     * @param formatStr
     * @return
     * 2017年4月27日 上午11:40:55
     */
    public static String dateFormatByFormat(String dateStr, String formatStr) {
        Date date = null;
		try {
			date = new SimpleDateFormat(formatStr).parse(dateStr);
			return format(date, dateStr);
		} catch (ParseException e) {
			log.error("格式化日期串失败 【日期串】" + dateStr, e);
			e.printStackTrace();
			return dateStr;
		}
    }
    
    /**
     * 日期格式化
     * @param dateStr
     * @return
     * 2016年9月12日 下午5:34:56
     */
    public static String dateFormat(String dateStr) {
        Date date = null;
		try {
			date = format.parse(dateStr);
			return format(date, dateStr);
		} catch (ParseException e) {
			log.error("格式化日期串失败 【日期串】" + dateStr, e);
			e.printStackTrace();
			return dateStr;
		}
    }
    
    
    private static String format(Date date, String dateStr) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            if(delta <= 4L* ONE_DAY){
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
            }
        }
        int blockIndex = dateStr.indexOf(' ');
        if (blockIndex > 0)
        	return dateStr.substring(0, blockIndex);
        else
        	return dateStr;
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    
}

