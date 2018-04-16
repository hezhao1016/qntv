package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ��Ƶ��
 * @author user
 *
 */
public class Video {
	private int videoid;//id
	private String videoname;//��Ƶ���� 	 	
	private String videopath;//��Ƶ��ַ
	private String picturepath;//����ͼƬ��ַ
	private int videoclassid;//��Ƶ���	
	private String uploadname;//�ϴ���Ա
	private String createdate;//�ϴ�ʱ��
	private String describe;//��Ƶ����
	private String size;//��Ƶ��С
	private int	playcount;//���Ŵ���
	private String director;//����
	private String actor;//����
	private String zone;//����
	private String years;//���
	private String title;//����
	
	
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
