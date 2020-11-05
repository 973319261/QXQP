package com.gx.dao;

import java.util.List;

import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.vo.ReceptionVo;

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
			List<SYS_RecRepairItemDetail> listRecRepairItem,
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
