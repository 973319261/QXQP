package com.gx.dao;

import java.util.List;

import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecProductDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.vo.ReceptionVo;

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
	public int updateListReceptione(List<PW_Reception> listReception,
			List<SYS_RecRepairItemDetail> listRecRepairItem,
			List<SYS_RecProductDetail> listRecProduct,
			List<SYS_RecOtherCostDetail> listRecOtherCost,
			List<SYS_InsuranceDetail> listArrInsuranceMoney,
			List<SYS_ThreePacksDetail> listThreePacksDetail);

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
			int balanceStateID, int startIndex, int pageSize);
	/**
	 * 查询客户接待单据信息条数
	 * @param toAudit
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @return
	 */
	public int selectReceptionCount(String toAudit, String maintenanceNum,
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
