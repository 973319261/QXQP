package com.koi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.IInsuranceDao;
import com.koi.po.PwInsurance;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.util.SqlHelper;
import com.koi.vo.InsuranceDetailVo;
import com.koi.vo.InsuranceVo;

@Repository("insuranceDao")
public class InsuranceDaoImpl implements IInsuranceDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
	String selectDanHao="SELECT * FROM(SELECT *,ROW_NUMBER()OVER(ORDER BY ReceptionID)Num FROM(SELECT p.*,InsuranceMoney,InsuranceDetailID,ReportNum,PolicyNum,PolicyMoney," +
			"SYS_InsuranceCom.InsuranceComID,RepairName,CustomerSou,DocumentState,BalanceState " +
			"FROM  PW_Reception p,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND SYS_InsuranceDetail." +
			"InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE 1=1 ";
	String selectToDanHao="SELECT p.*,InsuranceMoney,InsuranceDetailID,ReportNum,PolicyNum,PolicyMoney," +
			"SYS_InsuranceCom.InsuranceComID,RepairName,CustomerSou,DocumentState,BalanceState " +
			"FROM  PW_Reception p,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND SYS_InsuranceDetail." +
			"InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID AND p.ReceptionID = ?";
	String selectDanHaoCount="SELECT COUNT(*) as num FROM  PW_Reception p,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND SYS_InsuranceDetail." +
			"InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	String selectInsurance="SELECT * FROM(SELECT *,ROW_NUMBER()OVER(ORDER BY ReceptionID)Num FROM(SELECT i.*,CustomerNum,p.ReceptionID,CarNum,VehicleType,MaintenanceNum,Owner,InsuranceMoney,ReportNum," +
			"PolicyNum,PolicyMoney,SYS_InsuranceCom.InsuranceComID,RepairName,CustomerSou,DocumentState,p.DocumentStateID,BalanceState,p.BalanceStateID" +
			" FROM PW_Reception p,PW_Insurance i,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair," +
			"SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND i.InsuranceDetailID=" +
			"SYS_InsuranceDetail.InsuranceDetailID AND SYS_InsuranceDetail.InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID =" +
			"SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID " +
			"AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE 1=1 ";
	String selectToInsurance="SELECT i.*,CustomerNum,p.ReceptionID,CarNum,VehicleType,MaintenanceNum,Owner,InsuranceMoney,ReportNum," +
			"PolicyNum,PolicyMoney,SYS_InsuranceCom.InsuranceComID,RepairName,CustomerSou,DocumentState,p.DocumentStateID,BalanceState,p.BalanceStateID" +
			" FROM PW_Reception p,PW_Insurance i,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair," +
			"SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND i.InsuranceDetailID=" +
			"SYS_InsuranceDetail.InsuranceDetailID AND SYS_InsuranceDetail.InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID =" +
			"SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID " +
			"AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID AND i.InsuranceDetailID = ?";
	String selectInsuranceCount="SELECT COUNT(*) as num FROM  PW_Reception p,PW_Insurance i,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair," +
			"SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND i.InsuranceDetailID=" +
			"SYS_InsuranceDetail.InsuranceDetailID AND SYS_InsuranceDetail.InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID =" +
			"SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID " +
			"AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	@Override
	public int insuranceNum(String d) {
		String insuranceNum = "SELECT TOP 1 SUBSTRING(InsuranceNum,11,4) as num from PW_Insurance "
				+ "WHERE InsuranceNum LIKE '%"
				+ d
				+ "%' ORDER BY  InsuranceNum DESC";
		Long num = 0l;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(insuranceNum);
		sqlQuery.addScalar("num", Hibernate.STRING);
		Object str = sqlQuery.uniqueResult();
		num = str == null ? 0l : Integer.valueOf(str.toString());
		return num.intValue();
	}

	@Override
	public List<InsuranceDetailVo> selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long startIndex, Long pageSize) {
		List<InsuranceDetailVo> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectDanHao);
		if (!"".equals(toAudit)) {
			buffer.append(" AND ToAudit =" + toAudit);
		}
		if (!"".equals(maintenanceNum)) {
			buffer.append(" AND MaintenanceNum like '%" + maintenanceNum + "%'");
		}
		if (!"".equals(carNum)) {
			buffer.append(" AND CarNum like '%" + carNum + "%'");
		}
		if (documentStateID > 0) {
			buffer.append(" AND DocumentStateID =" + documentStateID);
		}
		if (balanceStateID > 0) {
			buffer.append(" AND BalanceStateID =" + balanceStateID);
		}
		buffer.append(")AS db WHERE db.Num BETWEEN " + startIndex + " AND "
				+ pageSize);
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString())
				.addEntity(InsuranceDetailVo.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectDanHaoCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectDanHaoCount);
		if (!"".equals(toAudit)) {
			buffer.append(" AND p.ToAudit =" + toAudit);
		}
		if (!"".equals(maintenanceNum)) {
			buffer.append(" AND p.MaintenanceNum like '%" + maintenanceNum
					+ "%'");
		}
		if (!"".equals(carNum)) {
			buffer.append(" AND p.CarNum like '%" + carNum + "%'");
		}
		if (documentStateID > 0) {
			buffer.append(" AND p.DocumentStateID =" + documentStateID);
		}
		if (balanceStateID > 0) {
			buffer.append(" AND p.BalanceStateID =" + balanceStateID);
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString());
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public List<InsuranceVo> selectInsurance(String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			String toAudit, Long startIndex, Long pageSize) {
		List<InsuranceVo> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectInsurance);
		if (!"".equals(toAudit)) {
			buffer.append(" AND ToAudit =" + toAudit);
		}
		if (!"".equals(maintenanceNum)) {
			buffer.append(" AND MaintenanceNum like '%" + maintenanceNum + "%'");
		}
		if (!"".equals(carNum)) {
			buffer.append(" AND CarNum like '%" + carNum + "%'");
		}
		if (documentStateID > 0) {
			buffer.append(" AND DocumentStateID =" + documentStateID);
		}
		if (balanceStateID > 0) {
			buffer.append(" AND BalanceStateID =" + balanceStateID);
		}
		buffer.append(")AS db WHERE db.Num BETWEEN " + startIndex + " AND "
				+ pageSize);
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString())
				.addEntity(InsuranceVo.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectInsuranceCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectInsuranceCount);
		if (!"".equals(toAudit)) {
			buffer.append(" AND p.ToAudit =" + toAudit);
		}
		if (!"".equals(maintenanceNum)) {
			buffer.append(" AND p.MaintenanceNum like '%" + maintenanceNum
					+ "%'");
		}
		if (!"".equals(carNum)) {
			buffer.append(" AND p.CarNum like '%" + carNum + "%'");
		}
		if (documentStateID > 0) {
			buffer.append(" AND p.DocumentStateID =" + documentStateID);
		}
		if (balanceStateID > 0) {
			buffer.append(" AND p.BalanceStateID =" + balanceStateID);
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString());
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public InsuranceVo selectInsuranceData(int insuranceDetailID) {
		InsuranceVo list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectToInsurance);
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString()).addEntity(InsuranceVo.class);
		sqlQuery.setLong(0, insuranceDetailID);
		list =  (InsuranceVo) sqlQuery.uniqueResult();
		return list;
	}

	@Override
	public InsuranceDetailVo selectInsurances(int receptionID) {
		InsuranceDetailVo list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectToDanHao).addEntity(InsuranceDetailVo.class);
		sqlQuery.setInteger(0, receptionID);
		list = (InsuranceDetailVo) sqlQuery.uniqueResult();
		return list;
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		List<Object> list=new ArrayList<Object>();
		List<SysRecRepairItemDetail> listRecRepairItemDetail=this.listRecRepairItemDetail(receptionID);
		List<SysCollageDetai> listCollageDetai=this.listCollageDetai(receptionID);
		list.add(listRecRepairItemDetail);
		list.add(listCollageDetai);
		return list;
		
	}
	
	@Override
	public int bavaInsurance(List<PwInsurance> listInsurance,
			List<SysInsuranceDetail> listInsuranceDetail) {
		int insuranceID=0;
		if(listInsurance.get(0).getInsuranceId()==null){
			insuranceID=sqlHelper.insert(listInsurance.get(0));
		}else{
			sqlHelper.update(listInsurance.get(0));
			insuranceID=listInsurance.get(0).getInsuranceId();
		}
		sqlHelper.update(listInsuranceDetail.get(0));
	    return insuranceID;
	    
		
	}

	@Override
	public int toAudit(int insuranceID) {
		int i=0;
	    List<PwInsurance> list = (List<PwInsurance>) sqlHelper.select(
	    		PwInsurance.class, "InsuranceID", insuranceID);
		list.get(0).setToAudit(true);
		i = sqlHelper.update(list.get(0));
	    return i;
	}

	@Override
	public int toNotAudit(int insuranceID) {
		int i=0;
		List<PwInsurance> list = (List<PwInsurance>) sqlHelper.select(
	    		PwInsurance.class, "InsuranceID", insuranceID);
		list.get(0).setToAudit(false);
		i = sqlHelper.update(list.get(0));
	    return i;
	}

	@Override
	public boolean delectInsurance(int insuranceID) {
		return sqlHelper.delete(PwInsurance.class, "InsuranceID", insuranceID);
	}
	
	//SYS_RecRepairItemDetail修理项目明细表
	private List<SysRecRepairItemDetail> listRecRepairItemDetail(int receptionID){
		String sql="SELECT * FROM SYS_RecRepairItemDetail WHERE ReceptionID = ? AND MaintainabilityID=6";
		List<SysRecRepairItemDetail> listRecRepairItemDetail = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(
				SysRecRepairItemDetail.class);
		sqlQuery.setInteger(0, receptionID);
		listRecRepairItemDetail = sqlQuery.list();
		return listRecRepairItemDetail;
	}
	
	//SYS_RecProductDetail产品明细表
	private List<SysCollageDetai> listCollageDetai(int receptionID){
		String sql="SELECT * FROM PW_Reception r,PW_Collage c,SYS_CollageDetai WHERE r.ReceptionID=c.ReceptionID AND " +
				"c.CollageID=SYS_CollageDetai.CollageID AND r.ReceptionID=? AND SYS_CollageDetai.MaintainabilityID=6";
		List<SysCollageDetai> listCollageDetai = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(
				SysCollageDetai.class);
		sqlQuery.setInteger(0, receptionID);
		listCollageDetai = sqlQuery.list();
		return listCollageDetai;
	}
}
