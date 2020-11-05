package com.gx.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.gx.vo.AppendOptionVo;

public class JdbcHelper {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/**
	 * 把单条数据的ResultSet的结果放到 java对象中 注意：实体类字段和数据库字段要一致
	 * 
	 * @param rs
	 *            ResultSet结果集
	 * @param c
	 *            java类的class
	 * @return
	 */
	public static <T> T getSingleResult(ResultSet rs, Class<T> obj) {
		// 创建实例
		T instance = null;
		try {
			// ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
			ResultSetMetaData metaData = rs.getMetaData();
			Field[] fields = obj.getDeclaredFields();
			// 获取总的列数
			int count = metaData.getColumnCount();
			// 遍历ResultSet
			while (rs.next()) {
				instance = obj.newInstance(); // 通过反射构造一个T类型的实例
				for (int i = 1; i <= count; i++) {

					// ---获取列名
					String name = metaData.getColumnName(i);
					for (Field field : fields) {
						if (name.equals(field.getName())) {
							// ---获取类型
							String type = obj.getDeclaredField(name)
									.getGenericType().toString();
							Method setMethod = null;
							// ---判断读取数据的类型
							if ("class java.lang.String".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										String.class);
								setMethod.invoke(instance,
										String.valueOf(rs.getString(i)).trim());
							} else if ("class java.lang.Integer".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Integer.class);
								setMethod.invoke(instance, rs.getInt(i));
							} else if ("class java.lang.Boolean".equals(type)
									|| "boolean".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Boolean.class);
								setMethod.invoke(instance, rs.getBoolean(i));
							} else if ("class java.util.Date".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										java.util.Date.class);
								setMethod.invoke(instance, rs.getTimestamp(i));
							} else if ("class java.lang.Long".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Long.class);
								setMethod.invoke(instance, rs.getLong(i));
							} else if ("class java.lang.Double".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Double.class);
								setMethod.invoke(instance, rs.getDouble(i));
							} else if ("class java.lang.Float".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Float.class);
								setMethod.invoke(instance, rs.getFloat(i));
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return instance;
	}

	/**
	 * 把多条数据的ResultSet的结果放到 List<T>中
	 * 
	 * @param rs
	 *            ResultSet结果集
	 * @param obj
	 *            java类的class
	 * @return
	 */
	public static <T> List<T> getResult(ResultSet rs, Class<T> obj) {
		List<T> list = new ArrayList<T>();
		T instance = null;
		try {
			ResultSetMetaData metaData = rs.getMetaData();// ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
			Field[] fields = obj.getDeclaredFields();
			int count = metaData.getColumnCount();// 获取总的列数
			while (rs.next()) {// 遍历ResultSet
				instance = obj.newInstance();// ---创建对象实例
				for (int i = 1; i <= count; i++) {
					String name = metaData.getColumnName(i);// ---获取列名
					for (Field field : fields) {
						if (name.equals(field.getName())) {
							String type = obj.getDeclaredField(name)
									.getGenericType().toString();// ---获取类型
							Method setMethod = null;
							// ---判断读取数据的类型
							if ("class java.lang.String".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										String.class);
								setMethod.invoke(instance,
										String.valueOf(rs.getString(i)).trim());
							} else if ("class java.lang.Integer".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Integer.class);
								setMethod.invoke(instance, rs.getInt(i));
							} else if ("class java.lang.Boolean".equals(type)
									|| "boolean".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Boolean.class);
								setMethod.invoke(instance, rs.getBoolean(i));
							} else if ("class java.util.Date".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										java.util.Date.class);
								setMethod.invoke(instance, rs.getTimestamp(i));
							} else if ("class java.lang.Long".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Long.class);
								setMethod.invoke(instance, rs.getLong(i));
							} else if ("class java.lang.Double".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Double.class);
								setMethod.invoke(instance, rs.getDouble(i));
							} else if ("class java.lang.Float".equals(type)) {
								setMethod = obj.getMethod("set" + name,
										Float.class);
								setMethod.invoke(instance, rs.getFloat(i));
							}
						}
					}
				}
				list.add(instance);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 下拉框绑定方法
	 * 
	 * @param sql
	 * @return list
	 */
	public static List<AppendOptionVo> getAppendOption(String sql) {
		List<AppendOptionVo> list = new ArrayList<AppendOptionVo>();
		AppendOptionVo kinds = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				kinds = new AppendOptionVo();
				kinds.setId(rs.getInt(1));
				kinds.setName(rs.getString(2));
				list.add(kinds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 新增 主键(默认第一个属性)
	 * 
	 * @param obj
	 *            传进来的对象
	 * @return 主键
	 */
	public static int insert(Object obj) {
		int id = 0;
		Class<?> c = obj.getClass(); // 获取obj的Class
		// 通过反射获取类名映射表名  利用StringBuffer进行插入SQL语句的构造
		StringBuffer sb1 = new StringBuffer("insert into " + c.getSimpleName()+ " (");
		StringBuffer sb2 = new StringBuffer(" values(");
		Field[] field = c.getDeclaredFields(); // 通过反射获取对象的属性数组
		for (int i = 1; i < field.length; i++) { // 遍历属性构造SQL语句
			if (i != field.length - 1) {// 拼接sql语句
				sb1.append(field[i].getName()).append(",");
				sb2.append("?,");
			} else {
				sb1.append(field[i].getName()).append(")");
				sb2.append("?);");
			}
		}
		String sql = sb1.append(sb2).toString();// sql语句
		try {
			conn = DbUtil.getConnection(); // 获取数据库连接，进行数据库操作
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);// " select @@identity"获取主键id
			for (int i = 1; i < field.length; i++) {
				field[i].setAccessible(true); // 设置属性的可访问性，可以访问私有属性
				try { // 通过Field的get(Object)方法获取Object对象的属性值
					Class<?> type = field[i].getType();// 获取类型
					if (type.isAssignableFrom(java.util.Date.class)&& field[i].get(obj) != null) {
						String vaule = String.valueOf(field[i].get(obj));
						if (vaule.indexOf("-") > 0) {// 将字符串转化为date类型，格式2016-10-12
							SimpleDateFormat format = new SimpleDateFormat(
									"yyyy-MM-dd");
							ps.setTimestamp(i, new Timestamp(format
									.parse(vaule).getTime()));
						} else {
							vaule = vaule.replace("CST", "").replaceAll(
									"\\(.*\\)", "");
							SimpleDateFormat format = new SimpleDateFormat(
									"EEE MMM dd hh:mm:ss yyyy", Locale.ENGLISH);
							ps.setTimestamp(i, new Timestamp(format
									.parse(vaule).getTime()));
						}
					} else if (type.isAssignableFrom(Integer.class)
							|| type.isAssignableFrom(Double.class)) {
						ps.setObject(i, field[i].get(obj) == null ? 0
								: field[i].get(obj));
					} else {
						ps.setObject(i, field[i].get(obj) == null ? ""
								: field[i].get(obj)); // 对预编译的SQL语句中的？进行赋值
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		   int i = ps.executeUpdate(); // 执行SQL
		   rs=ps.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getInt(1);// 返回主键
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
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
	public static int update(Object obj) {
		int r = 0;
		Class<?> c = obj.getClass();
		StringBuffer sb = new StringBuffer("update " + c.getSimpleName()
				+ " set ");
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
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 1; i < field.length; i++) {
				field[i].setAccessible(true);
				Class<?> type = field[i].getType();
				if (type.isAssignableFrom(java.util.Date.class)
						&& field[i].get(obj) != null) {
					String vaule = String.valueOf(field[i].get(obj));
					if (vaule.indexOf("-") > 0) {
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd");
						ps.setTimestamp(i, new Timestamp(format.parse(vaule)
								.getTime()));
					} else {
						vaule = vaule.replace("CST", "").replaceAll("\\(.*\\)",
								"");
						// 将字符串转化为date类型，格式2016-10-12
						SimpleDateFormat format = new SimpleDateFormat(
								"EEE MMM dd hh:mm:ss yyyy", Locale.ENGLISH);
						ps.setTimestamp(i, new Timestamp(format.parse(vaule)
								.getTime()));
					}
				} else if (type.isAssignableFrom(Integer.class)
						|| type.isAssignableFrom(Double.class)) {
					ps.setObject(i,
							field[i].get(obj) == null ? 0 : field[i].get(obj));
				} else {
					ps.setObject(i,
							field[i].get(obj) == null ? "" : field[i].get(obj)); // 对预编译的SQL语句中的？进行赋值
				}
			}
			field[0].setAccessible(true);
			ps.setObject(field.length, field[0].get(obj));
			r = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(null, ps, conn);
		}
		return r;
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
	public static boolean delete(Class<?> c, String field, Object condition) {
		boolean flag = false;
		StringBuffer sb = new StringBuffer("delete from ");
		sb.append(c.getSimpleName()).append(" where "); // 获取对象属性数组
		// 获取第一个属性的属性名构造删除sql
		sb.append(field).append("=?");
		String sql = sb.toString();
		try {
			Connection conn = DbUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, condition);
			flag = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(null, ps, conn);
		}
		return flag;
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
	public static ResultSet select(Class<?> c, String field, Object condition) {
		StringBuffer sb = new StringBuffer("select * from " + c.getSimpleName());
		sb.append(" where "); // 获取对象属性数组
		// 获取第一个属性的属性名构造删除sql
		sb.append(field).append("=?");
		String sql = sb.toString();
		try {
			Connection conn = DbUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, condition);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
