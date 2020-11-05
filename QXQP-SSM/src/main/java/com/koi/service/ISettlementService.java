package com.koi.service;

import java.util.List;


public interface ISettlementService {
	/**
	 * 保存结算单
	 * @param listBalance
	 * @return
	 */
	public int baveBalance(String listBalance);
	/**
	 * 审核
	 * @param receptionID
	 * @return
	 */
	public int toAudit(int receptionID);
	/**
	 * 反审核
	 * @param receptionID
	 * @return
	 */
	public int toNotAudit(int receptionID);
	/**
	 * 查询结算明细信息
	 * @param receptionID
	 * @return
	 */
	public Object selectBalanceDetail(int receptionID);
}
