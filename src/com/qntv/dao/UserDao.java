package com.qntv.dao;

import com.qntv.entity.User;

public interface UserDao {

	// ----------------------------------李浩浩-----------------------------------

	/**
	 * 添加用户
	 */
	public boolean addUser(User u);

	/**
	 * 验证用户登录
	 */
	public User login(String name, String pwd);

	/**
	 * 根据原密码获取 密码
	 */
	public User getPassword(String pwd);

	/**
	 * 根据用户名获取当前用户
	 */
	public User getUserByUsername(String name);

	/**
	 * 根据用户名和答案来判断是否给密码
	 */
	public String getMima(String name, String answer);

	/**
	 * 判断用户名是否存在
	 */
	boolean isExistUserName(String name);

	/**
	 * 根据旧密码和用户名 修改新密码
	 */
	boolean updatePassword(String oldPwd, String newPwd, String username);

	/**
	 * 修改用户信息
	 */
	boolean updateUser(User u);

	// -------------------------------------------------------------------------

	
	
	
	
	// --------------------------------何钊---------------------------------------
	
	/**
	 * 添加用户积分
	 * 
	 * @param username
	 * @return
	 */
	boolean addScoreByUserName(String username, int score);
	// -------------------------------------------------------------------------
}
