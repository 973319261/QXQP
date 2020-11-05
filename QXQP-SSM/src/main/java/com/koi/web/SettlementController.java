package com.koi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.service.ISettlementService;
@Controller
@RequestMapping("/settlementController")
public class SettlementController {
	@Autowired
	private ISettlementService settlementService;
	// 返回参数
	private Object result;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 查询领料明细表
	@ResponseBody
	@RequestMapping(value = "/selectBalanceDetail", produces = "application/json;charset=UTF-8")
	public Object selectBalanceDetail(Integer receptionID) {
		result = settlementService.selectBalanceDetail(receptionID);
		return gson.toJson(result);
	}

	// 反审核
	@ResponseBody
	@RequestMapping(value = "/toNotAudit", produces = "application/json;charset=UTF-8")
	public Object toNotAudit(Integer receptionID) {
		result = settlementService.toNotAudit(receptionID);
		return gson.toJson(result);
	}

	// 审核
	@ResponseBody
	@RequestMapping(value = "/toAudit", produces = "application/json;charset=UTF-8")
	public Object toAudit(Integer receptionID) {
		result = settlementService.toAudit(receptionID);
		return gson.toJson(result);
	}

	// 保存结算单
	@ResponseBody
	@RequestMapping(value = "/baveBalance", produces = "application/json;charset=UTF-8")
	public Object baveBalance(String pwBalance) {
		result = settlementService.baveBalance(pwBalance);
		return gson.toJson(result);
	}

}
