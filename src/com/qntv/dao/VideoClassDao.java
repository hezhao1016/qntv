package com.qntv.dao;

import java.util.List;

import com.qntv.entity.Video;
import com.qntv.entity.VideoClass;

public interface VideoClassDao {
	
	// ----------------------------------何钊-----------------------------------
	/**
	 * 获得所有视频类型
	 * @return
	 */
	List<VideoClass> getAllVideoClass();
	
	/**
	 * 根据Id获得视频类型
	 * @param cid
	 * @return
	 */
	VideoClass getVideoClassById(int cid);
	
	/**
	 * 添加视频类型
	 * @param vc
	 * @return
	 */
	boolean addVideoClass(VideoClass vc);
	
	/**
	 * 根据Id修改视频类型
	 * @param vc
	 * @return
	 */
	boolean updateVideoClassById(VideoClass vc);
	
	/**
	 * 根据Id删除视频类型
	 * @param cid
	 * @return
	 */
	boolean delVideoClassById(int cid);

//----------2015-3-5 项目框架整合好之后写的代码------------
	
	/**
	 * 根据类型名称获得视频类型
	 * @param cName
	 * @return
	 */
	VideoClass getVideoClassByClassName(String cName);
	
	/**
	 * 根据Id与Name查询是否存在此类型
	 * @param vc
	 * @return
	 */
	boolean isExistVideoClassByIdAndName(VideoClass vc);
	//-----------------------------------------------------------------------
}
