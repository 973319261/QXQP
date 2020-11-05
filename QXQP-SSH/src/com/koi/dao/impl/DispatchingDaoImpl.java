package com.koi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.IDispatchingDao;
import com.koi.po.PwReception;
import com.koi.po.SysDispatch;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.util.SqlHelper;
import com.koi.vo.ReceptionVo;

@Repository("dispatchingDao")
public class DispatchingDaoImpl implements IDispatchingDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
	String selectReception = "SELECT p.*,RepairName,CustomerSou,DocumentState,BalanceState FROM " +
			"PW_Reception p,SYS_Repair,SYS_DocumentState,SYS_CustomerSou,SYS_BalanceState WHERE " +
			"p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID=SYS_DocumentState.DocumentStateID AND " +
			"p.BalanceStateID=SYS_BalanceState.BalanceStateID AND p.CustomerSouID=SYS_CustomerSou.CustomerSouID AND " +
			"P.ReceptionID=?";
	@Override
	public int updateListRepairItemDetail(
			List<SysRecRepairItemDetail> listRecRepairItem,
			Double maintenAmount, String selfCoding, int receptionID,
			boolean toSendWork) {
		int r=0;
		PwReception list = (PwReception) sqlHelper.select(PwReception.class, "ReceptionID", receptionID).get(0);
		list.setMaintenAmount(maintenAmount);
		list.setSelfCoding(selfCoding);
		list.setToSendWork(toSendWork);
		r=sqlHelper.update(list);
		if(r > 0){
			 //配件明细表
            if (listRecRepairItem.size() != 0)
            {
                List<Integer> oldID = new ArrayList<Integer>();//原来ID
                List<Integer> newID = new ArrayList<Integer>();//新ID
                List<SysRecRepairItemDetail> repairItemDetail = (List<SysRecRepairItemDetail>) sqlHelper.select(SysRecRepairItemDetail.class, "ReceptionID", receptionID);
                for (SysRecRepairItemDetail item: repairItemDetail)
                {
                    oldID.add(item.getRecRepairItemDetailId());
                }
                for (int i = 0; i < listRecRepairItem.size(); i++)
                {
                    listRecRepairItem.get(i).setReceptionId(receptionID);
                    if (listRecRepairItem.get(i).getRecRepairItemDetailId() == null)
                    {
                    	sqlHelper.insert(listRecRepairItem.get(i));
                    }
                    else
                    {
                        newID.add(listRecRepairItem.get(i).getRecRepairItemDetailId());
                        sqlHelper.update(listRecRepairItem.get(i));
                    }
                }
                oldID.removeAll(newID);//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                for (Integer item:oldID)
                {
                	sqlHelper.delete(SysRecRepairItemDetail.class, "RecRepairItemDetailID", item);//删除
                }
            }
            else
            {
            	sqlHelper.delete(SysRecRepairItemDetail.class, "ReceptionID", receptionID);//删除全部
            }
		}
		return r;
	}
	
	@Override
	public double selectDispatch(int dispatchID) {
		List<SysDispatch> list = (List<SysDispatch>) sqlHelper.select(SysDispatch.class, "DispatchID", dispatchID);
		return list.get(0).getPrice();
	}
	@Override
	public ReceptionVo selectReception(int receptionID) {
		ReceptionVo list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectReception).addEntity(ReceptionVo.class);
		sqlQuery.setInteger(0, receptionID);
		list=(ReceptionVo) sqlQuery.uniqueResult();
		return list;
	}
}
