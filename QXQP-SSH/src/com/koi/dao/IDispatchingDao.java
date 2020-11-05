package com.koi.dao;

import java.util.List;

import com.koi.po.SysRecRepairItemDetail;
import com.koi.vo.ReceptionVo;


public interface IDispatchingDao {
	/**
	 * 保存派工信息
	 * 
	 * @param listRecRepairItem
	 * @param maintenAmount
	 * @param selfCoding
	 * @param receptionID
	 * @param toSendWork
	 * @return
	 */
	public int updateListRepairItemDetail(
			List<SysRecRepairItemDetail> listRecRepairItem,
			Double maintenAmount, String selfCoding, int receptionID,
			boolean toSendWork);
	/**
	 * 查询派工结算方式金额
	 * @param DispatchID
	 * @return
	 */
	public double selectDispatch(int dispatchID);
	/**
	 * 查询主页面信息
	 * @param dispatchID
	 * @return
	 */
	public ReceptionVo selectReception(int receptionID);
}
