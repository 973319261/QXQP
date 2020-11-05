package com.koi.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.UserDao;
import com.koi.po.PwUser;
import com.koi.service.IUserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public String login(PwUser pwUser, String validateCode,
			HttpServletRequest request) {
		String str;
		PwUser user = userDao.findUserByName(pwUser.getUserNum());
		if (user != null) {// 用户不存在
			if (user.getToUse() == true) {
				if (pwUser.getPassword().equals(user.getPassword())) {
					String sessionValidateCode = String.valueOf(request
							.getSession().getAttribute("validateCode"));
					if(validateCode!="") {
						if (validateCode.equalsIgnoreCase(sessionValidateCode)) {
							request.getSession().setAttribute("user", user);
							str = "seccess";
						} else {
							str = "对不起，您输入的验证码有误！";
						}
					}else {
						str = "请输入验证码！";
					}
				} else {
					str = "对不起，输入密码错误！";
				}
			} else {
				str = "该用户无法登陆！";
			}
		} else {
			str = "对不起，用户名不存在";
		}
		return str;
	}
}
