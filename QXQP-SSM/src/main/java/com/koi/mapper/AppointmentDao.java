package com.koi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koi.po.PwPredate;
import com.koi.po.SysPreOtherCostDetail;
import com.koi.po.SysPreProductDetail;
import com.koi.po.SysPreRepairItemDetail;


public interface AppointmentDao {
	/**
	 * 生成预约单号
	 * @param d yyyyMMdd
	 * @return
	 */
	public int predateNum(@Param("d") String d);
	/**
	 * 查询预约单据信息
	 * @param PredateNum 单号
	 * @param ToAudit 是否审核
	 * @return
	 */
	public List<PwPredate> selectPredate(@Param("predateNum") String predateNum,@Param("toAudit")String toAudit,@Param("startIndex")Long startIndex,@Param("pageSize")Long pageSize);
	/**
	 * 查询预约单据条数
	 * @param PredateNum
	 * @param ToAudit
	 * @return
	 */
	public Long selectPredateCount(@Param("predateNum")String predateNum,@Param("toAudit")String toAudit);
	/**
	 * 查询预约明细信息
	 * @param PredateID 客户预约ID
	 * @return List
	 */
	public List<SysPreRepairItemDetail> selectPreRepairItemDetail(int predateID);
	public List<SysPreProductDetail> selectPreProductDetail(int predateID);
	public List<SysPreOtherCostDetail> selectPreOtherCostDetail(int predateID);
	/**
	 * 保存主页面信息
	 * @param listPredate
	 * @param listPreRepairItem
	 * @param listPreProduct
	 * @param listPreOtherCost
	 * @return
	 */
	public int insertPredate(PwPredate predate);
	public int updatePredate(PwPredate predate);
	public boolean deletePredate(int predateId);
	public int insertPreRepairItem(SysPreRepairItemDetail listPreRepairItem);
	public int updatePreRepairItem(SysPreRepairItemDetail listPreRepairItem);
	public boolean deletePreRepairItem(int predateId);
	public int insertPreProduct(SysPreProductDetail listPreProduct);
	public int updatePreProduct(SysPreProductDetail listPreProduct);
	public boolean deletePreProduct(int predateId);
	public int insertPreOtherCost(SysPreOtherCostDetail listPreOtherCost);
	public int updatePreOtherCost(SysPreOtherCostDetail listPreOtherCost);
	public boolean deletePreOtherCost(int predateId);
	
	/**
	 * 审核或者反审核预约单
	 * @param PredateID
	 * @return
	 */
	public boolean toAudit(@Param("toAudit")boolean toAudit, @Param("predateID")int predateID);

	/**
	 * 转单
	 * @param PredateID
	 * @param MaintenanceNum
	 * @return
	 */
	public boolean selectToMainten(@Param("predateID")int predateID,@Param("maintenanceNum")String maintenanceNum);
}
