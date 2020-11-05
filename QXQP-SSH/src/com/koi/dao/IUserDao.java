package com.koi.dao;

import com.koi.po.PwUser;


public interface IUserDao {
	/**
	 * 通过用户名称查询用户信息
	 * @param name
	 * @return
	 */
	public PwUser findUserByName(String name);
}
