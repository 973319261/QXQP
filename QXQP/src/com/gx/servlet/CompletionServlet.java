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

import com.gx.service.ICompletionService;
import com.gx.service.impl.CompletionServiceImpl;
import com.gx.util.Bsgrid;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;
import com.gx.vo.ReceptionVo;

@WebServlet(urlPatterns="/servlet/CompletionServlet")
public class CompletionServlet extends HttpServlet{
	private ICompletionService completionService=new CompletionServiceImpl();
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fun=request.getParameter("fun");
		if("selectReception".equals(fun)){
			selectReception(request,response);
		} else if("updateCompletionTrue".equals(fun)){
			updateCompletionTrue(request,response);
		} else if("updateCompletionFalse".equals(fun)){
			updateCompletionFalse(request,response);
		}
	}
	//完工状态修改
	public void updateCompletionFalse(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		String receptionVo=request.getParameter("ReceptionVo");
		List<ReceptionVo> listReception=ParameterMapping.jsonToVo(receptionVo, ReceptionVo.class);
		i=completionService.updateCompletionFalse(listReception);
		response.getWriter().write(String.valueOf(i));
	}
	//完工状态修改
	public void updateCompletionTrue(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		String receptionVo=request.getParameter("ReceptionVo");
		List<ReceptionVo> listReception=ParameterMapping.jsonToVo(receptionVo, ReceptionVo.class);
		i=completionService.updateCompletionTrue(listReception);
		response.getWriter().write(String.valueOf(i));
	}
	//查询客户接待单据信息
	public void selectReception(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String startDate = request.getParameter("StartDate");
		String endDate = request.getParameter("EndDate");
		String toCompletion = request.getParameter("ToCompletion");
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = completionService.selectReceptionCount(startDate, endDate, toCompletion);
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		List<ReceptionVo> list = completionService.selectReception(startDate, endDate, toCompletion, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		JSONObject jsonObject = Bsgrid.getJson(json, curPage, totalRows);
		response.getWriter().write(jsonObject.toString());
	}
}
