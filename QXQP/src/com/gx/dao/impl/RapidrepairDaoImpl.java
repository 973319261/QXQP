package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gx.dao.IRapidrepairDao;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecProductDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;

public class RapidrepairDaoImpl implements IRapidrepairDao {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	@Override
	public int maintenanceNum(String d) {
		String maintenanceNum = "SELECT TOP 1 SUBSTRING(MaintenanceNum,10,4) from PW_Reception "
				+ "WHERE MaintenanceNum LIKE '%"
				+ d
				+ "%' ORDER BY  MaintenanceNum DESC";
		int num = 0;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(maintenanceNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return num;
	}

	@Override
	public int updateListReceptione(List<PW_Reception> listReception,
			List<SYS_RecRepairItemDetail> listRecRepairItem,
			List<SYS_CollageDetai> listCollageDetai,
			List<SYS_RecOtherCostDetail> listRecOtherCost,
			List<SYS_InsuranceDetail> listArrInsuranceMoney,
			List<SYS_ThreePacksDetail> listThreePacksDetail) {
		int id = 0;
		 if (listRecRepairItem.size() != 0)
         {
             listReception.get(0).setToSendWork(true);//派工状态
         }
         else {
             listReception.get(0).setToSendWork(false);//派工状态
         }
         if ( listReception.get(0).getReceptionID()== null)
         {
             listReception.get(0).setDocumentStateID(1); //未结算
             listReception.get(0).setBalanceStateID (3);//未付款
             listReception.get(0).setCollageState("未领料");//领料状态
             listReception.get(0).setToCompletion(false);//领料状态
             id=JdbcHelper.insert(listReception.get(0));
         }
         else
         {
        	 id=listReception.get(0).getReceptionID();
        	 rs=JdbcHelper.select(PW_Reception.class, "ReceptionID", id);
        	 PW_Reception list=JdbcHelper.getSingleResult(rs, PW_Reception.class);
             listReception.get(0).setMaintenAmount(list.getMaintenAmount());
             listReception.get(0).setDocumentStateID(list.getDocumentStateID());
             listReception.get(0).setBalanceStateID(list.getBalanceStateID());
             listReception.get(0).setCollageState(list.getCollageState());
             listReception.get(0).setToCompletion(list.getToCompletion());
             JdbcHelper.update(listReception.get(0));//修改
         }
		if (id > 0) {// 主表保存成功
			List<Integer> oldID = new ArrayList<Integer>();// 原来ID
			List<Integer> newID = new ArrayList<Integer>();// 传过来的ID
			if (listRecRepairItem.size() != 0) {// 修理项目明细表有数据
				// 维修明细表
				rs = JdbcHelper.select(SYS_RecRepairItemDetail.class,
						"ReceptionID", id);
				List<SYS_RecRepairItemDetail> list = JdbcHelper.getResult(rs,
						SYS_RecRepairItemDetail.class);// 修理项目明细表
				for (SYS_RecRepairItemDetail item : list) {
					oldID.add(item.getRecRepairItemDetailID());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecRepairItem.size(); i++) {// 遍历修理项目明细表
					listRecRepairItem.get(i).setReceptionID(id);
					if (listRecRepairItem.get(i).getRecRepairItemDetailID() == null) {
						JdbcHelper.insert(listRecRepairItem.get(i));// 新增
					} else {
						int recRepairItemDetailID = listRecRepairItem.get(i)
								.getRecRepairItemDetailID();
						newID.add(recRepairItemDetailID);
						JdbcHelper.update(listRecRepairItem.get(0));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					JdbcHelper.delete(SYS_RecRepairItemDetail.class,
							"RecRepairItemDetailID", item);
				}
			} else {
				JdbcHelper.delete(SYS_RecRepairItemDetail.class, "ReceptionID",
						id);
			}
			if (listCollageDetai.size() != 0) {// /配件明细表有数据
				for (int i = 0; i < listCollageDetai.size(); i++) {
					int collageDetaiID=listCollageDetai.get(i).getCollageDetaiID();
					int maintainabilityID=listCollageDetai.get(i).getMaintainabilityID();
					rs=JdbcHelper.select(SYS_CollageDetai.class, "CollageDetaiID", collageDetaiID);
					SYS_CollageDetai list=JdbcHelper.getSingleResult(rs, SYS_CollageDetai.class);
					list.setMaintainabilityID(maintainabilityID);
					JdbcHelper.update(list);
				}
			}
			if (listRecOtherCost.size() != 0) {// 费用明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				rs = JdbcHelper.select(SYS_RecOtherCostDetail.class,
						"ReceptionID", id);
				List<SYS_RecOtherCostDetail> list = JdbcHelper.getResult(rs,
						SYS_RecOtherCostDetail.class);// 费用明细表
				for (SYS_RecOtherCostDetail item : list) {
					oldID.add(item.getRecOtherCostDetailID());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecOtherCost.size(); i++) {// 遍历修理项目明细表
					listRecOtherCost.get(i).setReceptionID(id);
					if (listRecOtherCost.get(i).getRecOtherCostDetailID() == null) {
						JdbcHelper.insert(listRecOtherCost.get(i));// 新增
					} else {
						newID.add(listRecOtherCost.get(i)
								.getRecOtherCostDetailID());
						JdbcHelper.update(listRecOtherCost.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					JdbcHelper.delete(SYS_RecOtherCostDetail.class,
							"RecOtherCostDetailID", item);
				}
			} else {
				JdbcHelper.delete(SYS_RecOtherCostDetail.class, "ReceptionID",
						id);
			}
			if (listArrInsuranceMoney.size() != 0) {
				// 保险理赔明细
				listArrInsuranceMoney.get(0).setReceptionID(id);
				if (listArrInsuranceMoney.get(0).getInsuranceDetailID() == null) {
					JdbcHelper.insert(listArrInsuranceMoney.get(0));// 新增
				} else {
					JdbcHelper.update(listArrInsuranceMoney.get(0));// 修改
				}
			}
			if (listThreePacksDetail.size() != 0) {
				// 三包索赔明细
				listThreePacksDetail.get(0).setReceptionID(id);
				if (listThreePacksDetail.get(0).getThreePacksDetailID() == null) {
					JdbcHelper.insert(listThreePacksDetail.get(0));// 新增
				} else {
					JdbcHelper.update(listThreePacksDetail.get(0));// 修改
				}
			}
		}
		return id;
	}

}
