package com.gx.service.impl;

import java.util.List;

import com.gx.dao.IClaimsetDao;
import com.gx.dao.impl.ClaimsetDaoImpl;
import com.gx.pojo.PW_Balance;
import com.gx.pojo.PW_Insurance;
import com.gx.pojo.PW_ThreePacks;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.service.IClaimsetService;
import com.gx.vo.ReceptionVo;
import com.gx.vo.ReturnJson;

public class ClaimsetServiceImpl implements IClaimsetService {
	private IClaimsetDao claimsetDao = new ClaimsetDaoImpl();

	@Override
	public int insuranceNum(String d) {
		return claimsetDao.insuranceNum(d);
	}

	@Override
	public List<ReceptionVo> selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			int startIndex, int pageSize) {
		return claimsetDao.selectDanHao(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, startIndex, pageSize);
	}

	@Override
	public int selectDanHaoCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		return claimsetDao.selectDanHaoCount(maintenanceNum, carNum, documentStateID, balanceStateID, toAudit);
	}

	@Override
	public List<ReceptionVo> selectThreePacks(String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			String toAudit, int startIndex, int pageSize) {
		return claimsetDao.selectThreePacks(maintenanceNum, carNum, documentStateID, balanceStateID, toAudit, startIndex, pageSize);
	}

	@Override
	public int selectThreePacksCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		return claimsetDao.selectThreePacksCount(maintenanceNum, carNum, documentStateID, balanceStateID, toAudit);
	}

	@Override
	public ReceptionVo selectThreePacksData(int insuranceDetailID) {
		return claimsetDao.selectThreePacksData(insuranceDetailID);
	}

	@Override
	public ReceptionVo selectThreePackss(int receptionID) {
		return claimsetDao.selectThreePackss(receptionID);
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		return claimsetDao.selectReceptionDetail(receptionID);
	}

	@Override
	public int baveThreePacks(List<PW_ThreePacks> listThreePacks,int claimComID) {
		return claimsetDao.baveThreePacks(listThreePacks, claimComID);
	}

	@Override
	public int toAudit(int threePacksID) {
		return claimsetDao.toAudit(threePacksID);
	}

	@Override
	public int toNotAudit(int threePacksID) {
		return claimsetDao.toNotAudit(threePacksID);
	}

	@Override
	public boolean delectThreePacks(int threePacksID) {
		return claimsetDao.delectThreePacks(threePacksID);
	}

	@Override
	public ReturnJson selectBalance(String businessNum) {
		return claimsetDao.selectBalance(businessNum);
	}

	@Override
	public PW_Balance selectBalances(int balanceID) {
		return claimsetDao.selectBalances(balanceID);
	}

	@Override
	public int baveBalance(List<PW_Balance> listBalance) {
		return claimsetDao.baveBalance(listBalance);
	}
}
