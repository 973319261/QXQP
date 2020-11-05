package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gx.dao.ISettlementDao;
import com.gx.pojo.PW_Balance;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.SYS_InsuranceDetail;
import com.gx.pojo.SYS_RecOtherCostDetail;
import com.gx.pojo.SYS_RecProductDetail;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.pojo.SYS_ThreePacksDetail;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;

public class SettlementDaoImpl implements ISettlementDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int baveBalance(List<PW_Balance> listBalance) {
		int i = 0;
		int receptionID = listBalance.get(0).getReceptionID();
		double shouldAmount = listBalance.get(0).getShouldAmount()!=null?listBalance.get(0).getShouldAmount():0.0;
		listBalance.get(0).setDocumentsTypeID(1);
		rs = JdbcHelper.select(PW_Reception.class, "ReceptionID", receptionID);
		PW_Reception listReception = JdbcHelper.getSingleResult(rs,
				PW_Reception.class);
		listReception.setToCompletion(true);// 已完工
		listReception.setCompletionDate(new Date());// 完工日期
		if (listBalance.get(0).getBalanceID() != null) {
			i = listBalance.get(0).getBalanceID();
			rs = JdbcHelper.select(PW_Balance.class, "BalanceID", i);
			PW_Balance oldBalance = JdbcHelper.getSingleResult(rs,
					PW_Balance.class);// 原来的结算单
			oldBalance.setOptimalAmount(oldBalance.getOptimalAmount()
					+ listBalance.get(0).getOptimalAmount());// 优惠金额
			oldBalance.setCollectionAmount(oldBalance.getCollectionAmount()
					+ listBalance.get(0).getCollectionAmount());// 收款金额
			if (shouldAmount <= oldBalance.getOptimalAmount()
					+ oldBalance.getCollectionAmount()) {
				listReception.setDocumentStateID(3);// 完工已结算
				listReception.setBalanceStateID(1);// 付讫（已清）
				oldBalance.setBalanceStateID(1);// 付讫（已清）
			} else {
				listReception.setDocumentStateID(3);// 完工已结算
				listReception.setBalanceStateID(2);// 挂账（部分）
				oldBalance.setBalanceStateID(2);// 挂账（部分）
			}
			JdbcHelper.update(oldBalance);
		} else {
			double optimalAmount = listBalance.get(0).getOptimalAmount()!=null?listBalance.get(0).getOptimalAmount():0.0;// 优惠金额
			double collectionAmount = listBalance.get(0).getCollectionAmount()!=null?listBalance.get(0).getCollectionAmount():0.0;// 收款金额
			listBalance.get(0).setDocumentsTypeID(2);
			if (shouldAmount <= optimalAmount + collectionAmount) {
				listReception.setDocumentStateID(3);// 完工已结算
				listReception.setBalanceStateID(1);// 付讫（已清）
				listBalance.get(0).setBalanceStateID(1);// 付讫（已清）
			} else {
				listReception.setDocumentStateID(3);// 完工已结算
				listReception.setBalanceStateID(2);// 挂账（部分）
				listBalance.get(0).setBalanceStateID(2);// 挂账（部分）
			}
			JdbcHelper.insert(listBalance.get(0));
		}
		i=JdbcHelper.update(listReception);
		return i;
	}

	@Override
	public int toAudit(int receptionID) {
		int i = 0;
		rs = JdbcHelper.select(PW_Reception.class, "ReceptionID", receptionID);
		PW_Reception list = JdbcHelper.getSingleResult(rs, PW_Reception.class);
		list.setToAudit(true);
		i = JdbcHelper.update(list);
		return i;
	}

	@Override
	public int toNotAudit(int receptionID) {
		int i = 0;
		rs = JdbcHelper.select(PW_Reception.class, "ReceptionID", receptionID);
		PW_Reception list = JdbcHelper.getSingleResult(rs, PW_Reception.class);
		list.setToAudit(false);
		i = JdbcHelper.update(list);
		return i;
	}

	@Override
	public List<Object> selectBalanceDetail(int receptionID) {
		List<Object> list = new ArrayList<Object>();
		rs = JdbcHelper.select(SYS_RecRepairItemDetail.class, "ReceptionID",
				receptionID);
		List<SYS_RecRepairItemDetail> listRecRepairItemDetail = JdbcHelper
				.getResult(rs, SYS_RecRepairItemDetail.class);// 修理项目明细表
		List<SYS_CollageDetai> listCollageDetai=this.selectCollageDetai(receptionID);//领料明细表
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
		list.add(listCollageDetai);
		list.add(listRecOtherCostDetail);
		list.add(listInsuranceDetail);
		list.add(listThreePacksDetail);
		return list;
	}
	//查询SYS_CollageDetai领料明细表
	private List<SYS_CollageDetai> selectCollageDetai(int receptionID){
		String sql="SELECT SYS_CollageDetai.* FROM PW_Collage c,SYS_CollageDetai WHERE c.CollageID=SYS_CollageDetai.CollageID AND c.ReceptionID=?";
		List<SYS_CollageDetai> listCollageDetai=null;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, receptionID);
			rs=ps.executeQuery();
			listCollageDetai=JdbcHelper.getResult(rs, SYS_CollageDetai.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCollageDetai;
	}

}
