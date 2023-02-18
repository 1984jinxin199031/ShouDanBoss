/**
 * 
 */
package com.jxsq.common.util;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/**
 * @author JX
 * 资源文件工具
 */
public class PropertyUtil {
	/**
	 * 系统通用配置
	 */
	private static Prop propSys = PropKit.use("sys.properties");
	/**
	 * redis配置
	 */
	private static Prop propRedis = PropKit.use("redis.properties");
	/**
	 * APP配置
	 */
	private static Prop propApp = PropKit.use("app.properties");
	/**
	 * 邮箱配置
	 */
	private static Prop propEmail = PropKit.use("email.properties");
	/**
	 * 推送配置
	 */
	private static Prop propPush = PropKit.use("appPush.properties");
	/**
	 * oss配置
	 */
	private static Prop propOss = PropKit.use("oss.properties");
	/**
	 * 接收账户配置
	 */
	private static Prop propReceiveAccount = PropKit.use("receiveAccount.properties");
	/**
	 * 结算配置
	 */
	private static Prop propSettle = PropKit.use("settle.properties");
	/**
	 * 过滤来源地址
	 */
	private static Prop propUrlInterceptor = PropKit.use("urlInterceptor.properties");
	/**
	 * 朋友圈
	 */
	private static Prop propFriendGroup = PropKit.use("friendGroup.properties");
	
	
	/**
	 * 获得【头像固定地址前缀】
	 * @return
	 */
	public static String getHeadFixationUrl()
	{
		return propSys.get("headImg.fixation.url");
	}
	
	/**
	 * 获得 HTTPS【头像固定地址前缀】
	 * @return
	 */
	public static String getHeadFixationHttpsUrl()
	{
		return propSys.get("headImg.fixation.https.url");
	}
	
	/**
	 * 通过【大小类型串】获得【头像宽度】
	 * @param sizeStr
	 * @return
	 */
	public static int getHeadWidthBySize(String sizeStr)
	{
		return Integer.parseInt(propSys.get("headImg." + sizeStr + ".width"));
	}
	
	/**
	 * 通过【大小类型串】获得【头像高度】
	 * @param sizeStr
	 * @return
	 */
	public static int getHeadHeightBySize(String sizeStr)
	{
		return Integer.parseInt(propSys.get("headImg." + sizeStr + ".height"));
	}
	
	/**
	 * 获得【Redis】缓存名
	 * @return
	 */
	public static String getRedisName()
	{
		return propRedis.get("server.name");
	}
	
	/**
	 * 获得【帖子图片】前缀地址
	 * @return
	 */
	public static String getPostImgFixationUrl()
	{
		return propSys.get("post.img.fixation.url");
	}
	
	/**
	 * 获得【帖子图片】HTTPS前缀地址
	 * @return
	 */
	public static String getPostImgFixationHttpsUrl()
	{
		return propSys.get("post.img.fixation.https.url");
	}
	
	/**
	 * 获得【首页顶部轮播图】前缀地址
	 * @return
	 */
	public static String getHomeTopFloatFixationUrl()
	{
		return propSys.get("homeTopFloat.img.fixation.url");
	}

	/**
	 * 获得HTTPS 【首页顶部轮播图】前缀地址
	 * @return
	 */
	public static String getHomeTopFloatFixationHttpsUrl()
	{
		return propSys.get("homeTopFloat.img.fixation.https.url");
	}
	
	/**
	 * 获得【服务器】上下文地址
	 * @return
	 */
	public static String getServerLocalUrl()
	{
		return propSys.get("localUrl");
	}
	
	/**
	 * 获得HTTPS 【服务器】上下文地址
	 * @return
	 */
	public static String getServerLocalHttpsUrl()
	{
		return propSys.get("https.localUrl");
	}
	
	/**
	 * 获得【默认头像】地址
	 * @return
	 */
	public static String getDefaultHeadImgUrl()
	{
		return propSys.get("headImg.default.url");
	}
	
	/**
	 * 获得HTTPS 【默认头像】地址
	 * @return
	 */
	public static String getDefaultHeadImgHttpsUrl()
	{
		return propSys.get("headImg.default.https.url");
	}
	
	/**
	 * 获得【加密密码秘钥】
	 * @return
	 */
	public static String getEncodePwdKey()
	{
		return propApp.get("app.encodePasswordKey");
	}
	
	/**
	 * 获得【保存头像路径】
	 * @return
	 */
	public static String getSaveHeadImgPath()
	{
		return propSys.get("saveHeadImg.fixation.url");
	}
	
