package com.koi.dao;

import java.util.List;

import com.koi.po.PwReception;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecProductDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.vo.ReceptionVo;


public interface ICustomerDao {
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
	public List<ReceptionVo> selectReception(String toAudit,
			String maintenanceNum, String carNum, int documentStateID,
			int balanceStateID, Long startIndex, Long pageSize);
	/**
	 * 查询客户接待单据信息条数
	 * @param toAudit
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @return
	 */
	public Long selectReceptionCount(String toAudit, String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID);
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
