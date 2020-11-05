package com.koi.dao;

import java.util.List;

import com.koi.po.PwPredate;
import com.koi.po.SysPreOtherCostDetail;
import com.koi.po.SysPreProductDetail;
import com.koi.po.SysPreRepairItemDetail;


public interface IAppointmentDao {
	/**
	 * 生成预约单号
	 * @param d yyyyMMdd
	 * @return
	 */
	public int predateNum(String d);
	/**
	 * 查询预约单据信息
	 * @param PredateNum 单号
	 * @param ToAudit 是否审核
	 * @return
	 */
	public List<PwPredate> selectPredate(String predateNum,String toAudit,Long startIndex,Long pageSize);
	/**
	 * 查询预约单据条数
	 * @param PredateNum
	 * @param ToAudit
	 * @return
	 */
	public Long selectPredateCount(String predateNum,String toAudit);
	/**
	 * 查询预约明细信息
	 * @param PredateID 客户预约ID
	 * @return List
	 */
	public List<Object> selectPredateDetail(int predateID);
	/**
	 * 保存主页面信息
	 * @param listPredate
	 * @param listPreRepairItem
	 * @param listPreProduct
	 * @param listPreOtherCost
	 * @return
	 */
	public int updateListPredate(List<PwPredate> listPredate, List<SysPreRepairItemDetail> listPreRepairItem,List<SysPreProductDetail> listPreProduct,
	          List<SysPreOtherCostDetail> listPreOtherCost);
	/**
	 * 删除信息
	 * @param predateID
	 * @return
	 */
	public boolean deleteListPredate(int predateID);
	/**
	 * 审核预约单
	 * @param PredateID
	 * @return
	 */
	public boolean toAudit(int psredateID);
	/**
	 * 反审核预约单
	 * @param PredateID
	 * @return
	 */
	public boolean toNotAudit(int predateID);
	/**
	 * 查询是否已转单
	 * @param PredateID
	 * @param MaintenanceNum
	 * @return
	 */
	public boolean selectToMainten(int predateID,String maintenanceNum);
}
