package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gx.dao.IClaimsetDao;
import com.gx.pojo.PW_Balance;
import com.gx.pojo.PW_ThreePacks;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;
import com.gx.vo.ReceptionVo;
import com.gx.vo.ReturnJson;

public class ClaimsetDaoImpl implements IClaimsetDao {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	String selectDanHao="SELECT * FROM(SELECT p.*,ClaimMoney,ThreePacksDetailID," +
			"SYS_ClaimCom.ClaimComID,RepairName,CustomerSou,DocumentState,BalanceState,ROW_NUMBER()OVER(ORDER BY " +
			"p.ReceptionID)Num FROM  PW_Reception p,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND SYS_ThreePacksDetail." +
			"ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp ";
	String selectDanHaoCount="SELECT COUNT(*) FROM  PW_Reception p,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair,SYS_DocumentState," +
			"SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND SYS_ThreePacksDetail." +
			"ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=" +
			"SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND " +
			"p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	String selectThreePacks="SELECT * FROM(SELECT t.*,CustomerNum,p.ReceptionID,CarNum,VehicleType,MaintenanceNum,Owner," +
			"SYS_ClaimCom.ClaimComID,RepairName,CustomerSou,DocumentState,p.DocumentStateID,BalanceState,p.BalanceStateID,ROW_NUMBER()OVER" +
			"(ORDER BY p.ReceptionID)Num FROM  PW_Reception p,PW_ThreePacks t,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair," +
			"SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND t.ThreePacksDetailID=" +
			"SYS_ThreePacksDetail.ThreePacksDetailID AND SYS_ThreePacksDetail.ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID =" +
			"SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID " +
			"AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp ";
	String selectThreePacksCount="SELECT COUNT(*) FROM  PW_Reception p,PW_ThreePacks t,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair," +
			"SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND t.ThreePacksDetailID=" +
			"SYS_ThreePacksDetail.ThreePacksDetailID AND SYS_ThreePacksDetail.ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID =" +
			"SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID " +
			"AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	@Override
	public int insuranceNum(String d) {
		String insuranceNum = "SELECT TOP 1 SUBSTRING(InsuranceNum,11,4) from PW_ThreePacks "
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
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return i;
	}

	@Override
	public List<ReceptionVo> selectThreePacks(String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			String toAudit, int startIndex, int pageSize) {
		List<ReceptionVo> list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectThreePacks);	
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
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int selectThreePacksCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectThreePacksCount);	
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
	public ReceptionVo selectThreePacksData(int insuranceDetailID) {
		ReceptionVo list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectThreePacks);	
			buffer.append("WHERE tmp.ThreePacksDetailID = ? ");
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
	public ReceptionVo selectThreePackss(int receptionID) {
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
	public int baveThreePacks(List<PW_ThreePacks> listThreePacks,
			int claimComID) {
		int threePacksID=0;
		if(listThreePacks.get(0).getThreePacksID()==null){
			threePacksID=JdbcHelper.insert(listThreePacks.get(0));
		}else{
			JdbcHelper.update(listThreePacks.get(0));
			threePacksID=listThreePacks.get(0).getThreePacksID();
		}
		int threePacksDetailID = listThreePacks.get(0).getThreePacksDetailID();
		rs=JdbcHelper.select(SYS_ThreePacksDetail.class, "ThreePacksDetailID", threePacksDetailID);
		SYS_ThreePacksDetail listThreePacksDetail=JdbcHelper.getSingleResult(rs, SYS_ThreePacksDetail.class);
	    JdbcHelper.update(listThreePacksDetail);
	    return threePacksID;
	}

	@Override
	public int toAudit(int threePacksID) {
		int i=0;
		rs=JdbcHelper.select(PW_ThreePacks.class, "ThreePacksID", threePacksID);
		PW_ThreePacks pw_ThreePacks=JdbcHelper.getSingleResult(rs, PW_ThreePacks.class);
		pw_ThreePacks.setToAudit(true);
	    i=JdbcHelper.update(pw_ThreePacks);
	    return i;
	}

	@Override
	public int toNotAudit(int threePacksID) {
		int i=0;
		rs=JdbcHelper.select(PW_ThreePacks.class, "ThreePacksID", threePacksID);
		PW_ThreePacks pw_ThreePacks=JdbcHelper.getSingleResult(rs, PW_ThreePacks.class);
		pw_ThreePacks.setToAudit(false);
	    i=JdbcHelper.update(pw_ThreePacks);
	    return i;
	}

	@Override
	public boolean delectThreePacks(int threePacksID) {
		return JdbcHelper.delete(PW_ThreePacks.class, "ThreePacksID", threePacksID);
	}

	@Override
	public ReturnJson selectBalance(String businessNum) {
		ReturnJson returnJson =new ReturnJson();
		rs=JdbcHelper.select(PW_Balance.class, "BusinessNum", businessNum);
		PW_Balance pw_Balance=JdbcHelper.getSingleResult(rs, PW_Balance.class);
		if(pw_Balance!=null){
			if(pw_Balance.getBalanceStateID()==1){
				 returnJson.setState(false);
                 returnJson.setText("您和该用户没有付款信息，或你们往来账已平衡！");
                 returnJson.setObjData("该单已有付款记录，不能反审核，如要更改费用，请到财务管理客户费用中进行平衡处理。");
			}  else if (pw_Balance.getBalanceStateID() == 2)
            {
                returnJson.setState(true);
                returnJson.setStates("A");
                returnJson.setText(pw_Balance.getBalanceID().toString());
                returnJson.setObjData("该单已有付款记录，不能反审核，如要更改费用，请到财务管理客户费用中进行平衡处理。");
            }
		}else {
            returnJson.setState(true);
        }
		return returnJson;
	}

	@Override
	public PW_Balance selectBalances(int balanceID) {
		PW_Balance pw_Balance=null;
		rs=JdbcHelper.select(PW_Balance.class, "BalanceID", balanceID);
		pw_Balance=JdbcHelper.getSingleResult(rs, PW_Balance.class);
		return pw_Balance;
	}

	@Override
	public int baveBalance(List<PW_Balance> listBalance) {
		  int i=0;
		  double optimalAmount=listBalance.get(0).getOptimalAmount()==null?0.0:listBalance.get(0).getOptimalAmount();
          if (listBalance.get(0).getBalanceID() != null)
          {
        	  
        	  rs=JdbcHelper.select(PW_Balance.class, "BalanceID", listBalance.get(0).getBalanceID());
        	  PW_Balance oldBalance=JdbcHelper.getSingleResult(rs, PW_Balance.class);//原来的结算单
              oldBalance.setOptimalAmount(oldBalance.getOptimalAmount() + optimalAmount);//优惠金额
              oldBalance.setCollectionAmount(oldBalance.getCollectionAmount() + listBalance.get(0).getCollectionAmount());//收款金额
              if (listBalance.get(0).getShouldAmount() <= oldBalance.getOptimalAmount() + oldBalance.getCollectionAmount())
              {
                  oldBalance.setBalanceStateID(1);//付讫（已清）
              }
              else
              {
                  oldBalance.setBalanceStateID(2);//挂账（部分）
              }
              JdbcHelper.update(oldBalance);
              i=listBalance.get(0).getBalanceID();
          }
          else {
              listBalance.get(0).setDocumentsTypeID(2);
              
              if (listBalance.get(0).getShouldAmount()  <= optimalAmount + listBalance.get(0).getCollectionAmount())
              {
                  listBalance.get(0).setBalanceStateID(1);//付讫（已清）
              }
              else
              {
                  listBalance.get(0).setBalanceStateID(2);//挂账（部分）
              }
              i=JdbcHelper.insert(listBalance.get(0));
          }
		return i;
	}
	//SYS_RecRepairItemDetail修理项目明细表
		private List<SYS_RecRepairItemDetail> listRecRepairItemDetail(int receptionID){
			String sql="SELECT * FROM SYS_RecRepairItemDetail WHERE ReceptionID = ? AND (MaintainabilityID=3 or MaintainabilityID=4)";
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
					"c.CollageID=SYS_CollageDetai.CollageID AND r.ReceptionID=? AND (SYS_CollageDetai.MaintainabilityID=4 OR " +
					"SYS_CollageDetai.MaintainabilityID=3)";
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
