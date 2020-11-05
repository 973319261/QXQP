package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.CommonDao;
import com.koi.mapper.RapidrepairDao;
import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.service.IRapidrepairService;
import com.koi.util.Util;

@Transactional
@Service("rapidrepairService")
public class RapidrepairServiceImpl implements IRapidrepairService {
	@Autowired
	private RapidrepairDao rapidrepairDao;
	@Autowired
	private CommonDao commonDao;
	@Override
	public String maintenanceNum() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String d = sf.format(date);
		int num = rapidrepairDao.maintenanceNum("K" + d) + 1;
		StringBuffer CustomerNum = new StringBuffer("K");
		String str = "K" + d + String.format("%04d", num);
		return str;
	}

	@Override
	public int updateListReceptione(String pwReception,
			String sysRecRepairItemDetail, String sysCollageDetai,
			String sysRecOtherCostDetail, String sysInsuranceDetail,
			String sysThreePacksDetail) {
		List<PwReception> listReception = Util.jsonToVo(pwReception,
				PwReception.class);
		List<SysRecRepairItemDetail> listRecRepairItem = Util.jsonToVo(
				sysRecRepairItemDetail, SysRecRepairItemDetail.class);
		List<SysCollageDetai> listCollageDetai = Util.jsonToVo(sysCollageDetai,
				SysCollageDetai.class);
		List<SysRecOtherCostDetail> listRecOtherCost = Util.jsonToVo(
				sysRecOtherCostDetail, SysRecOtherCostDetail.class);
		List<SysInsuranceDetail> listArrInsuranceMoney = Util.jsonToVo(
				sysInsuranceDetail, SysInsuranceDetail.class);
		List<SysThreePacksDetail> listThreePacksDetail = Util.jsonToVo(
				sysThreePacksDetail, SysThreePacksDetail.class);
		int id = 0;
		 if (listRecRepairItem.size() != 0)
        {
            listReception.get(0).setToSendWork(true);//派工状态
        }
        else {
            listReception.get(0).setToSendWork(false);//派工状态
        }
        if ( listReception.get(0).getReceptionId()== null)
        {
            listReception.get(0).setDocumentStateId(1); //未结算
            listReception.get(0).setBalanceStateId(3);//未付款
            listReception.get(0).setCollageState("未领料");//领料状态
            listReception.get(0).setToCompletion(false);//领料状态
            commonDao.insertReception(listReception.get(0));
            id=listReception.get(0).getReceptionId();
        }
        else
        {
        	id=listReception.get(0).getReceptionId();
       	    PwReception list =commonDao.selectReceptionById(id);
            listReception.get(0).setMaintenAmount(list.getMaintenAmount());
            listReception.get(0).setDocumentStateId(list.getDocumentStateId());
            listReception.get(0).setBalanceStateId(list.getBalanceStateId());
            listReception.get(0).setCollageState(list.getCollageState());
            listReception.get(0).setToCompletion(list.getToCompletion());
            commonDao.updateReception(listReception.get(0));//修改
        }
		if (id > 0) {// 主表保存成功
			List<Integer> oldID = new ArrayList<Integer>();// 原来ID
			List<Integer> newID = new ArrayList<Integer>();// 传过来的ID
			if (listRecRepairItem.size() != 0) {// 修理项目明细表有数据
				// 维修明细表
				List<SysRecRepairItemDetail> list =  commonDao.selectRecRepairItemDetail(id);
				for (SysRecRepairItemDetail item : list) {
					oldID.add(item.getRecRepairItemDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecRepairItem.size(); i++) {// 遍历修理项目明细表
					listRecRepairItem.get(i).setReceptionId(id);
					if (listRecRepairItem.get(i).getRecRepairItemDetailId() == null) {
						commonDao.insertRecRepairItemDetail(listRecRepairItem.get(i));// 新增
					} else {
						int recRepairItemDetailID = listRecRepairItem.get(i)
								.getRecRepairItemDetailId();
						newID.add(recRepairItemDetailID);
						commonDao.updateRecRepairItemDetail(listRecRepairItem.get(0));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					commonDao.deleteRecRepairItemDetailById(item);
				}
			} else {
				commonDao.deleteRecProductDetail(id);
			}
			if (listCollageDetai.size() != 0) {// /配件明细表有数据
				for (int i = 0; i < listCollageDetai.size(); i++) {
					int collageDetaiID=listCollageDetai.get(i).getCollageDetaiId();
					int maintainabilityID=listCollageDetai.get(i).getMaintainabilityId();
					SysCollageDetai list=commonDao.selectCollageDetaiById(collageDetaiID);
					list.setMaintainabilityId(maintainabilityID);
					commonDao.updateCollageDetai(list);
				}
			}
			if (listRecOtherCost.size() != 0) {// 费用明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				List<SysRecOtherCostDetail> list = commonDao.selectRecOtherCostDetail(id);
				for (SysRecOtherCostDetail item : list) {
					oldID.add(item.getRecOtherCostDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecOtherCost.size(); i++) {// 遍历修理项目明细表
					listRecOtherCost.get(i).setReceptionId(id);
					if (listRecOtherCost.get(i).getRecOtherCostDetailId() == null) {
						commonDao.insertRecOtherCostDetail(listRecOtherCost.get(i));// 新增
					} else {
						newID.add(listRecOtherCost.get(i)
								.getRecOtherCostDetailId());
						commonDao.updateRecOtherCostDetail(listRecOtherCost.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					commonDao.deleteRecOtherCostDetailById(item);
				}
			} else {
				commonDao.deleteRecOtherCostDetail(id);
			}
			if (listArrInsuranceMoney.size() != 0) {
				// 保险理赔明细
				listArrInsuranceMoney.get(0).setReceptionId(id);
				if (listArrInsuranceMoney.get(0).getInsuranceDetailId() == null) {
					commonDao.insertInsuranceDetail(listArrInsuranceMoney.get(0));// 新增
				} else {
					commonDao.updateInsuranceDetail(listArrInsuranceMoney.get(0));// 修改
				}
			}
			if (listThreePacksDetail.size() != 0) {
				// 三包索赔明细
				listThreePacksDetail.get(0).setReceptionId(id);
				if (listThreePacksDetail.get(0).getThreePacksDetailId() == null) {
					commonDao.insertThreePacksDetail(listThreePacksDetail.get(0));// 新增
				} else {
					commonDao.updateThreePacksDetail(listThreePacksDetail.get(0));// 修改
				}
			}
		}
		return id;
	}

}
