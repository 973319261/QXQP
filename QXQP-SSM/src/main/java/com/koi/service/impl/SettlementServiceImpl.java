package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.CommonDao;
import com.koi.mapper.SettlementDao;
import com.koi.po.PwBalance;
import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.service.ISettlementService;
import com.koi.util.Util;
@Transactional
@Service("settlementService")
public class SettlementServiceImpl implements ISettlementService {
	@Autowired
	private SettlementDao settlementDao;
	@Autowired
	private CommonDao commonDao;
	private SimpleDateFormat fs=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Override
	public int baveBalance(String pwBalance) {
		PwBalance listBalance=Util.jsonToVo(pwBalance, PwBalance.class).get(0);
		int i = 0;
		int receptionID = listBalance.getReceptionId();
		double shouldAmount = listBalance.getShouldAmount() != null ? listBalance
				.getShouldAmount() : 0.0;
		listBalance.setDocumentsTypeId(1);
		PwReception listReception = commonDao.selectReceptionById( receptionID);
		listReception.setToCompletion(true);// 已完工
		listReception.setCompletionDate(fs.format(new Date()));// 完工日期
		if (listBalance.getBalanceId() != null) {
			i = listBalance.getBalanceId();
			PwBalance oldBalance = commonDao.selectBalanceById(i);
			oldBalance.setOptimalAmount(oldBalance.getOptimalAmount()
					+ listBalance.getOptimalAmount());// 优惠金额
			oldBalance.setCollectionAmount(oldBalance.getCollectionAmount()
					+ listBalance.getCollectionAmount());// 收款金额
			if (shouldAmount <= oldBalance.getOptimalAmount()
					+ oldBalance.getCollectionAmount()) {
				listReception.setDocumentStateId(3);// 完工已结算
				listReception.setBalanceStateId(1);// 付讫（已清）
				oldBalance.setBalanceStateId(1);// 付讫（已清）
			} else {
				listReception.setDocumentStateId(3);// 完工已结算
				listReception.setBalanceStateId(2);// 挂账（部分）
				oldBalance.setBalanceStateId(2);// 挂账（部分）
			}
			commonDao.updateBalance(oldBalance);
		} else {
			double optimalAmount = listBalance.getOptimalAmount() != null ? listBalance
					.getOptimalAmount() : 0.0;// 优惠金额
			double collectionAmount = listBalance.getCollectionAmount() != null ? listBalance
					.getCollectionAmount() : 0.0;// 收款金额
			listBalance.setDocumentsTypeId(2);
			if (shouldAmount <= optimalAmount + collectionAmount) {
				listReception.setDocumentStateId(3);// 完工已结算
				listReception.setBalanceStateId(1);// 付讫（已清）
				listBalance.setBalanceStateId(1);// 付讫（已清）
			} else {
				listReception.setDocumentStateId(3);// 完工已结算
				listReception.setBalanceStateId(2);// 挂账（部分）
				listBalance.setBalanceStateId(2);// 挂账（部分）
			}
			commonDao.insertBalance(listBalance);
		}
		i=commonDao.updateReception(listReception);
		return i;
	}

	@Override
	public int toAudit(int receptionID) {
		// TODO Auto-generated method stub
		return settlementDao.toAudit(true,receptionID);
	}

	@Override
	public int toNotAudit(int receptionID) {
		// TODO Auto-generated method stub
		return settlementDao.toAudit(false,receptionID);
	}

	@Override
	public Object selectBalanceDetail(int receptionID) {
		List<Object> list = new ArrayList<Object>();
		List<SysRecRepairItemDetail> listRecRepairItemDetail = (List<SysRecRepairItemDetail>) commonDao.selectRecRepairItemDetail(receptionID);
		List<SysCollageDetai> listCollageDetai=settlementDao.selectCollageDetai(receptionID);//领料明细表
		List<SysRecOtherCostDetail> listRecOtherCostDetail = commonDao.selectRecOtherCostDetail(receptionID);
		List<SysInsuranceDetail> listInsuranceDetail = (List<SysInsuranceDetail>) commonDao.selectInsuranceDetail(receptionID);
		List<SysThreePacksDetail> listThreePacksDetail = (List<SysThreePacksDetail>) commonDao.selectThreePacksDetail(receptionID);
		list.add(listRecRepairItemDetail);
		list.add(listCollageDetai);
		list.add(listRecOtherCostDetail);
		list.add(listInsuranceDetail);
		list.add(listThreePacksDetail);
		return list;
	}
}
