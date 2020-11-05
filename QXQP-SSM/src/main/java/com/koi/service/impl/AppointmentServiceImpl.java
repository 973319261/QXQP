package com.koi.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.AppointmentDao;
import com.koi.po.PwPredate;
import com.koi.po.SysPreOtherCostDetail;
import com.koi.po.SysPreProductDetail;
import com.koi.po.SysPreRepairItemDetail;
import com.koi.service.IAppointmentService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
@Transactional
@Service("appointmentService")
public class AppointmentServiceImpl implements IAppointmentService {
	@Autowired
	private AppointmentDao appointmentDao;

	@Override
	public String predateNum() {
		Date date = new Date();
		SimpleDateFormat sfdate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sfdatetime = new SimpleDateFormat("yyyyMMddHHmmss");
		String d = sfdate.format(date);
		String dt = sfdatetime.format(date);
		int num = appointmentDao.predateNum(d) + 1;
		String str = "BJ" + dt + String.format("%04d", num);
		return str;
	}

	@Override
	public Object selectPredate(String predateNum, String toAudit,
			 Long pageSize,Long curPage) {
		// TODO Auto-generated method stub
		Long totalRows = appointmentDao.selectPredateCount(predateNum, toAudit);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<PwPredate> list = appointmentDao.selectPredate(predateNum,
				toAudit, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj =Bsgrid.getList(json, curPage, totalRows);// 设置返回的参数
		return obj;
	}


	@Override
	public List<Object> selectPredateDetail(int predateID) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		List<SysPreRepairItemDetail> listPreRepairItemDetail = appointmentDao.selectPreRepairItemDetail(predateID);
		List<SysPreProductDetail> listPreProductDetail = appointmentDao.selectPreProductDetail(predateID);
		List<SysPreOtherCostDetail> listPreOtherCostDetail = appointmentDao.selectPreOtherCostDetail(predateID);
		list.add(listPreRepairItemDetail);
		list.add(listPreProductDetail);
		list.add(listPreOtherCostDetail);
		return list;
	}

	@Override
	public int updateListPredate(String predate,
			String preRepairItem,
			String preProduct,
			String preOtherCost) {
		
		List<SysPreRepairItemDetail> listPreRepairItem = Util.jsonToVo(preRepairItem, SysPreRepairItemDetail.class);
		List<SysPreProductDetail> listPreProduct = Util.jsonToVo(preProduct, SysPreProductDetail.class);
		List<SysPreOtherCostDetail> listPreOtherCost = Util.jsonToVo(preOtherCost, SysPreOtherCostDetail.class);
		PwPredate predateList = Util.jsonToVo(predate, PwPredate.class).get(0);
		int id = 0;
		if (predateList.getPredateId() == null) {
			//Android端 --开始
			if(predateList.getPredateNum()==null) {//维修单号
				predateList.setPredateNum(this.predateNum());
			}
			if(predateList.getOpenDate()==null) {//开单日期
				Timestamp d = new Timestamp(System.currentTimeMillis()); 
				predateList.setOpenDate(d);
			}
			if(predateList.getOpenDate()==null) {//开单日期
				Timestamp d = new Timestamp(System.currentTimeMillis()); 
				predateList.setOpenDate(d);
			}
			if(predateList.getToAudit()==null) {
				predateList.setToAudit(false);
			}
			//Android端 --结束
			appointmentDao.insertPredate(predateList);// 新增
		} else {
			appointmentDao.updatePredate(predateList);// 新增// 修改
		}
		id = Integer.valueOf(predateList.getPredateId().toString());
		if (id > 0) {// 主表保存成功
			List<Integer> oldID = new ArrayList<Integer>();// 原来ID
			List<Integer> newID = new ArrayList<Integer>();// 传过来的ID
			if (listPreRepairItem.size() != 0) {// 修理项目明细表有数据
				// 维修明细表
				List<SysPreRepairItemDetail> list = appointmentDao.selectPreRepairItemDetail(id);// 修理项目明细表
				for (SysPreRepairItemDetail item : list) {
					oldID.add(item.getPreRepairItemDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreRepairItem.size(); i++) {// 遍历修理项目明细表
					listPreRepairItem.get(i).setPredateId(id);
					if (listPreRepairItem.get(i).getPreRepairItemDetailId() == null) {
						appointmentDao.insertPreRepairItem(listPreRepairItem.get(i));// 新增
					} else {
						newID.add(listPreRepairItem.get(i)
								.getPreRepairItemDetailId());
						appointmentDao.updatePreRepairItem(listPreRepairItem.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					appointmentDao.deletePreRepairItem(item);
				}
			} else {
				appointmentDao.deletePreRepairItem(id);
			}

			if (listPreProduct.size() != 0) {// /配件明细表有数据
				oldID.clear();
				newID.clear();
				List<SysPreProductDetail> list = (List<SysPreProductDetail>) appointmentDao.selectPreProductDetail(id);// 配件明细表
				for (SysPreProductDetail item : list) {
					oldID.add(item.getPreProductDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreProduct.size(); i++) {// 遍历修理项目明细表
					listPreProduct.get(i).setPredateId(id);
					if (listPreProduct.get(i).getPreProductDetailId() == null) {
						appointmentDao.insertPreProduct(listPreProduct.get(i));// 新增
					} else {
						newID.add(listPreProduct.get(i).getPreProductDetailId());
						appointmentDao.updatePreProduct(listPreProduct.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					appointmentDao.deletePreProduct(item);
				}
			} else {
				appointmentDao.deletePreProduct(id);
			}

			if (listPreOtherCost.size() != 0) {// 费用明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				List<SysPreOtherCostDetail> list = appointmentDao.selectPreOtherCostDetail(id);// 费用明细表
				for (SysPreOtherCostDetail item : list) {
					oldID.add(item.getPreOtherCostDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreOtherCost.size(); i++) {// 遍历修理项目明细表
					listPreOtherCost.get(i).setPredateId(id);
					if (listPreOtherCost.get(i).getPreOtherCostDetailId() == null) {
						appointmentDao.insertPreOtherCost(listPreOtherCost.get(i));// 新增
					} else {
						newID.add(listPreOtherCost.get(i)
								.getPreOtherCostDetailId());
						appointmentDao.updatePreOtherCost(listPreOtherCost.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					appointmentDao.deletePreOtherCost(item);
				}
			} else {
				appointmentDao.deletePreOtherCost(id);
			}

		}
		return id;
	}

	@Override
	public boolean deleteListPredate(int predateID) {
		boolean flag = false;
		flag = appointmentDao.deletePredate(predateID);
		if (flag) {
			appointmentDao.deletePreRepairItem(predateID);// 删除维修明细表
			appointmentDao.deletePreProduct(predateID);// 删除产品明细表
			appointmentDao.selectPreOtherCostDetail(predateID);// 删除其他费用表
		}
		return flag;
	}

	@Override
	public boolean toAudit(int predateID) {
		// TODO Auto-generated method stub
		return appointmentDao.toAudit(true,predateID);
	}

	@Override
	public boolean toNotAudit(int predateID) {
		// TODO Auto-generated method stub
		return appointmentDao.toAudit(false,predateID);
	}

	@Override
	public boolean selectToMainten(int predateID, String maintenanceNum) {
		// TODO Auto-generated method stub
		return appointmentDao.selectToMainten(predateID, maintenanceNum);
	}
}
