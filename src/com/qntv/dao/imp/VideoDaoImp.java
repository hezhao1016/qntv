package com.qntv.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qntv.dao.BaseDao;
import com.qntv.dao.VideoDao;
import com.qntv.entity.Video;
import com.qntv.entity.VideoAndClass;

public class VideoDaoImp extends BaseDao implements VideoDao{

	// ----------------------------------何钊-----------------------------------
	/**
	 * 上传视频
	 * @param v
	 * @return
	 */
	public boolean uploadVedio(Video v) {
		String sql = "insert Video Values(null,?,?,?,?,?,now(),?,?,0,?,?,?,?,?)";
		Object [] prams = {v.getVideoname(),v.getVideopath(),v.getPicturepath(),
				v.getVideoclassid(),v.getUploadname(),v.getDescribe(),v.getSize(),
				v.getDirector(),v.getActor(),v.getZone(),v.getYears(),v.getTitle()};
		int result = update(sql, prams);
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据vid修改视频信息
	 * @param v
	 * @return
	 */
	public boolean updateVedioByVedioId(Video v) {
		String sql = "update Video set videoname=?,videopath=?,picturepath=?," +
				"videoclassid=?,uploadname=?,createdate=now(),`describe`=?," +
				"size=?,director=?,actor=?,zone=?,years=?,title=? where videoid=?";
		Object [] prams = {v.getVideoname(),v.getVideopath(),v.getPicturepath(),
				v.getVideoclassid(),v.getUploadname(),v.getDescribe(),v.getSize(),
				v.getDirector(),v.getActor(),v.getZone(),v.getYears(),v.getTitle(),v.getVideoid()};
		int result = update(sql, prams);
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据vid删除视频
	 * @param vid
	 * @return
	 */
	public boolean delVedioByVedioId(int vid) {
		String sql = "delete from Video where videoid=? and videoid not in(select videoid from UserCollect)";
		int result = update(sql, new Object[]{vid});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据Id获得视频
	 * @param vid
	 * @return
	 */
	public Video getVideoByVId(int vid) {
		String sql = "select * from Video where videoid=?";
		rs = select(sql, new Object[]{vid});
		try {
			while(rs.next()){
				Video v = new Video(rs);
				return v;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 根据当前页数与每页数量获得视频集合,分页显示
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public List<Video> getVideosBypageSizeAndpageIndex(int pageSize,
			int pageIndex) {
		String sql = "select * from Video order by createdate desc LIMIT "+pageSize*(pageIndex-1)+","+pageSize;
		rs = select(sql, null);
		List<Video> videos = new ArrayList<Video>();
		try {
			while(rs.next()){
				Video v = new Video(rs);
				videos.add(v);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 根据pageSize获得视频总页数
	 * @param pageSize
	 * @return
	 */
	public int getTotalPageByPageSize(int pageSize) {
		String sql = "select count(*) from Video";
		rs = select(sql, null);
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
	 * 根据videoclassid查询Video表是否包含此类型的视频
	 * @param cid
	 * @return
	 */
	public boolean isHaveVideoClassByVideoClassId(int cid) {
		String sql = "select * from Video where videoclassid=?";
		rs = select(sql, new Object[]{cid});
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

//---------------2015-3-5 项目框架整合好之后写的代码------------------
	
	/**
	 * 根据电影名称获得视频
	 * @param vName
	 * @return
	 */
	public Video getVideoByVideoName(String vName) {
		String sql = "select * from Video where videoname=?";
		rs = select(sql, new Object[]{vName});
		try {
			while(rs.next()){
				Video v = new Video(rs);
				return v;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 根据Id与Name查询是否存在电影
	 * @param v
	 * @return
	 */
	public boolean isExistVideoByIdAndName(Video v) {
		String sql = "select * from Video where videoname=? and videoid <> ?";
		rs = select(sql, new Object[]{v.getVideoname(),v.getVideoid()});
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
	 * 搜索视频,分页显示
	 * @param pageSize
	 * @param pageIndex
	 * @param videoClassId
	 * @param years
	 * @param zone
	 * @param videoName
	 * @return
	 */
	public List<Video> getVideosByVideoClassId_Years_Zone_VideoName(
			int pageSize, int pageIndex, int videoClassId, String years,
			String zone, String videoName) {
		String sql = "select * from Video where 1=1 ";
		if(videoClassId!=-1){
			sql+=" and videoclassid="+videoClassId;
		}
		if(years!=null){
			sql+=" and years='"+years+"' ";
		}
		if(zone!=null){
			sql+=" and zone='"+zone+"' ";
		}
		if(videoName!=null){
			sql+=" and videoname like '%"+videoName+"%' ";
		}
		sql+="  order by createdate desc LIMIT "+pageSize*(pageIndex-1)+","+pageSize;
		rs = select(sql, null);
		List<Video> videos = new ArrayList<Video>();
		try {
			while(rs.next()){
				Video v = new Video(rs);
				videos.add(v);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return null;
	}

	/**
	 * 根据搜索视频,获得视频总页数
	 * @param pageSize
	 * @param videoClassId
	 * @param years
	 * @param zone
	 * @param videoName
	 * @return
	 */
	public int getTotalPageByVideoClassId_Years_Zone_VideoName(
			int pageSize, int videoClassId, String years, String zone,
			String videoName) {
		String sql = "select count(*) from Video where 1=1 ";
		if(videoClassId!=-1){
			sql+=" and videoclassid="+videoClassId;
		}
		if(years!=null){
			sql+=" and years='"+years+"' ";
		}
		if(zone!=null){
			sql+=" and zone='"+zone+"' ";
		}
		if(videoName!=null){
			sql+=" and videoname like '%"+videoName+"%' ";
		}
		rs = select(sql, null);
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
	// -----------------------------------------------------------------------
	
	
	//----------------------------------孙永杰----------------------------------
	/**
	 * 根据pageSize与classid获得视频总页数
	 * by  _孙永杰 
	 * @param pageSize
	 * @param id
	 * @return
	 */
	public int getTotalPageByPageSize(int pageSize, int id) {
		String sql = "";
		if (id == -1) {
			sql = "select count(*) from Video";
		} else {
			sql = "select count(*) from Video where videoclassid = "
					+ id;
		}

		rs = select(sql, null);
		try {
			rs.next();
			int count = rs.getInt(1);
			int totalPage = count % pageSize == 0 ? count / pageSize : count
					/ pageSize + 1;
			if (totalPage < 1) {
				totalPage = 1;
			}
			return totalPage;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return 0;
	}
	
	/**
	 * 分页获取播放量最高的视频
	 */
	public List getVideoFromTimeAndCount(int cid) {
		String sql = "";
		if (cid == -1) {
			sql = "SELECT * FROM Video  order by playcount desc LIMIT 0,7";
			rs = select(sql, null);
		} else {
			sql = "SELECT * FROM Video where videoclassid = ? order by playcount desc LIMIT 0,7";
			rs = select(sql, new Object[] { cid });
		}
		//System.out.println(sql);
		List videos = new ArrayList();
		try {
			while (rs.next()) {
				Video v = new Video(rs);
				videos.add(v);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * 根据类型ID获取一周内播放量最高的视频
	 */
	public List getVideoByClassId(int id) {
		String sql = "SELECT * FROM Video   where videoclassid=?  ORDER BY playcount DESC limit 0,7";
		rs = select(sql, new Object[] { id });
		List list = new ArrayList();
		try {
			while (rs.next()) {
				Video v = new Video(rs);
				list.add(v);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * 根据类型ID分页获取视频
	 */
	public List getVideoByClassIdAndPageindex(int pageindex, int pagesize,
			int id) {
		String sql = "";
		if (id == -1) {
			sql = "select * from Video  order by createdate desc LIMIT "+pagesize*(pageindex-1)+","+pagesize;
		} else {
			sql = "select * from Video where  videoclassid= "
					+ id
					+  " order by createdate desc LIMIT "+pagesize*(pageindex-1)+","+pagesize;
		}

		rs = select(sql, null);
		List<Video> videos = new ArrayList<Video>();
		try {
			while (rs.next()) {
				Video v = new Video(rs);
				videos.add(v);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * 根据pageSize获得分类视频总页数
	 * @param pageSize
	 * @return
	 */
	public int getTotalPageByPageSizeAndClassid(int pageSize) {
		String sql = "select count(*) from Video";
		rs = select(sql, null);
		try {
			rs.next();
			int count = rs.getInt(1);
			int totalPage = count % pageSize == 0 ? count / pageSize : count
					/ pageSize + 1;
			if (totalPage < 1) {
				totalPage = 1;
			}
			return totalPage;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return 0;
	}
	
	public int getTotalPageByPageSizeAndClassid(int pageSize, int classid) {
		String sql = "select count(*) from Video where videoclassid = "
				+ classid;
		rs = select(sql, null);
		try {
			rs.next();
			int count = rs.getInt(1);
			int totalPage = count % pageSize == 0 ? count / pageSize : count
					/ pageSize + 1;
			if (totalPage < 1) {
				totalPage = 1;
			}
			return totalPage;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return 0;
	}
	/**
	 * 根据SQL语句获取TOP 50视频
	 * @param sql
	 * @return
	 */
	public List getTop50VideoBySql(String sql) {
		rs = select(sql, null);
		List<Video> videos = new ArrayList<Video>();
		try {
			while (rs.next()) {
				Video v = new Video(rs);
				videos.add(v);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * 获取7个明星
	 */
	public List getActor() {
		String sql = "select * from video   order by playcount desc limit 0,7";
		rs = select(sql, null);
		List<Video> videos = new ArrayList<Video>();
		try {
			while (rs.next()) {
				Video v = new Video(rs);
				videos.add(v);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * 模糊查询
	 */
	public List search(String name) {
		String sql = "select * from Video as v inner join VideoClass as vc on v.videoclassid = vc.classid  where videoname like '%"
				+ name + "%' OR VC.CLASSNAME ='"+name+"' OR director LIKE '%"+name+"%' OR actor LIKE '%"+name+"%' OR zone ='"+name+"' OR years ='"+name+"'";
		rs = select(sql, null);
		List<VideoAndClass> videos = new ArrayList<VideoAndClass>();
		try {
			while (rs.next()) {
				VideoAndClass v = new VideoAndClass(rs);
				videos.add(v);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * 增加访问量
	 */
	public void addPlayCount(int id) {
		String sql = "update Video set playcount = playcount+1 where videoid = ?";
		update(sql, new Object[] { id });
	}

	public List getTop50VideoBySql(int classid, String actor, String zone,
			String time, String order) {
		String sql = "SELECT * FROM Video WHERE 1=1 ";
		if (classid != -1) {
			if (classid == 0) {
				sql += " AND 1 = 1";
				last_classid = " AND 1=1";
			} else {
				sql += " AND videoclassid = " + classid;
				last_classid = " AND videoclassid =" + classid;
			}
		} else {
			sql += last_classid;
		}
		if (!"".equals(actor)) {
			sql += " AND actor = " + actor;
			last_actor = " AND actor =" + actor;
		} else {
			sql += last_actor;
		}
		if (!"".equals(zone)) {
			if (!"全部".equals(zone)) {
				sql += " AND zone =  '" + zone + "'";
				last_zone = " and zone='" + zone + "'";
			} else {
				sql += " and 1=1";
				last_zone = " and 1=1";
			}
		} else {
			sql += last_zone;
		}
		if (!"".equals(time)) {
			sql += " AND " + time;
			last_time = " and " + time;
		} else {
			sql += last_time;
		}
		if (!"".equals(order)) {
			sql += " order by " + order;
			last_order = " order by " + order;
		} else {
			sql += last_order;
		}
		sql+=" limit 0,7";
		//System.out.println(sql);
		rs = select(sql, null);
		List<Video> videos = new ArrayList<Video>();
		//System.out.println(sql);
		try {
			while (rs.next()) {
				Video v = new Video(rs);
				videos.add(v);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
}
