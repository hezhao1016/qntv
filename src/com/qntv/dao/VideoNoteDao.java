package com.qntv.dao;

import java.util.List;

import com.qntv.entity.UserCollectSearch;
import com.qntv.entity.VideoNote;
import com.qntv.entity.VideoNoteSearch;

public interface VideoNoteDao {
	
	// ----------------------------------何钊-----------------------------------
	/**
	 * 根据用户获取最近观看记录，分页显示
	 * @param pageSize
	 * @param pageIndex
	 * @param username
	 * @return
	 */
	List<VideoNoteSearch> getAllVideoNoteByUserName(int pageSize,int pageIndex,String username);
	
	/**
	 * 根据用户获取最近观看记录的总页数
	 * @param pageSize
	 * @param username
	 * @return
	 */
	int getTotalPageByNote(int pageSize,String username);

	/**
	 * 根据Id删除用户观看记录
	 * @param vnid
	 * @return
	 */
	boolean delVideoNoteById(int vnid);
	
	/**
	 * 清空用户观看记录
	 * @return
	 */
	boolean delAllVideoNote(String username);
	
	
	/**
	 * 根据视频Id查询观看记录表是否有记录
	 * @param vid
	 * @return
	 */
	boolean isHaveLookNoteByVId(int vid);
	
	/**
	 * 根据记录Id获得VideoNoteSerach对象
	 * @param noteid
	 * @return
	 */
	VideoNoteSearch getVideoNoteSerachBynoteid(int noteid);

	/**
	 * 查询用户是否观看过此电影
	 * @param vn
	 * @return
	 */
	boolean isExistNoteByUserNameAndVideoId(VideoNote vn);
	
	/**
	 * 添加观看记录
	 * @param vn
	 * @return
	 */
	boolean addVideoNote(VideoNote vn);
	
	/**
	 * 修改观看记录的观看日期
	 * @param vn
	 * @return
	 */
	boolean updateNoteCreateDateByUserNameAndVideoId(VideoNote vn);
	
	/**
	 * 播放视频时，处理观看记录的逻辑
	 * 1、如果已经观看过这部电影，则将该记录的记录日期改为现在
	 * 2、如果没有看过，则添加一条
	 * 返回true/flase
	 * @param vn
	 * @return
	 */
	boolean VideoNoteInfoByPlayVideo(VideoNote vn);
	// -----------------------------------------------------------------------
}
