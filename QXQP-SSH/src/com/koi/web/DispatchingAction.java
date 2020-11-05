package com.koi.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.koi.service.IDispatchingService;


public class DispatchingAction implements ServletRequestAware{
	@Autowired
	private IDispatchingService dispatchingService;
	private Integer receptionID;
	private Integer dispatchID;
	private Double maintenAmount;
	private Boolean toSendWork;
	private String selfCoding;
	private String sysRecRepairItemDetail;
	// 返回参数
	private Object result;// 返回json值
	private HttpServletRequest request; 
	//查询主页面信息
	public String selectReception() {
		result = dispatchingService.selectReception(receptionID);
		return "result";
	}
	//查询派工结算方式金额
	public String selectDispatch() {
		result=dispatchingService.selectDispatch(dispatchID);
		return "result";
	}

	// 保存派工信息
	public String updateListRepairItemDetail() {
		result = dispatchingService.updateListRepairItemDetail(
				sysRecRepairItemDetail, maintenAmount, selfCoding, receptionID,
				toSendWork);
		return "result";
	}

	// 清除session ReceptionID
	public void clearDispatching() {
		request.getSession().removeAttribute("ReceptionID");
	}

	// 转维修派工保存ReceptionID
	public void dispatching() {
		request.getSession().setAttribute("ReceptionID", receptionID);
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public Object getResult() {
		return result;
	}
	public void setReceptionID(Integer receptionID) {
		this.receptionID = receptionID;
	}
	public void setDispatchID(Integer dispatchID) {
		this.dispatchID = dispatchID;
	}
	public void setMaintenAmount(Double maintenAmount) {
		this.maintenAmount = maintenAmount;
	}
	public void setToSendWork(Boolean toSendWork) {
		this.toSendWork = toSendWork;
	}
	public void setSelfCoding(String selfCoding) {
		this.selfCoding = selfCoding;
	}
	public void setSysRecRepairItemDetail(String sysRecRepairItemDetail) {
		this.sysRecRepairItemDetail = sysRecRepairItemDetail;
	}
	
}
