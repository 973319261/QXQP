package com.koi.service;

import javax.servlet.http.HttpServletRequest;

import com.koi.po.PwUser;


public interface IUserService {
	/**
	 * 通过用户名称查询用户信息
	 * @param name
	 * @return
	 */
	public String login(PwUser pwUser,String validateCode,HttpServletRequest request);
	/**
	 * 获取验证码
	 * @param validateCode
	 * @param request
	 * @return
	 */
}
