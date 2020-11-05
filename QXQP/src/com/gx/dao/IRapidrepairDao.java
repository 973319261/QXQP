package com.gx.dao;

import java.util.List;

import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;

public interface IRapidrepairDao {
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
	public int updateListReceptione(List<PW_Reception> listReception, List<SYS_RecRepairItemDetail> listRecRepairItem, List<SYS_CollageDetai> listCollageDetai,
            List<SYS_RecOtherCostDetail> listRecOtherCost, List<SYS_InsuranceDetail> listArrInsuranceMoney, List<SYS_ThreePacksDetail> listThreePacksDetail);
	
	
}
