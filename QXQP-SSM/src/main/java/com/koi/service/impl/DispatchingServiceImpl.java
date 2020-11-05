package com.koi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.CommonDao;
import com.koi.mapper.DispatchingDao;
import com.koi.po.PwReception;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.service.IDispatchingService;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.ReceptionVo;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Transactional
@Service("dispatchingService")
public class DispatchingServiceImpl implements IDispatchingService {
	@Autowired
	private DispatchingDao dispatchingDao;
	@Autowired
	private CommonDao commonDao;

	@Override
	public int updateListRepairItemDetail(
			String sysRecRepairItemDetail,
			Double maintenAmount, String selfCoding, int receptionID,
			boolean toSendWork) {
		List<SysRecRepairItemDetail> listRecRepairItem=Util.jsonToVo(sysRecRepairItemDetail, SysRecRepairItemDetail.class);
		int r=0;
		PwReception list = commonDao.selectReceptionById(receptionID);
		list.setMaintenAmount(maintenAmount);
		list.setSelfCoding(selfCoding);
		list.setToSendWork(toSendWork);
		r=commonDao.updateReception(list);
		if(r > 0){
			 //配件明细表
            if (listRecRepairItem.size() != 0)
            {
                List<Integer> oldID = new ArrayList<Integer>();//原来ID
                List<Integer> newID = new ArrayList<Integer>();//新ID
                List<SysRecRepairItemDetail> repairItemDetail =commonDao.selectRecRepairItemDetail(receptionID);
                for (SysRecRepairItemDetail item: repairItemDetail)
                {
                    oldID.add(item.getRecRepairItemDetailId());
                }
                for (int i = 0; i < listRecRepairItem.size(); i++)
                {
                    listRecRepairItem.get(i).setReceptionId(receptionID);
                    if (listRecRepairItem.get(i).getRecRepairItemDetailId() == null)
                    {
                    	commonDao.insertRecRepairItemDetail(listRecRepairItem.get(i));
                    }
                    else
                    {
                        newID.add(listRecRepairItem.get(i).getRecRepairItemDetailId());
                        commonDao.updateRecRepairItemDetail(listRecRepairItem.get(i));
                    }
                }
                oldID.removeAll(newID);//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                for (Integer item:oldID)
                {
                	commonDao.deleteRecRepairItemDetail(item);//删除
                }
            }
            else
            {
            	commonDao.deleteRecRepairItemDetail(receptionID);//删除全部
            }
		}
		return r;
	}

	@Override
	public double selectDispatch(int dispatchID) {
		// TODO Auto-generated method stub
		return dispatchingDao.selectDispatch(dispatchID);
	}

	@Override
	public Object selectReception(int receptionID) {
		ReceptionVo list = dispatchingDao.selectReception(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		return jsonArr;
	}
}
