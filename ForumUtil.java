/**
 * 
 */
package com.jxsq.common.util;

import java.util.ArrayList;
import java.util.List;

import com.jxsq.common.po.post.CustomForum;
import com.jxsq.common.vo.post.Forum;

/**
 * @author jx
 * 板块工具
 */
public class ForumUtil {
	/**
	 * 转换【板块】VO
	 * @param customForum
	 * @return
	 */
	private static Forum transferForumVo(CustomForum customForum)
	{
		Forum forum = new Forum();
		forum.setFid(customForum.getFid().toString());
		forum.setName(customForum.getName());
//		forum.setStatus(customForum.getStatus());
//		forum.setImgUrl(customForum.getImgUrl());
		return forum;
	}
	
	/**
	 * 转换【板块】VO
	 * @param customForum
	 * @return
	 */
	private static Forum transferForumVoFromForumPo(com.jxsq.common.po.post.Forum forum)
	{
		Forum forumVo = new Forum();
		forumVo.setFid(forum.getFid().toString());
		forumVo.setName(forum.getName());
		return forumVo;
	}
	
	/**
	 * 转换【板块列表】
	 * @param customForum
	 * @return
	 */
	public static List<Forum> transferForumList(List<CustomForum> customForumList)
	{
		List<Forum> forumList = new ArrayList<Forum>();
		for (int i = 0; i < customForumList.size(); i++) {
			forumList.add(transferForumVo(customForumList.get(i)));
		}
		return forumList;
	}
	
	/**
	 * 转换【板块列表】
	 * 从【板块PO】
	 * @param forumList
	 * @return
	 */
	public static List<Forum> transferForumListFromForumPo(List<com.jxsq.common.po.post.Forum> forumList)
	{
		List<Forum> forumVoList = new ArrayList<Forum>();
		for (int i = 0; i < forumList.size(); i++) {
			forumVoList.add(transferForumVoFromForumPo(forumList.get(i)));
		}
		return forumVoList;
	}
}
