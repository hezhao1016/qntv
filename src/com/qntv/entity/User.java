package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �û���
 * @author user
 *
 */
public class User {
	private String username;//�û���
	private String password;//����
	private String nickname;//�ǳ�
	private String picturepath;//ͷ��ͼƬ��ַ
	private String birthday;//��������
	private String adress;//��ַ
	private String phone;//�绰����
	private int isadmin;//�Ƿ��ǹ���Ա(0������ͨ�û���1�������Ա)
	private int score;//�û����֣��ۿ���Ƶ���10���֣�
	private int pwdid;//�ܱ��������ID
	private String answer;//�ܱ������
	
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
