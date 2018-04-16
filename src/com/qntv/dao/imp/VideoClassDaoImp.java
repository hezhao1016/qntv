package com.qntv.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qntv.dao.BaseDao;
import com.qntv.dao.VideoClassDao;
import com.qntv.entity.VideoClass;

public class VideoClassDaoImp extends BaseDao implements VideoClassDao{

	// ----------------------------------����-----------------------------------
	 /**
	 * ���������Ƶ����
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
	 * ����Id�����Ƶ����
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
	 * �����Ƶ����
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
	 * ����Id�޸���Ƶ����
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
	 * ����Idɾ����Ƶ����
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

	//----------2015-3-5 ��Ŀ������Ϻ�֮��д�Ĵ���------------

	/**
	 * �����������ƻ����Ƶ����
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
	 * ����Id��Name��ѯ�Ƿ���ڴ�����
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
