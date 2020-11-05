package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gx.dao.IAppointmentDao;
import com.gx.pojo.PW_Predate;
import com.gx.pojo.SYS_PreOtherCostDetail;
import com.gx.pojo.SYS_PreProductDetail;
import com.gx.pojo.SYS_PreRepairItemDetail;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;

public class AppointmentDaoImpl implements IAppointmentDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String selectPredate = "SELECT * FROM(SELECT p.PredateID,p.CarderID,p.RepairID,CarderName,VehicleType,CustomerNum,RepairName,PredateNum,"
			+ "OpenDate,MaintenanceNum,MaintainData,CarNum,Owner,CarMasterPhone,Contacts,Telephone,p.Remark,ToAudit,Amount,Receivable,Describe,"
			+ "ToTransferOrder,ROW_NUMBER()OVER(ORDER BY p.PredateID)Num FROM  PW_Predate p,SYS_Carder,SYS_Repair where p.CarderID = SYS_Carder.CarderID "
			+ "and p.RepairID = SYS_Repair.RepairID)AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	String selectPredateCount = "SELECT COUNT(*)  FROM  PW_Predate p,SYS_Carder,SYS_Repair where p.CarderID = SYS_Carder.CarderID "
			+ "and p.RepairID = SYS_Repair.RepairID";
	String updateAudit="UPDATE PW_Predate SET ToAudit=? WHERE PredateID=?";
	@Override
	public int predateNum(String d) {
		String predateNum = "SELECT TOP 1 SUBSTRING(PredateNum,17,4) from PW_Predate "
				+ "WHERE PredateNum LIKE '%"
				+ d
				+ "%' ORDER BY  PredateNum DESC";
		int num = 0;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(predateNum);
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
	public List<PW_Predate> selectPredate(String predateNum, String toAudit,
			int startIndex, int pageSize) {
		List<PW_Predate> list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectPredate);
			if (predateNum != "") {
				buffer.append(" AND tmp.PredateNum like '%" + predateNum + "%'");
			}
			if (toAudit != "") {
				buffer.append(" AND tmp.ToAudit ="+toAudit);
			}
			ps = conn.prepareStatement(buffer.toString());
			ps.setInt(1, startIndex);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			list = JdbcHelper.getResult(rs, PW_Predate.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public int selectPredateCount(String predateNum, String toAudit) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectPredateCount);
			if (predateNum != "") {
				buffer.append(" AND PredateNum like '%" + predateNum + "%'");
			}
			if (toAudit != "") {
				buffer.append(" AND p.ToAudit =" + toAudit);
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
	public List<Object> selectPredateDetail(int predateID) {
		List<Object> list = new ArrayList<Object>();
		rs = JdbcHelper.select(SYS_PreRepairItemDetail.class, "PredateID",
				predateID);
		List<SYS_PreRepairItemDetail> listPreRepairItemDetail = JdbcHelper
				.getResult(rs, SYS_PreRepairItemDetail.class);// 修理项目明细表
		rs = JdbcHelper.select(SYS_PreProductDetail.class, "PredateID",
				predateID);
		List<SYS_PreProductDetail> listPreProductDetail = JdbcHelper.getResult(
				rs, SYS_PreProductDetail.class);// 产品明细表
		rs = JdbcHelper.select(SYS_PreOtherCostDetail.class, "PredateID",
				predateID);
		List<SYS_PreOtherCostDetail> listPreOtherCostDetail = JdbcHelper
				.getResult(rs, SYS_PreOtherCostDetail.class);// 其他费用明细表
		list.add(listPreRepairItemDetail);
		list.add(listPreProductDetail);
		list.add(listPreOtherCostDetail);
		return list;
	}

	@Override
	public int updateListPredate(List<PW_Predate> listPredate,
			List<SYS_PreRepairItemDetail> listPreRepairItem,
			List<SYS_PreProductDetail> listPreProduct,
			List<SYS_PreOtherCostDetail> listPreOtherCost) {
		int id = 0;
		if (listPredate.get(0).getPredateID() == null) {
			id = JdbcHelper.insert(listPredate.get(0));// 新增
		} else {
			JdbcHelper.update(listPredate.get(0));// 修改
			id = listPredate.get(0).getPredateID();
		}
		if (id > 0) {// 主表保存成功
			List<Integer> oldID = new ArrayList<Integer>();// 原来ID
			List<Integer> newID = new ArrayList<Integer>();// 传过来的ID
			if (listPreRepairItem.size() != 0) {// 修理项目明细表有数据
				// 维修明细表
				rs = JdbcHelper.select(SYS_PreRepairItemDetail.class,
						"PredateID", id);
				List<SYS_PreRepairItemDetail> list = JdbcHelper.getResult(rs,
						SYS_PreRepairItemDetail.class);// 修理项目明细表
				for (SYS_PreRepairItemDetail item : list) {
					oldID.add(item.getPreRepairItemDetailID());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreRepairItem.size(); i++) {// 遍历修理项目明细表
					listPreRepairItem.get(i).setPredateID(id);
					if (listPreRepairItem.get(i).getPreRepairItemDetailID() == null) {
						JdbcHelper.insert(listPreRepairItem.get(i));// 新增
					} else {
						newID.add(listPreRepairItem.get(i)
								.getPreRepairItemDetailID());
						JdbcHelper.update(listPreRepairItem.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					JdbcHelper.delete(SYS_PreRepairItemDetail.class,
							"PreRepairItemDetailID", item);
				}
			} else {
				JdbcHelper.delete(SYS_PreRepairItemDetail.class, "PredateID",
						id);
			}
			if (listPreProduct.size() != 0) {// /配件明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				rs = JdbcHelper.select(SYS_PreProductDetail.class, "PredateID",
						id);
				List<SYS_PreProductDetail> list = JdbcHelper.getResult(rs,
						SYS_PreProductDetail.class);// 配件明细表
				for (SYS_PreProductDetail item : list) {
					oldID.add(item.getPreProductDetailID());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreProduct.size(); i++) {// 遍历修理项目明细表
					listPreProduct.get(i).setPredateID(id);
					if (listPreProduct.get(i).getPreProductDetailID() == null) {
						JdbcHelper.insert(listPreProduct.get(i));// 新增
					} else {
						newID.add(listPreProduct.get(i).getPreProductDetailID());
						JdbcHelper.update(listPreProduct.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					JdbcHelper.delete(SYS_PreProductDetail.class,
							"PreProductDetailID", item);
				}
			} else {
				JdbcHelper.delete(SYS_PreProductDetail.class, "PredateID", id);
			}
			if (listPreOtherCost.size() != 0) {// 费用明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				rs = JdbcHelper.select(SYS_PreOtherCostDetail.class,
						"PredateID", id);
				List<SYS_PreOtherCostDetail> list = JdbcHelper.getResult(rs,
						SYS_PreOtherCostDetail.class);// 费用明细表
				for (SYS_PreOtherCostDetail item : list) {
					oldID.add(item.getPreOtherCostDetailID());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreOtherCost.size(); i++) {// 遍历修理项目明细表
					listPreOtherCost.get(i).setPredateID(id);
					if (listPreOtherCost.get(i).getPreOtherCostDetailID() == null) {
						JdbcHelper.insert(listPreOtherCost.get(i));// 新增
					} else {
						newID.add(listPreOtherCost.get(i)
								.getPreOtherCostDetailID());
						JdbcHelper.update(listPreOtherCost.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					JdbcHelper.delete(SYS_PreOtherCostDetail.class,
							"PreOtherCostDetailID", item);
				}
			} else {
				JdbcHelper
						.delete(SYS_PreOtherCostDetail.class, "PredateID", id);
			}
		}
		return id;
	}

	@Override
	public boolean deleteListPredate(int predateID) {
		boolean flag=false;
	    flag=JdbcHelper.delete(PW_Predate.class, "PredateID", predateID);
	    if(flag){
	    	JdbcHelper.delete(SYS_PreRepairItemDetail.class, "PredateID", predateID);//删除维修明细表
	    	JdbcHelper.delete(SYS_PreProductDetail.class, "PredateID", predateID);//删除产品明细表
	    	JdbcHelper.delete(SYS_PreProductDetail.class, "PredateID", predateID);//删除其他费用表
	    }
		return flag;
	}

	@Override
	public boolean toAudit(int predateID) {
		boolean flag=false;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(updateAudit);
			ps.setBoolean(1, true);
			ps.setInt(2, predateID);
			flag=ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbUtil.close(null, ps, conn);
		}
		return flag;
	}

	@Override
	public boolean toNotAudit(int predateID) {
		boolean flag=false;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(updateAudit);
			ps.setBoolean(1, false);
			ps.setInt(2, predateID);
			flag=ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbUtil.close(null, ps, conn);
		}
		return flag;
	}
	String selectToMainten="UPDATE PW_Predate SET ToTransferOrder=?,MaintenanceNum=? WHERE PredateID=?";
	@Override
	public boolean selectToMainten(int predateID, String maintenanceNum) {
		boolean flag=false;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(selectToMainten);
			ps.setBoolean(1, true);
			ps.setString(2, maintenanceNum);
			ps.setInt(3, predateID);
			flag=ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbUtil.close(null, ps, conn);
		}
		return flag;
	}
}
