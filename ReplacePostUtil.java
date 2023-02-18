/**
 * 
 */
package com.jxsq.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.jxsq.common.po.post.Attachment;
import com.jxsq.common.po.post.AttachmentIndex;

/**
 * @author JX 
 * 正则工具 
 * 【优化】 1、替换集中
 */
public class ReplacePostUtil {
	private static final Logger log = Logger.getLogger(ReplacePostUtil.class);
	
	public static final String URL_REG_PREFIX_ONE = "<a href=\"http://bbs.jx.vc/forum.php\\?mod=viewthread&tid=\\d+";
	public static final String URL_REG_PREFIX_TWO = "<a href=\"http://bbs.jn1535.com/forum.php\\?mod=viewthread&tid=\\d+";
	public static final String URL_PREFIX_ONE = "<a href=\"http://bbs.jx.vc/forum.php?mod=viewthread&tid=";
	public static final String URL_PREFIX_TWO = "<a href=\"http://bbs.jn1535.com/forum.php?mod=viewthread&tid=";
	
	/**
	 * 循环替换分割线图片的前缀
	 * @param content
	 * @return
	 */
	private static String replaceRecycleImg(String content)
	{
		String regStr = "\\[img\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String imgReplacedPrefix = "";
		while (matcher.find()) {
			imgReplacedPrefix = "<img id=\"aimg_" + StringUtil.getRandomStrByLength(5) + 
			"\" onclick=\"zoom(this, this.src, 0, 0, 0)\" file=\"";
			content = content.replaceFirst(regStr, imgReplacedPrefix);
		}
		return content;
	}
	
	/**
	 * 通过【附件列表】替换所有【[attach]标签】
	 * 
	 * @param message
	 * @param attachmentList
	 * @return
	 */
	public static String replaceAllImg(String message, List<Attachment> attachmentList) {
		Attachment attachment = null;
		for (int i = 0; i < attachmentList.size(); i++) {
			attachment = attachmentList.get(i);
			message = message.replaceAll("\\[attach\\]\\s{0,9}" + attachment.getAid() + "\\s{0,9}\\[/attach\\]",
					"<img id=\"_aimg_" + attachment.getAid() + "\" aid=\"" + attachment.getAid()
							+ "\" zoomfile=\"data/attachment/forum/" + attachment.getAttachment()
							+ "\" src=\"data/attachment/forum/" + attachment.getAttachment()
							+ "\" onclick=\"zoom(this, this.src, 0, 0, 0)\" alt=\"" + attachment.getFilename() + 
							"\" title=\"" + attachment.getFilename() + "\" />");
		}
		return message;
	}
	
	/**
	 * 必须在【replaceSize】之前执行
	 * @param message
	 * @return
	 */
	public static String replaceSizePxRecycle(String message) {
		
		Pattern pattern = Pattern.compile("\\[size=\\d{1,5}px");
		Matcher matcher = pattern.matcher(message);
		String messageTotal = "";
		String pxValue = "";
		while (matcher.find()) {
			messageTotal = "\\" + matcher.group() + "\\]";
			pxValue = RegUtil.search("\\d{1,5}", messageTotal);
			message = message.replaceFirst(messageTotal, "<font style=\"font-size:" + pxValue + "px\">");
		}
		return message;
	}

	/**
	 * 从【帖子内容】获取【附件ID列表】
	 * 
	 * @param message
	 * @return
	 */
	public static String getAttachmentIdStr(String message) {
		Pattern pattern = Pattern
				.compile("\\[\\s{0,3}attach\\s{0,3}\\]\\s{0,3}\\d{1,9}\\s{0,3}\\[\\s{0,3}/attach\\s{0,3}\\]");
		Matcher matcher = pattern.matcher(message);
		StringBuilder idStrSB = new StringBuilder();
		Pattern patternAttachmentId = Pattern.compile("\\d{1,9}");
		Matcher matcherAttachmentId = null;
		String attachmentTag = "";
		String attachmentId = "";
		while (matcher.find()) {
			attachmentTag = matcher.group();
			matcherAttachmentId = patternAttachmentId.matcher(attachmentTag);
			if (matcherAttachmentId.find()) {
				attachmentId = matcherAttachmentId.group();
			}
			idStrSB.append(attachmentId).append(", ");
		}
		String idStr = idStrSB.toString();
		return idStr.substring(0, idStr.length() - 1);
	}

