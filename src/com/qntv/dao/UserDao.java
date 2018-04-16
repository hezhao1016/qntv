package com.qntv.dao;

import com.qntv.entity.User;

public interface UserDao {

	// ----------------------------------��ƺ�-----------------------------------

	/**
	 * ����û�
	 */
	public boolean addUser(User u);

	/**
	 * ��֤�û���¼
	 */
	public User login(String name, String pwd);

	/**
	 * ����ԭ�����ȡ ����
	 */
	public User getPassword(String pwd);

	/**
	 * �����û�����ȡ��ǰ�û�
	 */
	public User getUserByUsername(String name);

	/**
	 * �����û����ʹ����ж��Ƿ������
	 */
	public String getMima(String name, String answer);

	/**
	 * �ж��û����Ƿ����
	 */
	boolean isExistUserName(String name);

	/**
	 * ���ݾ�������û��� �޸�������
	 */
	boolean updatePassword(String oldPwd, String newPwd, String username);

	/**
	 * �޸��û���Ϣ
	 */
	boolean updateUser(User u);

	// -------------------------------------------------------------------------

	
	
	
	
	// --------------------------------����---------------------------------------
	
	/**
	 * ����û�����
	 * 
	 * @param username
	 * @return
	 */
	boolean addScoreByUserName(String username, int score);
	// -------------------------------------------------------------------------
}
