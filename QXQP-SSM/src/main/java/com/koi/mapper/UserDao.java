package com.koi.mapper;

import com.koi.po.PwUser;


public interface UserDao {
	/**
	 * 通过用户名称查询用户信息
	 * @param name
	 * @return
	 */
	public PwUser findUserByName(String name);
}
