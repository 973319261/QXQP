package com.gx.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ParameterMapping {
	public static <T> T getParameter(HttpServletRequest request, Class<T> obj) {
		T instance = null;// 创建实例
		try {
			
			instance = obj.newInstance();// ---创建对象实例
			Field[] fields = obj.getDeclaredFields();// 获取实体类字段
			for (Field field : fields) {
				Class<?> type = field.getType();// 获取字段类型
				String name = field.getName();// 获取字段名称
				String strname = "set" + name;// 设置方法名称
				Method method = obj.getMethod(strname, type);// ---获取setter方法
				String para = request.getParameter(name) == null ? "" : request
						.getParameter(name);// 获取页面传过来的参数
				if (type.isAssignableFrom(String.class)) {
					method.invoke(instance, para);
				} else if (type.isAssignableFrom(int.class)
						|| type.isAssignableFrom(Integer.class)) {
					method.invoke(instance,
							Integer.parseInt("".equals(para) ? "0" : para));
				} else if (type.isAssignableFrom(boolean.class)
						|| type.isAssignableFrom(Boolean.class)) {
					method.invoke(instance, Boolean.parseBoolean(para));
				} else if (type.isAssignableFrom(Date.class)) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					method.invoke(instance,
							format.parse("".equals(para) ? "0000-00-00" : para));
				} else if (type.isAssignableFrom(double.class)
						|| type.isAssignableFrom(Double.class)) {
					method.invoke(instance,
							Double.parseDouble("".equals(para) ? "0" : para));
				} else if (type.isAssignableFrom(float.class)
						|| type.isAssignableFrom(Float.class)) {
					method.invoke(instance,
							Float.parseFloat("".equals(para) ? "0" : para));
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public static <T> T getParameterFile(HttpServletRequest request,
			Class<T> obj) {
		// 创建实例
		T instance = null;
		DiskFileItemFactory factory = new DiskFileItemFactory();// 创建文件处理工厂
		ServletFileUpload upload = new ServletFileUpload(factory);// 获取处理上传的对象
		upload.setHeaderEncoding("UTF-8");// 设置上传文件编码
		try {
			// ---创建对象实例
			instance = obj.newInstance();
			// 获取实体类字段
			Field[] fields = obj.getDeclaredFields();
			List<FileItem> formItems = upload.parseRequest(request);// 处理上传文件
			for (FileItem item : formItems) {
				String itemName = item.getFieldName();
				for (Field field : fields) {
					// 获取字段类型
					Class<?> type = field.getType();
					// 获取字段名称
					String name = field.getName();
					// 设置方法名称
					String strname = "set" + name;
					// ---获取setter方法
					Method method = obj.getMethod(strname, type);
					// isFormField 表单元素
					if (itemName.equals(name)) {
						if (!item.isFormField()) {// 判断是否文件
							byte[] para = item.get();
							if (type.isAssignableFrom(byte[].class)) {
								method.invoke(instance, para);
							}
						} else {// 没有上传文件，单单上传表单信息，修改时原来的文件会丢失
								// 获取其他字段
							String para = item.getString("UTF-8");
							if (type.isAssignableFrom(String.class)) {
								method.invoke(instance, para);
							} else if (type.isAssignableFrom(int.class)
									|| type.isAssignableFrom(Integer.class)) {
								method.invoke(instance, Integer.parseInt(""
										.equals(para) ? "0" : para));
							} else if (type.isAssignableFrom(boolean.class)
									|| type.isAssignableFrom(Boolean.class)) {
								method.invoke(instance,
										Boolean.parseBoolean(para));
							} else if (type.isAssignableFrom(Date.class)) {
								SimpleDateFormat format = new SimpleDateFormat(
										"yyyy-MM-dd");
								method.invoke(instance, format.parse(""
										.equals(para) ? "0000-00-00" : para));
							} else if (type.isAssignableFrom(double.class)
									|| type.isAssignableFrom(Double.class)) {
								method.invoke(instance, Double.parseDouble(""
										.equals(para) ? "0.0" : para));
							} else if (type.isAssignableFrom(float.class)
									|| type.isAssignableFrom(Float.class)) {
								method.invoke(instance, Float.parseFloat(""
										.equals(para) ? "0.0" : para));
							}
						}
					}
				}

			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * 把json字符串转成list实体类
	 * @param stringJson json字符串
	 * @param vo 实体类 
	 * @return
	 */
	 public static <T>List<T> jsonToVo(String stringJson , Class<T> vo){
		  return (List<T>) com.alibaba.fastjson.JSONArray.parseArray(stringJson,vo);
	  }
}
