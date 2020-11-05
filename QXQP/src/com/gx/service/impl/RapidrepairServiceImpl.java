package com.gx.service.impl;

import java.util.List;

import com.gx.dao.IRapidrepairDao;
import com.gx.dao.impl.RapidrepairDaoImpl;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.service.IRapidrepairService;

public class RapidrepairServiceImpl implements IRapidrepairService {
	private IRapidrepairDao rapidrepairDao=new RapidrepairDaoImpl();
	@Override
	public int maintenanceNum(String d) {
		return rapidrepairDao.maintenanceNum(d);
	}

	@Override
	public int updateListReceptione(List<PW_Reception> listReception,
			List<SYS_RecRepairItemDetail> listRecRepairItem,
			List<SYS_CollageDetai> listCollageDetai,
			List<SYS_RecOtherCostDetail> listRecOtherCost,
			List<SYS_InsuranceDetail> listArrInsuranceMoney,
			List<SYS_ThreePacksDetail> listThreePacksDetail) {
		return rapidrepairDao.updateListReceptione(listReception, listRecRepairItem, listCollageDetai, listRecOtherCost, listArrInsuranceMoney, listThreePacksDetail);
	}

}
