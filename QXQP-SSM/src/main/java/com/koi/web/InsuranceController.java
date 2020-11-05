package com.koi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.service.IInsuranceService;
@Controller
@RequestMapping("/insuranceController")
public class InsuranceController  {
	@Autowired
	private IInsuranceService insuranceService;
	
	// 返回参数
	private Object result;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 删除索赔表
	@ResponseBody
	@RequestMapping(value = "/delectInsurance", produces = "application/json;charset=UTF-8")
	public Object delectInsurance(Integer insuranceID) {
		result = insuranceService.delectInsurance(insuranceID);
		return gson.toJson(result);
	}

	// 审核预约单
	@ResponseBody
	@RequestMapping(value = "/toNotAudit", produces = "application/json;charset=UTF-8")
	public Object toNotAudit(Integer insuranceID) {
		result = insuranceService.toNotAudit(insuranceID);
		return gson.toJson(result);
	}

	// 反审核预约单
	@ResponseBody
	@RequestMapping(value = "/toAudit", produces = "application/json;charset=UTF-8")
	public Object toAudit(Integer insuranceID) {
		result = insuranceService.toAudit(insuranceID);
		return gson.toJson(result);
	}

	// 保存索赔表
	@ResponseBody
	@RequestMapping(value = "/bavaInsurance", produces = "application/json;charset=UTF-8")
	public Object bavaInsurance(String pwInsurance,String sysInsuranceDetail) {
		result = insuranceService
				.bavaInsurance(pwInsurance, sysInsuranceDetail);
		return gson.toJson(result);
	}

	// 查询索赔明细信息
	@ResponseBody
	@RequestMapping(value = "/selectReceptionDetail", produces = "application/json;charset=UTF-8")
	public Object selectReceptionDetail(Integer receptionID) {
		result = insuranceService.selectReceptionDetail(receptionID);
		return gson.toJson(result);
	}
	@ResponseBody
	@RequestMapping(value = "/selectInsurances", produces = "application/json;charset=UTF-8")
	public Object selectInsurances(Integer receptionID) {
		result = insuranceService.selectInsurances(receptionID);
		return gson.toJson(result);
	}

	// 判断是否存在索赔单据
	@ResponseBody
	@RequestMapping(value = "/selectInsuranceData", produces = "application/json;charset=UTF-8")
	public Object selectInsuranceData(Integer insuranceDetailID) {
		result = insuranceService.selectInsuranceData(insuranceDetailID);
		return gson.toJson(result);
	}

	// 单据查询
	@ResponseBody
	@RequestMapping(value = "/selectInsurance", produces = "application/json;charset=UTF-8")
	public Object selectInsurance(String maintenanceNum,String carNum,
		 Integer documentStateID,Integer balanceStateID,String toAudit,Long curPage,Long pageSize) {
		result = insuranceService.selectInsurance(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, curPage, pageSize);
		return gson.toJson(result);
	}

	// 维修单号查询
	@ResponseBody
	@RequestMapping(value = "/selectDanHao", produces = "application/json;charset=UTF-8")
	public Object selectDanHao(String maintenanceNum,String carNum,
			Integer documentStateID,Integer balanceStateID,String toAudit,Long curPage,Long pageSize) {
		result = insuranceService.selectDanHao(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, curPage, pageSize);
		return gson.toJson(result);
	}

	// 生成预约单号
	@ResponseBody
	@RequestMapping(value = "/insuranceNum", produces = "application/json;charset=UTF-8")
	public Object insuranceNum() {
		result = insuranceService.insuranceNum();
		return gson.toJson(result);
	}

	// 清除session
	@ResponseBody
	@RequestMapping(value = "/clearInsurance", produces = "application/json;charset=UTF-8")
	public void clearInsurance(HttpServletRequest request) {
		request.getSession().removeAttribute("receptionID");
	}

	// 转保险理赔结算保存session
	@ResponseBody
	@RequestMapping(value = "/insurance", produces = "application/json;charset=UTF-8")
	public void insurance(HttpServletRequest request,Integer receptionID) {
		request.getSession().setAttribute("receptionID", receptionID);
	}

	
}
