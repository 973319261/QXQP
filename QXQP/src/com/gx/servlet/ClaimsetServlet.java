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

import com.gx.pojo.PW_Balance;
import com.gx.pojo.PW_Insurance;
import com.gx.pojo.PW_ThreePacks;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.service.IClaimsetService;
import com.gx.service.impl.ClaimsetServiceImpl;
import com.gx.util.Bsgrid;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;
import com.gx.vo.ReceptionVo;
import com.gx.vo.ReturnJson;
@WebServlet(urlPatterns="/servlet/ClaimsetServlet")
public class ClaimsetServlet extends HttpServlet{
	public IClaimsetService claimsetService=new ClaimsetServiceImpl();
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fun=request.getParameter("fun");
		if ("claimset".equals(fun)) {
			claimset(request, response);
		} else if ("clearclaimset".equals(fun)) {
			clearclaimset(request, response);
		} else if ("insuranceNum".equals(fun)) {
			insuranceNum(request, response);
		} else if ("selectDanHao".equals(fun)) {
			selectDanHao(request, response);
		} else if ("selectThreePacks".equals(fun)) {
			selectThreePacks(request, response);
		} else if ("selectThreePacksData".equals(fun)) {
			selectThreePacksData(request, response);
		} else if ("selectThreePackss".equals(fun)) {
			selectThreePackss(request, response);
		} else if ("selectReceptionDetail".equals(fun)) {
			selectReceptionDetail(request, response);
		} else if ("bavaThreePacks".equals(fun)) {
			bavaThreePacks(request, response);
		} else if ("toAudit".equals(fun)) {
			toAudit(request, response);
		} else if ("toNotAudit".equals(fun)) {
			toNotAudit(request, response);
		} else if ("delectThreePacks".equals(fun)) {
			delectThreePacks(request, response);
		} else if ("selectBalance".equals(fun)) {
			selectBalance(request, response);
		} else if ("selectBalances".equals(fun)) {
			selectBalances(request, response);
		} else if ("baveBalance".equals(fun)) {
			baveBalance(request, response);
		}
	}
	public void baveBalance(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		String pw_Balance=request.getParameter("PW_Balance");
		List<PW_Balance> listBalance=ParameterMapping.jsonToVo(pw_Balance, PW_Balance.class);
		i=claimsetService.baveBalance(listBalance);
		response.getWriter().write(String.valueOf(i));
	}
	public void selectBalances(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int balanceID=Integer.parseInt(request.getParameter("BalanceID"));
		PW_Balance pw_Balance=claimsetService.selectBalances(balanceID);
		JSONObject json=new JSONObject();
		json.put("list", pw_Balance);
		response.getWriter().write(json.toString());
	}
	public void selectBalance(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String businessNum=request.getParameter("BusinessNum");
		ReturnJson returnJson = claimsetService.selectBalance(businessNum);
		JSONObject json=new JSONObject();
		json.put("list", returnJson);
		response.getWriter().write(json.toString());
	}
	// 删除三包表
	public void delectThreePacks(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag = false;
		int threePacksID = Integer.parseInt(request.getParameter("ThreePacksID"));
		flag =claimsetService.delectThreePacks(threePacksID);
		response.getWriter().write(String.valueOf(flag));
		
	}
	// 反审核预约单
	public void toNotAudit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		int threePacksID = Integer.parseInt(request.getParameter("ThreePacksID"));
		i = claimsetService.toNotAudit(threePacksID);
		response.getWriter().write(String.valueOf(i));
	}
	// 审核预约单
	public void toAudit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		int threePacksID = Integer.parseInt(request.getParameter("ThreePacksID"));
		i = claimsetService.toAudit(threePacksID);
		response.getWriter().write(String.valueOf(i));
	}
	//保存三包表
	public void bavaThreePacks(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i = 0;
		String pw_ThreePacks = request.getParameter("PW_ThreePacks");
		int claimComID=Integer.parseInt(request.getParameter("ClaimComID"));
		List<PW_ThreePacks> listThreePacks = ParameterMapping.jsonToVo(
				pw_ThreePacks, PW_ThreePacks.class);
		i = claimsetService.baveThreePacks(listThreePacks, claimComID);
		response.getWriter().write(String.valueOf(i));
		
	}
	//查询索赔明细信息
	public void selectReceptionDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		List<Object> list = claimsetService.selectReceptionDetail(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		JSONObject json = new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}
	public void selectThreePackss(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		ReceptionVo receptionVo = claimsetService
				.selectThreePackss(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArr = JSONArray.fromObject(receptionVo, jsonConfig);
		JSONObject json = new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}
	// 判断是否存在索赔单据
	public void selectThreePacksData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int threePacksDetailID = Integer.parseInt(request
				.getParameter("ThreePacksDetailID"));
		ReceptionVo receptionVo = claimsetService
				.selectThreePacksData(threePacksDetailID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArr = JSONArray.fromObject(receptionVo, jsonConfig);
		JSONObject json = new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}
	// 单据查询
	public void selectThreePacks(HttpServletRequest request,
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
		int totalRows = claimsetService.selectThreePacksCount(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit);
		int startIndex = 1;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ReceptionVo> list = claimsetService.selectThreePacks(
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
		int totalRows = claimsetService.selectDanHaoCount(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit);
		int startIndex = 1;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ReceptionVo> list = claimsetService.selectDanHao(maintenanceNum,
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
		int num = claimsetService.insuranceNum(d) + 1;
		StringBuffer CustomerNum = new StringBuffer("SP");
		CustomerNum.append(d).append(String.format("%04d", num));
		response.getWriter().write(CustomerNum.toString());
	}
	// 清除session
	public void clearclaimset(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("receptionID");
	}
	//保险理赔结算保存session
	public void claimset(HttpServletRequest request,
			HttpServletResponse response) {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		request.getSession().setAttribute("receptionID", receptionID);
	}
}
