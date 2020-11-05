package com.gx.service.impl;

import java.util.List;

import com.gx.dao.IDispatchingDao;
import com.gx.dao.impl.DispatchingDaoImpl;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.service.IDispatchingService;
import com.gx.vo.ReceptionVo;

public class DispatchingServiceImpl implements IDispatchingService {
	private IDispatchingDao dispatchingDao = new DispatchingDaoImpl();

	@Override
	public int updateListRepairItemDetail(
			List<SYS_RecRepairItemDetail> listRecRepairItem,
			Double maintenAmount, String selfCoding, int receptionID,
			boolean toSendWork) {
		return dispatchingDao.updateListRepairItemDetail(listRecRepairItem,
				maintenAmount, selfCoding, receptionID, toSendWork);
	}

	@Override
	public double selectDispatch(int dispatchID) {
		return dispatchingDao.selectDispatch(dispatchID);
	}

	@Override
	public ReceptionVo selectReception(int receptionID) {
		return dispatchingDao.selectReception(receptionID);
	}

}
