package com.koi.util;

import java.util.Random;

public class ValidateCode  {

	public static final char CHARS[]={'A','B','C','D','E','F','G','H','G','K','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'
        ,'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z','2','3','4','5','6','7','8','9'};

	public static Random random = new Random();
	//获取随机数
	public static String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}
}