	/**
	 * 获得【临时头像路径】
	 * @return
	 */
	public static String getTempHeadImgPath()
	{
		return propSys.get("tempHeadImg.fixation.url");
	}
	
	/**
	 * 获得【附件下载】KEY
	 * @return
	 */
	public static String getAttachKey()
	{
		return propSys.get("attachKey");
	}
	
	/**
	 * 获得【帖子附件(图片)】的获取地址
	 * @return
	 */
	public static String getAttachGetAddress()
	{
		return propSys.get("attach.get.address");
	}
	
	/**
	 * 获得【附件类型】GIF的所在路径
	 * @return
	 */
	public static String getAttachTypeGifPath()
	{
		return propSys.get("attachType.gif.path");
	}
	
	/**
	 * 
	* <p>Description:获取指定系统配置的参数 </p>
	* <p>Company:jxsq </p>
	* @author MINGHUA
	* @date 2016年8月19日 下午8:15:22
	 */
	public static String getSysProperty(String fieldName)
	{
		return propSys.get(fieldName);
	}
	
	/**
	 * 获得【附件】请求地址
	 * @return
	 */
	public static String getAttachRequestUrl()
	{
		return propSys.get("attach.request.url");
	}
	
	/**
	 * 获得【附件】请求的【https】地址
	 * @return
	 */
	public static String getAttachRequestHttpsUrl()
	{
		return propSys.get("attach.request.https.url");
	}
	
	/**
	 * 获得【附件】临时保存目录
	 * @return
	 */
	public static String getAttachFileTempPath()
	{
		return propSys.get("attachFile.tempPath");
	}
	
	/**
	 * 获得【 redis的服务端口】
	 * 转int
	 * @return
	 */
	public static int getRedisServerPost()
	{
		return Integer.parseInt(propRedis.get("server.post"));
	}
	
	/**
	 * 获得【首页启动图】路径
	 * @return
	 */
	public static String getHomeStartImgPath()
	{
		return propSys.get("home.start.img.path");
	}
	
	/**
	 * 获得HTTPS【首页启动图】路径
	 * @return
	 */
	public static String getHomeStartImgHttpsPath()
	{
		return propSys.get("home.start.img.https.path");
	}
	
	/**
	 * 获得【系统信息展示页】请求地址
	 * @return
	 */
	public static String getSysMsgShowPage()
	{
		return propSys.get("sysMsgPage.request.url");
	}
	
	/**
	 * 获得HTTPS 【系统信息展示页】请求地址
	 * @return
	 */
	public static String getSysMsgShowPageHttps()
	{
		return propSys.get("sysMsgPage.request.https.url");
	}
	
	/**
	 * 获得HOST地址
	 * @return
	 */
	public static String getHostUrl()
	{
		return propSys.get("hostUrl");
	}
	
	/**
	 * 股东转图商品图片前缀
	 * @return
	 */
	public static String getPartnerGoodsImgUrl()
	{
		return propSys.get("partner.goodsImg.url");
	}
	
	/**
	 * 股东转图商品图片【HTTPS】前缀
	 * @return
	 */
	public static String getPartnerGoodsImgHttpsUrl()
	{
		return propSys.get("partner.goodsImg.https.url");
	}
	
	/**
	 * 短信通道类型
	 * @return
	 */
	public static String getSmsInterfaceType()
	{
		return propSys.get("sms.interface.type");
	}
	
	/**
	 * 帖子图片的前缀地址
	 * 划分【http】和【https】
	 * @param httpFlag
	 * @return
	 * 2016年10月10日 下午4:19:50
	 */
	public static String getPostImgFixationUrl(boolean httpFlag)
	{
		if (!httpFlag)
			return PropertyUtil.getPostImgFixationUrl();
		else
			return PropertyUtil.getPostImgFixationHttpsUrl();
	}
	
	/**
	 * 附件的请求地址
	 * 划分【http】和【https】
	 * @param httpFlag
	 * @return
	 * 2016年10月10日 下午4:19:50
	 */
	public static String getAttachRequestUrl(boolean httpFlag)
	{
		if (!httpFlag)
			return getAttachRequestUrl();
		else
			return getAttachRequestHttpsUrl();
	}
	
	/**
	 * 头像前面的绝对地址
	 * 划分【http】和【https】
	 * @param httpFlag
	 * @return
	 * 2016年10月10日 下午4:19:50
	 */
	public static String getHeadFixationUrl(boolean httpFlag)
	{
		if (!httpFlag)
			return getHeadFixationUrl();
		else
			return getHeadFixationHttpsUrl();
	}
	
