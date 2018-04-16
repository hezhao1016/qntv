package com.qntv.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qntv.dao.BaseDao;
import com.qntv.dao.VideoNoteDao;
import com.qntv.entity.Video;
import com.qntv.entity.VideoNote;
import com.qntv.entity.VideoNoteSearch;

public class VideoNoteDaoImp extends BaseDao implements VideoNoteDao{

	// ----------------------------------何钊-----------------------------------
	/**
	 * 根据用户获取最近观看记录，分页显示
	 * @param pageSize
	 * @param pageIndex
	 * @param username
	 * @return
	 */
	public List<VideoNoteSearch> getAllVideoNoteByUserName(int pageSize,
			int pageIndex, String username) {
		String sql = "select vn.noteid,vn.username,vn.notedate,v.* from VideoNote as vn inner join Video as v on vn.videoid=v.videoid  where vn.username='"+username+"' order by vn.notedate desc LIMIT "+pageSize*(pageIndex-1)+","+pageSize;
		rs = select(sql, null);
		List<VideoNoteSearch> notes = new ArrayList<VideoNoteSearch>();
		try {
			while(rs.next()){
				VideoNoteSearch vns = new VideoNoteSearch(rs);
				notes.add(vns);
			}
			return notes;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 根据用户获取最近观看记录的总页数
	 * @param pageSize
	 * @param username
	 * @return
	 */
	public int getTotalPageByNote(int pageSize, String username) {
		String sql = "select count(*) from VideoNote where username=?";
		rs = select(sql, new Object[]{username});
		try {
			rs.next();
			int count = rs.getInt(1);
			int totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
			if(totalPage<1){
				totalPage=1;
			}
			return totalPage;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return 0;
	}

	/**
	 * 根据Id删除用户观看记录
	 * @param vnid
	 * @return
	 */
	public boolean delVideoNoteById(int vnid) {
		String sql = "delete from videonote where noteid=?";
		int result = update(sql, new Object[]{vnid});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据视频Id查询观看记录表是否有记录
	 * @param vid
	 * @return
	 */
	public boolean isHaveLookNoteByVId(int vid) {
		String sql = "select * from videonote where videoid=?";
		rs = select(sql, new Object[]{vid});
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

	/**
	 * 根据记录Id获得VideoNoteSerach对象
	 * @param noteid
	 * @return
	 */
	public VideoNoteSearch getVideoNoteSerachBynoteid(int noteid) {
		String sql = "select vn.noteid,vn.username,vn.notedate,v.* from VideoNote as vn inner join Video as v on vn.videoid=v.videoid where vn.noteid=?";
		rs = select(sql, new Object[]{noteid});
		try {
			if(rs.next()){
				VideoNoteSearch vns = new VideoNoteSearch(rs);
				return vns;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 清空用户观看记录
	 * @return
	 */
	public boolean delAllVideoNote(String username) {
		String sql = "delete from videonote where username=?";
		int result = update(sql,new Object[]{username});
		if(result >= 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 查询用户是否观看过此电影
	 * @param vn
	 * @return
	 */
	public boolean isExistNoteByUserNameAndVideoId(VideoNote vn) {
		String sql = "select * from videonote where username=? and videoid=?";
		rs = select(sql, new Object[]{vn.getUsername(),vn.getVideoid()});
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

	/**
	 * 添加观看记录
	 * @param vn
	 * @return
	 */
	public boolean addVideoNote(VideoNote vn) {
		String sql = "insert videonote Values(null,?,?,now())";
		int result = update(sql, new Object[]{vn.getUsername(),vn.getVideoid()});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 修改观看记录的观看日期
	 * @param vn
	 * @return
	 */
	public boolean updateNoteCreateDateByUserNameAndVideoId(VideoNote vn) {
		String sql = "update videonote set notedate=now() where username=? and videoid=?";
		int result = update(sql, new Object[]{vn.getUsername(),vn.getVideoid()});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 播放视频时，处理观看记录的逻辑
	 * 1、如果已经观看过这部电影，则将该记录的记录日期改为现在
	 * 2、如果没有看过，则添加一条
	 * 返回true/flase
	 * @param vn
	 * @return
	 */
	public boolean VideoNoteInfoByPlayVideo(VideoNote vn) {
		boolean result = false;
		//如果看过
		if(isExistNoteByUserNameAndVideoId(vn)){
			result = updateNoteCreateDateByUserNameAndVideoId(vn);
		}else{
		//没看过
			result = addVideoNote(vn);
		}
		return result;
	}
	// -----------------------------------------------------------------------
}
