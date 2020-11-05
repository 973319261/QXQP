package com.koi.mapper;

import com.koi.vo.ReceptionVo;


public interface DispatchingDao {
	
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
