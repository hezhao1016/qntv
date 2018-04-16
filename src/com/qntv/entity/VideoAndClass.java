package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Video实体扩展类_孙永杰
 * 
 * @author user
 * 
 */
public class VideoAndClass extends Video{
	
	private String classname;// 类别名称
	private boolean iscollect;//是否收藏

	public boolean getIscollect() {
		return iscollect;
	}

	public void setIscollect(boolean iscollect) {
		this.iscollect = iscollect;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	public VideoAndClass(){
		
	}
	public VideoAndClass(ResultSet rs){
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
			setClassname(rs.getString("classname"));
			setIscollect(false);//默认没有收藏
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
