package com.koi.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.service.ICustomerService;
@Controller
@RequestMapping("/customerController")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	// 返回参数
	private Object result;// 返回json值
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 删除客户信息
	@ResponseBody
	@RequestMapping(value = "/delectReception", produces = "application/json;charset=UTF-8")
	public Object delectReception(Integer receptionID) {
		result = customerService.delectReception(receptionID);
		return gson.toJson(result);
	}

	// 查询客户信息明细
	@ResponseBody
	@RequestMapping(value = "/selectReceptionDetail", produces = "application/json;charset=UTF-8")
	public Object selectReceptionDetail(Integer receptionID) {
		result = customerService.selectReceptionDetail(receptionID);
		return result;
	}

	// 查询客户信息
	@ResponseBody
	@RequestMapping(value = "/selectReception", produces = "application/json;charset=UTF-8")
	public Object selectReception(String toAudit,String maintenanceNum,
			String carNum,Integer documentStateID,Integer balanceStateID,Long pageSize,Long curPage) {
		result = customerService.selectReception(toAudit, maintenanceNum,
				carNum, documentStateID, balanceStateID, pageSize, curPage);
		return gson.toJson(result);
	}

	// 保存主页面信息
	@ResponseBody
	@RequestMapping(value = "/updateListReceptione", produces = "application/json;charset=UTF-8")
	public Object updateListReceptione(String pwReception,
			String sysRecRepairItemDetail,String sysRecProductDetail,
			String sysRecOtherCostDetail,String sysInsuranceDetail,String sysThreePacksDetail) {
		result = customerService.updateListReceptione(pwReception,
				sysRecRepairItemDetail, sysRecProductDetail,
				sysRecOtherCostDetail, sysInsuranceDetail, sysThreePacksDetail);
		return gson.toJson(result);
	}

	// 生成维修单号
	@ResponseBody
	@RequestMapping(value = "/maintenanceNum", produces = "application/json;charset=UTF-8")
	public Object maintenanceNum() {
		result = customerService.maintenanceNum();
		return gson.toJson(result);
	}

	// 清空Session返回预约单号的列表
	@ResponseBody
	@RequestMapping(value = "/cleanArrlist", produces = "application/json;charset=UTF-8")
	public void cleanArrlist(HttpServletRequest request) {
		request.getSession().removeAttribute("list");
	}

	// Session返回预约单号的列表
	@ResponseBody
	@RequestMapping(value = "/arrlistToAppointment", produces = "application/json;charset=UTF-8")
	public Object arrlistToAppointment(HttpServletRequest request) {
		result = request.getSession().getAttribute("list");
		return gson.toJson(result);
	}

	// 客户接待页面
	@ResponseBody
	@RequestMapping(value = "/customer", produces = "application/json;charset=UTF-8")
	public Object customer(String pwPredate,String sysPreRepairItemDetail,
			String sysPreProductDetail, String sysPreOtherCostDetail,HttpServletRequest request) {
		result = customerService.customer(pwPredate, sysPreRepairItemDetail,
				sysPreProductDetail, sysPreOtherCostDetail);
		request.getSession().setAttribute("list", result);
		return gson.toJson(result);
	}

}
