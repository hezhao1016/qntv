package com.qntv.entity;
/**
 * �û��ղ���
 * @author user
 *
 */
public class UserCollect {
	private int collectid;//�ղ�ID
	private String username;//�û�����
	private int videoid;//��ƵID
	private String collectdate;//�ղ�ʱ��

	public int getCollectid() {
		return collectid;
	}
	public void setCollectid(int collectid) {
		this.collectid = collectid;
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
	public String getCollectdate() {
		return collectdate;
	}
	public void setCollectdate(String collectdate) {
		this.collectdate = collectdate;
	}
}
