package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gx.dao.ICustomerDao;
import com.gx.pojo.PW_Predate;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecProductDetail;
import com.gx.pojo.SYS_PreRepairItemDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecProductDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;
import com.gx.vo.ReceptionVo;

public class CustomerDaoImpl implements ICustomerDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String selectReception = "SELECT * FROM(SELECT p.*,RepairName,CustomerSou,DocumentState,BalanceState," +
			"ROW_NUMBER()OVER(ORDER BY p.ReceptionID)Num FROM  PW_Reception p,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	String selectReceptionCount = "SELECT COUNT(*)  FROM  PW_Reception p,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return num;
	}

	@Override
	public int updateListReceptione(List<PW_Reception> listReception,
			List<SYS_RecRepairItemDetail> listRecRepairItem,
			List<SYS_RecProductDetail> listRecProduct,
			List<SYS_RecOtherCostDetail> listRecOtherCost,
			List<SYS_InsuranceDetail> listArrInsuranceMoney,
			List<SYS_ThreePacksDetail> listThreePacksDetail) {
		int id = 0;
		if (listReception.get(0).getReceptionID() == null) {
			listReception.get(0).setDocumentStateID(1); // 未结算
			listReception.get(0).setBalanceStateID(3);// 未付款
			listReception.get(0).setCollageState("未领料");// 领料状态
			listReception.get(0).setToCompletion(false);// 领料状态
			id = JdbcHelper.insert(listReception.get(0));// 新增
		} else {
			id = listReception.get(0).getReceptionID();
			rs = JdbcHelper.select(PW_Reception.class, "ReceptionID", id);
			PW_Reception list = JdbcHelper.getSingleResult(rs,
					PW_Reception.class);
			 listReception.get(0).setBalanceStateID(list.getBalanceStateID());
             listReception.get(0).setCollageState(list.getCollageState());
             listReception.get(0).setCompletionDate(list.getCompletionDate());
             listReception.get(0).setDocumentStateID(list.getDocumentStateID());
             listReception.get(0).setMaintenAmount (list.getMaintenAmount());
             listReception.get(0).setOilQuantity(list.getOilQuantity());
             listReception.get(0).setToAudit(list.getToAudit());
             listReception.get(0).setToCompletion(list.getToCompletion());
             listReception.get(0).setToSendWork(list.getToSendWork());
			JdbcHelper.update(listReception.get(0));// 修改
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
						rs = JdbcHelper.select(SYS_RecRepairItemDetail.class,
								"RecRepairItemDetailID", recRepairItemDetailID);
						SYS_RecRepairItemDetail recRepairItemDetail = JdbcHelper
								.getSingleResult(rs,
										SYS_RecRepairItemDetail.class);
						listRecRepairItem.get(i).setCompletionDate(
								recRepairItemDetail.getCompletionDate());
						listRecRepairItem.get(i).setRepairManID(
								recRepairItemDetail.getRepairManID());
						listRecRepairItem.get(i).setMaintenanceCrewID(
								recRepairItemDetail.getMaintenanceCrewID());
						listRecRepairItem.get(i).setJobHours(
								recRepairItemDetail.getJobHours());
						listRecRepairItem.get(i).setJobAmount(
								recRepairItemDetail.getJobAmount());
						JdbcHelper.update(listRecRepairItem.get(i));// 修改
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
			if (listRecProduct.size() != 0) {// /配件明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				rs = JdbcHelper.select(SYS_RecProductDetail.class,
						"ReceptionID", id);
				List<SYS_RecProductDetail> list = JdbcHelper.getResult(rs,
						SYS_RecProductDetail.class);// 配件明细表
				for (SYS_RecProductDetail item : list) {
					oldID.add(item.getRecProductDetailID());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecProduct.size(); i++) {// 遍历修理项目明细表
					listRecProduct.get(i).setReceptionID(id);
					if (listRecProduct.get(i).getRecProductDetailID() == null) {
						JdbcHelper.insert(listRecProduct.get(i));// 新增
					} else {
						newID.add(listRecProduct.get(i).getRecProductDetailID());
						JdbcHelper.update(listRecProduct.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					JdbcHelper.delete(SYS_RecProductDetail.class,
							"RecProductDetailID", item);
				}
			} else {
				JdbcHelper
						.delete(SYS_RecProductDetail.class, "ReceptionID", id);
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

	@Override
	public List<ReceptionVo> selectReception(String toAudit,
			String maintenanceNum, String carNum, int documentStateID,
			int balanceStateID, int startIndex, int pageSize) {
		List<ReceptionVo> list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectReception);	
			if (toAudit != "") {
				buffer.append(" AND tmp.ToAudit =" + toAudit);
			}
			if (maintenanceNum != "") {
				buffer.append(" AND tmp.MaintenanceNum like '%" + maintenanceNum + "%'");
			}
			if (carNum != "") {
				buffer.append(" AND tmp.CarNum like '%" + carNum + "%'");
			}
			if (documentStateID > 0) {
				buffer.append(" AND tmp.DocumentStateID ="+ documentStateID);
			}
			if (balanceStateID > 0) {
				buffer.append(" AND tmp.BalanceStateID ="+ balanceStateID);
			}
			ps = conn.prepareStatement(buffer.toString());
			ps.setInt(1, startIndex);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			list = JdbcHelper.getResult(rs, ReceptionVo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int selectReceptionCount(String toAudit, String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectReceptionCount);	
			if (toAudit != "") {
				buffer.append(" AND p.ToAudit =" + toAudit);
			}
			if (maintenanceNum != "") {
				buffer.append(" AND p.MaintenanceNum like '%" + maintenanceNum + "%'");
			}
			if (carNum != "") {
				buffer.append(" AND p.CarNum like '%" + carNum + "%'");
			}
			if (documentStateID > 0) {
				buffer.append(" AND p.DocumentStateID ="+ documentStateID);
			}
			if (balanceStateID > 0) {
				buffer.append(" AND p.BalanceStateID ="+ balanceStateID);
			}
			ps = conn.prepareStatement(buffer.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);// 获取总数
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return i;
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		List<Object> list = new ArrayList<Object>();
		rs = JdbcHelper.select(SYS_RecRepairItemDetail.class, "ReceptionID",
				receptionID);
		List<SYS_RecRepairItemDetail> listRecRepairItemDetail = JdbcHelper
				.getResult(rs, SYS_RecRepairItemDetail.class);// 修理项目明细表
		rs = JdbcHelper.select(SYS_RecProductDetail.class, "ReceptionID",
				receptionID);
		List<SYS_RecProductDetail> listRecProductDetail = JdbcHelper.getResult(
				rs, SYS_RecProductDetail.class);// 产品明细表
		rs = JdbcHelper.select(SYS_RecOtherCostDetail.class, "ReceptionID",
				receptionID);
		List<SYS_RecOtherCostDetail> listRecOtherCostDetail = JdbcHelper
				.getResult(rs, SYS_RecOtherCostDetail.class);// 其他费用明细表
		rs = JdbcHelper.select(SYS_InsuranceDetail.class, "ReceptionID",
				receptionID);
		List<SYS_InsuranceDetail> listInsuranceDetail = JdbcHelper
				.getResult(rs, SYS_InsuranceDetail.class);//保险理赔明细表
		rs = JdbcHelper.select(SYS_ThreePacksDetail.class, "ReceptionID",
				receptionID);
		List<SYS_ThreePacksDetail> listThreePacksDetail = JdbcHelper
				.getResult(rs, SYS_ThreePacksDetail.class);//三包索赔明细表
		list.add(listRecRepairItemDetail);
		list.add(listRecProductDetail);
		list.add(listRecOtherCostDetail);
		list.add(listInsuranceDetail);
		list.add(listThreePacksDetail);
		return list;
	}

	@Override
	public boolean delectReception(int receptionID) {
		boolean flag=false;
		flag=JdbcHelper.delete(PW_Reception.class, "ReceptionID", receptionID);
		if(flag){
			JdbcHelper.delete(SYS_RecProductDetail.class, "ReceptionID", receptionID);
			JdbcHelper.delete(SYS_RecRepairItemDetail.class, "ReceptionID", receptionID);
			JdbcHelper.delete(SYS_RecOtherCostDetail.class, "ReceptionID", receptionID);
			JdbcHelper.delete(SYS_InsuranceDetail.class, "ReceptionID", receptionID);
			JdbcHelper.delete(SYS_ThreePacksDetail.class, "ReceptionID", receptionID);
		}
		return flag;
	}
}
