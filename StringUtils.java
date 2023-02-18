package com.jxsq.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 字符串处理类
 * 
 * @author mah
 *
 */
public class StringUtils {
	private static final Logger logger = Logger.getLogger(StringUtils.class);
	public static final String STRING_NULL = "null";

	// 根据Unicode编码完美的判断中文汉字和符号
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}
 
	// 完整的判断中文汉字和符号
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i <ch.length; i++) {
			char c = ch[i];
			if (!isChinese(c)) {
				return false;
			}
		}
		return true;
	}
 
	// 只能判断部分CJK字符（CJK统一汉字）
	public static boolean isChineseByREG(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
		return pattern.matcher(str.trim()).find();
	}
 
	// 只能判断部分CJK字符（CJK统一汉字）
	public static boolean isChineseByName(String str) {
		if (str == null) {
			return false;
		}
		// 大小写不同：\\p 表示包含，\\P 表示不包含 
		// \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
		String reg = "\\p{InCJK Unified Ideographs}&amp;&amp;\\P{Cn}";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(str.trim()).find();
	}


	/**
	 * 校验url是否合法
	 * 
	 * @param url
	 * @return
	 */
	public static boolean veryUrl(String url) {
		String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		return url.matches(regEx);
	}

	/**
	 * 去除换行和空格
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * replace value of param with value of repalceValue when param is null
	 * 
	 * @param param
	 *            String
	 * @param param
	 *            String
	 */
	public static String checkParam(String param, String repalceValue) {

		return param == null || STRING_NULL.equalsIgnoreCase(param)
				|| "".equals(param.trim()) ? (repalceValue == null
				|| STRING_NULL.equalsIgnoreCase(repalceValue) ? ""
				: repalceValue) : param.trim();
	}

	/**
	 * 对一组字符串进行首字母从小到达排序(除签名字段和空值字段外)
	 * 
	 * @param sortString
	 * @return
	 * @throws
	 */
	public static String[] StringSort(String[] sortString) throws Exception {
		String temp = "";

		try {
			for (int i = 0; i < sortString.length; i++) {

				for (int j = i + 1; j < sortString.length; j++) {
					// 如果第一个字符串首字母（字母相同比较下一个字母）大于第二个字符串首字母，进行换位
					if (compare(sortString[i], sortString[j]) == false) {
						temp = sortString[i];
						sortString[i] = sortString[j];
						sortString[j] = temp;
					}
				}
			}

		} catch (Exception e) {
			logger.error("报文排序异常", e);
			throw new Exception("报文排序异常");
		}

		return sortString;
	}

	/**
	 * 比较字符串大小
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean compare(String s1, String s2) {
		boolean result = true;
		for (int i = 0; i < s1.length() && i < s2.length(); i++) {
			if (s1.charAt(i) > s2.charAt(i)) {
				result = false;
				break;
			} else if (s1.charAt(i) < s2.charAt(i)) {
				result = true;
				break;
			} else {
				if (s1.length() < s2.length()) {
					result = true;
				} else {
					result = false;
				}
			}
		}
		return result;
	}

	/**
	 * 生成流水号（唯一）
	 * 
	 * @return
	 */
	public static String getSerilNum() {
		String serlNum = "";
		int random = (int) (Math.random() * 9) + 10;
		serlNum = String.valueOf(Math.abs(UUID.randomUUID().toString()
				.hashCode()));
		String str = "0000000000";
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
		String nowStr = sdf.format(new Date());
		if (serlNum.length() > 10) {
			serlNum = serlNum.substring(0, 10);
		} else if (serlNum.length() < 10) {
			serlNum = serlNum + str.substring(serlNum.length());
		}
		serlNum = nowStr + random + serlNum;
		return serlNum;
	}

	/**
	 * 生成流水号（唯一）
	 * 
	 * @return
	 */
	public static String getErrorSerilNum() {
		String serlNum = "";
		int random = (int) (Math.random() * 9) + 10;
		serlNum = String.valueOf(Math.abs(UUID.randomUUID().toString()
				.hashCode()));
		String str = "0000000000";
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
		String nowStr = sdf.format(new Date());
		if (serlNum.length() > 10) {
			serlNum = serlNum.substring(0, 10);
		} else if (serlNum.length() < 10) {
			serlNum = serlNum + str.substring(serlNum.length());
		}
		serlNum = nowStr + random + serlNum;
		return serlNum;
	}

	/**
	 * 数组转为字符串
	 * 
	 * @param array
	 *            数组
	 * @param join
	 *            拼接字符
	 * @return
	 */
	public static String arrayToString(String[] array, String join) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if ((array[i].split("=")).length <= 1
					|| array[i].startsWith("sign")
					|| "".equals((array[i].split("=")[1]))
					|| "null".equals((array[i].split("=")[1]))) {
				continue;
			}
			sb.append(array[i]).append(join);
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	/**
	 * 分割传过来的参数值
	 * 
	 * @param tranDate
	 * @return HashMap<String,String>
	 * @throws Exception
	 */
	public static HashMap<String, String> resolveString(String tranData,
			String sp) throws Exception {
		try {
			HashMap<String, String> stringMap = new HashMap<String, String>();
			String[] array = tranData.split(sp);
			for (int i = 0; i < array.length; i++) {

				String[] keyValue = array[i].split("=");
				String key = keyValue[0];
				String value;
				if (keyValue.length >= 2) {
					value = array[i].substring(array[i].indexOf("=") + 1);
				} else {
					value = null;
				}
				stringMap.put(key, value);
			}
			return stringMap;
		} catch (Exception e) {
			logger.error("分割字符串错误:", e);
			throw e;
		}
	}

	/**
	 * 验证字符串是否为数字（#.##）且不为0.00,如果是返回true，否则返回false；
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumNotZero(String str) {
		if (!isNum(str))
			return false;
		Pattern pattern = Pattern.compile("^[0][.][0]{2}$");
		Matcher match = pattern.matcher(str);
		return match.matches() ? false : true;
	}

	/**
	 * 验证字符串是否为数字（#.##）,如果是返回true，否则返回false；
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern
				.compile("^[1-9][\\d]*[.][\\d]{2}|[0][.][\\d]{2}$");
		Matcher match = pattern.matcher(str);
		return match.matches();
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(StringUtils.isNumNotZero("3.20"));
		// StringBuffer sb=new
		// StringBuffer("abc=123&asdf=33&asdfds=&selBankId=BOC&eded=asdfwe&adfasdf=adfa");
		// String []array=sb.toString().split("&");
		// StringBuffer merMsg=new StringBuffer();
		// for(int i=0;i<array.length;i++)
		// {
		// if(array[i].contains("selBankId"))
		// continue;
		// merMsg=merMsg.append(array[i]).append("&");
		// }
		// System.out.println(merMsg);
		// String url="http://1.2.37.12/adf?sads.do";
		// System.out.println(StringUtils.veryUrl(url));
	}
}
