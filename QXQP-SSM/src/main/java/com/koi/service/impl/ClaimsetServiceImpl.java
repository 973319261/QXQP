package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.ClaimsetDao;
import com.koi.mapper.CommonDao;
import com.koi.po.PwBalance;
import com.koi.po.PwThreePacks;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.service.IClaimsetService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.ReturnJson;
import com.koi.vo.ThreePacksDetailVo;
import com.koi.vo.ThreePacksVo;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
@Transactional
@Service("claimsetService")
public class ClaimsetServiceImpl implements IClaimsetService {
	@Autowired
	private ClaimsetDao claimsetDao;
	@Autowired
	private CommonDao commonDao;
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
		List<Object> list = new ArrayList<Object>();
		List<SysRecRepairItemDetail> listRecRepairItemDetail = claimsetDao.selectReceptionDetail(receptionID);
		List<SysCollageDetai> listCollageDetai = claimsetDao.selectCollageDetai(receptionID);
		list.add(listRecRepairItemDetail);
		list.add(listCollageDetai);
		return list;
	}

	@Override
	public int baveThreePacks(String pwThreePacks, int claimComID) {
		PwThreePacks listThreePacks = Util.jsonToVo(pwThreePacks,
				PwThreePacks.class).get(0);
		int threePacksID = 0;
		if (listThreePacks.getThreePacksId() == null) {
			claimsetDao.insertThreePacks(listThreePacks);
		} else {
			claimsetDao.updateThreePacks(listThreePacks);
		}
		threePacksID = listThreePacks.getThreePacksId();
		int threePacksDetailID = listThreePacks.getThreePacksDetailId();
		SysThreePacksDetail threePacksDetail = commonDao.selectThreePacksDetailById(threePacksDetailID);
		commonDao.updateThreePacksDetail(threePacksDetail);
		return threePacksID;
	}

	@Override
	public int toAudit(int threePacksID) {
		int i = 0;
		PwThreePacks listThreePacks =claimsetDao.selectThreePacksById(threePacksID);
		listThreePacks.setToAudit(true);
		i = claimsetDao.updateThreePacks(listThreePacks);
		return i;
	}

	@Override
	public int toNotAudit(int threePacksID) {
		int i = 0;
		PwThreePacks listThreePacks =claimsetDao.selectThreePacksById(threePacksID);
		listThreePacks.setToAudit(false);
		i = claimsetDao.updateThreePacks(listThreePacks);
		return i;
	}

	@Override
	public boolean delectThreePacks(int threePacksID) {
		// TODO Auto-generated method stub
		return claimsetDao.delectThreePacks(threePacksID);
	}

	@Override
	public ReturnJson selectBalance(String businessNum) {
		// TODO Auto-generated method stub
		ReturnJson returnJson = new ReturnJson();
		PwBalance pw_Balance = commonDao.selectBalance(businessNum);;
		if (pw_Balance != null) {
			if (pw_Balance.getBalanceStateId() == 1) {
				returnJson.setState(false);
				returnJson.setText("您和该用户没有付款信息，或你们往来账已平衡！");
				returnJson
						.setObjData("该单已有付款记录，不能反审核，如要更改费用，请到财务管理客户费用中进行平衡处理。");
			} else if (pw_Balance.getBalanceStateId() == 2) {
				returnJson.setState(true);
				returnJson.setStates("A");
				returnJson.setText(pw_Balance.getBalanceId().toString());
				returnJson
						.setObjData("该单已有付款记录，不能反审核，如要更改费用，请到财务管理客户费用中进行平衡处理。");
			}
		} else {
			returnJson.setState(true);
		}
		return returnJson;
	}

	@Override
	public PwBalance selectBalances(int balanceID) {
		// TODO Auto-generated method stub
		return commonDao.selectBalanceById(balanceID);
	}

	@Override
	public int baveBalance(String pwBalance) {
		PwBalance listBalance = Util.jsonToVo(pwBalance, PwBalance.class).get(0);
		int i = 0;
		double optimalAmount = listBalance.getOptimalAmount() == null ? 0.0
				: listBalance.getOptimalAmount();
		if (listBalance.getBalanceId() != null) {

			PwBalance oldBalance =commonDao.selectBalanceById(listBalance.getBalanceId());
			oldBalance.setOptimalAmount(
					oldBalance.getOptimalAmount() + optimalAmount);// 优惠金额
			oldBalance.setCollectionAmount(
					oldBalance.getCollectionAmount()
							+ listBalance.getCollectionAmount());// 收款金额
			if (listBalance.getShouldAmount() <= oldBalance.getOptimalAmount()
					+ oldBalance.getCollectionAmount()) {
				oldBalance.setBalanceStateId(1);// 付讫（已清）
			} else {
				oldBalance.setBalanceStateId(2);// 挂账（部分）
			}
			commonDao.updateBalance(oldBalance);
			i = listBalance.getBalanceId();
		} else {
			listBalance.setDocumentsTypeId(2);
			if (listBalance.getShouldAmount() <= optimalAmount
					+ listBalance.getCollectionAmount()) {
				listBalance.setBalanceStateId(1);// 付讫（已清）
			} else {
				listBalance.setBalanceStateId(2);// 挂账（部分）
			}
			i = commonDao.insertBalance(listBalance);
		}
		return i;
	}
}
