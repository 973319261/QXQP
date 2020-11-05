package com.koi.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.koi.service.IClaimsetService;

public class ClaimsetAction implements ServletRequestAware {
	@Autowired
	private IClaimsetService claimsetService;
	// 接收参数
	private Integer balanceID;
	private Integer threePacksID;
	private Integer claimComID;
	private Integer receptionID;
	private Long threePacksDetailID;
	private Integer documentStateID;
	private Integer balanceStateID;
	private Long curPage;
	private Long pageSize;
	private String toAudit;
	private String maintenanceNum;
	private String carNum;
	private String businessNum;
	private String pwBalance;
	private String pwThreePacks;
	// 返回参数
	private Object result;
	private HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String baveBalance() {
		result = claimsetService.baveBalance(pwBalance);
		return "result";
	}

	public String selectBalances() {
		result = claimsetService.selectBalances(balanceID);
		return "result";
	}

	public String selectBalance() {
		result = claimsetService.selectBalance(businessNum);
		return "result";
	}

	// 删除三包表
	public String delectThreePacks() {
		result = claimsetService.delectThreePacks(threePacksID);
		return "result";
	}

	// 反审核预约单
	public String toNotAudit() {
		result = claimsetService.toNotAudit(threePacksID);
		return "result";
	}

	// 审核预约单
	public String toAudit() {
		result = claimsetService.toAudit(threePacksID);
		return "result";
	}

	// 保存三包表
	public String bavaThreePacks() {
		result = claimsetService.baveThreePacks(pwThreePacks, claimComID);
		return "result";
	}

	// 查询索赔明细信息
	public String selectReceptionDetail() {
		result = claimsetService.selectReceptionDetail(receptionID);
		return "result";
	}

	public String selectThreePackss() {
		result = claimsetService.selectThreePackss(receptionID);
		return "result";
	}

	// 判断是否存在索赔单据
	public String selectThreePacksData() {
		result = claimsetService.selectThreePacksData(threePacksDetailID);
		return "result";
	}

	// 单据查询
	public String selectThreePacks() {
		result = claimsetService.selectThreePacks(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, curPage, pageSize);
		return "result";
	}

	// 维修单号查询
	public String selectDanHao() {
		result = claimsetService.selectDanHao(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, curPage, pageSize);
		return "result";
	}

	// 生成预约单号
	public String insuranceNum() {
		result = claimsetService.insuranceNum();
		return "result";
	}

	// 清除session
	public void clearclaimset() {
		request.getSession().removeAttribute("receptionID");
	}

	// 保险理赔结算保存session
	public void claimset() {
		request.getSession().setAttribute("receptionID", receptionID);
	}

	public Object getResult() {
		return result;
	}

	public void setBalanceID(Integer balanceID) {
		this.balanceID = balanceID;
	}

	public void setThreePacksID(Integer threePacksID) {
		this.threePacksID = threePacksID;
	}

	public void setClaimComID(Integer claimComID) {
		this.claimComID = claimComID;
	}

	public void setReceptionID(Integer receptionID) {
		this.receptionID = receptionID;
	}

	public void setThreePacksDetailID(Long threePacksDetailID) {
		this.threePacksDetailID = threePacksDetailID;
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

	public void setToAudit(String toAudit) {
		this.toAudit = toAudit;
	}

	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public void setPwBalance(String pwBalance) {
		this.pwBalance = pwBalance;
	}

	public void setPwThreePacks(String pwThreePacks) {
		this.pwThreePacks = pwThreePacks;
	}

}