	/**
	 * 默认头像地址
	 * 划分【http】和【https】
	 * @param httpFlag
	 * @return
	 * 2016年10月10日 下午4:19:50
	 */
	public static String getDefaultHeadImgUrl(boolean httpFlag)
	{
		if (!httpFlag)
			return getDefaultHeadImgUrl();
		else
			return getDefaultHeadImgHttpsUrl();
	}
	
	/**
	 * 主页轮播图前缀
	 * 划分【http】和【https】
	 * @param httpFlag
	 * @return
	 * 2016年10月10日 下午4:19:50
	 */
	public static String getHomeTopFloatFixationUrl(boolean httpFlag)
	{
		if (!httpFlag)
			return getHomeTopFloatFixationUrl();
		else
			return getHomeTopFloatFixationHttpsUrl();
	}

	/**
	 * 系统信息展示页
	 * 划分【http】和【https】
	 * @param httpFlag
	 * @return
	 * 2016年10月10日 下午4:19:50
	 */
	public static String getSysMsgShowPage(boolean httpFlag)
	{
		if (!httpFlag)
			return getSysMsgShowPage();
		else
			return getSysMsgShowPageHttps();
	}
	
	/**
	 * 上下文地址
	 * 划分【http】和【https】
	 * @param httpFlag
	 * @return
	 * 2016年10月10日 下午4:19:50
	 */
	public static String getServerLocalUrl(boolean httpFlag)
	{
		if (!httpFlag)
			return getServerLocalUrl();
		else
			return getServerLocalHttpsUrl();
	}
	
	/**
	 * 股东转图商品图片前缀
	 * @param httpFlag
	 * @return
	 * 2016年10月19日 上午10:40:52
	 */
	public static String getPartnerGoodsImgUrl(boolean httpFlag)
	{
		if (!httpFlag)
			return getPartnerGoodsImgUrl();
		else
			return getPartnerGoodsImgHttpsUrl();
	}
	
	/**
	 * 获得【首页启动图】路径
	 * @param httpFlag
	 * @return
	 * 2016年10月19日 上午10:40:52
	 */
	public static String getHomeStartImgPath(boolean httpFlag)
	{
		if (!httpFlag)
			return getHomeStartImgPath();
		else
			return getHomeStartImgHttpsPath();
	}
	
	/**
	 * 获得【发送到哪】邮箱地址
	 * @return
	 * 2016年11月29日 下午5:48:58
	 */
	public static String getToMail()
	{
		return propEmail.get("to.email");
	}
	
	/**
	 * 获得【发送到哪抄送】邮箱地址
	 * @return
	 * 2016年11月30日 下午3:48:27
	 */
	public static String getMailCopyTo()
	{
		return propEmail.get("copy.to");
	}
	
	/**
	 * 获得【发帖传图】临时目录
	 * @return
	 * 2016年11月30日 下午3:48:27
	 */
	public static String getPostImageTempPath()
	{
		return propSys.get("postImage.tempPath");
	}
	
	/**
	 * 获得【发帖传图】的获取路径
	 * 【服务器路径】
	 * @return
	 * 2016年11月30日 下午3:48:27
	 */
	public static String getPostImgGetAddress()
	{
		return propSys.get("postImage.getImg.address");
	}
	
	/**
	 * 获得【推送】appId
	 * @return
	 * 2017年1月10日 下午4:27:37
	 */
	public static String getPushAppId()
	{
		return propPush.get("appId");
	}
	
	/**
	 * 获得【推送】appKey
	 * @return
	 * 2017年1月10日 下午4:27:37
	 */
	public static String getPushAppKey()
	{
		return propPush.get("appKey");
	}
	
	/**
	 * 获得【推送】masterSecret
	 * @return
	 * 2017年1月10日 下午4:27:37
	 */
	public static String getPushMasterSecret()
	{
		return propPush.get("masterSecret");
	}
	
	/**
	 * 获得【推送】地址
	 * @return
	 * 2017年1月10日 下午4:27:37
	 */
	public static String getPushUrl()
	{
		return propPush.get("url");
	}
	
	/**
	 * 获得访问ID
	 * @return
	 * 2017年2月23日 下午3:12:02
	 */
	public static String getAccessId()
	{
		return propOss.get("access.id");
	}
	
	/**
	 * 获得密码
	 * @return
	 * 2017年2月23日 下午3:12:02
	 */
	public static String getSecretKey()
	{
		return propOss.get("secret.key");
	}
	
	/**
	 * 获得节点
	 * @return
	 * 2017年2月23日 下午3:13:05
	 */
	public static String getEndPoint()
	{
		return propOss.get("endPoint");
	}
	
