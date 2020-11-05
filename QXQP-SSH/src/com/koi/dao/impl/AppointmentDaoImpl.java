package com.koi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.IAppointmentDao;
import com.koi.po.PwPredate;
import com.koi.po.SysPreOtherCostDetail;
import com.koi.po.SysPreProductDetail;
import com.koi.po.SysPreRepairItemDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.util.SqlHelper;

@Repository("appointmentDao")
public class AppointmentDaoImpl implements IAppointmentDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
	String selectPredate = "SELECT * FROM(SELECT *,ROW_NUMBER()OVER(ORDER BY PredateID)Num FROM(SELECT p.PredateID,p.CarderID,p.RepairID,CarderName,DeserveMoney,VehicleType,CustomerNum,RepairName,PredateNum,"
			+ "OpenDate,MaintenanceNum,MaintainData,CarNum,Owner,CarMasterPhone,Contacts,Telephone,p.Remark,ToAudit,Amount,Receivable,Describe,"
			+ "ToTransferOrder FROM  PW_Predate p,SYS_Carder,SYS_Repair where p.CarderID = SYS_Carder.CarderID "
			+ "and p.RepairID = SYS_Repair.RepairID)AS tmp WHERE 1=1 ";
	String selectPredateCount = "SELECT COUNT(*) as num FROM  PW_Predate p,SYS_Carder,SYS_Repair where p.CarderID = SYS_Carder.CarderID "
			+ "and p.RepairID = SYS_Repair.RepairID";
	String updateAudit = "UPDATE PW_Predate SET ToAudit=? WHERE PredateID=?";
	String selectToMainten = "UPDATE PW_Predate SET ToTransferOrder=?,MaintenanceNum=? WHERE PredateID=?";

	@Override
	public int predateNum(String d) {
		String predateNum = "SELECT TOP 1 SUBSTRING(PredateNum,17,4) as num from PW_Predate "
				+ "WHERE PredateNum LIKE '%"
				+ d
				+ "%' ORDER BY  PredateNum DESC";
		Long num = 0l;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(predateNum);
		sqlQuery.addScalar("num", Hibernate.STRING);
		Object str = sqlQuery.uniqueResult();
		num = str == null ? 0l : Integer.valueOf(str.toString());
		return num.intValue();
	}

	@Override
	public List<PwPredate> selectPredate(String predateNum, String toAudit,
			Long startIndex, Long pageSize) {
		List<PwPredate> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectPredate);
		if (!"".equals(predateNum)) {
			buffer.append(" AND PredateNum like '%" + predateNum + "%'");
		}
		if (!"".equals(toAudit)) {
			buffer.append(" AND ToAudit =" + toAudit);
		}
		buffer.append(")AS db WHERE db.Num BETWEEN "+ startIndex + " AND "+ pageSize);
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString())
				.addEntity(PwPredate.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectPredateCount(String predateNum, String toAudit) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectPredateCount);
		if (!"".equals(predateNum)) {
			buffer.append(" AND PredateNum like '%" + predateNum + "%'");
		}
		if (!"".equals(toAudit)) {
			buffer.append(" AND p.ToAudit =" + toAudit);
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString());
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public List<Object> selectPredateDetail(int predateID) {
		List<Object> list = new ArrayList<Object>();
		session = sessionFactory.getCurrentSession();
		List<SysPreRepairItemDetail> listPreRepairItemDetail = (List<SysPreRepairItemDetail>) sqlHelper
				.select(SysPreRepairItemDetail.class, "PredateID", predateID);
		List<SysPreProductDetail> listPreProductDetail = (List<SysPreProductDetail>) sqlHelper
				.select(SysPreProductDetail.class, "PredateID", predateID);
		List<SysPreOtherCostDetail> listPreOtherCostDetail = (List<SysPreOtherCostDetail>) sqlHelper
				.select(SysPreOtherCostDetail.class, "PredateID", predateID);

		list.add(listPreRepairItemDetail);
		list.add(listPreProductDetail);
		list.add(listPreOtherCostDetail);
		return list;
	}

	@Override
	public int updateListPredate(List<PwPredate> listPredate,
			List<SysPreRepairItemDetail> listPreRepairItem,
			List<SysPreProductDetail> listPreProduct,
			List<SysPreOtherCostDetail> listPreOtherCost) {
		int id = 0;
		session = sessionFactory.getCurrentSession();
		if (listPredate.get(0).getPredateId() == null) {
			id = sqlHelper.insert(listPredate.get(0));// 新增
		} else {
			sqlHelper.update(listPredate.get(0));// 修改
			id = Integer.valueOf(listPredate.get(0).getPredateId().toString());
		}
		if (id > 0) {// 主表保存成功
			List<Integer> oldID = new ArrayList<Integer>();// 原来ID
			List<Integer> newID = new ArrayList<Integer>();// 传过来的ID
			if (listPreRepairItem.size() != 0) {// 修理项目明细表有数据
				// 维修明细表
				List<SysPreRepairItemDetail> list = (List<SysPreRepairItemDetail>) sqlHelper
						.select(SysPreRepairItemDetail.class, "PredateID", id);// 修理项目明细表
				for (SysPreRepairItemDetail item : list) {
					oldID.add(item.getPreRepairItemDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreRepairItem.size(); i++) {// 遍历修理项目明细表
					listPreRepairItem.get(i).setPredateId(id);
					if (listPreRepairItem.get(i).getPreRepairItemDetailId() == null) {
						sqlHelper.insert(listPreRepairItem.get(i));// 新增
					} else {
						newID.add(listPreRepairItem.get(i)
								.getPreRepairItemDetailId());
						sqlHelper.update(listPreRepairItem.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					sqlHelper.delete(SysPreRepairItemDetail.class,
							"PreRepairItemDetailID", item);
				}
			} else {
				sqlHelper.delete(SysPreRepairItemDetail.class, "PredateID", id);
			}

			if (listPreProduct.size() != 0) {// /配件明细表有数据
				oldID.clear();
				newID.clear();
				List<SysPreProductDetail> list = (List<SysPreProductDetail>) sqlHelper
						.select(SysPreProductDetail.class, "PredateID", id);// 配件明细表
				for (SysPreProductDetail item : list) {
					oldID.add(item.getPreProductDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreProduct.size(); i++) {// 遍历修理项目明细表
					listPreProduct.get(i).setPredateId(id);
					if (listPreProduct.get(i).getPreProductDetailId() == null) {
						sqlHelper.insert(listPreProduct.get(i));// 新增
					} else {
						newID.add(listPreProduct.get(i).getPreProductDetailId());
						sqlHelper.update(listPreProduct.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					sqlHelper.delete(SysPreProductDetail.class,
							"PreProductDetailID", item);
				}
			} else {
				sqlHelper.delete(SysPreProductDetail.class, "PredateID", id);
			}

			if (listPreOtherCost.size() != 0) {// 费用明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				List<SysPreOtherCostDetail> list = (List<SysPreOtherCostDetail>) sqlHelper
						.select(SysPreOtherCostDetail.class, "PredateID", id);// 费用明细表
				for (SysPreOtherCostDetail item : list) {
					oldID.add(item.getPreOtherCostDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreOtherCost.size(); i++) {// 遍历修理项目明细表
					listPreOtherCost.get(i).setPredateId(id);
					if (listPreOtherCost.get(i).getPreOtherCostDetailId() == null) {
						sqlHelper.insert(listPreOtherCost.get(i));// 新增
					} else {
						newID.add(listPreOtherCost.get(i)
								.getPreOtherCostDetailId());
						sqlHelper.update(listPreOtherCost.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					sqlHelper.delete(SysPreOtherCostDetail.class,
							"PreOtherCostDetailID", item);
				}
			} else {
				sqlHelper.delete(SysPreOtherCostDetail.class, "PredateID", id);
			}

		}
		return id;
	}

	@Override
	public boolean deleteListPredate(int predateID) {
		boolean flag = false;
		flag = sqlHelper.delete(PwPredate.class, "PredateID", predateID);
		if (flag) {
			sqlHelper.delete(SysPreRepairItemDetail.class, "PredateID",
					predateID);// 删除维修明细表
			sqlHelper.delete(SysPreProductDetail.class, "PredateID", predateID);// 删除产品明细表
			sqlHelper.delete(SysPreOtherCostDetail.class, "PredateID", predateID);// 删除其他费用表
		}
		return flag;
	}

	@Override
	public boolean toAudit(int psredateID) {
		boolean flag = false;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(updateAudit).addEntity(
				PwPredate.class);
		sqlQuery.setBoolean(0, true);
		sqlQuery.setInteger(1, psredateID);
		flag = sqlQuery.executeUpdate() == 0 ? false : true;
		return flag;
	}

	@Override
	public boolean toNotAudit(int predateID) {
		boolean flag = false;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(updateAudit).addEntity(
				PwPredate.class);
		sqlQuery.setBoolean(0, false);
		sqlQuery.setInteger(1, predateID);
		flag = sqlQuery.executeUpdate() == 0 ? false : true;
		return flag;
	}

	@Override
	public boolean selectToMainten(int predateID, String maintenanceNum) {
		boolean flag = false;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectToMainten).addEntity(
				PwPredate.class);
		sqlQuery.setBoolean(0, true);
		sqlQuery.setString(1, maintenanceNum);
		sqlQuery.setInteger(2, predateID);
		flag = sqlQuery.executeUpdate() == 0 ? false : true;
		return flag;
	}
}
