package com.koi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.IRapidrepairDao;
import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.util.SqlHelper;

@Repository("rapidrepairDao")
public class RapidrepairDaoImpl implements IRapidrepairDao  {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
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
			List<SysCollageDetai> listCollageDetai,
			List<SysRecOtherCostDetail> listRecOtherCost,
			List<SysInsuranceDetail> listArrInsuranceMoney,
			List<SysThreePacksDetail> listThreePacksDetail) {
		int id = 0;
		 if (listRecRepairItem.size() != 0)
         {
             listReception.get(0).setToSendWork(true);//派工状态
         }
         else {
             listReception.get(0).setToSendWork(false);//派工状态
         }
         if ( listReception.get(0).getReceptionId()== null)
         {
             listReception.get(0).setDocumentStateId(1); //未结算
             listReception.get(0).setBalanceStateId(3);//未付款
             listReception.get(0).setCollageState("未领料");//领料状态
             listReception.get(0).setToCompletion(false);//领料状态
             id=sqlHelper.insert(listReception.get(0));
         }
         else
         {
        	 id=listReception.get(0).getReceptionId();
        	 List<PwReception> list =(List<PwReception>) sqlHelper.select(PwReception.class, "ReceptionID", id);
             listReception.get(0).setMaintenAmount(list.get(0).getMaintenAmount());
             listReception.get(0).setDocumentStateId(list.get(0).getDocumentStateId());
             listReception.get(0).setBalanceStateId(list.get(0).getBalanceStateId());
             listReception.get(0).setCollageState(list.get(0).getCollageState());
             listReception.get(0).setToCompletion(list.get(0).getToCompletion());
             sqlHelper.update(listReception.get(0));//修改
         }
		if (id > 0) {// 主表保存成功
			List<Integer> oldID = new ArrayList<Integer>();// 原来ID
			List<Integer> newID = new ArrayList<Integer>();// 传过来的ID
			if (listRecRepairItem.size() != 0) {// 修理项目明细表有数据
				// 维修明细表
				List<SysRecRepairItemDetail> list = (List<SysRecRepairItemDetail>) sqlHelper.select(SysRecRepairItemDetail.class,
						"ReceptionID", id);
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
						sqlHelper.update(listRecRepairItem.get(0));// 修改
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
			if (listCollageDetai.size() != 0) {// /配件明细表有数据
				for (int i = 0; i < listCollageDetai.size(); i++) {
					int collageDetaiID=listCollageDetai.get(i).getCollageDetaiId();
					int maintainabilityID=listCollageDetai.get(i).getMaintainabilityId();
					List<SysCollageDetai> list=(List<SysCollageDetai>) sqlHelper.select(SysCollageDetai.class, "CollageDetaiID", collageDetaiID);
					list.get(0).setMaintainabilityId(maintainabilityID);
					sqlHelper.update(list.get(0));
				}
			}
			if (listRecOtherCost.size() != 0) {// 费用明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				List<SysRecOtherCostDetail> list = (List<SysRecOtherCostDetail>) sqlHelper.select(SysRecOtherCostDetail.class,
						"ReceptionID", id);
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
					sqlHelper.delete(SysRecOtherCostDetail.class,
							"RecOtherCostDetailID", item);
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
}
