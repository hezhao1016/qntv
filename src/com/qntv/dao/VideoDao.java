package com.qntv.dao;

import java.util.List;

import com.qntv.entity.Video;

public interface VideoDao {
	// ----------------------------------����-----------------------------------
	/**
	 * �ϴ���Ƶ
	 * 
	 * @param v
	 * @return
	 */
	boolean uploadVedio(Video v);

	/**
	 * ����vid�޸���Ƶ��Ϣ
	 * 
	 * @param v
	 * @return
	 */
	boolean updateVedioByVedioId(Video v);

	/**
	 * ����vidɾ����Ƶ
	 * 
	 * @param vid
	 * @return
	 */
	boolean delVedioByVedioId(int vid);

	/**
	 * ����Id�����Ƶ
	 * 
	 * @param vid
	 * @return
	 */
	Video getVideoByVId(int vid);

	/**
	 * ���ݵ�ǰҳ����ÿҳ���������Ƶ����,��ҳ��ʾ
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	List<Video> getVideosBypageSizeAndpageIndex(int pageSize, int pageIndex);

	/**
	 * ����pageSize�����Ƶ��ҳ��
	 * 
	 * @param pageSize
	 * @return
	 */
	int getTotalPageByPageSize(int pageSize);

	/**
	 * ����videoclassid��ѯVideo���Ƿ���������͵���Ƶ
	 * 
	 * @param cid
	 * @return
	 */
	boolean isHaveVideoClassByVideoClassId(int cid);

	// ----------2015-3-5 ��Ŀ������Ϻ�֮��д�Ĵ���------------

	/**
	 * ���ݵ�Ӱ���ƻ����Ƶ
	 * 
	 * @param vName
	 * @return
	 */
	Video getVideoByVideoName(String vName);

	/**
	 * ����Id��Name��ѯ�Ƿ���ڵ�Ӱ
	 * 
	 * @param v
	 * @return
	 */
	boolean isExistVideoByIdAndName(Video v);

	/**
	 * ������Ƶ,��ҳ��ʾ
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @param videoClassId
	 * @param years
	 * @param zone
	 * @param videoName
	 * @return
	 */
	List<Video> getVideosByVideoClassId_Years_Zone_VideoName(int pageSize,
			int pageIndex, int videoClassId, String years, String zone,
			String videoName);

	/**
	 * ����������Ƶ,�����Ƶ��ҳ��
	 * 
	 * @param pageSize
	 * @param videoClassId
	 * @param years
	 * @param zone
	 * @param videoName
	 * @return
	 */
	int getTotalPageByVideoClassId_Years_Zone_VideoName(int pageSize,
			int videoClassId, String years, String zone, String videoName);

	// -----------------------------------------------------------------------

	
	
	
	// ----------------------------------������-----------------------------------
	/**
	 * ����pageSize��classid�����Ƶ��ҳ��
	 * by  _������ 
	 * @param pageSize
	 * @param id
	 * @return
	 */
	int getTotalPageByPageSize(int pageSize, int id);
	
	// ��ҳ��ȡ��������ߵ���Ƶ
	List getVideoFromTimeAndCount(int cid);

	// ��������ID��ȡһ���ڲ�������ߵ���Ƶ
	List getVideoByClassId(int id);

	// ��������ID��ҳ��ȡ��Ƶ
	List getVideoByClassIdAndPageindex(int pageindex, int pagesize, int id);

	// ����pageSize��÷�����Ƶ��ҳ��
	int getTotalPageByPageSizeAndClassid(int pageSize, int classid);

	// ����SQL����ȡTOP 50��Ƶ
	List getTop50VideoBySql(int classid, String actor, String zone,
			String time, String order);

	// ��ȡ7������
	List getActor();

	// ģ����ѯ
	List search(String name);

	// ���ӷ�����
	void addPlayCount(int id);
	//-------------------------------------------------------------------------
}
