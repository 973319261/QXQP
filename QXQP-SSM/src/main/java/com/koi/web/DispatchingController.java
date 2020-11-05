package com.koi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.service.IDispatchingService;

@Controller
@RequestMapping("/dispatchingController")
public class DispatchingController  {
	@Autowired
	private IDispatchingService dispatchingService;
	
	// 返回参数
	private Object result;// 返回json值
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	//查询主页面信息
	@ResponseBody
	@RequestMapping(value = "/selectReception", produces = "application/json;charset=UTF-8")
	public Object selectReception(Integer receptionID) {
		result = dispatchingService.selectReception(receptionID);
		return gson.toJson(result);
	}
	//查询派工结算方式金额
	@ResponseBody
	@RequestMapping(value = "/selectDispatch", produces = "application/json;charset=UTF-8")
	public Object selectDispatch(Integer dispatchID) {
		result=dispatchingService.selectDispatch(dispatchID);
		return gson.toJson(result);
	}

	// 保存派工信息
	@ResponseBody
	@RequestMapping(value = "/updateListRepairItemDetail", produces = "application/json;charset=UTF-8")
	public Object updateListRepairItemDetail(
			String sysRecRepairItemDetail,Double maintenAmount,String selfCoding,Integer receptionID,
			Boolean toSendWork) {
		result = dispatchingService.updateListRepairItemDetail(
				sysRecRepairItemDetail, maintenAmount, selfCoding, receptionID,
				toSendWork);
		return gson.toJson(result);
	}

	// 清除session ReceptionID
	@ResponseBody
	@RequestMapping(value = "/clearDispatching", produces = "application/json;charset=UTF-8")
	public void clearDispatching(HttpServletRequest request) {
		request.getSession().removeAttribute("ReceptionID");
	}

	// 转维修派工保存ReceptionID
	@ResponseBody
	@RequestMapping(value = "/dispatching", produces = "application/json;charset=UTF-8")
	public void dispatching(HttpServletRequest request,Integer receptionID) {
		request.getSession().setAttribute("ReceptionID", receptionID);
	}
	

}
