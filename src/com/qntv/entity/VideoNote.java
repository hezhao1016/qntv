package com.qntv.entity;
/**
 * �ۿ���¼��
 * @author Administrator
 *
 */
public class VideoNote {
	private int noteid;//��¼id
	private String username;//�û�����
	private int videoid;//��ƵID
	private String notedate;//�ۿ���ʱ��
	
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
