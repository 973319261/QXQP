package com.koi.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.koi.service.IInsuranceService;

public class InsuranceAction implements ServletRequestAware {
	@Autowired
	private IInsuranceService insuranceService;
	// 接收参数
	private Integer insuranceID;
	private Integer receptionID;
	private Integer insuranceDetailID;
	private String toAudit;
	private String maintenanceNum;
	private String carNum;
	private Integer documentStateID;
	private Integer balanceStateID;
	private Long curPage;
	private Long pageSize;
	private String pwInsurance;
	private String sysInsuranceDetail;
	// 返回参数
	private Object result;
	private HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	// 删除索赔表
	public String delectInsurance() {
		result = insuranceService.delectInsurance(insuranceID);
		return "result";
	}

	// 审核预约单
	public String toNotAudit() {
		result = insuranceService.toNotAudit(insuranceID);
		return "result";
	}

	// 反审核预约单
	public String toAudit() {
		result = insuranceService.toAudit(insuranceID);
		return "result";
	}

	// 保存索赔表
	public String bavaInsurance() {
		result = insuranceService
				.bavaInsurance(pwInsurance, sysInsuranceDetail);
		return "result";
	}

	// 查询索赔明细信息
	public String selectReceptionDetail() {
		result = insuranceService.selectReceptionDetail(receptionID);
		return "result";
	}

	public String selectInsurances() {
		result = insuranceService.selectInsurances(receptionID);
		return "result";
	}

	// 判断是否存在索赔单据
	public String selectInsuranceData() {
		result = insuranceService.selectInsuranceData(insuranceDetailID);
		return "result";
	}

	// 单据查询
	public String selectInsurance() {
		result = insuranceService.selectInsurance(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, curPage, pageSize);
		return "result";
	}

	// 维修单号查询
	public String selectDanHao() {
		result = insuranceService.selectDanHao(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, curPage, pageSize);
		return "result";
	}

	// 生成预约单号
	public String insuranceNum() {
		result = insuranceService.insuranceNum();
		return "result";
	}

	// 清除session
	public void clearInsurance() {
		request.getSession().removeAttribute("receptionID");
	}

	// 转保险理赔结算保存session
	public void insurance() {
		request.getSession().setAttribute("receptionID", receptionID);
	}

	public Object getResult() {
		return result;
	}

	public void setInsuranceService(IInsuranceService insuranceService) {
		this.insuranceService = insuranceService;
	}

	public void setInsuranceID(Integer insuranceID) {
		this.insuranceID = insuranceID;
	}

	public void setReceptionID(Integer receptionID) {
		this.receptionID = receptionID;
	}

	public void setInsuranceDetailID(Integer insuranceDetailID) {
		this.insuranceDetailID = insuranceDetailID;
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

	public void setDocumentStateID(Integer documentStateID) {
		this.documentStateID = documentStateID;
	}

	public void setBalanceStateID(Integer balanceStateID) {
		this.balanceStateID = balanceStateID;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public void setPwInsurance(String pwInsurance) {
		this.pwInsurance = pwInsurance;
	}

	public void setSysInsuranceDetail(String sysInsuranceDetail) {
		this.sysInsuranceDetail = sysInsuranceDetail;
	}

}
