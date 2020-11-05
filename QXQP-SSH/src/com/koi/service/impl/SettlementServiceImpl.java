package com.koi.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.ISettlementDao;
import com.koi.po.PwBalance;
import com.koi.service.ISettlementService;
import com.koi.util.Json;
import com.koi.util.Util;
@Transactional
@Service("settlementService")
public class SettlementServiceImpl implements ISettlementService {
	@Autowired
	private ISettlementDao settlementDao;

	@Override
	public int baveBalance(String pwBalance) {
		List<PwBalance> listBalance=Util.jsonToVo(pwBalance, PwBalance.class);
		return settlementDao.baveBalance(listBalance);
	}

	@Override
	public int toAudit(int receptionID) {
		// TODO Auto-generated method stub
		return settlementDao.toAudit(receptionID);
	}

	@Override
	public int toNotAudit(int receptionID) {
		// TODO Auto-generated method stub
		return settlementDao.toNotAudit(receptionID);
	}

	@Override
	public Object selectBalanceDetail(int receptionID) {
		Object obj = settlementDao.selectBalanceDetail(receptionID);
		/*JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		Object obj = JSONArray.fromObject(list, jsonConfig);*/
		return obj;
	}
}
