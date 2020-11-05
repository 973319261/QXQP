package com.koi.service;

import com.koi.po.PwBalance;
import com.koi.vo.ReceptionVo;
import com.koi.vo.ReturnJson;



public interface IClaimsetService{
	/**
	 * 生成预约单号
	 * 
	 * @return
	 */
	public String insuranceNum();
	/**
	 * 维修单号查询
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Object selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long startIndex, Long pageSize);

	/**
	 * 三包单据查询
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Object selectThreePacks(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long startIndex, Long pageSize);
	
	/**
	 * 查询是否存在索赔单据
	 * @param threePacksDetailID
	 * @return
	 */
	public Object selectThreePacksData(Long threePacksDetailID);
	/**
	 * 
	 * @param receptionID
	 * @return
	 */
	public Object selectThreePackss(int receptionID);
	/**
	 * 查询索赔明细信息
	 * @param receptionID
	 * @return
	 */
	public Object selectReceptionDetail(int receptionID);
	/**
	 * 保存索赔表
	 * @param listInsurance
	 * @param claimComID
	 * @return
	 */
	public int baveThreePacks(String pwThreePacks, int claimComID);
	/**
	 * 审核预约单
	 * @param threePacksID
	 * @return
	 */
	public int toAudit(int threePacksID);
	/**
	 * 反审核预约单
	 * @param threePacksID
	 * @return
	 */
	public int toNotAudit(int threePacksID);
	/**
	 * 删除索赔单
	 * @param threePacksID
	 * @return
	 */
	public boolean delectThreePacks(int threePacksID);
	/**
	 * 查询单据付款状态
	 * @param businessNum
	 * @return
	 */
	public ReturnJson selectBalance(String businessNum);
	/**
	 * 查询付款数据
	 * @param balanceID
	 * @return
	 */
	public PwBalance selectBalances(int balanceID);
	/**
	 * 保存结算单
	 * @param listBalance
	 * @return
	 */
	public int baveBalance(String pwBalance);
}