	/**
	 * 获得【获取】节点
	 * @return
	 */
	public static String getGETEndPoint()
	{
		return propOss.get("get.endPoint");
	}
	
	/**
	 * 获得默认的bucketName
	 * @return
	 * 2017年2月24日 上午11:45:20
	 */
	public static String getDefaultBucketName()
	{
		return propOss.get("bucket.name.default");
	}
	
	/**
	 * 获得【保存头像远程路径】
	 * @return
	 */
	public static String getSaveHeadImgRemotePath()
	{
		return propSys.get("saveHeadImg.remote.fixation.url");
	}
	
	/**
	 * 获得【接收钱账户UID】
	 * @return
	 */
	public static String getReceiveAccountUid()
	{
		return propReceiveAccount.get("receive.account.uid");
	}
	
	/**
	 * 获得【获取远程头像地址的固定前缀】
	 * @return
	 */
	public static String getHeadImgRemoteFixationUrl()
	{
		return propSys.get("getHeadImg.remote.fixation.url");
	}
	
	/**
	 * 获得【提问付费围观手续费费率】
	 * @return
	 */
	public static String getQuestionPayLookFeeRate()
	{
		return propSettle.get("question.payLook.feeRate");
	}
	
	/**
	 * 获得【提问采纳答案手续费费率】
	 * @return
	 */
	public static String getQuestionAcceptAnswerFeeRate()
	{
		return propSettle.get("question.acceptAnswer.feeRate");
	}
	
	/**
	 * 获得【付费围观支付金额】
	 * @return
	 */
	public static String getQuestionPayLookAmount()
	{
		return propSettle.get("question.payLook.amount");
	}
	
	/**
	 * 获得【STSKeyId】
	 * @return
	 */
	public static String getStsKeyId()
	{
		return propOss.get("sts.getPermission.key.id");
	}

	/**
	 * 获得【STSKeySecret】
	 * @return
	 */
	public static String getStsKeySecret()
	{
		return propOss.get("sts.getPermission.key.secret");
	}
	
	/**
	 * 获得【STS角色ID】
	 * @return
	 */
	public static String getStsRoleArn()
	{
		return propOss.get("sts.getPermission.roleArn");
	}
	
	/**
	 * 获得【STS角色操作权限表达式】
	 * @return
	 */
	public static String getStsPolicy()
	{
		return propOss.get("sts.getPermission.policy");
	}
	
	/**
	 * 获得【过滤的地址】
	 * @return
	 */
	public static String getInterceptorHost()
	{
		return propUrlInterceptor.get("interceptor.host");
	}
	
	/**
	 * 获得【获取朋友圈远程图片地址前缀】
	 * @return
	 */
	public static String getFriendGroupImgFixationUrl()
	{
		return propSys.get("getFriendGroupImg.fixation.url");
	}
	
	/**
	 * 获得【帖子远程图片地址前缀】
	 * @return
	 */
	public static String getPostImgRemoteFixationUrl()
	{
		return propSys.get("post.img.remote.fixation.url");
	}
	
	/**
	 * 获得【帖子远程图片地址HTTPS前缀】
	 * @return
	 */
	public static String getPostImgRemoteFixationHttpsUrl()
	{
		return propSys.get("post.img.remote.fixation.https.url");
	}
	
	
	/**
	 * 获得【分享页地址前缀】
	 * @return
	 */
	public static String getShareHttpsUrl()
	{
		return propSys.get("share.https.url");
	}

	/**
	 * 获得
	 * 1、【问题打赏】
	 * 2、【问题答案打赏】
	 * 手续费费率
	 * @return
	 */
	public static String getQuestionRewardFeeRate()
	{
		return propSettle.get("question.reward.feeRate");
	}
	
	/**
	 * 朋友圈链接跳转的DES【KEY】
	 * @return
	 */
	public static String getUrlJumpDesKey()
	{
		return propFriendGroup.get("urlJump.des.key");
	}
	
	/**
	 * 手机APP显示的最大回复数
	 * @return
	 */
	public static String getMaxReplyNum()
	{
		return propFriendGroup.get("max.reply.num");
	}
	
	/**
	 * 获得【音频回答】的bucketName
	 * @return
	 * 2017年6月13日 下午5:02:48
	 */
	public static String getAudioAnswerBucketName()
	{
		return propOss.get("audio.answer.bucket.name");
	}
	
	/**
	 * 获得【音频回答】的【KEY前缀】
	 * @return
	 * 2017年6月13日 下午5:02:48
	 */
	public static String getAudioAnswerKeyPrefix()
	{
		return propOss.get("audio.answer.key.prefix");
	}
	
	
	
}