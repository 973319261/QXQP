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

import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.service.IRapidrepairService;
import com.gx.service.impl.RapidrepairServiceImpl;
import com.gx.util.ParameterMapping;

@WebServlet(urlPatterns = "/servlet/RapidrepairServlet")
public class RapidrepairServlet extends HttpServlet {
	private IRapidrepairService rapidrepairService=new RapidrepairServiceImpl();
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fun=request.getParameter("fun");
		if("maintenanceNum".equals(fun)){
			maintenanceNum(request,response);
		} else if("updateListReceptione".equals(fun)){
			updateListReceptione(request,response);
		}
	}
	//保存主页面信息
	public void updateListReceptione(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		String pw_Predate= request.getParameter("PW_Reception");
		String sys_RecRepairItemDetail= request.getParameter("SYS_RecRepairItemDetail");
		String sys_CollageDetai= request.getParameter("SYS_CollageDetai");
		String sys_RecOtherCostDetail= request.getParameter("SYS_RecOtherCostDetail");
		String sys_InsuranceDetail= request.getParameter("SYS_InsuranceDetail");
		String sys_ThreePacksDetail= request.getParameter("SYS_ThreePacksDetail");
		List<PW_Reception> listReception = ParameterMapping.jsonToVo(pw_Predate, PW_Reception.class);
		List<SYS_RecRepairItemDetail> listRecRepairItem = ParameterMapping.jsonToVo(sys_RecRepairItemDetail, SYS_RecRepairItemDetail.class);
		List<SYS_CollageDetai> listCollageDetai = ParameterMapping.jsonToVo(sys_CollageDetai, SYS_CollageDetai.class);
		List<SYS_RecOtherCostDetail> listRecOtherCost = ParameterMapping.jsonToVo(sys_RecOtherCostDetail, SYS_RecOtherCostDetail.class);
		List<SYS_InsuranceDetail> listArrInsuranceMoney = ParameterMapping.jsonToVo(sys_InsuranceDetail, SYS_InsuranceDetail.class);
		List<SYS_ThreePacksDetail> listThreePacksDetail = ParameterMapping.jsonToVo(sys_ThreePacksDetail, SYS_ThreePacksDetail.class);
		i=rapidrepairService.updateListReceptione(listReception, listRecRepairItem, listCollageDetai, listRecOtherCost, listArrInsuranceMoney, listThreePacksDetail);
		response.getWriter().write(String.valueOf(i));
	}

	//生成单号
	public void maintenanceNum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String d = sf.format(date);
		int num = rapidrepairService.maintenanceNum("K"+d) + 1;
		StringBuffer CustomerNum = new StringBuffer("K");
		CustomerNum.append(d).append(String.format("%04d", num));// 如果不足4位则前面补0
		response.getWriter().write(CustomerNum.toString());
	}
}
