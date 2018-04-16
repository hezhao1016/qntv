package com.qntv.dao.imp;

import java.sql.SQLException;

import com.qntv.dao.BaseDao;
import com.qntv.dao.UserDao;
import com.qntv.entity.User;

public class UserDaoImp extends BaseDao implements UserDao {

	// ----------------------------------李浩浩-----------------------------------
	/**
	 * 添加用户
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
	 * 验证用户登录
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
	 * 根据原密码获取 密码
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
	 * 根据用户名获取当前用户
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
	 * 根据用户名和答案来判断是否给密码
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
	 * 判断用户名是否存在
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
	 * 根据旧密码和用户名 修改新密码
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
	 * 修改用户信息
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

	// --------------------------------何钊---------------------------------------
	/**
	 * 添加用户积分
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
