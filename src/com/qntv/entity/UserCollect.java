package com.qntv.entity;
/**
 * 用户收藏类
 * @author user
 *
 */
public class UserCollect {
	private int collectid;//收藏ID
	private String username;//用户名称
	private int videoid;//视频ID
	private String collectdate;//收藏时间

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
