package com.gx.service.impl;

import java.util.List;

import com.gx.dao.ISettlementDao;
import com.gx.dao.impl.SettlementDaoImpl;
import com.gx.pojo.PW_Balance;
import com.gx.service.ISettlementService;

public class SettlementSerivceImpl implements ISettlementService {
	private ISettlementDao settlementDao=new SettlementDaoImpl();

	@Override
	public int baveBalance(List<PW_Balance> listBalance) {
		return settlementDao.baveBalance(listBalance);
	}

	@Override
	public int toAudit(int receptionID) {
		return settlementDao.toAudit(receptionID);
	}

	@Override
	public int toNotAudit(int receptionID) {
		return settlementDao.toNotAudit(receptionID);
	}

	@Override
	public List<Object> selectBalanceDetail(int receptionID) {
		return settlementDao.selectBalanceDetail(receptionID);
	}
}
