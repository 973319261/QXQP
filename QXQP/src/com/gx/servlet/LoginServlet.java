package com.gx.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gx.pojo.User;
import com.gx.service.IUserService;
import com.gx.service.impl.UserServiceImpl;
import com.gx.util.ParameterMapping;
import com.gx.util.ValidateCode;

@WebServlet(urlPatterns = "/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {
	private IUserService userService = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
		if ("validate".equals(type)) {
			validate(request, response);
		} else if ("login".equals(type)) {
			login(request, response);
		}
	}

	// 登录
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String msg = null;
		String paraValidateCode=request.getParameter("ValidateCode");
		String parachecked=request.getParameter("RememberMe");
		User paraUser = ParameterMapping.getParameter(request, User.class);
		User user = userService.findUserByName(paraUser.getUserNum());
		if (user != null) {// 用户不存在
			if (user.getToUse() == true) {
				if (paraUser.getPassword().equals(user.getPassword())) {
					String validateCode=String.valueOf(request.getSession().getAttribute("validateCode"));
					if(paraValidateCode.equalsIgnoreCase(validateCode)){
						if(parachecked=="true"){
							Cookie  userNum=new Cookie("UserNum", paraUser.getUserNum());
							Cookie  password=new Cookie("Password", paraUser.getPassword());
							Cookie  check=new Cookie("Check","true");
							response.addCookie(userNum);
							response.addCookie(password);
							response.addCookie(check);
						}
						request.getSession().setAttribute("user", user);
						msg = "seccess";
					}else{
						msg="对不起，您输入的验证码有误！";
					}
				} else {
					msg = "对不起，输入密码错误！";
				}
			} else {
				msg = "该用户无法登陆！";
			}
		} else {
			msg = "对不起，用户名不存在";
		}
		response.getWriter().write(msg);
	}

	// 验证码
	public void validate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String validateCode = ValidateCode.getRandomString();
		request.getSession().setAttribute("validateCode", validateCode);
		response.getWriter().write(validateCode);
	}
}
