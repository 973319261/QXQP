package com.koi.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.koi.po.PwUser;
import com.koi.service.IUserService;
import com.koi.util.ValidateCode;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<PwUser>,
		ServletRequestAware {
	@Autowired
	private IUserService userService;
	// 接收参数
	private PwUser pwUser;
	private String validateCode;
	// 返回参数
	private String result;
	private HttpServletRequest request;

	// 登录
	public String login() {
		result = userService.login(pwUser, validateCode, request);
		return "result";
	}

	// 验证码
	public String validateCode() {
		validateCode = ValidateCode.getRandomString();
		request.getSession().setAttribute("validateCode", validateCode);
		return "validateCode";
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getResult() {
		return result;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public PwUser getModel() {
		if (pwUser == null) {
			pwUser = new PwUser();
		}
		return pwUser;
	}
}