	/**
	 * 循环替换【td宽度】
	 * [td=50%]
	 * @param content
	 * @return
	 */
	public static String replaceTdWidthRecycle(String content)
	{
		String regStr = "\\[td\\s{0,3}=\\s{0,3}[0-9]{0,4}%\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			content = content.replaceFirst(regStr, "<td>");
		}
		return content;
	}
	
	/**
	 * 循环替换【td+数字】
	 * [td=145]
	 * @param content
	 * @return
	 */
	public static String replaceTdNumberRecycle(String content)
	{
		String regStr = "\\[td\\s{0,3}=\\s{0,3}[0-9]{1,4}\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			content = content.replaceFirst(regStr, "<td>");
		}
		return content;
	}
	
	/**
	 * 循环替换【td+数字+逗号】
	 * [td=1,2,134]
	 * @param content
	 * @return
	 */
	public static String replaceTdMuchNumberRecycle(String content)
	{
		String regStr = "\\[td\\s{0,3}=\\s{0,3}\\d{1,3},\\d{1,3},\\d{1,4}\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			content = content.replaceFirst(regStr, "<td>");
		}
		return content;
	}
	
	/**
	 * 循环替换【td宽度】
	 * [td=50%]
	 * @param content
	 * @return
	 */
	public static String replaceTdSpanRecycle(String content)
	{
		String regStr = "\\[td\\s{0,3}=\\s{0,3}[0-9]{0,3},[0-9]{0,3}\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String tdValueStr = "";
		String spanValue = "";
		while (matcher.find()) {
			tdValueStr = matcher.group();
			spanValue = tdValueStr.substring(tdValueStr.indexOf('=') + 1, tdValueStr.lastIndexOf(','));
			content = content.replaceFirst(regStr, "<td colspan=\"" + spanValue + "\">");
		}
		return content;
	}
	
	/**
	 * 循环替换【组合P标签】
	 * [p=24, null, left]
	 * @param content
	 * @return
	 */
	public static String replacePTagRecycle(String content)
	{
		String regStr = "\\[p\\s{0,3}=\\s{0,3}[0-9a-zA-Z]+,\\s{0,3}[a-zA-Z0-9]+,\\s{0,3}[a-zA-Z0-9]+\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String pValueStr = "";
		String[] pTagValueArray = null;
		while (matcher.find()) {
			pValueStr = matcher.group();
			pTagValueArray = getPTagValueArray(pValueStr);
			content = content.replaceFirst(regStr, getPTagTransformed(pTagValueArray));
		}
		return content;
	}
	
	/**
	 * 获得【P标签】值数组
	 * @param pValueStr
	 * @return
	 */
	private static String[] getPTagValueArray(String pValueStr)
	{
		pValueStr = pValueStr.substring(pValueStr.indexOf('=') + 1, pValueStr.length() - 1);
		return pValueStr.split(",");
	}
	
	/**
	 * 获得【组合P标签】转换后的串
	 * @param pTagValueArray
	 * @return
	 */
	private static String getPTagTransformed(String[] pTagValueArray)
	{
		StringBuilder stringBuilder = new StringBuilder("<p style=\"line-height:");
		stringBuilder.append(pTagValueArray[0].trim()).append("px;text-indent:nullem;text-align:");
		stringBuilder.append(pTagValueArray[2].trim()).append("\">");
		return stringBuilder.toString();
	}
	
	private static void setReplaceRuleList(List<String[]> replaceRuleList, String regStr, String replacedContent)
	{
		String[] replaceRuleArray = new String[2];
		replaceRuleArray[0] = regStr;
		replaceRuleArray[1] = replacedContent;
		replaceRuleList.add(replaceRuleArray);
	}
	
	/**
	 * \\n替换为空
	 * \\r替换为空
	 * 【用途】
	 * 1、集碎片
	 * @param content
	 * @return
	 * 2016年9月10日 下午6:20:19
	 */
	public static String replaceBrNull(String content) {
		List<String[]> replaceRuleList = new ArrayList<String[]>();
		setReplaceRuleList(replaceRuleList, "\\n", "");
		setReplaceRuleList(replaceRuleList, "\\r", "");
		return RegUtil.replaceRecycleByList(replaceRuleList, content);
	}
	
	/**
	 * \\n替换为<br/>
	 * 【用途】
	 * 1、普通帖子
	 * @param content
	 * @return
	 * 2016年9月10日 下午6:20:19
	 */
	public static String replaceBr(String content) {
		List<String[]> replaceRuleList = new ArrayList<String[]>();
		setReplaceRuleList(replaceRuleList, "\\n", "<br/>");
		return RegUtil.replaceRecycleByList(replaceRuleList, content);
	}
	
	/**
	 * 复杂正则解释帖子
	 * @param content
	 */
	public static String complexPost(String content) {
		content = replaceRecycleImg(content);
		content = replaceSizePxRecycle(content);
		content = replacePTagRecycle(content);
		content = replaceTdNumberRecycle(content);
		content = replaceTdMuchNumberRecycle(content);
		content = replaceTdWidthRecycle(content);
		content = replaceTdSpanRecycle(content);
		List<String[]> replaceRuleList = new ArrayList<String[]>();
		setReplaceRuleList(replaceRuleList, "\\[size=[\\d.]+pt\\]", "<font style=\"font-size:12.0pt\">");
		setReplaceRuleList(replaceRuleList, "\\s{2}", "&nbsp; ");
		setReplaceRuleList(replaceRuleList, "\\s{4}", "&nbsp; &nbsp; ");
		setReplaceRuleList(replaceRuleList, "\\[b\\]", "<strong>");
		setReplaceRuleList(replaceRuleList, "\\[/b\\]", "</strong>");
		setReplaceRuleList(replaceRuleList, "\\[color\\s{0,1}=\\s{0,1}", "<font style=\"color:");
		setReplaceRuleList(replaceRuleList, "\\[\\s{0,5}color=#", "<font color=\"#");
		setReplaceRuleList(replaceRuleList, "\\[color=(rgb|rgba){1}", "<font style=\"color:");
		setReplaceRuleList(replaceRuleList, "\\[/backcolor\\]", "");
		setReplaceRuleList(replaceRuleList, "\\[font\\s{0,2}=\\s{0,2}", "<font face=\"");
		setReplaceRuleList(replaceRuleList, "\\[/color\\]", "</font>");
		setReplaceRuleList(replaceRuleList, "\\[/font\\]", "</font>");
		setReplaceRuleList(replaceRuleList, "\\[align\\s{0,1}=\\s{0,1}left\\]", "<div align=\"left\">");
		setReplaceRuleList(replaceRuleList, "\\[align\\s{0,1}=\\s{0,1}center\\]", "<div align=\"center\">");
		setReplaceRuleList(replaceRuleList, "\\[align\\s{0,1}=\\s{0,1}right\\]", "<div align=\"right\">");
		setReplaceRuleList(replaceRuleList, "\\[/align\\]", "</div>");
		setReplaceRuleList(replaceRuleList, "\\[list\\]\\s{0,1}\\[\\s{0,1}\\*\\s{0,1}\\]", "<ul><li>");
		setReplaceRuleList(replaceRuleList, "\\[/align\\]\\s{0,1}\\[\\s{0,1}\\*\\s{0,1}\\]", "</div></li><li>");
		setReplaceRuleList(replaceRuleList, "\\[/align\\]\\s{0,1}\\[/list\\]", "</div></li></ul>");
		
		setReplaceRuleList(replaceRuleList, "\\[table=[0-9]{0,3}%\\]", "<table cellspacing=\"0\">");
		
		setReplaceRuleList(replaceRuleList, "\\[/table\\]", "</table>");
		setReplaceRuleList(replaceRuleList, "px\\]", "px\">");
		setReplaceRuleList(replaceRuleList, "\\[/size\\]", "</font>");
		setReplaceRuleList(replaceRuleList, "\\[tr\\]", "<tr>");
		setReplaceRuleList(replaceRuleList, "\\[/tr\\]", "</tr>");
		setReplaceRuleList(replaceRuleList, "\\[td\\]", "<td>");
		setReplaceRuleList(replaceRuleList, "\\[/td\\]", "</td>");
		//float left和right 可能有问题
		setReplaceRuleList(replaceRuleList, "\\[float=left\\]", "<br style=\"clear: both\"><span style=\"float: left; margin-right: 5px;\">");
		setReplaceRuleList(replaceRuleList, "\\[float=right\\]", "<br style=\"clear: both\"><span style=\"float: right; margin-left: 5px;\">");
		setReplaceRuleList(replaceRuleList, "\\[/img\\]", "\" />");
		setReplaceRuleList(replaceRuleList, "\\[size=", "<font size=\"");
		setReplaceRuleList(replaceRuleList, "\\[s\\]", "<strike>");
		setReplaceRuleList(replaceRuleList, "\\[/s\\]", "</strike>");
		setReplaceRuleList(replaceRuleList, "\\[hr\\]", "<hr />");
		setReplaceRuleList(replaceRuleList, "\\[/p\\]", "</p>");
		setReplaceRuleList(replaceRuleList, "\\[u\\]", "<u>");
		setReplaceRuleList(replaceRuleList, "\\[/u\\]", "</u>");
		setReplaceRuleList(replaceRuleList, "\\[list\\]", "<ul>");
		setReplaceRuleList(replaceRuleList, "\\[list=1\\]", "<ul type=\"1\" >");
		setReplaceRuleList(replaceRuleList, "\\[list=a\\]", "<ul type=\"a\" >");
		setReplaceRuleList(replaceRuleList, "\\[list=A\\]", "<ul type=\"A\" >");
		//转义问题
		setReplaceRuleList(replaceRuleList, "\\[\\*\\]", "<li>");
		setReplaceRuleList(replaceRuleList, "\\[\\*\\]", "<li>");
		setReplaceRuleList(replaceRuleList, "\\[/list\\]", "</ul>");
		setReplaceRuleList(replaceRuleList, "\\[indent\\]", "<blockquote>");
		setReplaceRuleList(replaceRuleList, "\\[/indent\\]", "</blockquote>");
		setReplaceRuleList(replaceRuleList, "\\[/float\\]", "</span>");
		setReplaceRuleList(replaceRuleList, "\\[/url\\]", "</a>");
		setReplaceRuleList(replaceRuleList, "\\[table\\]", "<table cellspacing=\"0\" >");
		setReplaceRuleList(replaceRuleList, "\\[quote\\]", "<div style='margin-right: 50px;padding: 10px 10px 10px 45px;background: #FAFAFA url(http://bbs.jx.vc/static/image/common/qa.gif) no-repeat 10px 10px;'><blockquote style='background: url(http://bbs.jx.vc/static/image/common/qz.gif) no-repeat 100% 100%;'>");
		setReplaceRuleList(replaceRuleList, "\\[/quote\\]", "</blockquote></div>");
		setReplaceRuleList(replaceRuleList, "\\[free\\]", "<div><blockquote>");
		setReplaceRuleList(replaceRuleList, "\\[/free\\]", "</blockquote></div>");
		setReplaceRuleList(replaceRuleList, "\\[url\\s{0,3}=\\s{0,3}", "<a href=\"");
		
		
		
		return RegUtil.replaceRecycleByList(replaceRuleList, content);
	}
	
	/**
	 * 替换结束标签
	 * @param content
	 * @return
	 */
	public static String replaceEndTag(String content)
	{
		List<String[]> replaceRuleList = new ArrayList<String[]>();
		setReplaceRuleList(replaceRuleList, "\\]", "\">");
		return RegUtil.replaceRecycleByList(replaceRuleList, content);
	}
	
	/**
	 * 【循环替换postBg标签】
	 * @param content
	 * @return
	 */
	public static String replacePostBGTag(String content)
	{
		//[postbg]bg3.png[/postbg]
		String regStr = "\\[postbg\\][\\s\\S]{1,}\\[/postbg\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String str = "";
		while (matcher.find()) {
			str = matcher.group();
			content = content.replace(str, "");
		}
		return content;
	}
	
	/**
	 * 【循环替换i标签】
	 * @param content
	 * @return
	 */
	public static String replaceITag(String content)
	{
		//[i=s] 本帖最后由 蒋大胖子 于 2016-3-18 19:38 编辑 [/i]
		int iTagIndex = -1;
		String regStartStr = "\\[i\\s{0,3}=\\s{0,3}s\\]";
		String regEndStr = "\\[/i\\]";
		String contentStr = "";
		while((iTagIndex = content.indexOf("[i=s]")) >= 0)
		{
			contentStr = content.substring(iTagIndex + 5, content.indexOf("[/i]"));
			content = content.replaceFirst(regStartStr + contentStr + regEndStr, "");
		}
		return content;
	}
	
	/**
	 * 【循环替换i标签】
	 * [i]
	 * @param content
	 * @return
	 */
	public static String replaceIShortTag(String content)
	{
		//[i]1 [/i]
		content = content.replaceAll("\\[i\\]", "<i>");
		return content.replaceAll("\\[/i\\]", "</i>");
	}
	
	/**
	 * 回复列表
	 * 【循环替换URL标签】
	 * @param content
	 * @return
	 */
	public static String replaceReplyUrlTag(String content)
	{
		//[postbg]bg3.png[/postbg]
		String regStr = "\\[url[a-zA-Z0-9]\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String str = "";
		while (matcher.find()) {
			str = matcher.group();
			content = content.replace(str, "");
		}
		return content;
	}
	
	/**
	 * 【循环替换backcolor标签】
	 * @param content
	 * @return
	 */
	public static String replaceBackColor(String content)
	{
		//【格式】[backcolor=rgb(250, 250,
		String regStr = "\\[backcolor\\s{0,2}=\\s{0,2}[a-zA-Z0-9,\\(\\)\\s]+\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String str = "";
		while (matcher.find()) {
			str = matcher.group();
			content = content.replace(str, "");
		}
		return content;
	}
	
	/**
	 * 【循环替换带样式的img标签标签】
	 * @param content
	 * @return
	 */
	public static String replaceImgClass(String content)
	{
		//[postbg]bg3.png[/postbg]
		String regStr = "\\[img=\\d+,\\d+\\]";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String str = "";
		while (matcher.find()) {
			str = matcher.group();
			content = content.replace(str, "<img src=\"");
		}
		return content;
	}
	
	/**
	 * 替换【之前(PHP)URL前缀】为【新的系统地址】
	 * @param content
	 * @param urlPrefix
	 * @param urlRegPrefix
	 * @param mapUid
	 * @return
	 * 2016年12月20日 下午5:56:41
	 */
	public static String replaceUrlPrefix(String content, String urlPrefix, String urlRegPrefix,
			String mapUid)
	{
		Pattern pattern = Pattern.compile(urlRegPrefix);
		Matcher matcher = pattern.matcher(content);
		String str = "";
		String tid = "";
		String mapUidStr = "";
		if (!ValidateUtil.validateStrNullValue(mapUid) && !mapUid.equals("0"))
			mapUidStr = "?uid=" + mapUid;
		while (matcher.find()) {
			str = matcher.group();
			content = content.replace(getReplaceContent(str, content), "");
			tid = splitTidFromContent(str, urlPrefix);
			if (tid == null) continue;
			content = content.replace(str, "<a href=\"" + PropertyUtil.getServerLocalHttpsUrl() + "post/postDetail/" + tid + mapUidStr);
		}
		return content;
	}
	
	private static String getReplaceContent(String str, String content)
	{
		String replaceContent = content.substring(content.indexOf(str) + str.length());
		return replaceContent.substring(0, replaceContent.indexOf("\""));
	}
	
	/**
	 * 截取TID
	 * @param content
	 * @param urlPrefix
	 * @return
	 * 2016年12月13日 下午8:08:24
	 */
	private static String splitTidFromContent(String content, String urlPrefix)
	{
		try {
			return content.substring(content.indexOf(urlPrefix) + urlPrefix.length());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("替换URL前缀截取tid时出现异常【content】" + content, e);
			return null;
		}
	}
	
	/**
	 * 获得帖子图片【临时文件目录】
	 * @param uuid
	 * @return
	 * 2016年12月16日 上午11:32:01
	 */
	public static String getPostImgTempFileDir(String uuid)
	{
		String postMessageTempPath = PropertyUtil.getPostImageTempPath();
		return postMessageTempPath + uuid + "/";
	}
	
	/**
	 * 获得帖子图片【服务器(临时图片文件)访问路径】
	 * @param uuid
	 * @return
	 * 2016年12月16日 上午11:32:01
	 */
	public static String getPostImgServerAddress(String uuid)
	{
		String postMessageTempPath = PropertyUtil.getPostImgGetAddress();
		return postMessageTempPath + uuid + "/";
	}
	
	/**
	 * 替换【帖子内容】到BBCode
	 * @param attachmentIndexMap
	 * @param content
	 * @param uuid
	 * @return
	 * 2016年12月16日 上午11:25:03
	 */
	public static String replacePostContentToBBCode(Map<Integer, AttachmentIndex> attachmentIndexMap,
			String content, String uuid)
	{
		if (!ValidateUtil.validateStrNullValue(uuid))
			content = replaceImgInputToBBCode(attachmentIndexMap, content, uuid);
		content = replaceCombineBITag(content, "<i style=\"", "</i>");
		content = replaceCombineBITag(content, "<b style=\"", "</b>");
		content = replaceInputToBBCode(content);
		return content;
	}
	
	/**
	 * 替换下面的标签到【BBCode】
	 * 1、<b>
	 * 2、<i>
	 * 3、<br/>
	 * 4、<a>
	 * 5、<ul>和<li>
	 * 6、表单结束部分
	 * @param content
	 * @return
	 * 2016年12月15日 下午5:59:55
	 */
	private static String replaceInputToBBCode(String content)
	{
		List<String[]> replaceRuleList = new ArrayList<String[]>();
		setReplaceRuleList(replaceRuleList, "<b>", "\\[b\\]");
		setReplaceRuleList(replaceRuleList, "</b>", "\\[/b\\] ");
		setReplaceRuleList(replaceRuleList, "<i>", "\\[i\\] ");
		setReplaceRuleList(replaceRuleList, "</i>", "\\[/i\\] ");
		setReplaceRuleList(replaceRuleList, "<a href=\"", "\\[url=");
		setReplaceRuleList(replaceRuleList, "<br>", "\n");
		setReplaceRuleList(replaceRuleList, "</a>", "\\[/url\\]");
		setReplaceRuleList(replaceRuleList, "<ul>", "\\[list\\]");
		setReplaceRuleList(replaceRuleList, "</ul>", "\\[/list\\]");
		setReplaceRuleList(replaceRuleList, "<li>", "\\[\\*\\]");
		setReplaceRuleList(replaceRuleList, "</li>", "");
		setReplaceRuleList(replaceRuleList, "</span>", "");
		
		//最后执行
		setReplaceRuleList(replaceRuleList, "\">", "\\]");
		return RegUtil.replaceRecycleByList(replaceRuleList, content);
	}
	
	/**
	 * 替换【图片表单】成【BBCode】
	 * @param attachmentIndexMap
	 * @param content
	 * @return
	 * 2016年12月15日 下午5:51:02
	 */
	private static String replaceImgInputToBBCode(Map<Integer, AttachmentIndex> attachmentIndexMap,
			String content, String uuid)
	{
		Pattern pattern = Pattern.compile("<img src=\"");
		Matcher matcher = pattern.matcher(content);
		String path = getPostImgServerAddress(uuid);
		log.info("【替换图片表单】图片获取路径：" + path);
		String i = "0";
		int key = 0;
		while (matcher.find())
		{
			if (attachmentIndexMap == null)
			{
				log.error("【替换图片表单到BBCode】时帖子的图片附件不存在【uuid】" + uuid + "【content】" + content);
				break;
			}
			i = cutFileName(content, path);
			if (i == null)
			{
				log.error("【从表单内容截取没(.后缀)的文件名】截取不到【content】" + content + "【uuid】" + uuid);
				break;
			}
			try {
				key = Integer.parseInt(i);
			} catch (NumberFormatException e) {
				log.error("【文件名转换序号(整形)】转换异常【content】" + content + "【uuid】" + uuid + "【文件名】" + i);
				break;
			}
			if (!attachmentIndexMap.containsKey(key))
			{
				log.error("【通过(序号)获取附件数据】获取不到，发帖内容中的图片没有上传成功【content】" + content + "【uuid】" + uuid + "【文件名】" + i);
				break;
			}
			content = content.replace(cutImageInput(content), getAttachBBCode(attachmentIndexMap.get(key)));
		}
		return content;
	}
	
	/**
	 * 替换【组合b和i】内容
	 * @param content
	 * @param regStr
	 * @param endTag
	 * @return
	 * 2016年12月27日 下午2:33:01
	 */
	private static String replaceCombineBITag(String content, String regStr, String endTag)
	{
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(content);
		String replacedContent = "";
		while (matcher.find())
		{
			replacedContent = getReplacedContent(content, regStr, endTag);
			content = content.replaceFirst(replacedContent, getReplaceContent(replacedContent));
		}
		return content;
	}
	
	private static String getReplacedContent(String content, String regStr, String endTag)
	{
		content = content.substring(content.indexOf(regStr)); 
		return content.substring(0, content.indexOf(endTag) + endTag.length());
	}
	
	private static String getReplaceContent(String replacedContent)
	{
		String value = replacedContent.substring(replacedContent.indexOf('>') + 1 , replacedContent.indexOf("</"));
		return "[b][i]" + value + "[/b][/i]";
	}
	
	/**
	 * 从【帖子内容】截取【文件名(没后缀)】
	 * @param content
	 * @param path
	 * @return
	 * 2016年12月16日 上午11:36:34
	 */
	private static String cutFileName(String content, String path)
	{
		String imgInput = content.substring(content.indexOf(path) + path.length());
		int pointIndex = imgInput.indexOf('.');
		if (pointIndex > 0)
			return imgInput.substring(0, pointIndex);
		else
			return null;
	}
	
	/**
	 * 截取首个【图片表单】
	 * @param content
	 * @return
	 * 2016年12月15日 下午5:31:38
	 */
	private static String cutImageInput(String content)
	{
		String imgInput = content.substring(content.indexOf("<img src=\""));
		int index = imgInput.indexOf('>');
		if (index > 0)
			return imgInput.substring(0, index + 1);
		else 
			return "";
	}
	
	/**
	 * 获得【附件对象】的BBCode表现形式
	 * @param attachmentIndex
	 * @return
	 * 2016年12月15日 下午5:40:19
	 */
	private static String getAttachBBCode(AttachmentIndex attachmentIndex)
	{
		StringBuilder sb = new StringBuilder("[attach]");
		sb.append(attachmentIndex.getAid()).append("[/attach]");
		return sb.toString();
	}
	
	public static String getPostContent()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<img src=\"https://apptest.juxiuclub.com/tempPostImg/1612121811538660/0.jpg\" alt=\"\" class=\"wp-image-1482744705827 alignnone size-full\" width=\"400\" height=\"300\">");
		sb.append("<img src=\"https://apptest.juxiuclub.com/tempPostImg/1612121811538660/1.jpg\" alt=\"\" class=\"wp-image-1482744705827 alignnone size-full\" width=\"400\" height=\"300\">");
		sb.append("安吉斯都加上单价哦啊叫三等奖傲娇及时冻结囧囧哦你按时的");
		sb.append("<span id=\"img_container_1482755582595\" class=\"img_container compat\"><span class=\"upload-overlay\" contenteditable=\"false\"></span></span><span id=\"img_container_1482755582358\" class=\"img_container compat\"><span class=\"upload-overlay\" contenteditable=\"false\"></span></span>");
		sb.append("<a href=\"http://bbs.jn1535.com/forum.php?mod=viewthread&tid=3934\">想</a>");
		sb.append("                                                                                   第一行");
		sb.append("                                                                                   ");
		sb.append("                                                                                   <b>第二行</b>");
		sb.append("                                                                                   ");
		sb.append("                                                                                   <b><i>第三行</i></b>");
		sb.append("                                                                                   <ul>");
		sb.append("                                                                                   	<li><b><i>第四行</i></b></li>");
		sb.append("                                                                                   </ul>");
		sb.append("                                                                                   <a href=\"https://www.baidu.com\">超链接</a><b><i>");
		sb.append("                                                                                   </i></b>");
		sb.append("                                                                                   ");
		sb.append("                                                                                   <i>第五行</i>");
		sb.append("                                                                                   <ul>");
		sb.append("                                                                                   	<li><b>第六行</b></li>");
		sb.append("                                                                                   </ul>");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		// 普通格式分析
		// analyzeStr();

		// 获取文件ID
		// attachment();

		// 附件批量替换
		// attachmentReplace();

		// 复杂格式
		// test1();

		// 【3.txt】解释【待续】
		// txt3();

		// 投票,
		// vote();

		// 复杂帖子格式
