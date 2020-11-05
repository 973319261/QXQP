package com.koi.mapper;

public interface RapidrepairDao  {
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

}
