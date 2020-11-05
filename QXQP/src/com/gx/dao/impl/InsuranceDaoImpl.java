package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gx.dao.IInsuranceDao;
import com.gx.pojo.PW_Insurance;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;
import com.gx.vo.ReceptionVo;

public class InsuranceDaoImpl implements IInsuranceDao {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	String selectDanHao="SELECT * FROM(SELECT p.*,InsuranceMoney,InsuranceDetailID,ReportNum,PolicyNum,PolicyMoney," +
			"SYS_InsuranceCom.InsuranceComID,RepairName,CustomerSou,DocumentState,BalanceState,ROW_NUMBER()OVER(ORDER BY " +
			"p.ReceptionID)Num FROM  PW_Reception p,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND SYS_InsuranceDetail." +
			"InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp ";
	String selectDanHaoCount="SELECT COUNT(*) FROM  PW_Reception p,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND SYS_InsuranceDetail." +
			"InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	String selectInsurance="SELECT * FROM(SELECT i.*,CustomerNum,p.ReceptionID,CarNum,VehicleType,MaintenanceNum,Owner,InsuranceMoney,ReportNum," +
			"PolicyNum,PolicyMoney,SYS_InsuranceCom.InsuranceComID,RepairName,CustomerSou,DocumentState,p.DocumentStateID,BalanceState,p.BalanceStateID,ROW_NUMBER()OVER" +
			"(ORDER BY p.ReceptionID)Num FROM  PW_Reception p,PW_Insurance i,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair," +
			"SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND i.InsuranceDetailID=" +
			"SYS_InsuranceDetail.InsuranceDetailID AND SYS_InsuranceDetail.InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID =" +
			"SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID " +
			"AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp ";
	String selectInsuranceCount="SELECT COUNT(*) FROM  PW_Reception p,PW_Insurance i,SYS_InsuranceDetail,SYS_InsuranceCom,SYS_Repair," +
			"SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_InsuranceDetail.ReceptionID AND i.InsuranceDetailID=" +
			"SYS_InsuranceDetail.InsuranceDetailID AND SYS_InsuranceDetail.InsuranceComID= SYS_InsuranceCom.InsuranceComID AND p.RepairID =" +
			"SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID " +
			"AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	@Override
	public int insuranceNum(String d) {
		String insuranceNum = "SELECT TOP 1 SUBSTRING(InsuranceNum,11,4) from PW_Insurance "
				+ "WHERE InsuranceNum LIKE '%"
				+ d
				+ "%' ORDER BY  InsuranceNum DESC";
		int num = 0;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(insuranceNum);
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
	public List<ReceptionVo> selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			int startIndex, int pageSize) {
		List<ReceptionVo> list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectDanHao);	
			buffer.append("WHERE tmp.Num BETWEEN ? AND ?");
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
	public int selectDanHaoCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectDanHaoCount);	
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
	public List<ReceptionVo> selectInsurance(String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			String toAudit, int startIndex, int pageSize) {
		List<ReceptionVo> list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectInsurance);	
			buffer.append("WHERE tmp.Num BETWEEN ? AND ?");
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
	public int selectInsuranceCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectInsuranceCount);	
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
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return i;
	}

	@Override
	public ReceptionVo selectInsuranceData(int insuranceDetailID) {
		ReceptionVo list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectInsurance);	
			buffer.append("WHERE tmp.InsuranceDetailID = ? ");
			ps = conn.prepareStatement(buffer.toString());
			ps.setInt(1, insuranceDetailID);
			rs = ps.executeQuery();
			list = JdbcHelper.getSingleResult(rs, ReceptionVo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ReceptionVo selectInsurances(int receptionID) {
		ReceptionVo list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectDanHao);	
			buffer.append("WHERE tmp.ReceptionID = ?");
			ps = conn.prepareStatement(buffer.toString());
			ps.setInt(1, receptionID);
			rs = ps.executeQuery();
			list = JdbcHelper.getSingleResult(rs, ReceptionVo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		List<Object> list=new ArrayList<Object>();
		List<SYS_RecRepairItemDetail> listRecRepairItemDetail=this.listRecRepairItemDetail(receptionID);
		List<SYS_CollageDetai> listCollageDetai=this.listCollageDetai(receptionID);
		list.add(listRecRepairItemDetail);
		list.add(listCollageDetai);
		return list;
	}
	
	@Override
	public int bavaInsurance(List<PW_Insurance> listInsurance,
			List<SYS_InsuranceDetail> listInsuranceDetail) {
		int insuranceID=0;
		if(listInsurance.get(0).getInsuranceID()==null){
			insuranceID=JdbcHelper.insert(listInsurance.get(0));
		}else{
			JdbcHelper.update(listInsurance.get(0));
			insuranceID=listInsurance.get(0).getInsuranceID();
		}
	    JdbcHelper.update(listInsuranceDetail.get(0));
	    return insuranceID;
	}

	@Override
	public int toAudit(int insuranceID) {
		int i=0;
		rs=JdbcHelper.select(PW_Insurance.class, "InsuranceID", insuranceID);
		PW_Insurance pw_Insurance=JdbcHelper.getSingleResult(rs, PW_Insurance.class);
		pw_Insurance.setToAudit(true);
	    i=JdbcHelper.update(pw_Insurance);
	    return i;
	}

	@Override
	public int toNotAudit(int insuranceID) {
		int i=0;
		rs=JdbcHelper.select(PW_Insurance.class, "InsuranceID", insuranceID);
		PW_Insurance pw_Insurance=JdbcHelper.getSingleResult(rs, PW_Insurance.class);
		pw_Insurance.setToAudit(false);
	    i=JdbcHelper.update(pw_Insurance);
	    return i;
	}

	@Override
	public boolean delectInsurance(int insuranceID) {
		return JdbcHelper.delete(PW_Insurance.class, "InsuranceID", insuranceID);
	}
	
	//SYS_RecRepairItemDetail修理项目明细表
	private List<SYS_RecRepairItemDetail> listRecRepairItemDetail(int receptionID){
		String sql="SELECT * FROM SYS_RecRepairItemDetail WHERE ReceptionID = ? AND MaintainabilityID=6";
		List<SYS_RecRepairItemDetail> listRecRepairItemDetail=null;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, receptionID);
			rs=ps.executeQuery();
			listRecRepairItemDetail = JdbcHelper.getResult(rs, SYS_RecRepairItemDetail.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRecRepairItemDetail;
	}
	
	//SYS_RecProductDetail产品明细表
	private List<SYS_CollageDetai> listCollageDetai(int receptionID){
		String sql="SELECT * FROM PW_Reception r,PW_Collage c,SYS_CollageDetai WHERE r.ReceptionID=c.ReceptionID AND " +
				"c.CollageID=SYS_CollageDetai.CollageID AND r.ReceptionID=? AND SYS_CollageDetai.MaintainabilityID=6";
		List<SYS_CollageDetai> listCollageDetai=null;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, receptionID);
			rs=ps.executeQuery();
			listCollageDetai = JdbcHelper.getResult(rs, SYS_CollageDetai.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCollageDetai;
		
	}
}
