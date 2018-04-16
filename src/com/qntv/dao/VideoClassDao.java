package com.qntv.dao;

import java.util.List;

import com.qntv.entity.Video;
import com.qntv.entity.VideoClass;

public interface VideoClassDao {
	
	// ----------------------------------����-----------------------------------
	/**
	 * ���������Ƶ����
	 * @return
	 */
	List<VideoClass> getAllVideoClass();
	
	/**
	 * ����Id�����Ƶ����
	 * @param cid
	 * @return
	 */
	VideoClass getVideoClassById(int cid);
	
	/**
	 * �����Ƶ����
	 * @param vc
	 * @return
	 */
	boolean addVideoClass(VideoClass vc);
	
	/**
	 * ����Id�޸���Ƶ����
	 * @param vc
	 * @return
	 */
	boolean updateVideoClassById(VideoClass vc);
	
	/**
	 * ����Idɾ����Ƶ����
	 * @param cid
	 * @return
	 */
	boolean delVideoClassById(int cid);

//----------2015-3-5 ��Ŀ������Ϻ�֮��д�Ĵ���------------
	
	/**
	 * �����������ƻ����Ƶ����
	 * @param cName
	 * @return
	 */
	VideoClass getVideoClassByClassName(String cName);
	
	/**
	 * ����Id��Name��ѯ�Ƿ���ڴ�����
	 * @param vc
	 * @return
	 */
	boolean isExistVideoClassByIdAndName(VideoClass vc);
	//-----------------------------------------------------------------------
}
