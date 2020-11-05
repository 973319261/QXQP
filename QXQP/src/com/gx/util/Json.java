package com.gx.util;

import java.io.IOException;
import java.io.InputStream;

import net.sf.json.JsonConfig;

public class Json {
	/**
	 *格式json时间
	 * @param date
	 * @return
	 */
	public static <T>JsonConfig getJsontoDate(String date){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor(date));
		return jsonConfig;
	}
	public static String inputStream2String (InputStream in) throws IOException { 
		StringBuffer out = new StringBuffer(); 
		byte[] b = new byte[4096]; 
		for (int n; (n = in.read(b)) != -1;) { 
		out.append(new String(b, 0, n)); 
		} 
		return out.toString(); 
		}
}
