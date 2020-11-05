package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.CommonDao;
import com.koi.mapper.CustomerDao;
import com.koi.po.PwPredate;
import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysPreOtherCostDetail;
import com.koi.po.SysPreProductDetail;
import com.koi.po.SysPreRepairItemDetail;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecProductDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.service.ICustomerService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.ReceptionVo;

@Transactional
@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CommonDao commonDao;
	@Override
	public String maintenanceNum() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String d = sf.format(date);
		int num = customerDao.maintenanceNum("W" + d) + 1;
		String str = "W" + d + String.format("%04d", num);
		return str;
	}

	@Override
	public int updateListReceptione(String pwReception,
			String sysRecRepairItemDetail, String sysRecProductDetail,
			String sysRecOtherCostDetail, String sysInsuranceDetail,
			String sysThreePacksDetail) {
		List<PwReception> listReception = Util.jsonToVo(pwReception,
				PwReception.class);
		List<SysRecRepairItemDetail> listRecRepairItem = Util.jsonToVo(
				sysRecRepairItemDetail, SysRecRepairItemDetail.class);
		List<SysRecProductDetail> listRecProduct = Util.jsonToVo(
				sysRecProductDetail, SysRecProductDetail.class);
		List<SysRecOtherCostDetail> listRecOtherCost = Util.jsonToVo(
				sysRecOtherCostDetail, SysRecOtherCostDetail.class);
		List<SysInsuranceDetail> listArrInsuranceMoney = Util.jsonToVo(
				sysInsuranceDetail, SysInsuranceDetail.class);
		List<SysThreePacksDetail> listThreePacksDetail = Util.jsonToVo(
				sysThreePacksDetail, SysThreePacksDetail.class);
		int id = 0;
		if (listReception.get(0).getReceptionId() == null) {
			listReception.get(0).setDocumentStateId(1); // 未结算
			listReception.get(0).setBalanceStateId(3);// 未付款
			listReception.get(0).setCollageState("未领料");// 领料状态
			listReception.get(0).setToCompletion(false);// 领料状态
			commonDao.insertReception(listReception.get(0));
			id = listReception.get(0).getReceptionId();
		} else {
			id = listReception.get(0).getReceptionId();
			PwReception list = commonDao.selectReceptionById(id);
			listReception.get(0).setBalanceStateId(list.getBalanceStateId());
			listReception.get(0).setCollageState(list.getCollageState());
			listReception.get(0).setCompletionDate(list.getCompletionDate());
			listReception.get(0).setDocumentStateId(list.getDocumentStateId());
			listReception.get(0).setMaintenAmount(list.getMaintenAmount());
			listReception.get(0).setOilQuantity(list.getOilQuantity());
			listReception.get(0).setToAudit(list.getToAudit());
			listReception.get(0).setToCompletion(list.getToCompletion());
			listReception.get(0).setToSendWork(list.getToSendWork());
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
						SysRecRepairItemDetail recRepairItemDetail = commonDao.selectRecRepairItemDetailById(recRepairItemDetailID);
						listRecRepairItem.get(i).setCompletionDate(
								recRepairItemDetail.getCompletionDate());
						listRecRepairItem.get(i).setRepairManId(
								recRepairItemDetail.getRepairManId());
						listRecRepairItem.get(i).setMaintenanceCrewId(
								recRepairItemDetail.getMaintenanceCrewId());
						listRecRepairItem.get(i).setJobHours(
								recRepairItemDetail.getJobHours());
						listRecRepairItem.get(i).setJobAmount(
								recRepairItemDetail.getJobAmount());
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
			if (listRecProduct.size() != 0) {// /配件明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				List<SysRecProductDetail> list = commonDao.selectRecProductDetail(id);// 配件明细表
				for (SysRecProductDetail item : list) {
					oldID.add(item.getRecProductDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listRecProduct.size(); i++) {// 遍历修理项目明细表
					listRecProduct.get(i).setReceptionId(id);
					if(listRecProduct.get(i).getFittingsCode()!=null){
						if (listRecProduct.get(i).getRecProductDetailId() == null) {
							commonDao.insertRecProductDetail(listRecProduct.get(i));// 新增
						} else {
							newID.add(listRecProduct.get(i).getRecProductDetailId());
							commonDao.updateRecProductDetail(listRecProduct.get(i));// 修改
						}
					}
					
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					commonDao.deleteRecProductDetailById(item);
				}
			} else {
				commonDao.deleteRecProductDetail(id);
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

	@Override
	public Object selectReception(String toAudit, String maintenanceNum,
			String carNum, int documentStateID, int balanceStateID,
			Long pageSize, Long curPage) {
		// TODO Auto-generated method stub
		Long startIndex = 1L;
		Long totalRows = customerDao.selectReceptionCount(toAudit,
				maintenanceNum, carNum, documentStateID, balanceStateID);
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ReceptionVo> list = customerDao.selectReception(toAudit,
				maintenanceNum, carNum, documentStateID, balanceStateID,
				startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getList(json, curPage, totalRows);// 设置返回的参数
		return obj;
	}

	@Override
	public List<Object> selectReceptionDetail(int receptionID) {
		List<Object> list = new ArrayList<Object>();
		List<SysRecRepairItemDetail> listRecRepairItemDetail =commonDao.selectRecRepairItemDetail(receptionID);
		List<SysRecProductDetail> listRecProductDetails = commonDao.selectRecProductDetail(receptionID);
		List<SysRecOtherCostDetail> listRecOtherCostDetail = commonDao.selectRecOtherCostDetail(receptionID);
		List<SysInsuranceDetail> listInsuranceDetail =commonDao.selectInsuranceDetail(receptionID);
		List<SysThreePacksDetail> listThreePacksDetail = commonDao.selectThreePacksDetail(receptionID);
		list.add(listRecRepairItemDetail);
		list.add(listRecProductDetails);
		list.add(listRecOtherCostDetail);
		list.add(listInsuranceDetail);
		list.add(listThreePacksDetail);
		return list;
	}

	@Override
	public boolean delectReception(int receptionID) {
		boolean flag=false;
		flag=commonDao.deleteReception(receptionID);
		if(flag){
			commonDao.deleteRecRepairItemDetail(receptionID);
			commonDao.deleteRecProductDetail(receptionID);
			commonDao.deleteRecOtherCostDetail(receptionID);
			commonDao.deleteInsuranceDetail(receptionID);
			commonDao.deleteThreePacksDetail(receptionID);
		}
		return flag;
	}

	@Override
	public List<Object> customer(String pwPredate,
			String sysPreRepairItemDetail, String sysPreProductDetail,
			String sysPreOtherCostDetail) {
		List<Object> list = new ArrayList<Object>();
		List<PwPredate> listPredate = Util.jsonToVo(pwPredate, PwPredate.class);
		List<SysPreRepairItemDetail> listPreRepairItem = Util.jsonToVo(
				sysPreRepairItemDetail, SysPreRepairItemDetail.class);
		List<SysPreProductDetail> listPreProduct = Util.jsonToVo(sysPreProductDetail,
				SysPreProductDetail.class);
		List<SysPreOtherCostDetail> listPreOtherCost = Util.jsonToVo(
				sysPreOtherCostDetail, SysPreOtherCostDetail.class);
		list.add(listPreRepairItem);
		list.add(listPreProduct);
		list.add(listPreOtherCost);
		list.add(listPredate.get(0));
		return list;
	}
}
