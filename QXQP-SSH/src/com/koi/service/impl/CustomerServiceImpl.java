package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.ICustomerDao;
import com.koi.po.PwPredate;
import com.koi.po.PwReception;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysPreOtherCostDetail;
import com.koi.po.SysPreProductDetail;
import com.koi.po.SysPreRepairItemDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecProductDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.service.ICustomerService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.ReceptionVo;

@Transactional
@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDao customerDao;

	@Override
	public String maintenanceNum() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String d = sf.format(date);
		int num = customerDao.maintenanceNum("W" + d) + 1;
		String str = "W" + d + String.format("%04d", num);
		return str;
	}

	@Override
	public int updateListReceptione(String pwReception,
			String sysRecRepairItemDetail, String sysRecProductDetail,
			String sysRecOtherCostDetail, String sysInsuranceDetail,
			String sysThreePacksDetail) {
		List<PwReception> listReception = Util.jsonToVo(pwReception,
				PwReception.class);
		List<SysRecRepairItemDetail> listRecRepairItem = Util.jsonToVo(
				sysRecRepairItemDetail, SysRecRepairItemDetail.class);
		List<SysRecProductDetail> listRecProduct = Util.jsonToVo(
				sysRecProductDetail, SysRecProductDetail.class);
		List<SysRecOtherCostDetail> listRecOtherCost = Util.jsonToVo(
				sysRecOtherCostDetail, SysRecOtherCostDetail.class);
		List<SysInsuranceDetail> listArrInsuranceMoney = Util.jsonToVo(
				sysInsuranceDetail, SysInsuranceDetail.class);
		List<SysThreePacksDetail> listThreePacksDetail = Util.jsonToVo(
				sysThreePacksDetail, SysThreePacksDetail.class);
		return customerDao.updateListReceptione(listReception,
				listRecRepairItem, listRecProduct, listRecOtherCost,
				listArrInsuranceMoney, listThreePacksDetail);
	}

	@Override
	public Object selectReception(String toAudit, String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			Long pageSize, Long curPage) {
		// TODO Auto-generated method stub
		Long startIndex = 1L;
		Long totalRows = customerDao.selectReceptionCount(toAudit,
				maintenanceNum, carNum, documentStateID, balanceStateID);
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ReceptionVo> list = customerDao.selectReception(toAudit,
				maintenanceNum, carNum, documentStateID, balanceStateID,
				startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getList(json, curPage, totalRows);// 设置返回的参数
		return obj;
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		// TODO Auto-generated method stub
		return customerDao.selectReceptionDetail(receptionID);
	}

	@Override
	public boolean delectReception(int receptionID) {
		// TODO Auto-generated method stub
		return customerDao.delectReception(receptionID);
	}

	@Override
	public List<Object> customer(String pwPredate,
			String sysPreRepairItemDetail, String sysPreProductDetail,
			String sysPreOtherCostDetail) {
		List<Object> list = new ArrayList<Object>();
		List<PwPredate> listPredate = Util.jsonToVo(pwPredate, PwPredate.class);
		List<SysPreRepairItemDetail> listPreRepairItem = Util.jsonToVo(
				sysPreRepairItemDetail, SysPreRepairItemDetail.class);
		List<SysPreProductDetail> listPreProduct = Util.jsonToVo(sysPreProductDetail,
				SysPreProductDetail.class);
		List<SysPreOtherCostDetail> listPreOtherCost = Util.jsonToVo(
				sysPreOtherCostDetail, SysPreOtherCostDetail.class);
		list.add(listPreRepairItem);
		list.add(listPreProduct);
		list.add(listPreOtherCost);
		list.add(listPredate.get(0));
		return list;
	}
}