//		complexPost("xx");
		
//		String content = "[align=center][size=42px][i]1 [/i][b][color=#000000] ";
//		String content = "[sss][td=2,1,638][td=145]ss[/td][/td]";
//		String content = getPostContent();
//		System.out.println(replaceITag(content));
//		System.out.println(replaceIShortTag(content));
//		System.out.println(complexPost(content));
//		String content = getPostContent();
//		String path = getPostImgServerAddress("20161214153300048171924435623");
//		System.out.println(cutFileName(content, path));
//		System.out.println(replaceImgInputToBBCode(null, "", "20161214153300048171924435623"));
//		System.out.println(cutFileName(getPostContent(), "https://apptest.juxiuclub.com/tempPostImg/1612181716841054/"));
//		System.out.println("01234/".substring(0, 3));
//		content = content.replaceFirst("\\[url=http://stock.10jqka.com.cn/zhuanti/zg_list/\\]", "<a href=\"http://stock.10jqka.com.cn/zhuanti/zg_list/\" target=\"_blank\">");
//		System.out.println(content);
//		content = content.substring(content.indexOf("[url="), content.indexOf("[/url]"));
//		System.out.println(content.substring(0, content.lastIndexOf(']') + 1));
//		System.out.println(content.substring(content.indexOf('=') + 1, content.indexOf("]")));
	}
}
 