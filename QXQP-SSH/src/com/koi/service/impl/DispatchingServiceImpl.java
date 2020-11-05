package com.koi.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.IDispatchingDao;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.service.IDispatchingService;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.ReceptionVo;

@Transactional
@Service("dispatchingService")
public class DispatchingServiceImpl implements IDispatchingService {
	@Autowired
	private IDispatchingDao dispatchingDao;

	@Override
	public int updateListRepairItemDetail(
			String sysRecRepairItemDetail,
			Double maintenAmount, String selfCoding, int receptionID,
			boolean toSendWork) {
		List<SysRecRepairItemDetail> listRecRepairItem=Util.jsonToVo(sysRecRepairItemDetail, SysRecRepairItemDetail.class);
		return dispatchingDao.updateListRepairItemDetail(listRecRepairItem,
				maintenAmount, selfCoding, receptionID, toSendWork);
	}

	@Override
	public double selectDispatch(int dispatchID) {
		// TODO Auto-generated method stub
		return dispatchingDao.selectDispatch(dispatchID);
	}

	@Override
	public Object selectReception(int receptionID) {
		ReceptionVo list = dispatchingDao.selectReception(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		return jsonArr;
	}
}
