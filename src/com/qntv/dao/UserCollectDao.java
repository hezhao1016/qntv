package com.qntv.dao;

import java.util.List;

import com.qntv.entity.UserCollect;
import com.qntv.entity.UserCollectSearch;

public interface UserCollectDao {
	
	// ----------------------------------����-----------------------------------
	/**
	 * ����ղ���Ƶ
	 * @param uc
	 * @return
	 */
	boolean addCollectVideo(UserCollect uc);
	
	/**
	 * ɾ���ղ���Ƶ
	 * @param cid
	 * @return
	 */
	boolean delCollectVideoById(int cid);
	
	/**
	 * ����û��ղ�
	 * @return
	 */
	boolean delAllCollectVideo(String username);
	
	
	/**
	 * ����Id��ѯ�Ƿ��ղع�����Ƶ
	 * @param cid
	 * @return
	 */
	boolean isCollectVideoByVideoId(int vid);
	
//----------2015-3-5 ��Ŀ������Ϻ�֮��д�Ĵ���------------
	
	/**
	 * �����û��ղ�,�����Ƶ���ϣ���ҳ��ʾ
	 * @param pageSize
	 * @param pageIndex
	 * @param username
	 * @return
	 */
	List<UserCollectSearch> getAllVideosByCollect(int pageSize,int pageIndex,String username);
	
	/**
	 * �����û��ղ�,�����Ƶ��ҳ��
	 * @param pageSize
	 * @param username
	 * @return
	 */
	int getTotalPageByCollect(int pageSize,String username);
	
	/**
	 * �����ղ�Id���UserCollectSerach����
	 * @param collectid
	 * @return
	 */
	UserCollectSearch getUserCollectSerachBycollectid(int collectid);
	
	/**
	 * ����username��videoid��ѯ�Ƿ��ղع��˵�Ӱ
	 * @param username
	 * @param vid
	 * @return
	 */
	boolean isExistCollectByUserNameAndVideoId(String username,int vid);
	//---------------------------------------------------------------------
}
