package com.koi.service;

import java.util.List;

import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;

public interface IRapidrepairService {
	/**
	 * 生成单号
	 * 
	 * @return
	 */
	public String maintenanceNum();

	/**
	 * 保存主页面信息
	 * 
	 * @param listReception
	 * @param listRecRepairItem
	 * @param listCollageDetai
	 * @param listRecOtherCost
	 * @param listArrInsuranceMoney
	 * @param listThreePacksDetail
	 * @return id
	 */
	public int updateListReceptione(String pwReception,
			String sysRecRepairItemDetail, String sysCollageDetai,
			String sysRecOtherCostDetail, String sysInsuranceDetail,
			String sysThreePacksDetail);
}
