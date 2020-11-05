package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.IAppointmentDao;
import com.koi.po.PwPredate;
import com.koi.po.SysPreOtherCostDetail;
import com.koi.po.SysPreProductDetail;
import com.koi.po.SysPreRepairItemDetail;
import com.koi.service.IAppointmentService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
@Transactional
@Service("appointmentService")
public class AppointmentServiceImpl implements IAppointmentService {
	@Autowired
	private IAppointmentDao appointmentDao;

	@Override
	public String predateNum() {
		Date date = new Date();
		SimpleDateFormat sfdate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sfdatetime = new SimpleDateFormat("yyyyMMddHHmmss");
		String d = sfdate.format(date);
		String dt = sfdatetime.format(date);
		int num = appointmentDao.predateNum(d) + 1;
		String str = "BJ" + dt + String.format("%04d", num);
		return str;
	}

	@Override
	public Object selectPredate(String predateNum, String toAudit,
			 Long pageSize,Long curPage) {
		// TODO Auto-generated method stub
		Long totalRows = appointmentDao.selectPredateCount(predateNum,
				toAudit);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<PwPredate> list = appointmentDao.selectPredate(predateNum,
				toAudit, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getList(json, curPage, totalRows);// 设置返回的参数
		return obj;
	}


	@Override
	public List<Object> selectPredateDetail(int predateID) {
		// TODO Auto-generated method stub
		return appointmentDao.selectPredateDetail(predateID);
	}

	@Override
	public int updateListPredate(String predate,
			String preRepairItem,
			String preProduct,
			String preOtherCost) {
		List<PwPredate> listPredate = Util.jsonToVo(predate, PwPredate.class);
		List<SysPreRepairItemDetail> listPreRepairItem = Util.jsonToVo(preRepairItem, SysPreRepairItemDetail.class);
		List<SysPreProductDetail> listPreProduct = Util.jsonToVo(preProduct, SysPreProductDetail.class);
		List<SysPreOtherCostDetail> listPreOtherCost = Util.jsonToVo(preOtherCost, SysPreOtherCostDetail.class);
		// TODO Auto-generated method stub
		return appointmentDao.updateListPredate(listPredate, listPreRepairItem, listPreProduct, listPreOtherCost);
	}

	@Override
	public boolean deleteListPredate(int predateID) {
		// TODO Auto-generated method stub
		return appointmentDao.deleteListPredate(predateID);
	}

	@Override
	public boolean toAudit(int psredateID) {
		// TODO Auto-generated method stub
		return appointmentDao.toAudit(psredateID);
	}

	@Override
	public boolean toNotAudit(int predateID) {
		// TODO Auto-generated method stub
		return appointmentDao.toNotAudit(predateID);
	}

	@Override
	public boolean selectToMainten(int predateID, String maintenanceNum) {
		// TODO Auto-generated method stub
		return appointmentDao.selectToMainten(predateID, maintenanceNum);
	}
}
