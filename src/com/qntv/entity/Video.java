package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 视频类
 * @author user
 *
 */
public class Video {
	private int videoid;//id
	private String videoname;//视频名称 	 	
	private String videopath;//视频地址
	private String picturepath;//封面图片地址
	private int videoclassid;//视频类别	
	private String uploadname;//上传人员
	private String createdate;//上传时间
	private String describe;//视频描述
	private String size;//视频大小
	private int	playcount;//播放次数
	private String director;//导演
	private String actor;//主演
	private String zone;//地区
	private String years;//年代
	private String title;//标题
	
	
	public int getVideoid() {
		return videoid;
	}
	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	public String getVideoname() {
		return videoname;
	}
	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}
	public String getVideopath() {
		return videopath;
	}
	public void setVideopath(String videopath) {
		this.videopath = videopath;
	}
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
	public int getVideoclassid() {
		return videoclassid;
	}
	public void setVideoclassid(int videoclassid) {
		this.videoclassid = videoclassid;
	}
	public String getUploadname() {
		return uploadname;
	}
	public void setUploadname(String uploadname) {
		this.uploadname = uploadname;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getPlaycount() {
		return playcount;
	}
	public void setPlaycount(int playcount) {
		this.playcount = playcount;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Video(){
		
	}
	public Video(ResultSet rs){
		try {
			String createdate = rs.getString("createdate").substring(0,19);//对日期进行处理
			setCreatedate(createdate);
			setActor(rs.getString("actor"));
			setDescribe(rs.getString("describe"));
			setDirector(rs.getString("director"));
			setPicturepath(rs.getString("picturepath"));
			setPlaycount(rs.getInt("playcount"));
			setSize(rs.getString("size"));
			setUploadname(rs.getString("uploadname"));
			setVideoclassid(rs.getInt("videoclassid"));
			setVideoid(rs.getInt("videoid"));
			setVideoname(rs.getString("videoname"));
			setVideopath(rs.getString("videopath"));
			setYears(rs.getString("years"));
			setZone(rs.getString("zone"));
			setTitle(rs.getString("title"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
