package com.qntv.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qntv.dao.BaseDao;
import com.qntv.dao.PasswordClassDao;
import com.qntv.entity.PasswordClass;

public class PasswordClassDaoImp extends BaseDao implements PasswordClassDao{
	
	// ----------------------------------李浩浩-----------------------------------
	/**
	 * 获取所有密保问题类别
	 * @return
	 */
	public List getAllPassword() {
		String sql = "select * from passwordclass ";
		rs = select(sql,null);
		try {
			List list = new ArrayList(){};
			while(rs.next()){
				PasswordClass pc = new PasswordClass(rs);
				list.add(pc);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();	
		}
		return null;
	}
	
	// -------------------------------------------------------------------------
	
	
}
