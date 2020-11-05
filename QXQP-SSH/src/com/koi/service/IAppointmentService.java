package com.koi.service;

import java.util.List;

public interface IAppointmentService {
	/**
	 * 生成预约单号
	 * 
	 * yyyyMMdd
	 * 
	 * @return
	 */
	public String predateNum();

	/**
	 * 查询预约单据信息
	 * 
	 * @param PredateNum
	 *            单号
	 * @param ToAudit
	 *            是否审核
	 * @return
	 */
	public Object selectPredate(String predateNum, String toAudit,
			Long pageSize, Long curPage);

	/**
	 * 查询预约明细信息
	 * 
	 * @param PredateID
	 *            客户预约ID
	 * @return List
	 */
	public List<Object> selectPredateDetail(int predateID);

	/**
	 * 保存主页面信息
	 * 
	 * @param listPredate
	 * @param listPreRepairItem
	 * @param listPreProduct
	 * @param listPreOtherCost
	 * @return
	 */
	public int updateListPredate(String predate, String preRepairItem,
			String preProduct, String preOtherCost);

	/**
	 * 删除信息
	 * 
	 * @param predateID
	 * @return
	 */
	public boolean deleteListPredate(int predateID);

	/**
	 * 审核预约单
	 * 
	 * @param PredateID
	 * @return
	 */
	public boolean toAudit(int psredateID);

	/**
	 * 反审核预约单
	 * 
	 * @param PredateID
	 * @return
	 */
	public boolean toNotAudit(int predateID);

	/**
	 * 查询是否已转单
	 * 
	 * @param PredateID
	 * @param MaintenanceNum
	 * @return
	 */
	public boolean selectToMainten(int predateID, String maintenanceNum);

}
