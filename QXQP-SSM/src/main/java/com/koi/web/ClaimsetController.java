package com.koi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.service.IClaimsetService;
@Controller
@RequestMapping("/claimsetController")
public class ClaimsetController  {
	
	@Autowired
	private IClaimsetService claimsetService;
	
	// 返回参数
	private Object result;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	@ResponseBody
	@RequestMapping(value = "/baveBalance", produces = "application/json;charset=UTF-8")
	public Object baveBalance(String pwBalance) {
		result = claimsetService.baveBalance(pwBalance);
		return gson.toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectBalances", produces = "application/json;charset=UTF-8")
	public Object selectBalances(Integer balanceID) {
		result = claimsetService.selectBalances(balanceID);
		return gson.toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectBalance", produces = "application/json;charset=UTF-8")
	public Object selectBalance(String businessNum) {
		result = claimsetService.selectBalance(businessNum);
		return gson.toJson(result);
	}

	// 删除三包表
	@ResponseBody
	@RequestMapping(value = "/delectThreePacks", produces = "application/json;charset=UTF-8")
	public Object delectThreePacks(Integer threePacksID) {
		result = claimsetService.delectThreePacks(threePacksID);
		return gson.toJson(result);
	}

	// 反审核预约单
	@ResponseBody
	@RequestMapping(value = "/toNotAudit", produces = "application/json;charset=UTF-8")
	public Object toNotAudit(Integer threePacksID) {
		result = claimsetService.toNotAudit(threePacksID);
		return gson.toJson(result);
	}

	// 审核预约单
	@ResponseBody
	@RequestMapping(value = "/toAudit", produces = "application/json;charset=UTF-8")
	public Object toAudit(Integer threePacksID) {
		result = claimsetService.toAudit(threePacksID);
		return gson.toJson(result);
	}

	// 保存三包表
	@ResponseBody
	@RequestMapping(value = "/bavaThreePacks", produces = "application/json;charset=UTF-8")
	public Object bavaThreePacks(String pwThreePacks,Integer claimComID) {
		result = claimsetService.baveThreePacks(pwThreePacks, claimComID);
		return gson.toJson(result);
	}

	// 查询索赔明细信息
	@ResponseBody
	@RequestMapping(value = "/selectReceptionDetail", produces = "application/json;charset=UTF-8")
	public Object selectReceptionDetail(Integer receptionID) {
		result = claimsetService.selectReceptionDetail(receptionID);
		return gson.toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectThreePackss", produces = "application/json;charset=UTF-8")
	public Object selectThreePackss(Integer receptionID) {
		result = claimsetService.selectThreePackss(receptionID);
		return gson.toJson(result);
	}

	// 判断是否存在索赔单据
	@ResponseBody
	@RequestMapping(value = "/selectThreePacksData", produces = "application/json;charset=UTF-8")
	public Object selectThreePacksData(Long threePacksDetailID) {
		result = claimsetService.selectThreePacksData(threePacksDetailID);
		return gson.toJson(result);
	}

	// 单据查询
	@ResponseBody
	@RequestMapping(value = "/selectThreePacks", produces = "application/json;charset=UTF-8")
	public Object selectThreePacks(String maintenanceNum,String carNum,
			Integer documentStateID,Integer balanceStateID,String toAudit,Long curPage, Long pageSize) {
		result = claimsetService.selectThreePacks(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, curPage, pageSize);
		return gson.toJson(result);
	}

	// 维修单号查询
	@ResponseBody
	@RequestMapping(value = "/selectDanHao", produces = "application/json;charset=UTF-8")
	public Object selectDanHao(String maintenanceNum,String carNum,
			Integer documentStateID, Integer balanceStateID,String toAudit,Long curPage,Long pageSize) {
		result = claimsetService.selectDanHao(maintenanceNum, carNum,
				 documentStateID, balanceStateID, toAudit, curPage, pageSize);
		return gson.toJson(result);
	}

	// 生成预约单号
	@ResponseBody
	@RequestMapping(value = "/insuranceNum", produces = "application/json;charset=UTF-8")
	public Object insuranceNum() {
		result = claimsetService.insuranceNum();
		return gson.toJson(result);
	}

	// 清除session
	@ResponseBody
	@RequestMapping(value = "/clearclaimset", produces = "application/json;charset=UTF-8")
	public void clearclaimset(HttpServletRequest request) {
		request.getSession().removeAttribute("receptionID");
	}

	// 保险理赔结算保存session
	@ResponseBody
	@RequestMapping(value = "/claimset", produces = "application/json;charset=UTF-8")
	public void claimset(HttpServletRequest request,Integer receptionID) {
		request.getSession().setAttribute("receptionID", receptionID);
	}

	

}
