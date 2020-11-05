package com.gx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.gx.pojo.PW_Balance;
import com.gx.service.ISettlementService;
import com.gx.service.impl.SettlementSerivceImpl;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;

@WebServlet(urlPatterns="/servlet/SettlementServlet")
public class SettlementServlet extends HttpServlet{
	private ISettlementService settlementService=new SettlementSerivceImpl();
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fun=request.getParameter("fun");
		if("baveBalance".equals(fun)){
			baveBalance(request,response);
		} else if("toAudit".equals(fun)){
			toAudit(request,response);
		} else if("toNotAudit".equals(fun)){
			toNotAudit(request,response);
		} else if("selectBalanceDetail".equals(fun)){
			selectBalanceDetail(request,response);
		}
	}
	//查询领料明细表
	public void selectBalanceDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID=Integer.parseInt(request.getParameter("ReceptionID"));
		List<Object> list=settlementService.selectBalanceDetail(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		JSONObject json = new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}
	//反审核
	public void toNotAudit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		int receptionID=Integer.parseInt(request.getParameter("ReceptionID"));
		i=settlementService.toNotAudit(receptionID);
		response.getWriter().write(String.valueOf(i));
	}
	//审核
	public void toAudit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		int receptionID=Integer.parseInt(request.getParameter("ReceptionID"));
		i=settlementService.toAudit(receptionID);
		response.getWriter().write(String.valueOf(i));
	}
	//保存结算单
	public void baveBalance(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		String pw_Balance=request.getParameter("PW_Balance");
		List<PW_Balance> listBalance=ParameterMapping.jsonToVo(pw_Balance, PW_Balance.class);
		i=settlementService.baveBalance(listBalance);
		response.getWriter().write(String.valueOf(i));
	}
}
