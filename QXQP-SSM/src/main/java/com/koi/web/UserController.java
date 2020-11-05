package com.koi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.po.PwUser;
import com.koi.service.IUserService;
import com.koi.util.ValidateCode;
@Controller
@RequestMapping("/userController")
public class UserController{
	@Autowired
	private IUserService userService;
	// 返回参数
	private Object result;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	// 登录
	@ResponseBody
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public Object login(PwUser pwUser,String validateCode,HttpServletRequest request) {
		result = userService.login(pwUser, validateCode, request);
		return gson.toJson(result);
	}

	// 验证码
	@ResponseBody
	@RequestMapping(value = "/validateCode", produces = "application/json;charset=UTF-8")
	public String validateCode(HttpServletRequest request) {
		result = ValidateCode.getRandomString();
		request.getSession().setAttribute("validateCode", result);
		return gson.toJson(result);
	}
}
