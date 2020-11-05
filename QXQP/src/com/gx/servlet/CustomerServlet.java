package com.gx.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


import com.gx.pojo.PW_Predate;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_PreOtherCostDetail;
import com.gx.pojo.SYS_PreProductDetail;
import com.gx.pojo.SYS_PreRepairItemDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecProductDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.service.ICustomerService;
import com.gx.service.impl.CustomerServiceImpl;
import com.gx.util.Bsgrid;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;
import com.gx.vo.ReceptionVo;

@WebServlet(urlPatterns = "/servlet/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private ICustomerService customerService = new CustomerServiceImpl();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fun = request.getParameter("fun");
		if ("customer".equals(fun)) {
			customer(request, response);
		} else if ("arrlistToAppointment".equals(fun)) {
			arrlistToAppointment(request, response);
		} else if ("cleanArrlist".equals(fun)) {
			cleanArrlist(request, response);
		} else if ("maintenanceNum".equals(fun)) {
			maintenanceNum(request, response);
		} else if ("updateListReceptione".equals(fun)) {
			updateListReceptione(request, response);
		} else if ("selectReception".equals(fun)) {
			selectReception(request, response);
		} else if ("selectReceptionDetail".equals(fun)) {
			selectReceptionDetail(request, response);
		} else if ("delectReception".equals(fun)) {
			delectReception(request, response);
		}
	}
	//删除客户信息
	public void delectReception(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag=false;
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		flag=customerService.delectReception(receptionID);
		response.getWriter().write(String.valueOf(flag));
	}
	//查询客户信息明细
	public void selectReceptionDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		List<Object> list = customerService.selectReceptionDetail(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		JSONObject json = new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}
	//查询客户信息
	public void selectReception(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String toAudit = request.getParameter("ToAudit");
		String maintenanceNum = request.getParameter("MaintenanceNum");
		String carNum = request.getParameter("CarNum");
		int documentStateID = Integer.parseInt(request.getParameter("DocumentStateID"));
		int balanceStateID = Integer.parseInt(request.getParameter("BalanceStateID"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = customerService.selectReceptionCount(toAudit, maintenanceNum, carNum, documentStateID, balanceStateID);
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		List<ReceptionVo> list = customerService.selectReception(toAudit, maintenanceNum, carNum, documentStateID, balanceStateID, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		JSONObject jsonObject = Bsgrid.getJson(json, curPage, totalRows);
		response.getWriter().write(jsonObject.toString());
	}

	// 保存主页面信息
	public void updateListReceptione(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		String pw_Predate= request.getParameter("PW_Reception");
		String sys_RecRepairItemDetail= request.getParameter("SYS_RecRepairItemDetail");
		String sys_RecProductDetail= request.getParameter("SYS_RecProductDetail");
		String sys_RecOtherCostDetail= request.getParameter("SYS_RecOtherCostDetail");
		String sys_InsuranceDetail= request.getParameter("SYS_InsuranceDetail");
		String sys_ThreePacksDetail= request.getParameter("SYS_ThreePacksDetail");
		List<PW_Reception> listReception = ParameterMapping.jsonToVo(pw_Predate, PW_Reception.class);
		List<SYS_RecRepairItemDetail> listRecRepairItem = ParameterMapping.jsonToVo(sys_RecRepairItemDetail, SYS_RecRepairItemDetail.class);
		List<SYS_RecProductDetail> listRecProduct = ParameterMapping.jsonToVo(sys_RecProductDetail, SYS_RecProductDetail.class);
		List<SYS_RecOtherCostDetail> listRecOtherCost = ParameterMapping.jsonToVo(sys_RecOtherCostDetail, SYS_RecOtherCostDetail.class);
		List<SYS_InsuranceDetail> listArrInsuranceMoney = ParameterMapping.jsonToVo(sys_InsuranceDetail, SYS_InsuranceDetail.class);
		List<SYS_ThreePacksDetail> listThreePacksDetail = ParameterMapping.jsonToVo(sys_ThreePacksDetail, SYS_ThreePacksDetail.class);
		i=customerService.updateListReceptione(listReception, listRecRepairItem, listRecProduct, listRecOtherCost, listArrInsuranceMoney, listThreePacksDetail);
		response.getWriter().write(String.valueOf(i));
	}

	// 生成维修单号
	public void maintenanceNum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String d =sf.format(date);
		int num = customerService.maintenanceNum("W"+d) + 1;
		StringBuffer CustomerNum = new StringBuffer("W");
		CustomerNum.append(d).append(String.format("%04d", num));// 如果不足4位则前面补0
		response.getWriter().write(CustomerNum.toString());
	}

	// 清空Session返回预约单号的列表
	public void cleanArrlist(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("list");
	}

	// Session返回预约单号的列表
	public void arrlistToAppointment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Object list = request.getSession().getAttribute("list");
		JSONObject json = new JSONObject();
		json.put("list", list);
		response.getWriter().write(json.toString());
	}

	// 客户接待页面
	public void customer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Object> list = new ArrayList<Object>();
		String pw_Predate = request.getParameter("PW_Predate");
		String sys_PreRepairItemDetail = request
				.getParameter("SYS_PreRepairItemDetail");
		String sys_PreProductDetail = request
				.getParameter("SYS_PreProductDetail");
		String sys_PreOtherCostDetail = request
				.getParameter("SYS_PreOtherCostDetail");
		List<PW_Predate> listPredate = ParameterMapping.jsonToVo(pw_Predate,
				PW_Predate.class);
		List<SYS_PreRepairItemDetail> listPreRepairItem = ParameterMapping
				.jsonToVo(sys_PreRepairItemDetail,
						SYS_PreRepairItemDetail.class);
		List<SYS_PreProductDetail> listPreProduct = ParameterMapping.jsonToVo(
				sys_PreProductDetail, SYS_PreProductDetail.class);
		List<SYS_PreOtherCostDetail> listPreOtherCost = ParameterMapping
				.jsonToVo(sys_PreOtherCostDetail, SYS_PreOtherCostDetail.class);
		list.add(listPreRepairItem);
		list.add(listPreProduct);
		list.add(listPreOtherCost);
		list.add(listPredate.get(0));
		request.getSession().setAttribute("list", list);
	}
}
