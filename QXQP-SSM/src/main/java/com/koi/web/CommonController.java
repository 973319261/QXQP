package com.koi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysMaintenanceCus;
import com.koi.service.ICommonService;
@Controller
@RequestMapping("/commonController")
public class CommonController {
	@Autowired
	private ICommonService commonService;

	// 返回参数
	private Object result;// 返回json值
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 判断是否存在配件编码
	@ResponseBody
	@RequestMapping(value = "/judgingFittingsCode", produces = "application/json;charset=UTF-8")
	public Object judgingFittingsCode(String fittingsCode) {
		result = commonService.judgingFittingsCode(fittingsCode);
		return gson.toJson(result);
	}

	// 新增配件信息
	@ResponseBody
	@RequestMapping(value = "/updateFittingsInfo", produces = "application/json;charset=UTF-8")
	public Object updateFittingsInfo(SysFittingsInfo sysFittingsInfo) {
		result = commonService.insertFittingsInfo(sysFittingsInfo);
		return gson.toJson(result);
	}

	// 查询配件信息
	@ResponseBody
	@RequestMapping(value = "/seleceFittingsInfo", produces = "application/json;charset=UTF-8")
	public Object seleceFittingsInfo(String infoOne,String infoTow,String infoThree,
			String infoFour,Integer fittingsTypeID,Long curPage,Long pageSize) {
		result = commonService.seleceFittingsInfo(infoOne, infoTow, infoThree,
				infoFour, fittingsTypeID, curPage, pageSize);
		return gson.toJson(result);
	}

	// 查询配件类型
	@ResponseBody
	@RequestMapping(value = "/selectFittingsType", produces = "application/json;charset=UTF-8")
	public Object selectFittingsType() {
		result = commonService.selectFittingsType();
		return gson.toJson(result);
	}

	// 表格其他费用下拉框改变查询
	@ResponseBody
	@RequestMapping(value = "/selectExpensesChange", produces = "application/json;charset=UTF-8")
	public Object selectExpensesChange(Integer expensesID) {
		result = commonService.selectExpensesChange(expensesID);
		return gson.toJson(result);
	}

	// 表格修理项目下拉框改变查询
	@ResponseBody
	@RequestMapping(value = "/selectRepairItemChange", produces = "application/json;charset=UTF-8")
	public Object selectRepairItemChange(Integer repairItemID) {
		result = commonService.selectRepairItemChange(repairItemID);
		return gson.toJson(result);
	}

	// 生成客户编号
	@ResponseBody
	@RequestMapping(value = "/customerNum", produces = "application/json;charset=UTF-8")
	public Object customerNum() {
		result = commonService.customerNum();
		return gson.toJson(result);
	}

	// 新增/修改维修客户
	@ResponseBody
	@RequestMapping(value = "/updateMaintenanceCus", produces = "application/json;charset=UTF-8")
	public Object updateMaintenanceCus(SysMaintenanceCus sysMaintenanceCus) {
		result = commonService.updateMaintenanceCus(sysMaintenanceCus);
		return gson.toJson(result);
	}

	// 查询维修客户
	@ResponseBody
	@RequestMapping(value = "/seleceMaintenanceCus", produces = "application/json;charset=UTF-8")
	public Object seleceMaintenanceCus(String licenseCode,Long curPage,Long
			pageSize) {
		result = commonService.seleceMaintenanceCus(licenseCode, curPage,
				pageSize);
		return gson.toJson(result);
	}

	// 绑定下拉框
	@ResponseBody
	@RequestMapping(value = "/selectAppendOption", produces = "application/json;charset=UTF-8")
	public Object selectAppendOption(String type) {
		result = commonService.selectAppendOption(type);
		return gson.toJson(result);
	}


}
