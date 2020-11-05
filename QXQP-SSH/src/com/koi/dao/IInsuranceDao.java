package com.koi.dao;

import java.util.List;

import com.koi.po.PwInsurance;
import com.koi.po.SysInsuranceDetail;
import com.koi.vo.InsuranceDetailVo;
import com.koi.vo.InsuranceVo;
import com.koi.vo.ReceptionVo;


public interface IInsuranceDao{
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
	public List<InsuranceDetailVo> selectDanHao(String maintenanceNum, String carNum,
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
	public List<InsuranceVo> selectInsurance(String maintenanceNum, String carNum,
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
	public Long selectInsuranceCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit);
	/**
	 * 查询是否存在索赔单据
	 * @param insuranceDetailID
	 * @return
	 */
	public InsuranceVo selectInsuranceData(int insuranceDetailID);
	/**
	 * 
	 * @param receptionID
	 * @return
	 */
	public InsuranceDetailVo selectInsurances(int receptionID);
	/**
	 * 查询索赔明细信息
	 * @param receptionID
	 * @return
	 */
	public List<Object> selectReceptionDetail(int receptionID);
	/**
	 * 保存索赔表
	 * @param listInsurance
	 * @param listInsuranceDetail
	 * @return
	 */
	public int bavaInsurance(List<PwInsurance> listInsurance, List<SysInsuranceDetail> listInsuranceDetail);
	/**
	 * 审核预约单
	 * @param nsuranceID
	 * @return
	 */
	public int toAudit(int insuranceID);
	/**
	 * 反审核预约单
	 * @param insuranceID
	 * @return
	 */
	public int toNotAudit(int insuranceID);
	/**
	 * 删除索赔单
	 * @param insuranceID
	 * @return
	 */
	public boolean delectInsurance(int insuranceID);
	
}
