package com.gx.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.gx.pojo.PW_Collage;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.PW_Restock;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.User;
import com.gx.service.ICollageService;
import com.gx.service.impl.CollageServiceImpl;
import com.gx.util.Bsgrid;
import com.gx.util.Json;
import com.gx.util.ParameterMapping;
import com.gx.vo.CollageDetaiVo;
import com.gx.vo.CollageVo;
import com.gx.vo.ProductVo;
import com.gx.vo.ReceptionVo;
import com.gx.vo.RestockDetail;
import com.gx.vo.ReturnJson;

@WebServlet(urlPatterns="/servlet/Collage")
public class CollageServlet extends HttpServlet{
	private ICollageService collageService=new CollageServiceImpl();
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fun=request.getParameter("fun");
		if("selectReception".equals(fun)){
			selectReception(request,response);
		} else if("selectCollageData".equals(fun)){
			selectCollageData(request,response);
		} else if("selectDaoRuPeiJian".equals(fun)){
			selectDaoRuPeiJian(request,response);
		} else if("updateListCollage".equals(fun)){
			updateListCollage(request,response);
		} else if("selectCollage".equals(fun)){
			selectCollage(request,response);
		} else if("selectCollageDetai".equals(fun)){
			selectCollageDetai(request,response);
		} else if("collage".equals(fun)){
			collage(request,response);
		} else if("selectCollageToID".equals(fun)){
			selectCollageToID(request,response);
		} else if("restock".equals(fun)){
			restock(request,response);
		} else if("arrlistToRestock".equals(fun)){
			arrlistToRestock(request,response);
		} else if("cleanArrlist".equals(fun)){
			cleanArrlist(request,response);
		} else if("selectTableCollageDetai".equals(fun)){
			selectTableCollageDetai(request,response);
		} else if("selectRestock".equals(fun)){
			selectRestock(request,response);
		} else if("judgingQuantity".equals(fun)){
			judgingQuantity(request,response);
		} else if("updateListRestock".equals(fun)){
			updateListRestock(request,response);
		} else if("arrlistToCollage".equals(fun)){
			arrlistToCollage(request,response);
		}
	}
	//查询领料主信息
	public void arrlistToCollage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID=Integer.parseInt(request.getParameter("ReceptionID"));
		PW_Reception list= collageService.arrlistToCollage(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		JSONObject json=new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}
	//保存退料主页面
	public void updateListRestock(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		String pw_Restock=request.getParameter("PW_Restock");
		String restockDetail=request.getParameter("RestockDetail");
		List<PW_Restock> listRestock=ParameterMapping.jsonToVo(pw_Restock, PW_Restock.class);
		List<RestockDetail> listRestockDetai=ParameterMapping.jsonToVo(restockDetail, RestockDetail.class);
		i=collageService.updateListRestock(listRestock, listRestockDetai);
		response.getWriter().write(String.valueOf(i));
	}
	//判断领料数量
	public void judgingQuantity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String restockDetail=request.getParameter("RestockDetail");
		List<RestockDetail> listRestockDetai=ParameterMapping.jsonToVo(restockDetail, RestockDetail.class);
		ReturnJson returnJson=collageService.judgingQuantity(listRestockDetai);
		JSONObject json=new JSONObject();
		json.put("list", returnJson);
		response.getWriter().write(json.toString());
	}

	//查询退料主信息
	public void selectRestock(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
    	int receptionID =Integer.parseInt(request.getParameter("ReceptionID"));
    	CollageVo collageVo = collageService.selectRestock(receptionID);
    	collageVo.setStrAudit(collageVo.getToAudit()!=true?"未审核": "已审核");
    	JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(collageVo, jsonConfig);
    	JSONObject json=new JSONObject();
    	json.put("list", jsonArr);
    	response.getWriter().write(json.toString());
	}
	//查询可退料明细信息
	public void selectTableCollageDetai(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int collageID = Integer.parseInt(request.getParameter("CollageID"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = collageService.selectTableCollageDetaiCount(collageID);
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		List<CollageDetaiVo> list = collageService.selectTableCollageDetai(collageID, startIndex, pageSize);
		JSONObject jsonObject = Bsgrid.getList(list, curPage, totalRows);
		response.getWriter().write(jsonObject.toString());
	}
	//清除session中的退料信息
	public void cleanArrlist(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("restock");
		request.getSession().removeAttribute("receptionID");
	}
	//从session中获取退料信息
	public void arrlistToRestock(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	   Object list= request.getSession().getAttribute("restock");
	   JSONObject json=new JSONObject();
	   json.put("list", list);
	   response.getWriter().write(json.toString());
	}
	//保存退料信息到session
	public void restock(HttpServletRequest request,
			HttpServletResponse response) {
		String sys_CollageDetai=request.getParameter("SYS_CollageDetai");
		List<SYS_CollageDetai> list=ParameterMapping.jsonToVo(sys_CollageDetai, SYS_CollageDetai.class);
		request.getSession().setAttribute("restock", list);
	}

	//ReceptionID查询CollageID 退料模态框
	public void selectCollageToID(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int collageID=0;
		int receptionID=Integer.parseInt(request.getParameter("ReceptionID"));
		request.getSession().setAttribute("receptionID", receptionID);//保存receptionID到session中
		collageID=collageService.selectCollageToID(receptionID);
		response.getWriter().write(String.valueOf(collageID));
	}
	//领料
	public void collage(HttpServletRequest request,
			HttpServletResponse response) {
		int receptionID=Integer.parseInt(request.getParameter("ReceptionID"));
		HttpSession session = request.getSession();
		session.setAttribute("receptionID", receptionID);
	}
	//查询领料明细信息
	private void selectCollageDetai(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int collageID=Integer.parseInt(request.getParameter("CollageID"));
		List<SYS_CollageDetai> list = collageService.selectCollageDetai(collageID);
		JSONObject json=new JSONObject();
		json.put("list", list);
		response.getWriter().write(json.toString());
	}
	//查询领料单据信息
	private void selectCollage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = collageService.selectCollageCount();
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		List<CollageVo> list = collageService.selectCollage(startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		JSONObject jsonObject = Bsgrid.getJson(json, curPage, totalRows);
		response.getWriter().write(jsonObject.toString());
	}
	//保存领料主页面
	private void updateListCollage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int i=0;
		String pw_Collage=request.getParameter("PW_Collage");
		String sys_CollageDetai=request.getParameter("SYS_CollageDetai");
		List<PW_Collage> listCollage = ParameterMapping.jsonToVo(pw_Collage, PW_Collage.class);
		List<SYS_CollageDetai> listCollageDetai = ParameterMapping.jsonToVo(sys_CollageDetai, SYS_CollageDetai.class);
		User user=(User) request.getSession().getAttribute("user");
		for (int j = 0; j < listCollage.size(); j++) {
			 listCollage.get(i).setAuditor(user.getUserName());
             listCollage.get(i).setOperator(user.getUserName());
             listCollage.get(i).setAuditDate(new Date());
             listCollage.get(i).setToAudit(true);
		}
		
		i=collageService.updateListCollage(listCollage, listCollageDetai);
		response.getWriter().write(String.valueOf(i));
	}
	//查询领料导入配件信息
	private void selectDaoRuPeiJian(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID = Integer.parseInt(request.getParameter("ReceptionID"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = collageService.selectDaoRuPeiJianCount(receptionID);
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		List<ProductVo> list = collageService.selectDaoRuPeiJian(receptionID, startIndex, pageSize);
		JSONObject json = Bsgrid.getList(list, curPage, totalRows);
		response.getWriter().write(json.toString());
	}
	//查询领料主信息
	public void selectCollageData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int receptionID=Integer.parseInt(request.getParameter("ReceptionID"));
		CollageVo list= collageService.selectCollageData(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		JSONObject json=new JSONObject();
		json.put("list", jsonArr);
		response.getWriter().write(json.toString());
	}
	//查询客户接待单据信息
	private void selectReception(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String startDate = request.getParameter("StartDate");
		String endDate = request.getParameter("EndDate");
		String collageState = request.getParameter("CollageState");
		String carNum = request.getParameter("CarNum");
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));// 页面大小
		int totalRows = collageService.selectReceptionCount(startDate, endDate, collageState, carNum);
		int startIndex =  1 ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		List<ReceptionVo> list=collageService.selectReception(startDate, endDate, collageState, carNum, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		JSONObject jsonObject = Bsgrid.getJson(json, curPage, totalRows);
		response.getWriter().write(jsonObject.toString());	
	}
}
