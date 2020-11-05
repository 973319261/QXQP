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

import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.service.impl.DispatchingServiceImpl;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;
import com.gx.vo.ReceptionVo;

@WebServlet(urlPatterns = "/servlet/Dispatching")
public class DispatchingServlet extends HttpServlet {
	private DispatchingServiceImpl dispatchingService = new DispatchingServiceImpl();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fun = request.getParameter("fun");
		if ("dispatching".equals(fun)) {
			dispatching(request, response);
		} else if ("clearDispatching".equals(fun)) {
			clearDispatching(request, response);
		} else if ("updateListRepairItemDetail".equals(fun)) {
			updateListRepairItemDetail(request, response);
		} else if("selectDispatch".equals(fun)){
			selectDispatch(request,response);
		} else if("selectReception".equals(fun)){
			selectReception(request,response);
		}
	}
	//查询主页面信息
	public void selectReception(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		ReceptionVo list = dispatchingService.selectReception(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		JSONObject json=new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}
	//查询派工结算方式金额
	public void selectDispatch(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int dispatchID=Integer.parseInt(request.getParameter("DispatchID"));
		double price=dispatchingService.selectDispatch(dispatchID);
		response.getWriter().write(String.valueOf(price));
	}

	// 保存派工信息
	public void updateListRepairItemDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		String sys_RecRepairItemDetail= request.getParameter("SYS_RecRepairItemDetail");
		double maintenAmount= Double.parseDouble(request.getParameter("MaintenAmount"));
		String selfCoding= request.getParameter("SelfCoding");
		int receptionID=Integer.parseInt(request.getParameter("ReceptionID"));
		boolean toSendWork= Boolean.parseBoolean(request.getParameter("ToSendWork"));
		List<SYS_RecRepairItemDetail> listRecRepairItem=ParameterMapping.jsonToVo(sys_RecRepairItemDetail, SYS_RecRepairItemDetail.class);
		i = dispatchingService.updateListRepairItemDetail(
				listRecRepairItem, maintenAmount, selfCoding, receptionID,
				toSendWork);
		response.getWriter().write(String.valueOf(i));
	}

	// 清除session ReceptionID
	public void clearDispatching(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("ReceptionID");
	}

	// 转维修派工保存ReceptionID
	public void dispatching(HttpServletRequest request,
			HttpServletResponse response) {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		request.getSession().setAttribute("ReceptionID", receptionID);
	}
}
