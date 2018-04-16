package com.qntv.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qntv.dao.BaseDao;
import com.qntv.dao.UserCollectDao;
import com.qntv.entity.UserCollect;
import com.qntv.entity.UserCollectSearch;

public class UserCollectDaoImp extends BaseDao implements UserCollectDao{

	// ----------------------------------何钊-----------------------------------
	/**
	 * 添加收藏视频
	 * @param uc
	 * @return
	 */
	public boolean addCollectVideo(UserCollect uc) {
		String sql = "insert qntv.UserCollect Values(null,?,?,now())";
		int result = update(sql, new Object[]{uc.getUsername(),uc.getVideoid()});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除收藏视频
	 * @param cid
	 * @return
	 */
	public boolean delCollectVideoById(int cid) {
		String sql ="delete from qntv.UserCollect where collectid=?";
		int result = update(sql, new Object[]{cid});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据Id查询是否收藏过此视频
	 * @param cid
	 * @return
	 */
	public boolean isCollectVideoByVideoId(int vid) {
		String sql = "select * from qntv.userCollect where videoid=?";
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

//----------2015-3-5 项目框架整合好之后写的代码------------

	/**
	 * 根据用户收藏,获得视频集合，分页显示
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public List<UserCollectSearch> getAllVideosByCollect(int pageSize, int pageIndex,
			String username) {
		String sql = "select uc.collectid,uc.username,uc.collectdate,v.* from UserCollect as uc inner join Video as v on uc.videoid=v.videoid where uc.username='"+username+"' order by uc.collectdate desc LIMIT "+pageSize*(pageIndex-1)+","+pageSize;
		rs = select(sql, null);
		List<UserCollectSearch> collects = new ArrayList<UserCollectSearch>();
		try {
			while(rs.next()){
				UserCollectSearch ucs = new UserCollectSearch(rs);
				collects.add(ucs);
			}
			return collects;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 根据用户收藏,获得视频总页数
	 * @param pageSize
	 * @return
	 */
	public int getTotalPageByCollect(int pageSize, String username) {
		String sql = "select count(*) from UserCollect where username=?";
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
	 * 根据收藏Id获得UserCollectSerach对象
	 * @param collectid
	 * @return
	 */
	public UserCollectSearch getUserCollectSerachBycollectid(int collectid) {
		String sql = "select uc.collectid,uc.username,uc.collectdate,v.* from UserCollect as uc inner join Video as v on uc.videoid=v.videoid where uc.collectid=?";
		rs = select(sql, new Object[]{collectid});
		try {
			if(rs.next()){
				UserCollectSearch ucs = new UserCollectSearch(rs);
				return ucs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 根据username与videoid查询是否收藏过此电影
	 * @param username
	 * @param vid
	 * @return
	 */
	public boolean isExistCollectByUserNameAndVideoId(String username, int vid) {
		String sql = "select * from usercollect where username=? and videoid=?";
		rs = select(sql, new Object[]{username,vid});
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
	 * 清空用户收藏
	 * @return
	 */
	public boolean delAllCollectVideo(String username) {
		String sql = "delete from usercollect where username=?";
		int result = update(sql,new Object[]{username});
		if(result >= 1){
			return true;
		}else{
			return false;
		}
	}
	//----------------------------------------------------------------
}
