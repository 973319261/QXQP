package com.gx.service.impl;

import java.util.List;

import com.gx.dao.ICustomerDao;
import com.gx.dao.impl.CustomerDaoImpl;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecProductDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.service.ICustomerService;
import com.gx.vo.ReceptionVo;

public class CustomerServiceImpl implements ICustomerService {
	private ICustomerDao customerDao = new CustomerDaoImpl();

	@Override
	public int maintenanceNum(String d) {
		return customerDao.maintenanceNum(d);
	}

	@Override
	public int updateListReceptione(List<PW_Reception> listReception,
			List<SYS_RecRepairItemDetail> listRecRepairItem,
			List<SYS_RecProductDetail> listRecProduct,
			List<SYS_RecOtherCostDetail> listRecOtherCost,
			List<SYS_InsuranceDetail> listArrInsuranceMoney,
			List<SYS_ThreePacksDetail> listThreePacksDetail) {
		return customerDao.updateListReceptione(listReception,
				listRecRepairItem, listRecProduct, listRecOtherCost,
				listArrInsuranceMoney, listThreePacksDetail);
	}

	@Override
	public List<ReceptionVo> selectReception(String toAudit,
			String maintenanceNum, String carNum, int documentStateID,
			int balanceStateID, int startIndex, int pageSize) {
		return customerDao.selectReception(toAudit, maintenanceNum, carNum,
				documentStateID, balanceStateID, startIndex, pageSize);
	}

	@Override
	public int selectReceptionCount(String toAudit, String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID) {
		return customerDao.selectReceptionCount(toAudit, maintenanceNum, carNum,
				documentStateID, balanceStateID);
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		return customerDao.selectReceptionDetail(receptionID);
	}

	@Override
	public boolean delectReception(int receptionID) {
		return customerDao.delectReception(receptionID);
	}
}
