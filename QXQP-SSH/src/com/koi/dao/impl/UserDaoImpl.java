package com.koi.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.IUserDao;
import com.koi.po.PwUser;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	private String findUserByName = "select * from PW_User where UserNum=?";

	@Override
	public PwUser findUserByName(String name) {
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery=session.createSQLQuery(findUserByName).addEntity(PwUser.class);
		sqlQuery.setString(0, name);
		PwUser pwUser=(PwUser) sqlQuery.uniqueResult();
		return pwUser;
	}
}
