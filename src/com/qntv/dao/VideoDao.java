package com.qntv.dao;

import java.util.List;

import com.qntv.entity.Video;

public interface VideoDao {
	// ----------------------------------何钊-----------------------------------
	/**
	 * 上传视频
	 * 
	 * @param v
	 * @return
	 */
	boolean uploadVedio(Video v);

	/**
	 * 根据vid修改视频信息
	 * 
	 * @param v
	 * @return
	 */
	boolean updateVedioByVedioId(Video v);

	/**
	 * 根据vid删除视频
	 * 
	 * @param vid
	 * @return
	 */
	boolean delVedioByVedioId(int vid);

	/**
	 * 根据Id获得视频
	 * 
	 * @param vid
	 * @return
	 */
	Video getVideoByVId(int vid);

	/**
	 * 根据当前页数与每页数量获得视频集合,分页显示
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	List<Video> getVideosBypageSizeAndpageIndex(int pageSize, int pageIndex);

	/**
	 * 根据pageSize获得视频总页数
	 * 
	 * @param pageSize
	 * @return
	 */
	int getTotalPageByPageSize(int pageSize);

	/**
	 * 根据videoclassid查询Video表是否包含此类型的视频
	 * 
	 * @param cid
	 * @return
	 */
	boolean isHaveVideoClassByVideoClassId(int cid);

	// ----------2015-3-5 项目框架整合好之后写的代码------------

	/**
	 * 根据电影名称获得视频
	 * 
	 * @param vName
	 * @return
	 */
	Video getVideoByVideoName(String vName);

	/**
	 * 根据Id与Name查询是否存在电影
	 * 
	 * @param v
	 * @return
	 */
	boolean isExistVideoByIdAndName(Video v);

	/**
	 * 搜索视频,分页显示
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
	 * 根据搜索视频,获得视频总页数
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

	
	
	
	// ----------------------------------孙永杰-----------------------------------
	/**
	 * 根据pageSize与classid获得视频总页数
	 * by  _孙永杰 
	 * @param pageSize
	 * @param id
	 * @return
	 */
	int getTotalPageByPageSize(int pageSize, int id);
	
	// 分页获取播放量最高的视频
	List getVideoFromTimeAndCount(int cid);

	// 根据类型ID获取一周内播放量最高的视频
	List getVideoByClassId(int id);

	// 根据类型ID分页获取视频
	List getVideoByClassIdAndPageindex(int pageindex, int pagesize, int id);

	// 根据pageSize获得分类视频总页数
	int getTotalPageByPageSizeAndClassid(int pageSize, int classid);

	// 根据SQL语句获取TOP 50视频
	List getTop50VideoBySql(int classid, String actor, String zone,
			String time, String order);

	// 获取7个明星
	List getActor();

	// 模糊查询
	List search(String name);

	// 增加访问量
	void addPlayCount(int id);
	//-------------------------------------------------------------------------
}
