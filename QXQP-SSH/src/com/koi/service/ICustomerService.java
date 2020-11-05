package com.koi.service;

import java.util.List;

import com.koi.po.PwReception;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecProductDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.vo.ReceptionVo;

public interface ICustomerService {
	/**
	 * 生成维修单号
	 * 
	 * @return
	 */
	public String maintenanceNum();

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
	public int updateListReceptione(String pwReception,
			String sysRecRepairItemDetail, String sysRecProductDetail,
			String sysRecOtherCostDetail, String sysInsuranceDetail,
			String sysThreePacksDetail);

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
	public Object selectReception(String toAudit,
			String maintenanceNum, String carNum, int documentStateID,
			int balanceStateID, Long pageSize,Long curPage);
	/**
	 * 查询客户接待明细信息
	 * 
	 * @param receptionID
	 * @return
	 */
	public List<Object> selectReceptionDetail(int receptionID);

	/**
	 * 删除客户接待单据信息
	 * 
	 * @param receptionID
	 * @return
	 */
	public boolean delectReception(int receptionID);

	public List<Object> customer(String pwPredate,
			String sysPreRepairItemDetail, String sysPreProductDetail,
			String sysPreOtherCostDetail);
}
