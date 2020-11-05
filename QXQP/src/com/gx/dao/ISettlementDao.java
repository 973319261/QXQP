package com.gx.dao;

import java.util.List;

import com.gx.pojo.PW_Balance;

public interface ISettlementDao {
	/**
	 * 保存结算单
	 * @param listBalance
	 * @return
	 */
	public int baveBalance(List<PW_Balance> listBalance);
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
	public List<Object> selectBalanceDetail(int receptionID);
}
