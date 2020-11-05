package com.gx.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	private static String url=null;
	private static String user=null;
	private static String password=null;
	private static String driver=null;
	static{
		Properties properties=new Properties();
		try {
			InputStream in= DbUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			properties.load(in);
			url=properties.getProperty("url");
			user=properties.getProperty("user");
			password=properties.getProperty("password");
			driver=properties.getProperty("driver");
			Class.forName(driver);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection () throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
	public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
