package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gx.dao.IDispatchingDao;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.SYS_Dispatch;
import com.gx.pojo.SYS_RecRepairItemDetail;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;
import com.gx.vo.ReceptionVo;

public class DispatchingDaoImpl implements IDispatchingDao {
	private Connection conn=null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String selectReception = "SELECT p.*,RepairName,CustomerSou,DocumentState,BalanceState FROM " +
			"PW_Reception p,SYS_Repair,SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE " +
			"p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND " +
			"p.BalanceStateID=SYS_BalanceState.BalanceStateID AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID AND " +
			"P.ReceptionID=?";
	@Override
	public int updateListRepairItemDetail(
			List<SYS_RecRepairItemDetail> listRecRepairItem,
			Double maintenAmount, String selfCoding, int receptionID,
			boolean toSendWork) {
		int r=0;
		rs=JdbcHelper.select(PW_Reception.class, "ReceptionID", receptionID);
		PW_Reception list= JdbcHelper.getSingleResult(rs, PW_Reception.class);
		list.setMaintenAmount(maintenAmount);
		list.setSelfCoding(selfCoding);
		list.setToSendWork(toSendWork);
		r=JdbcHelper.update(list);
		if(r > 0){
			 //配件明细表
            if (listRecRepairItem.size() != 0)
            {
                List<Integer> oldID = new ArrayList<Integer>();//原来ID
                List<Integer> newID = new ArrayList<Integer>();//新ID
                rs=JdbcHelper.select(SYS_RecRepairItemDetail.class, "ReceptionID", receptionID);
                List<SYS_RecRepairItemDetail> repairItemDetail= JdbcHelper.getResult(rs, SYS_RecRepairItemDetail.class);
                for (SYS_RecRepairItemDetail item: repairItemDetail)
                {
                    oldID.add(item.getRecRepairItemDetailID());
                }
                for (int i = 0; i < listRecRepairItem.size(); i++)
                {
                    listRecRepairItem.get(i).setReceptionID(receptionID);
                    if (listRecRepairItem.get(i).getRecRepairItemDetailID() == null)
                    {
                        JdbcHelper.insert(listRecRepairItem.get(i));
                    }
                    else
                    {
                        newID.add(listRecRepairItem.get(i).getRecRepairItemDetailID());
                        JdbcHelper.update(listRecRepairItem.get(i));
                    }
                }
                oldID.removeAll(newID);//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                for (Integer item:oldID)
                {
                	JdbcHelper.delete(SYS_RecRepairItemDetail.class, "RecRepairItemDetailID", item);//删除
                }
            }
            else
            {
            	JdbcHelper.delete(SYS_RecRepairItemDetail.class, "ReceptionID", receptionID);//删除全部
            }
		}
		return r;
	}
	@Override
	public double selectDispatch(int dispatchID) {
		rs=JdbcHelper.select(SYS_Dispatch.class, "DispatchID", dispatchID);
		SYS_Dispatch list= JdbcHelper.getSingleResult(rs, SYS_Dispatch.class);
		return list.getPrice();
	}
	@Override
	public ReceptionVo selectReception(int dispatchID) {
		ReceptionVo list = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectReception);
			ps.setInt(1, dispatchID);
			rs = ps.executeQuery();
			list = JdbcHelper.getSingleResult(rs, ReceptionVo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
