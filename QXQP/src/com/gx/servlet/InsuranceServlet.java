package com.gx.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import com.gx.pojo.PW_Insurance;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.service.IInsuranceService;
import com.gx.service.impl.InsuranceServiceImpl;
import com.gx.util.Bsgrid;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;
import com.gx.vo.ReceptionVo;

@WebServlet(urlPatterns = "/servlet/InsuranceServlet")
public class InsuranceServlet extends HttpServlet {
	public IInsuranceService insuranceService = new InsuranceServiceImpl();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fun = request.getParameter("fun");
		if ("insurance".equals(fun)) {
			insurance(request, response);
		} else if ("clearInsurance".equals(fun)) {
			clearInsurance(request, response);
		} else if ("insuranceNum".equals(fun)) {
			insuranceNum(request, response);
		} else if ("selectDanHao".equals(fun)) {
			selectDanHao(request, response);
		} else if ("selectInsurance".equals(fun)) {
			selectInsurance(request, response);
		} else if ("selectInsuranceData".equals(fun)) {
			selectInsuranceData(request, response);
		} else if ("selectInsurances".equals(fun)) {
			selectInsurances(request, response);
		} else if ("selectReceptionDetail".equals(fun)) {
			selectReceptionDetail(request, response);
		} else if ("bavaInsurance".equals(fun)) {
			bavaInsurance(request, response);
		} else if ("toAudit".equals(fun)) {
			toAudit(request, response);
		} else if ("toNotAudit".equals(fun)) {
			toNotAudit(request, response);
		} else if ("delectInsurance".equals(fun)) {
			delectInsurance(request, response);
		}
	}

	// 删除索赔表
	public void delectInsurance(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag = false;
		int insuranceID = Integer.parseInt(request.getParameter("InsuranceID"));
		flag = insuranceService.delectInsurance(insuranceID);
		response.getWriter().write(String.valueOf(flag));
	}

	// 审核预约单
	public void toNotAudit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		int insuranceID = Integer.parseInt(request.getParameter("InsuranceID"));
		i = insuranceService.toNotAudit(insuranceID);
		response.getWriter().write(String.valueOf(i));
	}

	// 反审核预约单
	public void toAudit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int i = 0;
		int insuranceID = Integer.parseInt(request.getParameter("InsuranceID"));
		i = insuranceService.toAudit(insuranceID);
		response.getWriter().write(String.valueOf(i));
	}

	// 保存索赔表
	public void bavaInsurance(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		String pw_Insurance = request.getParameter("PW_Insurance");
		String sys_InsuranceDetail = request
				.getParameter("SYS_InsuranceDetail");
		List<PW_Insurance> listInsurance = ParameterMapping.jsonToVo(
				pw_Insurance, PW_Insurance.class);
		List<SYS_InsuranceDetail> listInsuranceDetail = ParameterMapping
				.jsonToVo(sys_InsuranceDetail, SYS_InsuranceDetail.class);
		i = insuranceService.bavaInsurance(listInsurance, listInsuranceDetail);
		response.getWriter().write(String.valueOf(i));
	}

	// 查询索赔明细信息
	public void selectReceptionDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		List<Object> list = insuranceService.selectReceptionDetail(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		JSONObject json = new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}

	public void selectInsurances(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		ReceptionVo receptionVo = insuranceService
				.selectInsurances(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArr = JSONArray.fromObject(receptionVo, jsonConfig);
		JSONObject json = new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}

	// 判断是否存在索赔单据
	public void selectInsuranceData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int insuranceDetailID = Integer.parseInt(request
				.getParameter("InsuranceDetailID"));
		ReceptionVo receptionVo = insuranceService
				.selectInsuranceData(insuranceDetailID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArr = JSONArray.fromObject(receptionVo, jsonConfig);
		JSONObject json = new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}

	// 单据查询
	public void selectInsurance(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String toAudit = request.getParameter("ToAudit");
		String maintenanceNum = request.getParameter("MaintenanceNum");
		String carNum = request.getParameter("CarNum");
		int documentStateID = Integer.parseInt(request
				.getParameter("DocumentStateID"));
		int balanceStateID = Integer.parseInt(request
				.getParameter("BalanceStateID"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = insuranceService.selectInsuranceCount(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit);
		int startIndex = 1;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ReceptionVo> list = insuranceService.selectInsurance(
				maintenanceNum, carNum, documentStateID, balanceStateID,
				toAudit, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		JSONObject jsonObject = Bsgrid.getJson(json, curPage, totalRows);
		response.getWriter().write(jsonObject.toString());
	}

	// 维修单号查询
	public void selectDanHao(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String toAudit = request.getParameter("ToAudit");
		String maintenanceNum = request.getParameter("MaintenanceNum");
		String carNum = request.getParameter("CarNum");
		int documentStateID = Integer.parseInt(request
				.getParameter("DocumentStateID"));
		int balanceStateID = Integer.parseInt(request
				.getParameter("BalanceStateID"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = insuranceService.selectDanHaoCount(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit);
		int startIndex = 1;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ReceptionVo> list = insuranceService.selectDanHao(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit, startIndex,
				pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		JSONObject jsonObject = Bsgrid.getJson(json, curPage, totalRows);
		response.getWriter().write(jsonObject.toString());
	}

	// 生成预约单号
	public void insuranceNum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat sfdate = new SimpleDateFormat("yyyyMMdd");
		String d = sfdate.format(date);
		int num = insuranceService.insuranceNum(d) + 1;
		StringBuffer CustomerNum = new StringBuffer("LP");
		CustomerNum.append(d).append(String.format("%04d", num));
		response.getWriter().write(CustomerNum.toString());
	}

	// 清除session
	public void clearInsurance(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("receptionID");
	}

	// 转保险理赔结算保存session
	public void insurance(HttpServletRequest request,
			HttpServletResponse response) {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		request.getSession().setAttribute("receptionID", receptionID);
	}
}
