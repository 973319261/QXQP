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

import com.koi.mapper.CommonDao;
import com.koi.mapper.InsuranceDao;
import com.koi.po.PwInsurance;
import com.koi.po.PwThreePacks;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.service.IInsuranceService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.InsuranceDetailVo;
import com.koi.vo.InsuranceVo;
import com.koi.vo.ReceptionVo;

@Transactional
@Service("insuranceService")
public class InsuranceServiceImpl implements IInsuranceService {
	@Autowired
	private InsuranceDao insuranceDao;
	@Autowired
	private CommonDao commonDao;
	@Override
	public String insuranceNum() {
		Date date = new Date();
		SimpleDateFormat sfdate = new SimpleDateFormat("yyyyMMdd");
		String d = sfdate.format(date);
		int num = insuranceDao.insuranceNum(d) + 1;
		String str = "LP" + d + String.format("%04d", num);
		return str;
	}

	@Override
	public Object selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long curPage, Long pageSize) {
		Long totalRows = insuranceDao.selectDanHaoCount(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<InsuranceDetailVo> list = insuranceDao.selectDanHao(
				maintenanceNum, carNum, documentStateID, balanceStateID,
				toAudit, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}

	@Override
	public Object selectInsurance(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long curPage, Long pageSize) {
		Long totalRows = insuranceDao.selectInsuranceCount(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<InsuranceVo> list = insuranceDao.selectInsurance(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit, startIndex,
				pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}

	@Override
	public Object selectInsuranceData(int insuranceDetailID) {
		InsuranceVo insuranceVo = insuranceDao
				.selectInsuranceData(insuranceDetailID);
		if(insuranceVo!=null){
			JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
			Object obj = JSONArray.fromObject(insuranceVo, jsonConfig);
			return obj;
		}
		return insuranceVo;
	}

	@Override
	public Object selectInsurances(int receptionID) {
		InsuranceDetailVo insuranceDetailVo = insuranceDao.selectInsurances(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		Object obj = JSONArray.fromObject(insuranceDetailVo, jsonConfig);
		return obj;
	}

	@Override
	public Object selectReceptionDetail(int receptionID) {
		List<Object> list=new ArrayList<Object>();
		List<SysRecRepairItemDetail> listRecRepairItemDetail=insuranceDao.selectReceptionDetail(receptionID);
		List<SysCollageDetai> listCollageDetai=insuranceDao.selectCollageDetai(receptionID);
		list.add(listRecRepairItemDetail);
		list.add(listCollageDetai);
		return list;
	}

	@Override
	public int bavaInsurance(String pwInsurance,
			String sysInsuranceDetail) {
		PwInsurance listInsurance = Util.jsonToVo(
				pwInsurance, PwInsurance.class).get(0);
		SysInsuranceDetail liInsuranceDetail = Util.jsonToVo(sysInsuranceDetail, SysInsuranceDetail.class).get(0);
		int insuranceID=0;
		if(listInsurance.getInsuranceId()==null){
			insuranceDao.insertInsurance(listInsurance);
		}else{
			insuranceDao.updateInsurance(listInsurance);
			
		}
		insuranceID=listInsurance.getInsuranceId();
		commonDao.updateInsuranceDetail(liInsuranceDetail);
	    return insuranceID;
	}

	@Override
	public int toAudit(int insuranceID) {
		int i = 0;
		PwInsurance pwInsurance =insuranceDao.selectInsuranceById(insuranceID);
		pwInsurance.setToAudit(true);
		i = insuranceDao.updateInsurance(pwInsurance);
		return i;
	}

	@Override
	public int toNotAudit(int insuranceID) {
		int i = 0;
		PwInsurance pwInsurance =insuranceDao.selectInsuranceById(insuranceID);
		pwInsurance.setToAudit(false);
		i = insuranceDao.updateInsurance(pwInsurance);
		return i;
	}

	@Override
	public boolean delectInsurance(int insuranceID) {
		// TODO Auto-generated method stub
		return insuranceDao.delectInsurance(insuranceID);
	}
}
