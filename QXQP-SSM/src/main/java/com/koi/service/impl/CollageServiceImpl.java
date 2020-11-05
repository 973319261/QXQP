package com.koi.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.CollageDao;
import com.koi.mapper.CommonDao;
import com.koi.po.PwCollage;
import com.koi.po.PwReception;
import com.koi.po.PwRestock;
import com.koi.po.PwUser;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysInventory;
import com.koi.service.ICollageService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.CollageDetaiVo;
import com.koi.vo.CollageVo;
import com.koi.vo.FittingsInfoVo;
import com.koi.vo.ProductVo;
import com.koi.vo.ReceptionVo;
import com.koi.vo.RestockDetailVo;
import com.koi.vo.RestockVo;
import com.koi.vo.ReturnJson;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Transactional
@Service("collageService")
public class CollageServiceImpl implements ICollageService {
	@Autowired
	private CollageDao collageDao;
	@Autowired
	private CommonDao commonDao;

	@Override
	public Object selectReception(String startDate, String endDate,
			String collageState, String carNum, Long curPage, Long pageSize) {
		Long totalRows = collageDao.selectReceptionCount(startDate, endDate,
				collageState, carNum);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ReceptionVo> list = collageDao.selectReception(startDate, endDate,
				collageState, carNum, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}

	@Override
	public Object arrlistToCollage(int receptionID) {
		PwReception list = commonDao.selectReceptionById(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		return jsonArr;
	}

	@Override
	public Object selectCollageData(int receptionID) {
		PwCollage list = collageDao.selectCollageById(receptionID);
		if (list != null) {
			JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
			Object obj = JSONArray.fromObject(list, jsonConfig);
			return obj;
		}
		return list;
	}

	@Override
	public Object selectDaoRuPeiJian(int receptionID, Long curPage,
			Long pageSize) {
		Long totalRows = collageDao.selectDaoRuPeiJianCount(receptionID);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ProductVo> list = collageDao.selectDaoRuPeiJian(receptionID,
				startIndex, pageSize);
		Object obj = Bsgrid.getList(list, curPage, totalRows);
		return obj;
	}

	@Override
	public int updateListCollage(String pwCollage, String sysCollageDetai,
			PwUser user) {
		List<PwCollage> listCollage = Util.jsonToVo(pwCollage, PwCollage.class);
		List<SysCollageDetai> listCollageDetai = Util.jsonToVo(sysCollageDetai,
				SysCollageDetai.class);
		for (int i = 0; i < listCollage.size(); i++) {
			listCollage.get(i).setAuditor(user.getUserName());
			listCollage.get(i).setOperator(user.getUserName());
			listCollage.get(i)
					.setAuditDate(new Timestamp(new Date().getTime()));
			listCollage.get(i).setToAudit(true);
		}
		int id = 0;
		if (listCollage.get(0).getCollageId() == null) {
			collageDao.insertCollage(listCollage.get(0));
		} else {
			collageDao.updateCollage(listCollage.get(0));
			
		}
		id = listCollage.get(0).getCollageId();
		if (id > 0) {
			if (listCollageDetai != null) {
				List<Integer> oldID = new ArrayList<Integer>();// 原来ID
				List<Integer> newID = new ArrayList<Integer>();// 新ID
				List<SysCollageDetai> lists =collageDao.selectCollageDetai(id);
				for (SysCollageDetai item : lists) {
					oldID.add(item.getCollageDetaiId());
				}
				for (int i = 0; i < listCollageDetai.size(); i++) {
					listCollageDetai.get(i).setCollageId(id);
					int collageDetaiID = listCollageDetai.get(i)
							.getCollageDetaiId() == null ? 0 : listCollageDetai
							.get(i).getCollageDetaiId();
					String fittingsCode = listCollageDetai.get(i)
							.getFittingsCode();// 配件编码
					String vehicleType = listCollageDetai.get(i)
							.getVehicleType();// 车型
					double invenQuan = listCollageDetai.get(i).getQuantity();// 数量
					int warehouseID = listCollageDetai.get(i).getWarehouseId();// 仓库ID
					// 查询库存表
					SysInventory listInventory = collageDao.selectInventory(
							fittingsCode, warehouseID);
					// 查询配件信息
					SysFittingsInfo listFittingsInfos =commonDao
							.selectFittingsInfo(fittingsCode);
					// 查询配件信息
					FittingsInfoVo listFittingsInfo = collageDao
							.selectFittingsInfoVo(fittingsCode);

					if (listCollageDetai.get(i).getCollageDetaiId() == null) {
						commonDao.insertCollageDetai(listCollageDetai.get(i));
					} else {
						newID.add(listCollageDetai.get(i).getCollageDetaiId());
						commonDao.updateCollageDetai(listCollageDetai.get(i));
					}
					if (listFittingsInfos != null) {
						SysCollageDetai lis = commonDao
								.selectCollageDetaiById(collageDetaiID);
						if (lis != null) {
							double Quantity = lis.getQuantity();
							listFittingsInfos.setInvenQuan(
									listFittingsInfos.getInvenQuan()
											+ (Quantity - invenQuan));// 修改配件表数量
						} else {
							double liInvenQuan = listFittingsInfos
									.getInvenQuan() != null ? listFittingsInfos
									.getInvenQuan() : 0;
							listFittingsInfos.setInvenQuan(
									liInvenQuan - invenQuan);// 修改配件表数量
						}
						commonDao.updateFittingsInfo(listFittingsInfos);
					}
					if (listInventory != null)// 修改库存信息
					{
						if (listCollageDetai.get(i).getCollageDetaiId() != null) {
							SysCollageDetai lis = commonDao.selectCollageDetaiById(listCollageDetai
													.get(i).getCollageDetaiId());
							if (lis.getWarehouseId() != warehouseID) {
								int liWarehouseID = lis.getWarehouseId();
								String liFittingsCode = lis
										.getFittingsCode();
								SysInventory oldInvenQuan = collageDao
										.selectInventory(liFittingsCode,
												liWarehouseID);// 旧库存表
								oldInvenQuan.setInvenQuan(oldInvenQuan
										.getInvenQuan() + invenQuan);
								collageDao.updateInventory(oldInvenQuan);
								listInventory.setInvenQuan(listInventory
										.getInvenQuan() - invenQuan);
							} else {
								double Quantity = lis.getQuantity();
								listInventory.setInvenQuan(listInventory
										.getInvenQuan()
										+ (Quantity - invenQuan));
							}
						} else {
							listInventory.setInvenQuan(listInventory
									.getInvenQuan() - invenQuan);
						}
						collageDao.updateInventory(listInventory);
					} else// 新增库存信息
					{
						SysInventory sys_Inventory = new SysInventory();
						sys_Inventory.setPosition(listCollageDetai.get(i)
								.getPosition());
						sys_Inventory.setWarehouseId(listCollageDetai.get(i)
								.getWarehouseId());
						if (listFittingsInfo != null) {
							sys_Inventory.setFittingsType(listFittingsInfo
									.getFittingsTypeName());
							sys_Inventory.setFittingsCode(listFittingsInfo
									.getFittingsCode());
							sys_Inventory.setFittingsName(listFittingsInfo
									.getFittingsName());
							sys_Inventory.setVehicleType(listFittingsInfo
									.getVehicleType());
							sys_Inventory.setSystemUnit(listFittingsInfo
									.getSystemUnit());
							sys_Inventory.setInvenQuan(-invenQuan);
							sys_Inventory.setNewIntake(listFittingsInfo
									.getIntake());
							sys_Inventory.setSalePrice(listFittingsInfo
									.getSalesPrice());
							sys_Inventory.setWholePrice(listFittingsInfo
									.getWholesalePrice());
							collageDao.insertInventory(sys_Inventory);// 新增
						} else {
							sys_Inventory.setNewIntake(0.0);
							sys_Inventory.setWholePrice(0.0);
							sys_Inventory.setFittingsCode(listCollageDetai.get(
									i).getFittingsCode());
							sys_Inventory.setFittingsName(listCollageDetai.get(
									i).getFittingsName());
							sys_Inventory.setVehicleType(listCollageDetai
									.get(i).getVehicleType());
							sys_Inventory.setSystemUnit(listCollageDetai.get(i)
									.getSystemUnit());
							sys_Inventory.setInvenQuan(-listCollageDetai.get(i)
									.getQuantity());
							sys_Inventory.setSalePrice(listCollageDetai.get(i)
									.getUnitPrice());
							sys_Inventory.setPosition(listCollageDetai.get(i)
									.getPosition());
							collageDao.insertInventory(sys_Inventory);// 新增
						}
					}
				}
				int receptionID = listCollage.get(0).getReceptionId();
				PwReception listReception =commonDao.selectReceptionById(receptionID);
				listReception.setCollageState("已领料");
				commonDao.updateReception(listReception);
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					SysCollageDetai listQuantity = commonDao.selectCollageDetaiById(item);
					String fittingsCode = listQuantity.getFittingsCode();
					int warehouseID = listQuantity.getWarehouseId();
					double quantity = listQuantity.getQuantity();
					SysInventory listInventory = collageDao.selectInventory(
							fittingsCode, warehouseID);// 库存表
					SysFittingsInfo listFittingsInfos = commonDao.selectFittingsInfo(fittingsCode);
					listInventory.setInvenQuan(listInventory.getInvenQuan()
							+ quantity);
					listFittingsInfos.setInvenQuan(
							listFittingsInfos.getInvenQuan() + quantity);
					collageDao.updateInventory(listInventory);// 修改库存数量
					commonDao.updateFittingsInfo(listFittingsInfos);// 修改配件数量
					commonDao.deleteCollageDetai(item);// 删除
				}
			} else {
				commonDao.deleteCollageDetai(id);// 删除全部
			}
		}
		return id;
	}

	@Override
	public Object selectCollage(Long curPage, Long pageSize) {
		Long totalRows = collageDao.selectCollageCount();
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<CollageVo> list = collageDao.selectCollage(startIndex, pageSize);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCollageState(
					list.get(i).getCollageState() != "已退料" ? list.get(i)
							.getCollageState() : "未领料");
		}
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}

