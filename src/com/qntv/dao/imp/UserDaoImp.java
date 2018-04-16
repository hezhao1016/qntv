package com.qntv.dao.imp;

import java.sql.SQLException;

import com.qntv.dao.BaseDao;
import com.qntv.dao.UserDao;
import com.qntv.entity.User;

public class UserDaoImp extends BaseDao implements UserDao {

	// ----------------------------------��ƺ�-----------------------------------
	/**
	 * ����û�
	 */
	public boolean addUser(User u) {
		String sql = "Insert User values(?,?,?,?,?,?,?,0,0,?,?)";
		int result = update(
				sql,
				new Object[] { u.getUsername(), u.getPassword(),
						u.getNickname(), u.getPicturepath(), u.getBirthday(),
						u.getAdress(), u.getPhone(), u.getPwdid(),
						u.getAnswer() });
		if (result > 0)
			return true;
		else
			return false;
	}
	/**
	 * ��֤�û���¼
	 */
	public User login(String name, String pwd) {
		String sql = "select * from User  where username =? and password =?";
		rs = select(sql, new Object[] { name, pwd });
		try {
			if (rs.next()) {
				User u = new User(rs);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * ����ԭ�����ȡ ����
	 */
	public User getPassword(String pwd) {
		String sql = "select * from User where password = ?";
		rs = select(sql, new Object[] { pwd });
		try {
			if (rs.next()) {
				User u = new User(rs);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	/**
	 * �����û�����ȡ��ǰ�û�
	 */
	public User getUserByUsername(String name) {
		String sql = "Select * from User where username=?";
		rs = select(sql, new Object[] { name });
		try {
			if (rs.next()) {
				User u = new User(rs);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * �����û����ʹ����ж��Ƿ������
	 */
	public String getMima(String name, String answer) {
		String sql = "Select * from User where username=? and answer=?";
		rs = select(sql, new Object[] { name, answer });
		try {
			if (rs.next()) {
				return rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	/**
	 * �ж��û����Ƿ����
	 */
	public boolean isExistUserName(String name) {
		String sql = "select * from User where username=?";
		rs = select(sql, new Object[] { name });
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	/**
	 * ���ݾ�������û��� �޸�������
	 */
	public boolean updatePassword(String oldPwd, String newPwd, String username) {
		String sql = "update User set password=? where username=? and password=?";
		int result = update(sql, new Object[] { newPwd, username, oldPwd });
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * �޸��û���Ϣ
	 */
	public boolean updateUser(User u) {
		String sql = " update User set nickname=?,picturepath=?,birthday=?,adress=?,phone=?,pwdid=?,answer=? where username=? ";
		int result = update(
				sql,
				new Object[] { u.getNickname(), u.getPicturepath(),
						u.getBirthday(), u.getAdress(), u.getPhone(),
						u.getPwdid(), u.getAnswer(), u.getUsername() });
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	// -------------------------------------------------------------------------

	// --------------------------------����---------------------------------------
	/**
	 * ����û�����
	 * @param username
	 * @return
	 */
	public boolean addScoreByUserName(String username, int score) {
		String sql = "update User set score=score+? where username=?";
		int result = update(sql, new Object[] { score, username });
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
	// -------------------------------------------------------------------------
}
