package com.gx.dao;

import com.gx.pojo.User;

public interface IUserDao {
	public User findUserByName(String name);
}
