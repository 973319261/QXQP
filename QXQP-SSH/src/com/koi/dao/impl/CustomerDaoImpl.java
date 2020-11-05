package com.koi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.ICustomerDao;
import com.koi.po.PwReception;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecProductDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.util.SqlHelper;
import com.koi.vo.ReceptionVo;

@Repository("customerDao")
public class CustomerDaoImpl implements ICustomerDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
	String selectReception = "SELECT * FROM(SELECT *,ROW_NUMBER() OVER ( ORDER BY ReceptionID ) Num  FROM(SELECT p.*,RepairName,"
			+ "CustomerSou,DocumentState,BalanceState FROM PW_Reception p,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE 1=1 ";
	String selectReceptionCount = "SELECT COUNT(*) as num FROM  PW_Reception p,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID";

	@Override
	public int maintenanceNum(String d) {
		String maintenanceNum = "SELECT TOP 1 SUBSTRING(MaintenanceNum,10,4) as num from PW_Reception "
				+ "WHERE MaintenanceNum LIKE '%"
				+ d
				+ "%' ORDER BY  MaintenanceNum DESC";
		Long num = 0l;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(maintenanceNum);
		sqlQuery.addScalar("num", Hibernate.STRING);
		Object str = sqlQuery.uniqueResult();
		num = str == null ? 0l : Integer.valueOf(str.toString());
		return num.intValue();
	}

	@Override
	public int updateListReceptione(List<PwReception> listReception,
			List<SysRecRepairItemDetail> listRecRepairItem,
			List<SysRecProductDetail> listRecProduct,
			List<SysRecOtherCostDetail> listRecOtherCost,
			List<SysInsuranceDetail> listArrInsuranceMoney,
			List<SysThreePacksDetail> listThreePacksDetail) {
		int id = 0;
		session = sessionFactory.getCurrentSession();
		if (listReception.get(0).getReceptionId() == null) {
			listReception.get(0).setDocumentStateId(1); // 未结算
			listReception.get(0).setBalanceStateId(3);// 未付款
			listReception.get(0).setCollageState("未领料");// 领料状态
			listReception.get(0).setToCompletion(false);// 领料状态
			id = sqlHelper.insert(listReception.get(0));// 新增
		} else {
			id = listReception.get(0).getReceptionId();
			PwReception list = (PwReception) sqlHelper.select(
					PwReception.class, "ReceptionID", id).get(0);
			listReception.get(0).setBalanceStateId(list.getBalanceStateId());
			listReception.get(0).setCollageState(list.getCollageState());
			listReception.get(0).setCompletionDate(list.getCompletionDate());
			listReception.get(0).setDocumentStateId(list.getDocumentStateId());
			listReception.get(0).setMaintenAmount(list.getMaintenAmount());
			listReception.get(0).setOilQuantity(list.getOilQuantity());
			listReception.get(0).setToAudit(list.getToAudit());
			listReception.get(0).setToCompletion(list.getToCompletion());
			listReception.get(0).setToSendWork(list.getToSendWork());
			sqlHelper.update(listReception.get(0));// 修改
		}
		if (id > 0) {// 主表保存成功
			List<Integer> oldID = new ArrayList<Integer>();// 原来ID
			List<Integer> newID = new ArrayList<Integer>();// 传过来的ID
			if (listRecRepairItem.size() != 0) {// 修理项目明细表有数据
				// 维修明细表
				List<SysRecRepairItemDetail> list = (List<SysRecRepairItemDetail>) sqlHelper
						.select(SysRecRepairItemDetail.class, "ReceptionID", id);// 修理项目明细表
				for (SysRecRepairItemDetail item : list) {
					oldID.add(item.getRecRepairItemDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecRepairItem.size(); i++) {// 遍历修理项目明细表
					listRecRepairItem.get(i).setReceptionId(id);
					if (listRecRepairItem.get(i).getRecRepairItemDetailId() == null) {
						sqlHelper.insert(listRecRepairItem.get(i));// 新增
					} else {
						int recRepairItemDetailID = listRecRepairItem.get(i)
								.getRecRepairItemDetailId();
						newID.add(recRepairItemDetailID);
						SysRecRepairItemDetail recRepairItemDetail = (SysRecRepairItemDetail) sqlHelper
								.select(SysRecRepairItemDetail.class,
										"recRepairItemDetailID", recRepairItemDetailID).get(0);
						listRecRepairItem.get(i).setCompletionDate(
								recRepairItemDetail.getCompletionDate());
						listRecRepairItem.get(i).setRepairManId(
								recRepairItemDetail.getRepairManId());
						listRecRepairItem.get(i).setMaintenanceCrewId(
								recRepairItemDetail.getMaintenanceCrewId());
						listRecRepairItem.get(i).setJobHours(
								recRepairItemDetail.getJobHours());
						listRecRepairItem.get(i).setJobAmount(
								recRepairItemDetail.getJobAmount());
						sqlHelper.update(listRecRepairItem.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					sqlHelper.delete(SysRecRepairItemDetail.class,
							"RecRepairItemDetailID", item);
				}
			} else {
				sqlHelper.delete(SysRecRepairItemDetail.class, "ReceptionID",
						id);
			}
			if (listRecProduct.size() != 0) {// /配件明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				List<SysRecProductDetail> list = (List<SysRecProductDetail>) sqlHelper
						.select(SysRecProductDetail.class, "ReceptionID", id);// 配件明细表
				for (SysRecProductDetail item : list) {
					oldID.add(item.getRecProductDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecProduct.size(); i++) {// 遍历修理项目明细表
					listRecProduct.get(i).setReceptionId(id);
					if(listRecProduct.get(i).getFittingsCode()!=null){
						if (listRecProduct.get(i).getRecProductDetailId() == null) {
							sqlHelper.insert(listRecProduct.get(i));// 新增
						} else {
							newID.add(listRecProduct.get(i).getRecProductDetailId());
							sqlHelper.update(listRecProduct.get(i));// 修改
						}
					}
					
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					sqlHelper.delete(SysRecProductDetail.class, "RecProductDetailID",
							item);
				}
			} else {
				sqlHelper.delete(SysRecProductDetail.class, "ReceptionID",
						id);
			}
			if (listRecOtherCost.size() != 0) {// 费用明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				List<SysRecOtherCostDetail> list = (List<SysRecOtherCostDetail>) sqlHelper
						.select(SysRecOtherCostDetail.class, "ReceptionID", id);// 费用明细表
				for (SysRecOtherCostDetail item : list) {
					oldID.add(item.getRecOtherCostDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecOtherCost.size(); i++) {// 遍历修理项目明细表
					listRecOtherCost.get(i).setReceptionId(id);
					if (listRecOtherCost.get(i).getRecOtherCostDetailId() == null) {
						sqlHelper.insert(listRecOtherCost.get(i));// 新增
					} else {
						newID.add(listRecOtherCost.get(i)
								.getRecOtherCostDetailId());
						sqlHelper.update(listRecOtherCost.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					sqlHelper.delete(SysRecOtherCostDetail.class, "RecOtherCostDetailID",
							item);
				}
			} else {
				sqlHelper.delete(SysRecOtherCostDetail.class, "ReceptionID",
						id);
			}
			if (listArrInsuranceMoney.size() != 0) {
				// 保险理赔明细
				listArrInsuranceMoney.get(0).setReceptionId(id);
				if (listArrInsuranceMoney.get(0).getInsuranceDetailId() == null) {
					sqlHelper.insert(listArrInsuranceMoney.get(0));// 新增
				} else {
					sqlHelper.update(listArrInsuranceMoney.get(0));// 修改
				}
			}
			if (listThreePacksDetail.size() != 0) {
				// 三包索赔明细
				listThreePacksDetail.get(0).setReceptionId(id);
				if (listThreePacksDetail.get(0).getThreePacksDetailId() == null) {
					sqlHelper.insert(listThreePacksDetail.get(0));// 新增
				} else {
					sqlHelper.update(listThreePacksDetail.get(0));// 修改
				}
			}
		}
		return id;
	}

	@Override
	public List<ReceptionVo> selectReception(String toAudit,
			String maintenanceNum, String carNum, int documentStateID,
			int balanceStateID, Long startIndex, Long pageSize) {
		List<ReceptionVo> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectReception);	
		if (!"".equals(toAudit)) {
			buffer.append(" AND tmp.ToAudit =" + toAudit);
		}
		if (!"".equals(maintenanceNum)) {
			buffer.append(" AND tmp.MaintenanceNum like '%" + maintenanceNum + "%'");
		}
		if (!"".equals(carNum)) {
			buffer.append(" AND tmp.CarNum like '%" + carNum + "%'");
		}
		if (documentStateID > 0) {
			buffer.append(" AND tmp.DocumentStateID ="+ documentStateID);
		}
		if (balanceStateID > 0) {
			buffer.append(" AND tmp.BalanceStateID ="+ balanceStateID);
		}
		buffer.append(")AS db WHERE db.Num BETWEEN "+ startIndex + " AND "+ pageSize);
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString()).addEntity(ReceptionVo.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectReceptionCount(String toAudit, String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectReceptionCount);	
		if (!"".equals(toAudit)) {
			buffer.append(" AND p.ToAudit =" + toAudit);
		}
		if (!"".equals(maintenanceNum)) {
			buffer.append(" AND p.MaintenanceNum like '%" + maintenanceNum + "%'");
		}
		if (!"".equals(carNum)) {
			buffer.append(" AND p.CarNum like '%" + carNum + "%'");
		}
		if (documentStateID > 0) {
			buffer.append(" AND p.DocumentStateID ="+ documentStateID);
		}
		if (balanceStateID > 0) {
			buffer.append(" AND p.BalanceStateID ="+ balanceStateID);
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString());
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		List<Object> list = new ArrayList<Object>();
		List<SysRecRepairItemDetail> sysRecRepairItemDetails = (List<SysRecRepairItemDetail>) sqlHelper.select(
				SysRecRepairItemDetail.class, "ReceptionID", receptionID);// 修理项目明细表
		List<SysRecProductDetail> sysRecProductDetails = (List<SysRecProductDetail>) sqlHelper.select(
				SysRecProductDetail.class, "ReceptionID", receptionID);// 产品明细表
		List<SysRecOtherCostDetail> sysRecOtherCostDetails = (List<SysRecOtherCostDetail>) sqlHelper.select(
				SysRecOtherCostDetail.class, "ReceptionID", receptionID);// 其他费用明细表
		List<SysInsuranceDetail> sysInsuranceDetails = (List<SysInsuranceDetail>) sqlHelper.select(
				SysInsuranceDetail.class, "ReceptionID", receptionID);//保险理赔明细表
		List<SysThreePacksDetail> sysThreePacksDetails = (List<SysThreePacksDetail>) sqlHelper.select(
				SysThreePacksDetail.class, "ReceptionID", receptionID);//三包索赔明细表
		list.add(sysRecRepairItemDetails);
		list.add(sysRecProductDetails);
		list.add(sysRecOtherCostDetails);
		list.add(sysInsuranceDetails);
		list.add(sysThreePacksDetails);
		return list;
	}

	@Override
	public boolean delectReception(int receptionID) {
		boolean flag=false;
		flag=sqlHelper.delete(PwReception.class, "ReceptionID", receptionID);
		if(flag){
			sqlHelper.delete(SysRecProductDetail.class, "ReceptionID", receptionID);
			sqlHelper.delete(SysRecRepairItemDetail.class, "ReceptionID", receptionID);
			sqlHelper.delete(SysRecOtherCostDetail.class, "ReceptionID", receptionID);
			sqlHelper.delete(SysInsuranceDetail.class, "ReceptionID", receptionID);
			sqlHelper.delete(SysThreePacksDetail.class, "ReceptionID", receptionID);
		}
		return flag;
	}
}
