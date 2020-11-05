package com.koi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.service.ICompletionService;
@Controller
@RequestMapping("/completionController")
public class CompletionController {
	@Autowired
	private ICompletionService completionService;

	// 返回参数
	private Object result;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 完工状态修改
	@ResponseBody
	@RequestMapping(value = "/updateCompletionFalse", produces = "application/json;charset=UTF-8")
	public Object updateCompletionFalse(String receptionVo) {
		result = completionService.updateCompletionFalse(receptionVo);
		return gson.toJson(result);
	}

	// 完工状态修改
	@ResponseBody
	@RequestMapping(value = "/updateCompletionTrue", produces = "application/json;charset=UTF-8")
	public Object updateCompletionTrue(String receptionVo) {
		result = completionService.updateCompletionTrue(receptionVo);
		return gson.toJson(result);
	}

	// 查询客户接待单据信息
	@ResponseBody
	@RequestMapping(value = "/selectReception", produces = "application/json;charset=UTF-8")
	public Object selectReception(String startDate, String endDate,
			String toCompletion, Long curPage, Long pageSize) {
		result = completionService.selectReception(startDate, endDate,
				toCompletion, curPage, pageSize);
		return gson.toJson(result);
	}
}
