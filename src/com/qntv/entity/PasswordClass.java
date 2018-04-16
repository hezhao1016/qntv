package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �ܱ�������
 * @author user
 *
 */
public class PasswordClass {
	private int pwdid;//����ID
	private String pwdname;//�ܱ���������
	
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
