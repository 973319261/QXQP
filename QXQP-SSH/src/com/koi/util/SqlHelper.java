package com.koi.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("sqlHelper")
public class SqlHelper {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	/**
	 * 新增 主键(默认第一个属性)
	 * 
	 * @param obj
	 *            传进来的对象
	 * @return 主键
	 */
	public int insert(Object obj) {
		Integer id = 0;
		Class<?> c = obj.getClass();
		session = sessionFactory.getCurrentSession();
		session.save(obj);
		try {
			Field[] field = c.getDeclaredFields();
			field[0].setAccessible(true);
			id = Integer.valueOf(field[0].get(obj).toString());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * 修改 主键(默认第一个属性)
	 * 
	 * @param obj
	 *            传进来的对象
	 * @return 主键
	 */
	public int update(Object obj) {
		int r = 0;
		Class<?> c = obj.getClass();
		StringBuffer sb = new StringBuffer("update " + formatName(c) + " set ");
		Field[] field = c.getDeclaredFields();
		for (int i = 1; i < field.length; i++) {
			if (i != field.length - 1) {
				sb.append(field[i].getName()).append("=?,");
			} else {
				sb.append(field[i].getName()).append("=? where ");
			}
		}
		sb.append(field[0].getName() + "=?");// 修改条件
		String sql = sb.toString();// sql语句

		field[0].setAccessible(true);
		try {
			session = sessionFactory.getCurrentSession();
			SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(c);
			for (int i = 1; i < field.length; i++) {
				field[i].setAccessible(true);
				Class<?> type = field[i].getType();// 获取属性类型
				String vaule = field[i].get(obj)!=null?field[i].get(obj).toString().trim():"";// 获取属性值
				System.out.println(type+"---"+vaule);
				if (type.isAssignableFrom(Integer.class)) {
					sqlQuery.setInteger(i-1, "".equals(vaule)?0:Integer.valueOf(vaule));
				} else if (type.isAssignableFrom(String.class)) {
					sqlQuery.setString(i-1, vaule);
				} else if (type.isAssignableFrom(Double.class)) {	
					sqlQuery.setDouble(i-1, "".equals(vaule)?0.0:Double.valueOf(vaule));
				} else if (type.isAssignableFrom(Boolean.class)) {
					sqlQuery.setInteger(i-1, Boolean.valueOf(vaule)==true?1:0);
				} else if (type.isAssignableFrom(Timestamp.class)||type.isAssignableFrom(java.sql.Date.class)) {
					sqlQuery.setString(i-1,vaule);
				}
			}
			sqlQuery.setLong(field.length - 1,
					Long.valueOf(field[0].get(obj).toString()));
			r = sqlQuery.executeUpdate();
		
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	
	/**
	 * 单个条件查询
	 * 
	 * @param obj
	 *            实体类对象
	 * @param field
	 *            条件字段
	 * @param condition
	 *            条件值
	 * @return
	 */
	public List<?> select(Class<?> c, String field, Object condition) {
		int rs = 0;
		StringBuffer sb = new StringBuffer("select * from " + formatName(c));
		sb.append(" where "); // 获取对象属性数组
		// 获取第一个属性的属性名构造删除sql
		sb.append(field).append("=?");
		String sql = sb.toString();
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(c);
		sqlQuery.setParameter(0, condition);
		List<Object> list = sqlQuery.list();
		return list;
	}

	/**
	 * 单个条件删除
	 * 
	 * @param obj
	 *            实体类对象
	 * @param field
	 *            条件字段
	 * @param condition
	 *            条件值
	 * @return
	 */
	public boolean delete(Class<?> c, String field, int condition) {
		boolean flag = false;
		StringBuffer sb = new StringBuffer("delete from ");
		sb.append(formatName(c)).append(" where "); // 获取对象属性数组
		// 获取第一个属性的属性名构造删除sql
		sb.append(field).append("=?");
		String sql = sb.toString();
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(c);
		sqlQuery.setInteger(0, condition);
		flag = sqlQuery.executeUpdate()==0?false:true;
		return flag;
	}
	/**
	 * 下拉框查询sql语句处理
	 * 
	 * @param str
	 * @return
	 */
	public Map sqlField(String str) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String dbName = str.replace("SYS_", "Sys");
			Class c = Class.forName("com.koi.po." + dbName);
			Field[] fields = c.getDeclaredFields();
			map.put("id", fields[0].getName());
			map.put("name", fields[1].getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 把po类名转为数据库表名
	 * @param c
	 * @return
	 */
	private String formatName(Class<?> c){
		String dbName = c.getSimpleName();
		if (dbName.startsWith("Pw")) {
			dbName = dbName.replace("Pw", "PW_");
		} else {
			dbName = dbName.replace("Sys", "SYS_");
		}
		return dbName;
	}
}
