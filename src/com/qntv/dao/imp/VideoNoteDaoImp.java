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

	// ----------------------------------����-----------------------------------
	/**
	 * �����û���ȡ����ۿ���¼����ҳ��ʾ
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
	 * �����û���ȡ����ۿ���¼����ҳ��
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
	 * ����Idɾ���û��ۿ���¼
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
	 * ������ƵId��ѯ�ۿ���¼���Ƿ��м�¼
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
	 * ���ݼ�¼Id���VideoNoteSerach����
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
	 * ����û��ۿ���¼
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
	 * ��ѯ�û��Ƿ�ۿ����˵�Ӱ
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
	 * ��ӹۿ���¼
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
	 * �޸Ĺۿ���¼�Ĺۿ�����
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
	 * ������Ƶʱ������ۿ���¼���߼�
	 * 1������Ѿ��ۿ����ⲿ��Ӱ���򽫸ü�¼�ļ�¼���ڸ�Ϊ����
	 * 2�����û�п����������һ��
	 * ����true/flase
	 * @param vn
	 * @return
	 */
	public boolean VideoNoteInfoByPlayVideo(VideoNote vn) {
		boolean result = false;
		//�������
		if(isExistNoteByUserNameAndVideoId(vn)){
			result = updateNoteCreateDateByUserNameAndVideoId(vn);
		}else{
		//û����
			result = addVideoNote(vn);
		}
		return result;
	}
	// -----------------------------------------------------------------------
}
