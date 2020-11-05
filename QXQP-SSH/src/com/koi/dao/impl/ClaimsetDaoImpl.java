package com.koi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.IClaimsetDao;
import com.koi.po.PwBalance;
import com.koi.po.PwThreePacks;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.util.SqlHelper;
import com.koi.vo.ReturnJson;
import com.koi.vo.ThreePacksDetailVo;
import com.koi.vo.ThreePacksVo;

@Repository("claimsetDao")
public class ClaimsetDaoImpl implements IClaimsetDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
	String selectDanHao = "SELECT * FROM(SELECT *,ROW_NUMBER()OVER(ORDER BY ReceptionID)Num FROM(SELECT p.*,ClaimMoney,ThreePacksDetailID,"
			+ "SYS_ClaimCom.ClaimComID,RepairName,CustomerSou,DocumentState,BalanceState "
			+ "FROM  PW_Reception p,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND SYS_ThreePacksDetail."
			+ "ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE 1=1 ";
	String selectToDanHao = "SELECT p.*,ClaimMoney,ThreePacksDetailID,"
			+ "SYS_ClaimCom.ClaimComID,RepairName,CustomerSou,DocumentState,BalanceState "
			+ "FROM  PW_Reception p,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND SYS_ThreePacksDetail."
			+ "ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID AND p.ReceptionID = ?";
	String selectDanHaoCount = "SELECT COUNT(*) as num FROM  PW_Reception p,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND SYS_ThreePacksDetail."
			+ "ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	String selectThreePacks = "SELECT * FROM(SELECT *,ROW_NUMBER()OVER(ORDER BY ReceptionID)Num FROM(SELECT t.*,CustomerNum,p.ReceptionID,CarNum,VehicleType,MaintenanceNum,Owner,"
			+ "SYS_ClaimCom.ClaimComID,RepairName,CustomerSou,DocumentState,p.DocumentStateID,BalanceState,p.BalanceStateID "
			+ "FROM  PW_Reception p,PW_ThreePacks t,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair,"
			+ "SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND t.ThreePacksDetailID="
			+ "SYS_ThreePacksDetail.ThreePacksDetailID AND SYS_ThreePacksDetail.ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID ="
			+ "SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID "
			+ "AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE 1=1 ";
	String selectToThreePacks = "SELECT t.*,CustomerNum,p.ReceptionID,CarNum,VehicleType,MaintenanceNum,Owner,"
			+ "SYS_ClaimCom.ClaimComID,RepairName,CustomerSou,DocumentState,p.DocumentStateID,BalanceState,p.BalanceStateID "
			+ "FROM  PW_Reception p,PW_ThreePacks t,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair,"
			+ "SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND t.ThreePacksDetailID="
			+ "SYS_ThreePacksDetail.ThreePacksDetailID AND SYS_ThreePacksDetail.ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID ="
			+ "SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID "
			+ "AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID AND t.ThreePacksDetailID = ?";
	String selectThreePacksCount = "SELECT COUNT(*) as num FROM  PW_Reception p,PW_ThreePacks t,SYS_ThreePacksDetail,SYS_ClaimCom,SYS_Repair,"
			+ "SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE p.ReceptionID=SYS_ThreePacksDetail.ReceptionID AND t.ThreePacksDetailID="
			+ "SYS_ThreePacksDetail.ThreePacksDetailID AND SYS_ThreePacksDetail.ClaimComID= SYS_ClaimCom.ClaimComID AND p.RepairID ="
			+ "SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID "
			+ "AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID";

	@Override
	public int insuranceNum(String d) {
		String insuranceNum = "SELECT TOP 1 SUBSTRING(InsuranceNum,11,4) as num from PW_ThreePacks "
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
	public List<ThreePacksDetailVo> selectDanHao(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit,
			Long startIndex, Long pageSize) {
		List<ThreePacksDetailVo> list = null;
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
				.addEntity(ThreePacksDetailVo.class);
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
	public List<ThreePacksVo> selectThreePacks(String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			String toAudit, Long startIndex, Long pageSize) {
		List<ThreePacksVo> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectThreePacks);
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
				.addEntity(ThreePacksVo.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectThreePacksCount(String maintenanceNum, String carNum,
			int documentStateID, int balanceStateID, String toAudit) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectThreePacksCount);
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
	public ThreePacksVo selectThreePacksData(Long insuranceDetailID) {
		ThreePacksVo list = new ThreePacksVo();
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectToThreePacks);
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString()).addEntity(ThreePacksVo.class);
		sqlQuery.setLong(0, insuranceDetailID);
		list =  (ThreePacksVo) sqlQuery.uniqueResult();
		return list;
	}

	@Override
	public ThreePacksDetailVo selectThreePackss(int receptionID) {
		ThreePacksDetailVo list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectToDanHao).addEntity(ThreePacksDetailVo.class);
		sqlQuery.setInteger(0, receptionID);
		list = (ThreePacksDetailVo) sqlQuery.uniqueResult();
		return list;
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		List<Object> list = new ArrayList<Object>();
		List<SysRecRepairItemDetail> listRecRepairItemDetail = this
				.listRecRepairItemDetail(receptionID);
		List<SysCollageDetai> listCollageDetai = this
				.listCollageDetai(receptionID);
		list.add(listRecRepairItemDetail);
		list.add(listCollageDetai);
		return list;
	}

	@Override
	public int baveThreePacks(List<PwThreePacks> listThreePacks, int claimComID) {
		int threePacksID = 0;
		if (listThreePacks.get(0).getThreePacksId() == null) {
			threePacksID = sqlHelper.insert(listThreePacks.get(0));
		} else {
			sqlHelper.update(listThreePacks.get(0));
			threePacksID = listThreePacks.get(0).getThreePacksId();
		}
		int threePacksDetailID = listThreePacks.get(0).getThreePacksDetailId();
		List<SysThreePacksDetail> list = (List<SysThreePacksDetail>) sqlHelper
				.select(SysThreePacksDetail.class, "ThreePacksDetailID",
						threePacksDetailID);
		sqlHelper.update(list.get(0));
		return threePacksID;
	}

	@Override
	public int toAudit(int threePacksID) {
		int i = 0;
		List<PwThreePacks> list = (List<PwThreePacks>) sqlHelper.select(
				PwThreePacks.class, "ThreePacksID", threePacksID);
		list.get(0).setToAudit(true);
		i = sqlHelper.update(list.get(0));
		return i;
	}

	@Override
	public int toNotAudit(int threePacksID) {
		int i = 0;
		List<PwThreePacks> list = (List<PwThreePacks>) sqlHelper.select(
				PwThreePacks.class, "ThreePacksID", threePacksID);
		list.get(0).setToAudit(false);
		i = sqlHelper.update(list.get(0));
		return i;
	}

	@Override
	public boolean delectThreePacks(int threePacksID) {
		return sqlHelper.delete(PwThreePacks.class, "ThreePacksID",
				threePacksID);
	}

	@Override
	public ReturnJson selectBalance(String businessNum) {
		ReturnJson returnJson = new ReturnJson();
		List<PwBalance> pw_Balance = (List<PwBalance>) sqlHelper.select(
				PwBalance.class, "BusinessNum", businessNum);
		if (pw_Balance.size() != 0) {
			if (pw_Balance.get(0).getBalanceStateId() == 1) {
				returnJson.setState(false);
				returnJson.setText("您和该用户没有付款信息，或你们往来账已平衡！");
				returnJson
						.setObjData("该单已有付款记录，不能反审核，如要更改费用，请到财务管理客户费用中进行平衡处理。");
			} else if (pw_Balance.get(0).getBalanceStateId() == 2) {
				returnJson.setState(true);
				returnJson.setStates("A");
				returnJson.setText(pw_Balance.get(0).getBalanceId().toString());
				returnJson
						.setObjData("该单已有付款记录，不能反审核，如要更改费用，请到财务管理客户费用中进行平衡处理。");
			}
		} else {
			returnJson.setState(true);
		}
		return returnJson;
	}

	@Override
	public PwBalance selectBalances(int balanceID) {
		PwBalance pwBalance = null;
		List<PwBalance> list = (List<PwBalance>) sqlHelper.select(
				PwBalance.class, "BalanceID", balanceID);
		return list.get(0);
	}

	@Override
	public int baveBalance(List<PwBalance> listBalance) {
		int i = 0;
		double optimalAmount = listBalance.get(0).getOptimalAmount() == null ? 0.0
				: listBalance.get(0).getOptimalAmount();
		if (listBalance.get(0).getBalanceId() != null) {

			List<PwBalance> oldBalance = (List<PwBalance>) sqlHelper.select(
					PwBalance.class, "BalanceID", listBalance.get(0)
							.getBalanceId());
			oldBalance.get(0).setOptimalAmount(
					oldBalance.get(0).getOptimalAmount() + optimalAmount);// 优惠金额
			oldBalance.get(0).setCollectionAmount(
					oldBalance.get(0).getCollectionAmount()
							+ listBalance.get(0).getCollectionAmount());// 收款金额
			if (listBalance.get(0).getShouldAmount() <= oldBalance.get(0)
					.getOptimalAmount()
					+ oldBalance.get(0).getCollectionAmount()) {
				oldBalance.get(0).setBalanceStateId(1);// 付讫（已清）
			} else {
				oldBalance.get(0).setBalanceStateId(2);// 挂账（部分）
			}
			sqlHelper.update(oldBalance.get(0));
			i = listBalance.get(0).getBalanceId();
		} else {
			listBalance.get(0).setDocumentsTypeId(2);
			if (listBalance.get(0).getShouldAmount() <= optimalAmount
					+ listBalance.get(0).getCollectionAmount()) {
				listBalance.get(0).setBalanceStateId(1);// 付讫（已清）
			} else {
				listBalance.get(0).setBalanceStateId(2);// 挂账（部分）
			}
			i = sqlHelper.insert(listBalance.get(0));
		}
		return i;
	}

	// SYS_RecRepairItemDetail修理项目明细表
	private List<SysRecRepairItemDetail> listRecRepairItemDetail(int receptionID) {
		String sql = "SELECT * FROM SYS_RecRepairItemDetail WHERE ReceptionID = ? AND (MaintainabilityID=3 or MaintainabilityID=4)";
		List<SysRecRepairItemDetail> listRecRepairItemDetail = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(
				SysRecRepairItemDetail.class);
		sqlQuery.setInteger(0, receptionID);
		listRecRepairItemDetail = sqlQuery.list();
		return listRecRepairItemDetail;
	}

	// SYS_RecProductDetail产品明细表
	private List<SysCollageDetai> listCollageDetai(int receptionID) {
		String sql = "SELECT * FROM PW_Reception r,PW_Collage c,SYS_CollageDetai WHERE r.ReceptionID=c.ReceptionID AND "
				+ "c.CollageID=SYS_CollageDetai.CollageID AND r.ReceptionID=? AND (SYS_CollageDetai.MaintainabilityID=4 OR "
				+ "SYS_CollageDetai.MaintainabilityID=3)";
		List<SysCollageDetai> listCollageDetai = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(
				SysCollageDetai.class);
		sqlQuery.setInteger(0, receptionID);
		listCollageDetai = sqlQuery.list();
		return listCollageDetai;

	}
}
