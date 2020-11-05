package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Reception;

import com.gx.dao.ICompletionDao;
import com.gx.pojo.PW_Reception;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;
import com.gx.vo.ReceptionVo;

public class CompletionDaoImpl implements ICompletionDao {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
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
	public List<ReceptionVo> selectReception(String startDate, String endDate,
			String toCompletion, int startIndex, int pageSize) {
		List<ReceptionVo> list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectReception);
			if (startDate != "" && endDate != "") {
				buffer.append(" AND tmp.BalanceDate >= '" + startDate
						+ "' AND tmp.BalanceDate <= '" + endDate + "'");
			}
			if (startDate != "" && endDate == "") {
				buffer.append(" AND tmp.BalanceDate >= '" + startDate + "'");
			}
			if (startDate == "" && endDate != "") {
				buffer.append(" AND tmp.BalanceDate <= '" + endDate + "'");
			}
			if (toCompletion != "") {
				if ("true".equals(toCompletion)){ 
					buffer.append(" AND tmp.ToCompletion ="+ 1);
				}else{
					buffer.append(" AND tmp.ToCompletion =" + 0);
				}
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
	public int selectReceptionCount(String startDate, String endDate,
			String toCompletion) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(selectReceptionCount);
			if (startDate != "" && endDate != "") {
				buffer.append(" AND BalanceDate >= '" + startDate
						+ "' AND BalanceDate <= '" + endDate + "'");
			}
			if (startDate != "" && endDate == "") {
				buffer.append(" AND BalanceDate >= '" + startDate + "'");
			}
			if (startDate == "" && endDate != "") {
				buffer.append(" AND BalanceDate <= '" + endDate + "'");
			}
			if (toCompletion != "") {
				if ("true".equals(toCompletion)){
					buffer.append(" AND ToCompletion ="+ 1);
				}else{
					buffer.append(" AND ToCompletion =" + 0);
				}
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
	public int updateCompletionTrue(List<ReceptionVo> listReception) {
		int i=0;
		for (ReceptionVo item : listReception) {
			rs=JdbcHelper.select(PW_Reception.class, "ReceptionID", item.getReceptionID());
			PW_Reception list=JdbcHelper.getSingleResult(rs, PW_Reception.class);
			list.setToCompletion(true);
			list.setCompletionDate(new Date());
			list.setDocumentStateID(2);
			i=JdbcHelper.update(list);
		}
		return i;
	}

	@Override
	public int updateCompletionFalse(List<ReceptionVo> listReception) {
		int i=0;
		for (ReceptionVo item : listReception) {
			rs=JdbcHelper.select(PW_Reception.class, "ReceptionID", item.getReceptionID());
			PW_Reception list=JdbcHelper.getSingleResult(rs, PW_Reception.class);
			list.setToCompletion(false);
			list.setCompletionDate(null);
			list.setDocumentStateID(1);
			i=JdbcHelper.update(list);
		}
		return i;
	}

}
