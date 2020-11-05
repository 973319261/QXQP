package com.koi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.po.PwPredate;
import com.koi.po.SysPreRepairItemDetail;
import com.koi.service.IAppointmentService;

@Controller
@RequestMapping("/appointmentController")
public class AppointmentController {
	@Autowired
	private IAppointmentService appointmentService;
	// 返回参数
	private Object result;// 返回json值
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 转单成功
	@ResponseBody
	@RequestMapping(value = "/selectToMainten", produces = "application/json;charset=UTF-8")
	public Object selectToMainten(Integer predateID, String maintenanceNum) {
		result = appointmentService.selectToMainten(predateID, maintenanceNum);
		return gson.toJson(result);
	}

	// 反审核
	@ResponseBody
	@RequestMapping(value = "/toNotAudit", produces = "application/json;charset=UTF-8")
	public Object toNotAudit(Integer predateID) {
		result = appointmentService.toNotAudit(predateID);
		return gson.toJson(result);
	}

	// 审核
	@ResponseBody
	@RequestMapping(value = "/toAudit", produces = "application/json;charset=UTF-8")
	public Object toAudit(Integer predateID) {
		result = appointmentService.toAudit(predateID);
		return gson.toJson(result);
	}

	// 删除信息
	@ResponseBody
	@RequestMapping(value = "/deleteListPredate", produces = "application/json;charset=UTF-8")
	public Object deleteListPredate(Integer predateID) {
		result = appointmentService.deleteListPredate(predateID);
		return gson.toJson(result);
	}

	// 保存主页面信息
	@ResponseBody
	@RequestMapping(value = "/updateListPredate", produces = "application/json;charset=UTF-8")
	public Object updateListPredate(String pwPredates, String sysPreRepairItemDetail, String sysPreProductDetail,
			String sysPreOtherCostDetail) {
		 result = appointmentService.updateListPredate(pwPredates,
		 sysPreRepairItemDetail, sysPreProductDetail, sysPreOtherCostDetail);
		return gson.toJson(result);
	}

	// 查询预约明细信息
	@ResponseBody
	@RequestMapping(value = "/selectPredateDetail", produces = "application/json;charset=UTF-8")
	public Object selectPredateDetail(Integer predateID) {
		result = appointmentService.selectPredateDetail(predateID);
		return gson.toJson(result);
	}

	// 查询预约信息
	@ResponseBody
	@RequestMapping(value = "/selectPredate", produces = "application/json;charset=UTF-8")
	public Object selectPredate(String predateNum, String toAudit, Long pageSize, Long curPage) {
		result = appointmentService.selectPredate(predateNum, toAudit, pageSize, curPage);
		return gson.toJson(result);

	}

	// 生成预约单号
	@ResponseBody
	@RequestMapping(value = "/predateNum", produces = "application/json;charset=UTF-8")
	public Object predateNum() {
		result = appointmentService.predateNum();
		return gson.toJson(result);
	}

}
