package com.gx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gx.pojo.User;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		 HttpSession session= (HttpSession) ((HttpServletRequest)req).getSession();
	        User user=(User) session.getAttribute("user");
		if(user!=null){
     		chain.doFilter(req,resp);
        }else{
            ((HttpServletResponse)resp).sendRedirect("/QXQP/jsp/login.jsp");
        }
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		
	}
}
