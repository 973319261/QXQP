package com.koi.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.koi.service.ISettlementService;

public class SettlementAction {
	@Autowired
	private ISettlementService settlementService;
	// 接收参数
	private Integer receptionID;
	private String pwBalance;
	// 返回参数
	private Object result;

	// 查询领料明细表
	public String selectBalanceDetail() {
		result = settlementService.selectBalanceDetail(receptionID);
		return "result";
	}

	// 反审核
	public String toNotAudit() {
		result = settlementService.toNotAudit(receptionID);
		return "result";
	}

	// 审核
	public String toAudit() {
		result = settlementService.toAudit(receptionID);
		return "result";
	}

	// 保存结算单
	public String baveBalance() {
		result = settlementService.baveBalance(pwBalance);
		return "result";
	}

	public Object getResult() {
		return result;
	}

	public void setReceptionID(Integer receptionID) {
		this.receptionID = receptionID;
	}

	public void setPwBalance(String pwBalance) {
		this.pwBalance = pwBalance;
	}

}
