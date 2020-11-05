package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gx.dao.IUserDao;
import com.gx.pojo.User;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;

public class UserDaoImpl implements IUserDao{
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	@Override
	public User findUserByName(String name) {
		String sql = "select * from PW_User where UserNum=?";
		User users = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			users=JdbcHelper.getSingleResult(rs, User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return users;
	}

}
