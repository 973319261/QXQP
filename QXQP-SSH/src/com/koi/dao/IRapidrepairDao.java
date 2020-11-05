package com.koi.dao;

import java.util.List;

import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;


public interface IRapidrepairDao  {
	/**
	 * 生成单号
	 * @param d
	 * @return
	 */
	public int maintenanceNum(String d);
	/**
	 * 保存主页面信息
	 * @param listReception
	 * @param listRecRepairItem
	 * @param listCollageDetai
	 * @param listRecOtherCost
	 * @param listArrInsuranceMoney
	 * @param listThreePacksDetail
	 * @return id
	 */
	public int updateListReceptione(List<PwReception> listReception, List<SysRecRepairItemDetail> listRecRepairItem, List<SysCollageDetai> listCollageDetai,
            List<SysRecOtherCostDetail> listRecOtherCost, List<SysInsuranceDetail> listArrInsuranceMoney, List<SysThreePacksDetail> listThreePacksDetail);
}