	@Override
	public List<SysCollageDetai> selectCollageDetai(int collageID) {
		// TODO Auto-generated method stub
		return collageDao.selectCollageDetai(collageID);
	}

	@Override
	public Object selectRestock(int receptionID) {
		RestockVo restockVo = collageDao.selectRestock(receptionID);
		restockVo.setStrAudit(restockVo.getToAudit() != true ? "未审核" : "已审核");
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(restockVo, jsonConfig);
		return jsonArr;
	}

	@Override
	public Object selectTableCollageDetai(int collageID, Long curPage,
			Long pageSize) {
		Long totalRows = collageDao.selectTableCollageDetaiCount(collageID);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<CollageDetaiVo> list = collageDao.selectTableCollageDetai(
				collageID, startIndex, pageSize);
		Object obj = Bsgrid.getList(list, curPage, totalRows);
		return obj;
	}

	@Override
	public ReturnJson judgingQuantity(String restockDetail) {
		List<RestockDetailVo> listRestockDetai = Util.jsonToVo(restockDetail,
				RestockDetailVo.class);
		ReturnJson returnJson = new ReturnJson();
		returnJson.setState(true);
		for (RestockDetailVo item : listRestockDetai) {
			SysCollageDetai listCollage = commonDao.selectCollageDetaiById(item.getCollageDetaiID());
			double quantity = item.getQuantity();
			if (listCollage != null) {
				if (quantity < 0) {
					returnJson.setText("配件编码为"
							+ listCollage.getFittingsCode()
							+ "的退料数量不能小于0!");
					returnJson.setState(false);
				} else if (quantity > listCollage.getQuantity()) {
					returnJson.setText("配件编码为"
							+ listCollage.getFittingsCode()
							+ "的退料数量不能超过领料的数量!");
					returnJson.setState(false);
				}
			}
		}
		return returnJson;
	}

