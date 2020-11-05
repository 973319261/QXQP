package com.koi.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
	/**
	 * 获得指定文件的byte数组 
	 * @param file
	 * @return
	 */
    public static byte[] getBytes(File file){  
        byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
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

	 /**
	  * 将String字符串转换为java.util.Date格式日期
	  * @param strDate
	  *            表示日期的字符串
	  * @param dateFormat
	  *            传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
	  * @return java.util.Date类型日期对象（如果转换失败则返回null）
	  */
	 public static java.util.Date strToUtilDate(Timestamp timestamp, String dateFormat) {
	     SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
	     java.util.Date date = null;
	     try {
	         date = sf.parse(dateToStr(timestamp,dateFormat));
	     } catch (ParseException e) {
	         e.printStackTrace();
	     }
	     return date;
	 }
	 /**
	  * 将java.sql.Timestamp对象转化为String字符串
	  * @param time
	  *            要格式的java.sql.Timestamp对象
	  * @param strFormat
	  *            输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
	  * @return 表示日期的字符串
	  */
	 private static String dateToStr(java.sql.Timestamp time, String strFormat) {
	     DateFormat df = new SimpleDateFormat(strFormat);
	     String str = df.format(time);
	     return str;
	 }
	 /**
	  * 将String字符串转换为java.util.Date格式日期
	  * @param strDate
	  *            表示日期的字符串
	  * @param dateFormat
	  *            传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
	  * @return java.util.Date类型日期对象（如果转换失败则返回null）
	  */
	 public static java.util.Date strToUtilDate(String strDate, String dateFormat) {
	     SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
	     java.util.Date date = null;
	     try {
	         date = sf.parse(strDate);
	     } catch (ParseException e) {
	         e.printStackTrace();
	     }
	     return date;
	 }
	 //获取数据库字段
	 public static Map sqlField(String type) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", type);
			try {
				String dbName = type.replace("SYS_", "Sys");
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
	  * 从身份证截取生日
	  * @param idCard
	  * @return
	  */
	 public static String getBirthday(String idCard) {
		String birthday="";
		Calendar c=Calendar.getInstance();
		switch(idCard.length()) {
			case 18:
				String year = idCard.substring(6, 10);
				String month = idCard.substring(10,12);
				String day = idCard.substring(12,14);
				birthday=year+"-"+ month+"-"+ day;
				break;
			default:
				break;
		}
		return birthday;
	}
}
