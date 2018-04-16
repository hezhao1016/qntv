package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Videoʵ����չ��_������
 * 
 * @author user
 * 
 */
public class VideoAndClass extends Video{
	
	private String classname;// �������
	private boolean iscollect;//�Ƿ��ղ�

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
			String createdate = rs.getString("createdate").substring(0,19);//�����ڽ��д���
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
			setIscollect(false);//Ĭ��û���ղ�
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
