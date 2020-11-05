package com.koi.service;



public interface IInsuranceService{
	/**
	 * 生成预约单号
	 * 
	 * @param d
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
			Long curPage, Long pageSize);
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
	public Object selectInsurance(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long curPage, Long pageSize);

	/**
	 * 查询是否存在索赔单据
	 * @param insuranceDetailID
	 * @return
	 */
	public Object selectInsuranceData(int insuranceDetailID);
	/**
	 * 
	 * @param receptionID
	 * @return
	 */
	public Object selectInsurances(int receptionID);
	/**
	 * 查询索赔明细信息
	 * @param receptionID
	 * @return
	 */
	public Object selectReceptionDetail(int receptionID);
	/**
	 * 保存索赔表
	 * @param listInsurance
	 * @param listInsuranceDetail
	 * @return
	 */
	public int bavaInsurance(String insurance, String insuranceDetail);
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
