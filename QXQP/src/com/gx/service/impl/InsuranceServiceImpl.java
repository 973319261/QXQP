package com.gx.service.impl;

import java.util.List;

import com.gx.dao.IInsuranceDao;
import com.gx.dao.impl.InsuranceDaoImpl;
import com.gx.pojo.PW_Insurance;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.service.IInsuranceService;
import com.gx.vo.ReceptionVo;

public class InsuranceServiceImpl implements IInsuranceService {
	private IInsuranceDao insuranceDao = new InsuranceDaoImpl();

	@Override
	public int insuranceNum(String d) {
		return insuranceDao.insuranceNum(d);
	}

	@Override
	public List<ReceptionVo> selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			int startIndex, int pageSize) {
		return insuranceDao.selectDanHao(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, startIndex, pageSize);
	}

	@Override
	public int selectDanHaoCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		return insuranceDao.selectDanHaoCount(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit);
	}

	@Override
	public List<ReceptionVo> selectInsurance(String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			String toAudit, int startIndex, int pageSize) {
		return insuranceDao.selectInsurance(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit, startIndex, pageSize);
	}

	@Override
	public int selectInsuranceCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		return insuranceDao.selectInsuranceCount(maintenanceNum, carNum,
				documentStateID, balanceStateID, toAudit);
	}

	@Override
	public ReceptionVo selectInsuranceData(int insuranceDetailID) {
		return insuranceDao.selectInsuranceData(insuranceDetailID);
	}

	@Override
	public ReceptionVo selectInsurances(int receptionID) {
		return insuranceDao.selectInsurances(receptionID);
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		return insuranceDao.selectReceptionDetail(receptionID);
	}

	@Override
	public int bavaInsurance(List<PW_Insurance> listInsurance,
			List<SYS_InsuranceDetail> listInsuranceDetail) {
		return insuranceDao.bavaInsurance(listInsurance, listInsuranceDetail);
	}

	@Override
	public int toAudit(int insuranceID) {
		return insuranceDao.toAudit(insuranceID);
	}

	@Override
	public int toNotAudit(int insuranceID) {
		return insuranceDao.toNotAudit(insuranceID);
	}

	@Override
	public boolean delectInsurance(int insuranceID) {
		return insuranceDao.delectInsurance(insuranceID);
	}
}