	@Override
	public int updateListRestock(String pwRestock, String restockDetail) {
		List<PwRestock> listRestock = Util.jsonToVo(pwRestock, PwRestock.class);
		List<RestockDetailVo> listRestockDetai = Util.jsonToVo(restockDetail,
				RestockDetailVo.class);
		int i = 0;
		for (int j = 0; j < listRestockDetai.size(); j++) {
			int collageDetaiID = listRestockDetai.get(i).getCollageDetaiID();
			String fittingsCode = listRestockDetai.get(i).getFittingsCode();
			int warehouseID = listRestockDetai.get(i).getWarehouseID();
			SysCollageDetai listCollage = commonDao.selectCollageDetaiById(collageDetaiID);
			SysFittingsInfo listFittingsInfos = commonDao.selectFittingsInfo( fittingsCode);
			SysInventory listInventory = collageDao.selectInventory(fittingsCode,
					warehouseID);
			double quantity = listRestockDetai.get(i).getQuantity();
			if (listCollage != null) {
				listCollage.setQuantity(
						listCollage.getQuantity() - quantity);
				if (listCollage.getQuantity() == 0.0)// 退料数量==领料数量则删除
				{
				  commonDao.deleteCollageDetaiById(collageDetaiID);
				} else// 退料数量！=领料数量则修改
				{
					commonDao.updateCollageDetai(listCollage);
				}
			}
			listInventory.setInvenQuan(listInventory.getInvenQuan()
					+ quantity);
			collageDao.updateInventory(listInventory);
			if (listFittingsInfos != null) {
				commonDao.updateFittingsInfo(listFittingsInfos);
			}
		}
		int receptionID = listRestock.get(0).getReceptionId();
		PwReception listReception = commonDao.selectReceptionById(receptionID);
		listReception.setCollageState("已退料");
		i = commonDao.updateReception(listReception);
		return i;
	}

	@Override
	public int selectCollageToID(int receptionID) {
		int collageID=0;
		PwCollage list= collageDao.selectCollageById(receptionID);
		if(list!=null){
			collageID = list.getCollageId();
		}
		return collageID;
	}
}
