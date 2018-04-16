package com.qntv.dao;

import java.util.List;

import com.qntv.entity.UserCollect;
import com.qntv.entity.UserCollectSearch;

public interface UserCollectDao {
	
	// ----------------------------------何钊-----------------------------------
	/**
	 * 添加收藏视频
	 * @param uc
	 * @return
	 */
	boolean addCollectVideo(UserCollect uc);
	
	/**
	 * 删除收藏视频
	 * @param cid
	 * @return
	 */
	boolean delCollectVideoById(int cid);
	
	/**
	 * 清空用户收藏
	 * @return
	 */
	boolean delAllCollectVideo(String username);
	
	
	/**
	 * 根据Id查询是否收藏过此视频
	 * @param cid
	 * @return
	 */
	boolean isCollectVideoByVideoId(int vid);
	
//----------2015-3-5 项目框架整合好之后写的代码------------
	
	/**
	 * 根据用户收藏,获得视频集合，分页显示
	 * @param pageSize
	 * @param pageIndex
	 * @param username
	 * @return
	 */
	List<UserCollectSearch> getAllVideosByCollect(int pageSize,int pageIndex,String username);
	
	/**
	 * 根据用户收藏,获得视频总页数
	 * @param pageSize
	 * @param username
	 * @return
	 */
	int getTotalPageByCollect(int pageSize,String username);
	
	/**
	 * 根据收藏Id获得UserCollectSerach对象
	 * @param collectid
	 * @return
	 */
	UserCollectSearch getUserCollectSerachBycollectid(int collectid);
	
	/**
	 * 根据username与videoid查询是否收藏过此电影
	 * @param username
	 * @param vid
	 * @return
	 */
	boolean isExistCollectByUserNameAndVideoId(String username,int vid);
	//---------------------------------------------------------------------
}
