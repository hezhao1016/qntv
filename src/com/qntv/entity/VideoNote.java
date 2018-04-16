package com.qntv.entity;
/**
 * 观看记录类
 * @author Administrator
 *
 */
public class VideoNote {
	private int noteid;//记录id
	private String username;//用户名称
	private int videoid;//视频ID
	private String notedate;//观看的时间
	
	public int getNoteid() {
		return noteid;
	}
	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getVideoid() {
		return videoid;
	}
	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	public String getNotedate() {
		return notedate;
	}
	public void setNotedate(String notedate) {
		this.notedate = notedate;
	}
	
}
