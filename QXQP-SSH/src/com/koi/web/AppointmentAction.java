package com.koi.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.koi.service.IAppointmentService;
import com.opensymphony.xwork2.ActionSupport;

public class AppointmentAction extends ActionSupport {
	@Autowired
	private IAppointmentService appointmentService;
	// 接收参数
	private Integer predateID;
	private String maintenanceNum;
	private String predateNum;
	private String toAudit;
	private Long pageSize;
	private Long curPage;
	private String pwPredates;
	private String sysPreRepairItemDetail;
	private String sysPreProductDetail;
	private String sysPreOtherCostDetail;
	// 返回参数
	private Object result;// 返回json值

	// 转单成功
	public String selectToMainten() {
		result = appointmentService.selectToMainten(predateID, maintenanceNum);
		return "result";
	}

	// 反审核
	public String toNotAudit() {
		result = appointmentService.toNotAudit(predateID);
		return "result";
	}

	// 审核
	public String toAudit() {
		result = appointmentService.toAudit(predateID);
		return "result";
	}

	// 删除信息
	public String deleteListPredate() {
		result = appointmentService.deleteListPredate(predateID);
		return "result";
	}

	// 保存主页面信息
	public String updateListPredate() {
		result = appointmentService.updateListPredate(pwPredates,
				sysPreRepairItemDetail, sysPreProductDetail,
				sysPreOtherCostDetail);
		return "result";
	}

	// 查询预约明细信息
	public String selectPredateDetail() {
		result = appointmentService.selectPredateDetail(predateID);
		return "result";
	}

	// 查询预约信息
	public String selectPredate() {
		result = appointmentService.selectPredate(predateNum, toAudit,
				pageSize, curPage);
		return "result";

	}
	// 生成预约单号
	public String predateNum() {
		result = appointmentService.predateNum();
		return "result";
	}

	public Object getResult() {
		return result;
	}

	public void setPredateID(Integer predateID) {
		this.predateID = predateID;
	}

	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}

	public void setPredateNum(String predateNum) {
		this.predateNum = predateNum;
	}

	public void setToAudit(String toAudit) {
		this.toAudit = toAudit;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public void setPwPredates(String pwPredates) {
		this.pwPredates = pwPredates;
	}

	public void setSysPreRepairItemDetail(String sysPreRepairItemDetail) {
		this.sysPreRepairItemDetail = sysPreRepairItemDetail;
	}

	public void setSysPreProductDetail(String sysPreProductDetail) {
		this.sysPreProductDetail = sysPreProductDetail;
	}

	public void setSysPreOtherCostDetail(String sysPreOtherCostDetail) {
		this.sysPreOtherCostDetail = sysPreOtherCostDetail;
	}
}
