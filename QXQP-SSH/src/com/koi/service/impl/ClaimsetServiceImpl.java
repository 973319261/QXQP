package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.IClaimsetDao;
import com.koi.po.PwBalance;
import com.koi.po.PwThreePacks;
import com.koi.service.IClaimsetService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.ReturnJson;
import com.koi.vo.ThreePacksDetailVo;
import com.koi.vo.ThreePacksVo;
@Transactional
@Service("claimsetService")
public class ClaimsetServiceImpl implements IClaimsetService {
	@Autowired
	private IClaimsetDao claimsetDao;

	@Override
	public String insuranceNum() {
		Date date = new Date();
		SimpleDateFormat sfdate = new SimpleDateFormat("yyyyMMdd");
		String d = sfdate.format(date);
		int num = claimsetDao.insuranceNum(d) + 1;
		String str = "SP" + d + String.format("%04d", num);
		return str;
	}

	@Override
	public Object selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long curPage, Long pageSize) {
		Long totalRows = claimsetDao.selectDanHaoCount(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ThreePacksDetailVo> list = claimsetDao.selectDanHao(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit, startIndex,
				pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}

	@Override
	public Object selectThreePacks(String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			String toAudit, Long curPage, Long pageSize) {
		Long totalRows = claimsetDao.selectThreePacksCount(maintenanceNum,
				carNum, documentStateID, balanceStateID, toAudit);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ThreePacksVo> list = claimsetDao.selectThreePacks(
				maintenanceNum, carNum, documentStateID, balanceStateID,
				toAudit, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}


	@Override
	public Object selectThreePacksData(Long threePacksDetailID) {
		ThreePacksVo threePacksVo = claimsetDao
				.selectThreePacksData(threePacksDetailID);
		if(threePacksVo!=null){
			JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
			Object obj = JSONArray.fromObject(threePacksVo, jsonConfig);
			return obj;
		}
		return threePacksVo;
	}

	@Override
	public Object selectThreePackss(int receptionID) {
		ThreePacksDetailVo threePacksDetailVo = claimsetDao
				.selectThreePackss(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		Object obj = JSONArray.fromObject(threePacksDetailVo, jsonConfig);
		return obj;
	}

	@Override
	public Object selectReceptionDetail(int receptionID) {
		List<Object> list = claimsetDao.selectReceptionDetail(receptionID);
		return list;
	}

	@Override
	public int baveThreePacks(String pwThreePacks, int claimComID) {
		List<PwThreePacks> listThreePacks = Util.jsonToVo(pwThreePacks,
				PwThreePacks.class);
		return claimsetDao.baveThreePacks(listThreePacks, claimComID);
	}

	@Override
	public int toAudit(int threePacksID) {
		// TODO Auto-generated method stub
		return claimsetDao.toAudit(threePacksID);
	}

	@Override
	public int toNotAudit(int threePacksID) {
		// TODO Auto-generated method stub
		return claimsetDao.toNotAudit(threePacksID);
	}

	@Override
	public boolean delectThreePacks(int threePacksID) {
		// TODO Auto-generated method stub
		return claimsetDao.delectThreePacks(threePacksID);
	}

	@Override
	public ReturnJson selectBalance(String businessNum) {
		// TODO Auto-generated method stub
		return claimsetDao.selectBalance(businessNum);
	}

	@Override
	public PwBalance selectBalances(int balanceID) {
		// TODO Auto-generated method stub
		return claimsetDao.selectBalances(balanceID);
	}

	@Override
	public int baveBalance(String pwBalance) {
		List<PwBalance> listBalance = Util.jsonToVo(pwBalance, PwBalance.class);
		return claimsetDao.baveBalance(listBalance);
	}
}
