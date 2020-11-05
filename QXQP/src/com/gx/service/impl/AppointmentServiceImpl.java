package com.gx.service.impl;

import java.util.List;

import com.gx.dao.IAppointmentDao;
import com.gx.dao.impl.AppointmentDaoImpl;
import com.gx.pojo.PW_Predate;
import com.gx.pojo.SYS_PreOtherCostDetail;
import com.gx.pojo.SYS_PreProductDetail;
import com.gx.pojo.SYS_PreRepairItemDetail;
import com.gx.service.IAppointmentService;

public class AppointmentServiceImpl implements IAppointmentService{
	private IAppointmentDao appointmentDao=new AppointmentDaoImpl();
	@Override
	public int predateNum(String d) {
		return appointmentDao.predateNum(d);
	}
	@Override
	public List<PW_Predate> selectPredate(String PredateNum, String ToAudit,int startIndex,int pageSize){
		return appointmentDao.selectPredate(PredateNum, ToAudit, startIndex, pageSize);
	}
	@Override
	public int selectPredateCount(String PredateNum, String ToAudit) {
		return appointmentDao.selectPredateCount(PredateNum, ToAudit);
	}
	@Override
	public List<Object> selectPredateDetail(int predateID) {
		return appointmentDao.selectPredateDetail(predateID);
	}
	@Override
	public int updateListPredate(List<PW_Predate> listPredate,
			List<SYS_PreRepairItemDetail> listPreRepairItem,
			List<SYS_PreProductDetail> listPreProduct,
			List<SYS_PreOtherCostDetail> listPreOtherCost) {
		return appointmentDao.updateListPredate(listPredate, listPreRepairItem, listPreProduct, listPreOtherCost);
	}
	@Override
	public boolean deleteListPredate(int predateID) {
		return appointmentDao.deleteListPredate(predateID);
	}
	@Override
	public boolean toAudit(int psredateID) {
		return appointmentDao.toAudit(psredateID);
	}
	@Override
	public boolean toNotAudit(int predateID) {
		return appointmentDao.toNotAudit(predateID);
	}
	@Override
	public boolean selectToMainten(int predateID, String maintenanceNum) {
		return appointmentDao.selectToMainten(predateID, maintenanceNum);
	}
}
