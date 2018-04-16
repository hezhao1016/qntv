package com.qntv.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qntv.dao.BaseDao;
import com.qntv.dao.VideoClassDao;
import com.qntv.entity.VideoClass;

public class VideoClassDaoImp extends BaseDao implements VideoClassDao{

	// ----------------------------------何钊-----------------------------------
	 /**
	 * 获得所有视频类型
	 * @return
	 */
	public List<VideoClass> getAllVideoClass(){
		String sql = "select * from VideoClass";
		rs = select(sql, null);
		List<VideoClass> vcs = new ArrayList<VideoClass>();
		try {
			while(rs.next()){
				VideoClass vc = new VideoClass(rs);
				vcs.add(vc);
			}
			return vcs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}
	
	/**
	 * 根据Id获得视频类型
	 * @param cid
	 * @return
	 */
	public VideoClass getVideoClassById(int cid){
		String sql = "select * from VideoClass where classid=?";
		rs = select(sql, new Object[]{cid});
		try {
			while(rs.next()){
				VideoClass vc = new VideoClass(rs);
				return vc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}
	
	/**
	 * 添加视频类型
	 * @param vc
	 * @return
	 */
	public boolean addVideoClass(VideoClass vc){
		String sql = "insert VideoClass Values(null,?)";
		int result = update(sql, new Object[]{vc.getClassname()});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据Id修改视频类型
	 * @param vc
	 * @return
	 */
	public boolean updateVideoClassById(VideoClass vc){
		String sql = "update VideoClass set classname=? where classid=?";
		int result = update(sql, new Object[]{vc.getClassname(),vc.getClassid()});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据Id删除视频类型
	 * @param cid
	 * @return
	 */
	public boolean delVideoClassById(int cid){
		String sql = "delete from VideoClass where classid=?";
		int result = update(sql, new Object[]{cid});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	//----------2015-3-5 项目框架整合好之后写的代码------------

	/**
	 * 根据类型名称获得视频类型
	 * @param cName
	 * @return
	 */
	public VideoClass getVideoClassByClassName(String cName) {
		String sql = "select * from VideoClass where classname=?";
		rs = select(sql, new Object[]{cName});
		try {
			while(rs.next()){
				VideoClass vc = new VideoClass(rs);
				return vc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 根据Id与Name查询是否存在此类型
	 * @param vc
	 * @return
	 */
	public boolean isExistVideoClassByIdAndName(VideoClass vc) {
		String sql = "select * from VideoClass where classname=? and classid <> ?";
		rs = select(sql, new Object[]{vc.getClassname(),vc.getClassid()});
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return false;
	}
	//-------------------------------------------------------------------
}
