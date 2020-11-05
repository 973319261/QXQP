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

import com.gx.pojo.PW_Predate;
import com.gx.pojo.SYS_PreOtherCostDetail;
import com.gx.pojo.SYS_PreProductDetail;
import com.gx.pojo.SYS_PreRepairItemDetail;
import com.gx.service.IAppointmentService;
import com.gx.service.impl.AppointmentServiceImpl;
import com.gx.util.Bsgrid;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;

@WebServlet(urlPatterns = "/servlet/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
	private IAppointmentService appointmentService = new AppointmentServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fun = request.getParameter("fun");
		if ("predateNum".equals(fun)) {
			predateNum(request, response);
		} else if ("selectPredate".equals(fun)) {
			selectPredate(request, response);
		} else if ("selectPredateDetail".equals(fun)) {
			selectPredateDetail(request, response);
		} else if ("updateListPredate".equals(fun)) {
			updateListPredate(request, response);
		} else if ("deleteListPredate".equals(fun)) {
			deleteListPredate(request, response);
		} else if ("toAudit".equals(fun)) {
			toAudit(request, response);
		} else if ("toNotAudit".equals(fun)) {
			toNotAudit(request, response);
		} else if("selectToMainten".equals(fun)){
			selectToMainten(request,response);
		}
	}
	//转单成功
	public void selectToMainten(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag=false;
		String str=request.getParameter("PredateID");
		if(!"".equals(str)){
			int predateID = Integer.parseInt(str);
			String maintenanceNum=request.getParameter("MaintenanceNum");
			flag=appointmentService.selectToMainten(predateID, maintenanceNum);
			response.getWriter().write(String.valueOf(flag));
		}
	}
	//反审核
	public void toNotAudit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag=false;
		int predateID = Integer.parseInt(request.getParameter("PredateID"));
		flag=appointmentService.toNotAudit(predateID);
		response.getWriter().write(String.valueOf(flag));
	}
	//审核
	public void toAudit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag=false;
		int predateID = Integer.parseInt(request.getParameter("PredateID"));
		flag=appointmentService.toAudit(predateID);
		response.getWriter().write(String.valueOf(flag));
	}
	//删除信息
	public void deleteListPredate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag=false;
		int predateID = Integer.parseInt(request.getParameter("PredateID"));
		flag=appointmentService.deleteListPredate(predateID);
		response.getWriter().write(String.valueOf(flag));
	}
	// 保存主页面信息
	public void updateListPredate(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int i=0;
		String pw_Predate= request.getParameter("PW_Predate");
		String sys_PreRepairItemDetail= request.getParameter("SYS_PreRepairItemDetail");
		String sys_PreProductDetail= request.getParameter("SYS_PreProductDetail");
		String sys_PreOtherCostDetail= request.getParameter("SYS_PreOtherCostDetail");
		List<PW_Predate> listPredate = ParameterMapping.jsonToVo(pw_Predate, PW_Predate.class);
		List<SYS_PreRepairItemDetail> listPreRepairItem = ParameterMapping.jsonToVo(sys_PreRepairItemDetail, SYS_PreRepairItemDetail.class);
		List<SYS_PreProductDetail> listPreProduct = ParameterMapping.jsonToVo(sys_PreProductDetail, SYS_PreProductDetail.class);
		List<SYS_PreOtherCostDetail> listPreOtherCost = ParameterMapping.jsonToVo(sys_PreOtherCostDetail, SYS_PreOtherCostDetail.class);
		i=appointmentService.updateListPredate(listPredate, listPreRepairItem, listPreProduct, listPreOtherCost);
		response.getWriter().write(String.valueOf(i));
	}

	// 查询预约明细信息
	public void selectPredateDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int predateID = Integer.parseInt(request.getParameter("PredateID"));
		List<Object> list = appointmentService.selectPredateDetail(predateID);
		JSONObject json = new JSONObject();
		json.put("list", list);
		response.getWriter().write(json.toString());
	}

	// 查询预约信息
	public void selectPredate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String predateNum = request.getParameter("PredateNum");
		String toAudit = request.getParameter("ToAudit");
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = appointmentService.selectPredateCount(predateNum,
				toAudit);
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		List<PW_Predate> list = appointmentService.selectPredate(predateNum,
				toAudit, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		JSONObject jsonObject = Bsgrid.getJson(json, curPage, totalRows);
		response.getWriter().write(jsonObject.toString());
	}

	// 生成预约单号
	public void predateNum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat sfdate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sfdatetime = new SimpleDateFormat("yyyyMMddHHmmss");
		String d = sfdate.format(date);
		String dt = sfdatetime.format(date);
		int num = appointmentService.predateNum(d) + 1;
		StringBuffer CustomerNum = new StringBuffer("BJ");
		CustomerNum.append(dt).append(String.format("%04d", num));
		response.getWriter().write(CustomerNum.toString());
	}
}
