package com.qntv.dao;

import java.util.List;

import com.qntv.entity.UserCollectSearch;
import com.qntv.entity.VideoNote;
import com.qntv.entity.VideoNoteSearch;

public interface VideoNoteDao {
	
	// ----------------------------------����-----------------------------------
	/**
	 * �����û���ȡ����ۿ���¼����ҳ��ʾ
	 * @param pageSize
	 * @param pageIndex
	 * @param username
	 * @return
	 */
	List<VideoNoteSearch> getAllVideoNoteByUserName(int pageSize,int pageIndex,String username);
	
	/**
	 * �����û���ȡ����ۿ���¼����ҳ��
	 * @param pageSize
	 * @param username
	 * @return
	 */
	int getTotalPageByNote(int pageSize,String username);

	/**
	 * ����Idɾ���û��ۿ���¼
	 * @param vnid
	 * @return
	 */
	boolean delVideoNoteById(int vnid);
	
	/**
	 * ����û��ۿ���¼
	 * @return
	 */
	boolean delAllVideoNote(String username);
	
	
	/**
	 * ������ƵId��ѯ�ۿ���¼���Ƿ��м�¼
	 * @param vid
	 * @return
	 */
	boolean isHaveLookNoteByVId(int vid);
	
	/**
	 * ���ݼ�¼Id���VideoNoteSerach����
	 * @param noteid
	 * @return
	 */
	VideoNoteSearch getVideoNoteSerachBynoteid(int noteid);

	/**
	 * ��ѯ�û��Ƿ�ۿ����˵�Ӱ
	 * @param vn
	 * @return
	 */
	boolean isExistNoteByUserNameAndVideoId(VideoNote vn);
	
	/**
	 * ��ӹۿ���¼
	 * @param vn
	 * @return
	 */
	boolean addVideoNote(VideoNote vn);
	
	/**
	 * �޸Ĺۿ���¼�Ĺۿ�����
	 * @param vn
	 * @return
	 */
	boolean updateNoteCreateDateByUserNameAndVideoId(VideoNote vn);
	
	/**
	 * ������Ƶʱ������ۿ���¼���߼�
	 * 1������Ѿ��ۿ����ⲿ��Ӱ���򽫸ü�¼�ļ�¼���ڸ�Ϊ����
	 * 2�����û�п����������һ��
	 * ����true/flase
	 * @param vn
	 * @return
	 */
	boolean VideoNoteInfoByPlayVideo(VideoNote vn);
	// -----------------------------------------------------------------------
}
