package com.koi.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.ICompletionDao;
import com.koi.po.PwReception;
import com.koi.util.SqlHelper;
import com.koi.vo.ReceptionVo;

@Repository("completionDao")
public class CompletionDaoImpl implements ICompletionDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
	private String selectReception = "SELECT * FROM(SELECT *,ROW_NUMBER()OVER(ORDER BY ReceptionID)Num FROM(SELECT p.*,RepairName,CustomerSou,DocumentState,BalanceState"
			+ " FROM  PW_Reception p,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE 1=1 ";
	private String selectReceptionCount = "SELECT COUNT(*) as num  FROM  PW_Reception p,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	@Override
	public List<ReceptionVo> selectReception(String startDate, String endDate,
			String toCompletion, Long startIndex, Long pageSize) {
		List<ReceptionVo> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectReception);
		if (!"".equals(startDate) && !"".equals(endDate)) {
			buffer.append(" AND BalanceDate >= '" + startDate
					+ "' AND BalanceDate <= '" + endDate + "'");
		}
		if (!"".equals(startDate) && "".equals(endDate)) {
			buffer.append(" AND BalanceDate >= '" + startDate + "'");
		}
		if ("".equals(startDate) && !"".equals(endDate)) {
			buffer.append(" AND BalanceDate <= '" + endDate + "'");
		}
		if (!"".equals(toCompletion)) {
			if ("true".equals(toCompletion)) {
				buffer.append(" AND ToCompletion =" + 1);
			} else {
				buffer.append(" AND ToCompletion =" + 0);
			}
		}
		buffer.append(")AS db WHERE db.Num BETWEEN "+ startIndex + " AND "+ pageSize);
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString())
				.addEntity(ReceptionVo.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectReceptionCount(String startDate, String endDate,
			String toCompletion) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectReceptionCount);
		if (!"".equals(startDate) && !"".equals(endDate)) {
			buffer.append(" AND BalanceDate >= '" + startDate
					+ "' AND BalanceDate <= '" + endDate + "'");
		}
		if (!"".equals(startDate) && "".equals(endDate)) {
			buffer.append(" AND BalanceDate >= '" + startDate + "'");
		}
		if ("".equals(startDate) && !"".equals(endDate)) {
			buffer.append(" AND BalanceDate <= '" + endDate + "'");
		}
		if (!"".equals(toCompletion)) {
			if ("true".equals(toCompletion)) {
				buffer.append(" AND ToCompletion =" + 1);
			} else {
				buffer.append(" AND ToCompletion =" + 0);
			}
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString());
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public int updateCompletionTrue(List<ReceptionVo> listReception) {
		int i = 0;
		for (ReceptionVo item : listReception) {
			List<PwReception> list = (List<PwReception>) sqlHelper.select(
					PwReception.class, "ReceptionID", item.getReceptionId());
			list.get(0).setToCompletion(true);
			list.get(0).setCompletionDate(format.format(new Date()));
			list.get(0).setDocumentStateId(2);
			i = sqlHelper.update(list.get(0));
		}
		return i;
	}

	@Override
	public int updateCompletionFalse(List<ReceptionVo> listReception) {
		int i = 0;
		for (ReceptionVo item : listReception) {
			List<PwReception> list = (List<PwReception>) sqlHelper.select(
					PwReception.class, "ReceptionID", item.getReceptionId());
			list.get(0).setToCompletion(false);
			list.get(0).setCompletionDate(null);
			list.get(0).setDocumentStateId(1);
			i = sqlHelper.update(list.get(0));
		}
		return i;
	}
}
