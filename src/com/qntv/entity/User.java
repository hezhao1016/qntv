package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户类
 * @author user
 *
 */
public class User {
	private String username;//用户名
	private String password;//密码
	private String nickname;//昵称
	private String picturepath;//头像图片地址
	private String birthday;//出生日期
	private String adress;//地址
	private String phone;//电话号码
	private int isadmin;//是否是管理员(0代表普通用户，1代表管理员)
	private int score;//用户积分（观看视频获得10积分）
	private int pwdid;//密保问题类别ID
	private String answer;//密保问题答案
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPwdid() {
		return pwdid;
	}
	public void setPwdid(int pwdid) {
		this.pwdid = pwdid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public User(){
		
	}
	public User(ResultSet rs){
		try {
			this.setUsername(rs.getString("username"));
			this.setPassword(rs.getString("password"));
			this.setNickname(rs.getString("nickname"));
			this.setPicturepath(rs.getString("picturepath"));
			this.setBirthday(rs.getString("birthday"));
			this.setAdress(rs.getString("adress"));
			this.setPhone(rs.getString("phone"));
			this.setIsadmin(rs.getInt("isadmin"));
			this.setScore(rs.getInt("score"));
			this.setPwdid(rs.getInt("pwdid"));
			this.setAnswer(rs.getString("answer"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
