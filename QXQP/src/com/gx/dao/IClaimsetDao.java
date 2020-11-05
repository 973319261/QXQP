package com.gx.dao;

import java.util.List;

import com.gx.pojo.PW_Balance;
import com.gx.pojo.PW_ThreePacks;
import com.gx.vo.ReceptionVo;
import com.gx.vo.ReturnJson;

public interface IClaimsetDao {
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
	public List<ReceptionVo> selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			int startIndex, int pageSize);
	/**
	 * 维修单号总数
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @return
	 */
	public int selectDanHaoCount(String maintenanceNum, String carNum,
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
	public List<ReceptionVo> selectThreePacks(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			int startIndex, int pageSize);
	/**
	 * 单据查询总数
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @return
	 */
	public int selectThreePacksCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit);
	/**
	 * 查询是否存在索赔单据
	 * @param threePacksDetailID
	 * @return
	 */
	public ReceptionVo selectThreePacksData(int threePacksDetailID);
	/**
	 * 
	 * @param receptionID
	 * @return
	 */
	public ReceptionVo selectThreePackss(int receptionID);
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
	public int baveThreePacks(List<PW_ThreePacks> listThreePacks, int claimComID);
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
	public PW_Balance selectBalances(int balanceID);
	/**
	 * 保存结算单
	 * @param listBalance
	 * @return
	 */
	public int baveBalance(List<PW_Balance> listBalance);
}
