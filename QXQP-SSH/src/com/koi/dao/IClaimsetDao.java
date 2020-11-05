package com.koi.dao;

import java.util.List;

import com.koi.po.PwBalance;
import com.koi.po.PwThreePacks;
import com.koi.vo.ReceptionVo;
import com.koi.vo.ReturnJson;
import com.koi.vo.ThreePacksDetailVo;
import com.koi.vo.ThreePacksVo;



public interface IClaimsetDao{
	/**
	 * 生成预约单号
	 * 
	 * @param d
	 * @return
	 */
	public int insuranceNum(String d);
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
	public List<ThreePacksDetailVo> selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long startIndex, Long pageSize);
	/**
	 * 维修单号总数
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @return
	 */
	public Long selectDanHaoCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit);
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
	public List<ThreePacksVo> selectThreePacks(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long startIndex, Long pageSize);
	/**
	 * 单据查询总数
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @return
	 */
	public Long selectThreePacksCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit);
	/**
	 * 查询是否存在索赔单据
	 * @param threePacksDetailID
	 * @return
	 */
	public ThreePacksVo selectThreePacksData(Long threePacksDetailID);
	/**
	 * 
	 * @param receptionID
	 * @return
	 */
	public ThreePacksDetailVo selectThreePackss(int receptionID);
	/**
	 * 查询索赔明细信息
	 * @param receptionID
	 * @return
	 */
	public List<Object> selectReceptionDetail(int receptionID);
	/**
	 * 保存索赔表
	 * @param listInsurance
	 * @param claimComID
	 * @return
	 */
	public int baveThreePacks(List<PwThreePacks> listThreePacks, int claimComID);
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
	public int baveBalance(List<PwBalance> listBalance);
}
