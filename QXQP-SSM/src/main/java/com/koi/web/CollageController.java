package com.koi.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.po.PwUser;
import com.koi.po.SysCollageDetai;
import com.koi.service.ICollageService;
import com.koi.util.Util;
@Controller
@RequestMapping("/collageController")
public class CollageController {
	@Autowired
	private ICollageService collageService;
	
	// 返回参数
	private Object result;// 返回json值
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 查询领料主信息
	@ResponseBody
	@RequestMapping(value = "/arrlistToCollage", produces = "application/json;charset=UTF-8")
	public Object arrlistToCollage(Integer receptionID) {
		result = collageService.arrlistToCollage(receptionID);
		return gson.toJson(result);
	}

	// 保存退料主页面
	@ResponseBody
	@RequestMapping(value = "/updateListRestock", produces = "application/json;charset=UTF-8")
	public Object updateListRestock(String pwRestock,String restockDetail) {
		result = collageService.updateListRestock(pwRestock, restockDetail);
		return gson.toJson(result);
	}

	// 判断领料数量
	@ResponseBody
	@RequestMapping(value = "/judgingQuantity", produces = "application/json;charset=UTF-8")
	public Object judgingQuantity(String restockDetail) {
		result = collageService.judgingQuantity(restockDetail);
		return gson.toJson(result);
	}

	// 查询退料主信息
	@ResponseBody
	@RequestMapping(value = "/selectRestock", produces = "application/json;charset=UTF-8")
	public Object selectRestock(Integer receptionID) {
		result = collageService.selectRestock(receptionID);
		return gson.toJson(result);
	}

	// 查询可退料明细信息
	@ResponseBody
	@RequestMapping(value = "/selectTableCollageDetai", produces = "application/json;charset=UTF-8")
	public Object selectTableCollageDetai(Integer collageID,Long curPage,Long
			pageSize) {
		result = collageService.selectTableCollageDetai(collageID, curPage,
				pageSize);
		return gson.toJson(result);
	}

	// 清除session中的退料信息
	@ResponseBody
	@RequestMapping(value = "/cleanArrlist", produces = "application/json;charset=UTF-8")
	public void cleanArrlist(HttpServletRequest request) {
		request.getSession().removeAttribute("restock");
		request.getSession().removeAttribute("receptionID");
	}

	// 从session中获取退料信息
	@ResponseBody
	@RequestMapping(value = "/arrlistToRestock", produces = "application/json;charset=UTF-8")
	public Object arrlistToRestock(HttpServletRequest request) {
		result = request.getSession().getAttribute("restock");
		return gson.toJson(result);
	}

	// 保存退料信息到session
	@ResponseBody
	@RequestMapping(value = "/restock", produces = "application/json;charset=UTF-8")
	public void restock(HttpServletRequest request,String sysCollageDetai) {
		List<SysCollageDetai> list = Util.jsonToVo(sysCollageDetai,
				SysCollageDetai.class);
		request.getSession().setAttribute("restock", list);
	}

	// ReceptionID查询CollageID 退料模态框
	@ResponseBody
	@RequestMapping(value = "/selectCollageToID", produces = "application/json;charset=UTF-8")
	public Object selectCollageToID(HttpServletRequest request, Integer receptionID) {
		request.getSession().setAttribute("receptionID", receptionID);// 保存receptionID到session中
		result = collageService.selectCollageToID(receptionID);
		return gson.toJson(result);
	}

	// 领料
	@ResponseBody
	@RequestMapping(value = "/collage", produces = "application/json;charset=UTF-8")
	public void collage(HttpServletRequest request,Integer receptionID) {
		request.getSession().setAttribute("receptionID", receptionID);
	}

	// 查询领料明细信息
	@ResponseBody
	@RequestMapping(value = "/selectCollageDetai", produces = "application/json;charset=UTF-8")
	public Object selectCollageDetai(Integer collageID) {
		result = collageService.selectCollageDetai(collageID);
		return gson.toJson(result);
	}

	// 查询领料单据信息
	@ResponseBody
	@RequestMapping(value = "/selectCollage", produces = "application/json;charset=UTF-8")
	public Object selectCollage(Long curPage,Long pageSize) {
		result = collageService.selectCollage(curPage, pageSize);
		return gson.toJson(result);
	}

	// 保存领料主页面
	@ResponseBody
	@RequestMapping(value = "/updateListCollage", produces = "application/json;charset=UTF-8")
	public Object updateListCollage(HttpServletRequest request,String pwCollage,String sysCollageDetai) {
		PwUser user = (PwUser) request.getSession().getAttribute("user");
		result = collageService.updateListCollage(pwCollage, sysCollageDetai,
				user);
		return gson.toJson(result);
	}

	// 查询领料导入配件信息
	@ResponseBody
	@RequestMapping(value = "/selectDaoRuPeiJian", produces = "application/json;charset=UTF-8")
	public Object selectDaoRuPeiJian(Integer receptionID,Long curPage,Long
			pageSize) {
		result = collageService.selectDaoRuPeiJian(receptionID, curPage,
				pageSize);
		return gson.toJson(result);
	}

	// 查询领料主信息
	@ResponseBody
	@RequestMapping(value = "/selectCollageData", produces = "application/json;charset=UTF-8")
	public Object selectCollageData(Integer receptionID) {
		result = collageService.selectCollageData(receptionID);
		return gson.toJson(result);
	}

	// 查询客户接待单据信息
	@ResponseBody
	@RequestMapping(value = "/selectReception", produces = "application/json;charset=UTF-8")
	public Object selectReception(String startDate,String endDate,
			String collageState, String carNum, Long curPage,Long pageSize) {
		result = collageService.selectReception(startDate, endDate,
				collageState, carNum, curPage, pageSize);
		return gson.toJson(result);
	}

}
