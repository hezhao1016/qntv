package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ��Ƶ�����
 * @author user
 *
 */
public class VideoClass {
	private int classid;//ID
	private String classname;//�������
	
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public VideoClass(){
		
	}
	public VideoClass(ResultSet rs){
		try {
			this.setClassid(rs.getInt("classid"));
			this.setClassname(rs.getString("classname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
