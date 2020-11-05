package com.gx.service.impl;

import com.gx.dao.IUserDao;
import com.gx.dao.impl.UserDaoImpl;
import com.gx.pojo.User;
import com.gx.service.IUserService;

public class UserServiceImpl implements IUserService{
	private IUserDao userDao=new UserDaoImpl();
	@Override
	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

}
