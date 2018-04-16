package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 密保问题类
 * @author user
 *
 */
public class PasswordClass {
	private int pwdid;//问题ID
	private String pwdname;//密保问题名称
	
	public int getPwdid() {
		return pwdid;
	}
	public void setPwdid(int pwdid) {
		this.pwdid = pwdid;
	}
	public String getPwdname() {
		return pwdname;
	}
	public void setPwdname(String pwdname) {
		this.pwdname = pwdname;
	}
	
	public PasswordClass(){
		
	}
	public PasswordClass(ResultSet rs){
		try {
			this.setPwdid((rs.getInt("pwdid")));
			this.setPwdname(rs.getString("pwdname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
