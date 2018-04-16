package com.qntv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qntv.util.PropsUtil;

public class BaseDao {
	Connection conn = null;
	protected ResultSet rs = null;
	PreparedStatement ps = null;
	String driver;
	String url;
	String username;
	String password;
	//查询所用
	public String last_classid = " and 1=1";
	public String last_actor = " and 1=1";
	public String last_zone = " and 1=1";
	public String last_time = " and 1=1";
	public String last_order = " and 1=1";
	
	{
		PropsUtil propsUtil = new PropsUtil("config.properties");
		
		url = propsUtil.getString("jdbc.url");
		driver = propsUtil.getString("jdbc.driver");
		username = propsUtil.getString("jdbc.username");
		password = propsUtil.getString("jdbc.password");
	}
	
	/**
	 * 获得链接
	 */
	public void getConnection() {
		//1、JDBC方式链接数据库
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//2、数据连接池链接数据库
		/*try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/qntv");
			this.conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}

	/**
	 * 释放资源
	 */
	public void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询通用
	 * @param sql
	 * @param o
	 * @return
	 */
	public ResultSet select(String sql, Object[] o) {
		getConnection();
		try {
			ps = conn.prepareStatement(sql);
			if (o != null) {
				for (int i = 0; i < o.length; i++) {
					ps.setObject(i + 1, o[i]);
				}
			}
			System.out.println("==========="+sql);
			if(o!= null && o.length > 0){
				System.out.println("参数：");
				for (int i = 0; i < o.length; i++) {
					if(i == o.length-1){
						System.out.print(o[i]+"\n");
					}else{
						System.out.print(o[i]+",");
					}
				}
			}
			
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 增删改通用
	 * @param sql
	 * @param o
	 * @return
	 */
	public int update(String sql, Object[] o) {
		getConnection();
		try {
			ps = conn.prepareStatement(sql);
			if (o != null) {
				for (int i = 0; i < o.length; i++) {
					ps.setObject(i + 1, o[i]);
				}
			}
			System.out.println("==========="+sql);
			if(o!= null && o.length > 0){
				System.out.println("参数：");
				for (int i = 0; i < o.length; i++) {
					if(i == o.length-1){
						System.out.print(o[i]+"\n");
					}else{
						System.out.print(o[i]+",");
					}
				}
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return -1;
	}
}
