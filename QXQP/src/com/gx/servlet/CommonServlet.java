package com.gx.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import com.gx.pojo.PW_Predate;
import com.gx.pojo.SYS_Expenses;
import com.gx.pojo.SYS_FittingsInfo;
import com.gx.pojo.SYS_FittingsType;
import com.gx.pojo.SYS_MaintenanceCus;
import com.gx.pojo.SYS_PreRepairItemDetail;
import com.gx.pojo.SYS_RepairItem;
import com.gx.service.ICommonService;
import com.gx.service.impl.CommonServiceImpl;
import com.gx.util.Bsgrid;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;
import com.gx.vo.AppendOptionVo;
import com.gx.vo.FittingsInfoVo;

@WebServlet(urlPatterns = "/servlet/CommonServlet")
public class CommonServlet extends HttpServlet {
	private ICommonService commonService = new CommonServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("t") == null ? "" : request
				.getParameter("t");
		String fun = request.getParameter("fun");
		if (type != "") {
			selectAppendOption(request, response);
		}
		if ("seleceMaintenanceCus".equals(fun)) {
			seleceMaintenanceCus(request, response);
		} else if ("updateMaintenanceCus".equals(fun)) {
			updateMaintenanceCus(request, response);
		} else if ("customerNum".equals(fun)) {
			customerNum(request, response);
		} else if("selectRepairItemChange".equals(fun)){
			selectRepairItemChange(request,response);
		} else if("selectExpensesChange".equals(fun)){
			selectExpensesChange(request,response);
		} else if("selectFittingsType".equals(fun)){
			selectFittingsTypes(request,response);
		} else if("seleceFittingsInfo".equals(fun)){
			seleceFittingsInfo(request,response);
		} else if("updateFittingsInfo".equals(fun)){
			updateFittingsInfo(request,response);
		} else if("judgingFittingsCode".equals(fun)){
			judgingFittingsCode(request,response);
		} 

	}
	
	//判断是否存在配件编码
	public void judgingFittingsCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean falg=false;
		String fittingsCode=request.getParameter("FittingsCode");
		SYS_FittingsInfo sys_FittingsInfo=commonService.judgingFittingsCode(fittingsCode);
		if(sys_FittingsInfo==null){
			falg=true;
		}
		response.getWriter().write(String.valueOf(falg));	
	}
	//新增配件信息
	public void updateFittingsInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		SYS_FittingsInfo sys_FittingsInfo=ParameterMapping.getParameterFile(request, SYS_FittingsInfo.class);
		i=commonService.updateFittingsInfo(sys_FittingsInfo);
		response.getWriter().write(String.valueOf(i));
	}
	//查询配件信息
	public void seleceFittingsInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String infoOne=request.getParameter("InfoOne");
		String infoTow=request.getParameter("InfoTow");
		String infoThree=request.getParameter("InfoThree");
		String infoFour=request.getParameter("InfoFour");
		int fittingsTypeID=Integer.parseInt(request.getParameter("FittingsTypeID"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		int totalRows = commonService.seleceFittingsInfoCount(infoOne, infoTow, infoThree, infoFour, fittingsTypeID);// 总数
		List<FittingsInfoVo> list= commonService.seleceFittingsInfo(infoOne, infoTow, infoThree, infoFour, fittingsTypeID, startIndex, pageSize);
		JSONObject jsonObject = Bsgrid.getList(list, curPage, totalRows);//设置返回的参数
		response.getWriter().write(jsonObject.toString());//返回json数据
	}
	//查询配件类型
	public void selectFittingsTypes(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<SYS_FittingsType> list=commonService.selectFittingsType();
		JSONObject json=new JSONObject();
		json.put("list", list);
		response.getWriter().write(json.toString());
	}
	//表格其他费用下拉框改变查询
	public void selectExpensesChange(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int expensesID=Integer.parseInt(request.getParameter("ExpensesID"));
		SYS_Expenses list=commonService.selectExpensesChange(expensesID);
		JSONObject json=new JSONObject();
		json.put("list", list);
		response.getWriter().write(json.toString());
	}
	//表格修理项目下拉框改变查询
	public void selectRepairItemChange(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int repairItemID=Integer.parseInt(request.getParameter("RepairItemID"));
		SYS_RepairItem list = commonService.selectRepairItemChange(repairItemID);
		JSONObject json=new JSONObject();
		json.put("list", list);
		response.getWriter().write(json.toString());
	}

	// 生成客户编号
	public void customerNum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String d = sf.format(date);
		int num = commonService.customerNum(d)+1;
		StringBuffer CustomerNum = new StringBuffer("KH");
		CustomerNum.append(d).append("-").append(String.format("%04d", num));//如果不足4位则前面补0
		response.getWriter().write(CustomerNum.toString());
	}

	// 新增/修改维修客户
	public void updateMaintenanceCus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		SYS_MaintenanceCus maintenanceCus = ParameterMapping.getParameter(
				request, SYS_MaintenanceCus.class);
		int i = commonService.updateMaintenanceCus(maintenanceCus);
		response.getWriter().write(String.valueOf(i));
	}

	// 查询维修客户
	public void seleceMaintenanceCus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String licenseCode = request.getParameter("LicenseCode");
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int totalRows = commonService.seleceMaintenanceCusCount(licenseCode);// 总数
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 结束索引
		List<SYS_MaintenanceCus> list = commonService.seleceMaintenanceCus(
				licenseCode, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		JSONObject jsonObject = Bsgrid.getJson(json, curPage, totalRows);//设置返回的参数
		response.getWriter().write(jsonObject.toString());//返回json数据
	}

	// 绑定下拉框
	public void selectAppendOption(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String type = request.getParameter("t");
		JSONObject json = new JSONObject();
		List<AppendOptionVo> list = commonService.selectAppendOption(type);
		json.put("list", list);
		response.getWriter().write(json.toString());
	}
}
