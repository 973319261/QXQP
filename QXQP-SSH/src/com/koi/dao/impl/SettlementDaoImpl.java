package com.koi.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.ISettlementDao;
import com.koi.po.PwBalance;
import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.util.SqlHelper;

@Repository("settlementDao")
public class SettlementDaoImpl implements ISettlementDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
	private String selectCollageDetai = "SELECT SYS_CollageDetai.* FROM PW_Collage c,SYS_CollageDetai WHERE c.CollageID=SYS_CollageDetai.CollageID AND c.ReceptionID=?";
	private SimpleDateFormat fs=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Override
	public int baveBalance(List<PwBalance> listBalance) {
		int i = 0;
		int receptionID = listBalance.get(0).getReceptionId();
		double shouldAmount = listBalance.get(0).getShouldAmount() != null ? listBalance
				.get(0).getShouldAmount() : 0.0;
		listBalance.get(0).setDocumentsTypeId(1);
		List<PwReception> listReception = (List<PwReception>) sqlHelper.select(PwReception.class, "ReceptionID", receptionID);
		listReception.get(0).setToCompletion(true);// 已完工
		listReception.get(0).setCompletionDate(fs.format(new Date()));// 完工日期
		if (listBalance.get(0).getBalanceId() != null) {
			i = listBalance.get(0).getBalanceId();
			List<PwBalance> oldBalance = (List<PwBalance>) sqlHelper.select(PwBalance.class, "BalanceID", i);
			oldBalance.get(0).setOptimalAmount(oldBalance.get(0).getOptimalAmount()
					+ listBalance.get(0).getOptimalAmount());// 优惠金额
			oldBalance.get(0).setCollectionAmount(oldBalance.get(0).getCollectionAmount()
					+ listBalance.get(0).getCollectionAmount());// 收款金额
			if (shouldAmount <= oldBalance.get(0).getOptimalAmount()
					+ oldBalance.get(0).getCollectionAmount()) {
				listReception.get(0).setDocumentStateId(3);// 完工已结算
				listReception.get(0).setBalanceStateId(1);// 付讫（已清）
				oldBalance.get(0).setBalanceStateId(1);// 付讫（已清）
			} else {
				listReception.get(0).setDocumentStateId(3);// 完工已结算
				listReception.get(0).setBalanceStateId(2);// 挂账（部分）
				oldBalance.get(0).setBalanceStateId(2);// 挂账（部分）
			}
			sqlHelper.update(oldBalance.get(0));
		} else {
			double optimalAmount = listBalance.get(0).getOptimalAmount() != null ? listBalance
					.get(0).getOptimalAmount() : 0.0;// 优惠金额
			double collectionAmount = listBalance.get(0).getCollectionAmount() != null ? listBalance
					.get(0).getCollectionAmount() : 0.0;// 收款金额
			listBalance.get(0).setDocumentsTypeId(2);
			if (shouldAmount <= optimalAmount + collectionAmount) {
				listReception.get(0).setDocumentStateId(3);// 完工已结算
				listReception.get(0).setBalanceStateId(1);// 付讫（已清）
				listBalance.get(0).setBalanceStateId(1);// 付讫（已清）
			} else {
				listReception.get(0).setDocumentStateId(3);// 完工已结算
				listReception.get(0).setBalanceStateId(2);// 挂账（部分）
				listBalance.get(0).setBalanceStateId(2);// 挂账（部分）
			}
			sqlHelper.insert(listBalance.get(0));
		}
		i=sqlHelper.update(listReception.get(0));
		return i;
	}

	@Override
	public int toAudit(int receptionID) {
		List<PwReception> list = (List<PwReception>) sqlHelper.select(PwReception.class, "ReceptionID", receptionID);
		list.get(0).setToAudit(true);
		return sqlHelper.update(list.get(0));
	}

	@Override
	public int toNotAudit(int receptionID) {
		List<PwReception> list = (List<PwReception>) sqlHelper.select(PwReception.class, "ReceptionID", receptionID);
		list.get(0).setToAudit(false);
 		return sqlHelper.update(list.get(0));
	}

	@Override
	public List<Object> selectBalanceDetail(int receptionID) {
		List<Object> list = new ArrayList<Object>();
		List<SysRecRepairItemDetail> listRecRepairItemDetail = (List<SysRecRepairItemDetail>) sqlHelper.select(SysRecRepairItemDetail.class, "ReceptionID", receptionID);
		List<SysCollageDetai> listCollageDetai=this.selectCollageDetai(receptionID);//领料明细表
		List<SysRecOtherCostDetail> listRecOtherCostDetail = (List<SysRecOtherCostDetail>) sqlHelper.select(SysRecOtherCostDetail.class, "ReceptionID", receptionID);
		List<SysInsuranceDetail> listInsuranceDetail = (List<SysInsuranceDetail>) sqlHelper.select(SysInsuranceDetail.class, "ReceptionID", receptionID);
		List<SysThreePacksDetail> listThreePacksDetail = (List<SysThreePacksDetail>) sqlHelper.select(SysThreePacksDetail.class, "ReceptionID", receptionID);
		list.add(listRecRepairItemDetail);
		list.add(listCollageDetai);
		list.add(listRecOtherCostDetail);
		list.add(listInsuranceDetail);
		list.add(listThreePacksDetail);
		return list;
	}

	// 查询SYS_CollageDetai领料明细表
	private List<SysCollageDetai> selectCollageDetai(int receptionID) {
		List<SysCollageDetai> listCollageDetai = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectCollageDetai)
				.addEntity(SysCollageDetai.class);
		sqlQuery.setInteger(0, receptionID);
		listCollageDetai = sqlQuery.list();
		return listCollageDetai;
	}
}
