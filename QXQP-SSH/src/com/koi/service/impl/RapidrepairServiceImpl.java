package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.IRapidrepairDao;
import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.service.IRapidrepairService;
import com.koi.util.Util;

@Transactional
@Service("rapidrepairService")
public class RapidrepairServiceImpl implements IRapidrepairService {
	@Autowired
	private IRapidrepairDao rapidrepairDao;

	@Override
	public String maintenanceNum() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String d = sf.format(date);
		int num = rapidrepairDao.maintenanceNum("K" + d) + 1;
		StringBuffer CustomerNum = new StringBuffer("K");
		String str = "K" + d + String.format("%04d", num);
		return str;
	}

	@Override
	public int updateListReceptione(String pwReception,
			String sysRecRepairItemDetail, String sysCollageDetai,
			String sysRecOtherCostDetail, String sysInsuranceDetail,
			String sysThreePacksDetail) {
		List<PwReception> listReception = Util.jsonToVo(pwReception,
				PwReception.class);
		List<SysRecRepairItemDetail> listRecRepairItem = Util.jsonToVo(
				sysRecRepairItemDetail, SysRecRepairItemDetail.class);
		List<SysCollageDetai> listCollageDetai = Util.jsonToVo(sysCollageDetai,
				SysCollageDetai.class);
		List<SysRecOtherCostDetail> listRecOtherCost = Util.jsonToVo(
				sysRecOtherCostDetail, SysRecOtherCostDetail.class);
		List<SysInsuranceDetail> listArrInsuranceMoney = Util.jsonToVo(
				sysInsuranceDetail, SysInsuranceDetail.class);
		List<SysThreePacksDetail> listThreePacksDetail = Util.jsonToVo(
				sysThreePacksDetail, SysThreePacksDetail.class);
		return rapidrepairDao.updateListReceptione(listReception,
				listRecRepairItem, listCollageDetai, listRecOtherCost,
				listArrInsuranceMoney, listThreePacksDetail);
	}

}
