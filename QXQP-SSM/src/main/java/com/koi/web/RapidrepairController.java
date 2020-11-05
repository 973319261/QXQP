package com.koi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.service.IRapidrepairService;
@Controller
@RequestMapping("/rapidrepairController")
public class RapidrepairController {
	@Autowired
	private IRapidrepairService rapidrepairService;

	// 返回参数
	private Object result;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 保存主页面信息
	@ResponseBody
	@RequestMapping(value = "/updateListReceptione", produces = "application/json;charset=UTF-8")
	public Object updateListReceptione(String pwReception,
			String sysRecRepairItemDetail,String sysCollageDetai,String sysRecOtherCostDetail,
			String sysInsuranceDetail, String sysThreePacksDetail) {
		result = rapidrepairService.updateListReceptione(pwReception,
				sysRecRepairItemDetail, sysCollageDetai, sysRecOtherCostDetail,
				sysInsuranceDetail, sysThreePacksDetail);
		return gson.toJson(result);
	}

	// 生成单号
	@ResponseBody
	@RequestMapping(value = "/maintenanceNum", produces = "application/json;charset=UTF-8")
	public Object maintenanceNum() {
		result = rapidrepairService.maintenanceNum();
		return gson.toJson(result);
	}

}
