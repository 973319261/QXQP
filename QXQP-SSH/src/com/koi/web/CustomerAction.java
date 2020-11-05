package com.koi.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.koi.service.ICustomerService;

public class CustomerAction implements ServletRequestAware {
	@Autowired
	private ICustomerService customerService;

	// 接收参数
	private int receptionID;
	private int documentStateID;
	private int balanceStateID;
	private String toAudit;
	private String maintenanceNum;
	private String carNum;
	private Long pageSize;
	private Long curPage;
	private String pwReception;
	private String sysRecRepairItemDetail;
	private String sysRecProductDetail;
	private String sysRecOtherCostDetail;
	private String sysInsuranceDetail;
	private String sysThreePacksDetail;
	private String pwPredate;
	private String sysPreRepairItemDetail;
	private String sysPreProductDetail;
	private String sysPreOtherCostDetail;
	// 返回参数
	private Object result;// 返回json值
	private HttpServletRequest request;

	// 删除客户信息
	public String delectReception() {
		result = customerService.delectReception(receptionID);
		return "result";
	}

	// 查询客户信息明细
	public String selectReceptionDetail() {
		result = customerService.selectReceptionDetail(receptionID);
		return "result";
	}

	// 查询客户信息
	public String selectReception() {
		result = customerService.selectReception(toAudit, maintenanceNum,
				carNum, documentStateID, balanceStateID, pageSize, curPage);
		return "result";
	}

	// 保存主页面信息
	public String updateListReceptione() {
		result = customerService.updateListReceptione(pwReception,
				sysRecRepairItemDetail, sysRecProductDetail,
				sysRecOtherCostDetail, sysInsuranceDetail, sysThreePacksDetail);
		return "result";
	}

	// 生成维修单号
	public String maintenanceNum() {
		result = customerService.maintenanceNum();
		return "result";
	}

	// 清空Session返回预约单号的列表
	public void cleanArrlist() {
		request.getSession().removeAttribute("list");
	}

	// Session返回预约单号的列表
	public String arrlistToAppointment() {
		result = request.getSession().getAttribute("list");
		return "result";
	}

	// 客户接待页面
	public String customer() {

		result = customerService.customer(pwPredate, sysPreRepairItemDetail,
				sysPreProductDetail, sysPreOtherCostDetail);
		request.getSession().setAttribute("list", result);
		return "result";
	}

	public String getPwReception() {
		return pwReception;
	}

	public void setPwReception(String pwReception) {
		this.pwReception = pwReception;
	}

	public String getSysRecRepairItemDetail() {
		return sysRecRepairItemDetail;
	}

	public void setSysRecRepairItemDetail(String sysRecRepairItemDetail) {
		this.sysRecRepairItemDetail = sysRecRepairItemDetail;
	}

	public String getSysRecProductDetail() {
		return sysRecProductDetail;
	}

	public void setSysRecProductDetail(String sysRecProductDetail) {
		this.sysRecProductDetail = sysRecProductDetail;
	}

	public String getSysRecOtherCostDetail() {
		return sysRecOtherCostDetail;
	}

	public void setSysRecOtherCostDetail(String sysRecOtherCostDetail) {
		this.sysRecOtherCostDetail = sysRecOtherCostDetail;
	}

	public String getSysInsuranceDetail() {
		return sysInsuranceDetail;
	}

	public void setSysInsuranceDetail(String sysInsuranceDetail) {
		this.sysInsuranceDetail = sysInsuranceDetail;
	}

	public String getSysThreePacksDetail() {
		return sysThreePacksDetail;
	}

	public void setSysThreePacksDetail(String sysThreePacksDetail) {
		this.sysThreePacksDetail = sysThreePacksDetail;
	}

	public Object getResult() {
		return result;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public void setReceptionID(int receptionID) {
		this.receptionID = receptionID;
	}

	public String getPwPredate() {
		return pwPredate;
	}

	public void setPwPredate(String pwPredate) {
		this.pwPredate = pwPredate;
	}

	public String getSysPreRepairItemDetail() {
		return sysPreRepairItemDetail;
	}

	public void setSysPreRepairItemDetail(String sysPreRepairItemDetail) {
		this.sysPreRepairItemDetail = sysPreRepairItemDetail;
	}

	public String getSysPreProductDetail() {
		return sysPreProductDetail;
	}

	public void setSysPreProductDetail(String sysPreProductDetail) {
		this.sysPreProductDetail = sysPreProductDetail;
	}

	public String getSysPreOtherCostDetail() {
		return sysPreOtherCostDetail;
	}

	public void setSysPreOtherCostDetail(String sysPreOtherCostDetail) {
		this.sysPreOtherCostDetail = sysPreOtherCostDetail;
	}

	public void setDocumentStateID(int documentStateID) {
		this.documentStateID = documentStateID;
	}

	public void setBalanceStateID(int balanceStateID) {
		this.balanceStateID = balanceStateID;
	}

	public void setToAudit(String toAudit) {
		this.toAudit = toAudit;
	}

	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
