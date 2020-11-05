package com.koi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koi.po.PwReception;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecProductDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.vo.ReceptionVo;


public interface CustomerDao {
	/**
	 * 生成维修单号
	 * 
	 * @return
	 */
	public int maintenanceNum(String d);

	/**
	 * 保存主页面信息
	 * 
	 * @param listReception
	 * @param listRecRepairItem
	 * @param listRecProduct
	 * @param listRecOtherCost
	 * @param listArrInsuranceMoney
	 * @param listThreePacksDetail
	 * @return
	 */
	public int updateListReceptione(List<PwReception> listReception,
			List<SysRecRepairItemDetail> listRecRepairItem,
			List<SysRecProductDetail> listRecProduct,
			List<SysRecOtherCostDetail> listRecOtherCost,
			List<SysInsuranceDetail> listArrInsuranceMoney,
			List<SysThreePacksDetail> listThreePacksDetail);

	/**
	 * 查询客户接待单据信息
	 * 
	 * @param toAudit
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ReceptionVo> selectReception(@Param("toAudit")String toAudit,
			@Param("maintenanceNum")String maintenanceNum, @Param("carNum")String carNum, @Param("documentStateID")int documentStateID,
			@Param("balanceStateID")int balanceStateID, @Param("startIndex")Long startIndex, @Param("pageSize")Long pageSize);
	/**
	 * 查询客户接待单据信息条数
	 * @param toAudit
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @return
	 */
	public Long selectReceptionCount(@Param("toAudit")String toAudit,
			@Param("maintenanceNum")String maintenanceNum, @Param("carNum")String carNum, @Param("documentStateID")int documentStateID,
			@Param("balanceStateID")int balanceStateID);
	/**
	 * 查询客户接待明细信息
	 * @param receptionID
	 * @return
	 */
	public List<Object> selectReceptionDetail(int receptionID);
	/**
	 * 删除客户接待单据信息
	 * @param receptionID
	 * @return
	 */
	public boolean delectReception(int receptionID);
}
