package com.koi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.ICollageDao;
import com.koi.po.PwCollage;
import com.koi.po.PwReception;
import com.koi.po.PwRestock;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysInventory;
import com.koi.util.SqlHelper;
import com.koi.vo.CollageDetaiVo;
import com.koi.vo.CollageVo;
import com.koi.vo.FittingsInfoVo;
import com.koi.vo.ProductVo;
import com.koi.vo.ReceptionVo;
import com.koi.vo.RestockDetailVo;
import com.koi.vo.RestockVo;
import com.koi.vo.ReturnJson;

@Repository("collageDao")
public class CollageDaoImpl implements ICollageDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;
	String selectReception = "SELECT * FROM(SELECT *,ROW_NUMBER()OVER(ORDER BY ReceptionID)Num FROM(SELECT p.*,RepairName,CustomerSou,DocumentState,BalanceState"
			+ " FROM  PW_Reception p,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE 1=1 ";
	String selectReceptionCount = "SELECT COUNT(*)as num  FROM  PW_Reception p,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	String selectCollageData = "SELECT * FROM(SELECT c.*,Owner,CarNum,MaintenanceNum,CollageState,ROW_NUMBER()OVER(ORDER BY p.ReceptionID)" +
			"Num FROM  PW_Collage c,PW_Reception p WHERE c.ReceptionID=p.ReceptionID)AS tmp  WHERE tmp.Num BETWEEN ? AND ?";
	String selectCollageCount = "SELECT COUNT(*)as num FROM  PW_Collage,PW_Reception WHERE"
			+ " PW_Collage.ReceptionID=PW_Reception.ReceptionID";
	String selectDaoRuPeiJian = "SELECT * FROM(SELECT r.*,MaintainabilityName,ROW_NUMBER()OVER(ORDER BY r.ReceptionID)Num FROM "
			+ "SYS_RecProductDetail r,SYS_Maintainability WHERE r.MaintainabilityID=SYS_Maintainability.MaintainabilityID  AND r.ReceptionID=?) "
			+ "AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	String selectDaoRuPeiJianCount = "SELECT COUNT(*)as num FROM  SYS_RecProductDetail,SYS_Maintainability WHERE SYS_RecProductDetail.MaintainabilityID"
			+ "=SYS_Maintainability.MaintainabilityID AND SYS_RecProductDetail.ReceptionID=?";
	String selectlistFittingsInfo = "SELECT f.*,FittingsTypeName,VehicleType,SystemUnit FROM SYS_FittingsInfo f,SYS_FittingsType,SYS_VehicleType,SYS_SystemUnit"
			+ " WHERE f.FittingsTypeID=SYS_FittingsType.FittingsTypeID AND f.VehicleTypeID=SYS_VehicleType.VehicleTypeID AND f.SystemUnitID="
			+ "SYS_SystemUnit.SystemUnitID AND f.FittingsCode=?";
	String selectInventory = "SELECT * FROM SYS_Inventory i WHERE i.FittingsCode=? AND i.WarehouseID=?";
	String selectTableCollageDetai = "SELECT * FROM(SELECT c.*,WarehouseName,MaintainabilityName,MaintenanceNum,ROW_NUMBER()OVER(ORDER BY c.CollageDetaiID)Num FROM "
			+ "SYS_CollageDetai c,SYS_Maintainability,SYS_Warehouse,PW_Collage,dbo.PW_Reception WHERE c.MaintainabilityID="
			+ "SYS_Maintainability.MaintainabilityID AND c.WarehouseID=SYS_Warehouse.WarehouseID AND c.CollageID="
			+ "PW_Collage.CollageID AND dbo.PW_Collage.ReceptionID=dbo.PW_Reception.ReceptionID AND  c.CollageID=?)"
			+ "AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	String selectTableCollageDetaiCount = "SELECT COUNT(*)as num FROM SYS_CollageDetai c,SYS_Maintainability,"
			+ "SYS_Warehouse,PW_Collage,dbo.PW_Reception WHERE c.MaintainabilityID=SYS_Maintainability.MaintainabilityID "
			+ "AND c.WarehouseID=SYS_Warehouse.WarehouseID AND c.CollageID=PW_Collage.CollageID AND dbo.PW_Collage."
			+ "ReceptionID=dbo.PW_Reception.ReceptionID AND  c.CollageID=?";
	String selectRestock = "SELECT p.*,c.CollageID,c.ToAudit FROM PW_Reception p,PW_Collage c WHERE p.ReceptionID=c.ReceptionID "
			+ "AND p.ReceptionID=?";

	@Override
	public List<ReceptionVo> selectReception(String startDate, String endDate,
			String collageState, String carNum, Long startIndex, Long pageSize) {
		List<ReceptionVo> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(selectReception);
		if (!"".equals(startDate) && !"".equals(endDate)) {
			buffer.append(" AND tmp.BalanceDate >= '" + startDate
					+ "' AND tmp.BalanceDate <= '" + endDate + "'");
		}
		if (!"".equals(startDate) && "".equals(endDate)) {
			buffer.append(" AND tmp.BalanceDate >= '" + startDate + "'");
		}
		if ("".equals(startDate) && !"".equals(endDate)) {
			buffer.append(" AND tmp.BalanceDate <= '" + endDate + "'");
		}
		if (!"".equals(carNum)) {
			buffer.append(" AND tmp.CarNum like '%" + carNum + "%'");
		}
		if (!"".equals(collageState)) {
			buffer.append(" AND tmp.CollageState like '%" + collageState + "%'");
		}
		buffer.append(")AS db WHERE db.Num BETWEEN " + startIndex + " AND "
				+ pageSize);
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString())
				.addEntity(ReceptionVo.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectReceptionCount(String startDate, String endDate,
			String collageState, String carNum) {
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
		if (!"".equals(carNum)) {
			buffer.append(" AND CarNum like '%" + carNum + "%'");
		}
		if (!"".equals(collageState)) {
			buffer.append(" AND CollageState like '%" + collageState + "%'");
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString());
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public PwReception arrlistToCollage(int receptionID) {
		List<PwReception> list = (List<PwReception>) sqlHelper.select(
				PwReception.class, "ReceptionID", receptionID);
		return list.get(0);
	}

	@Override
	public PwCollage selectCollageData(int receptionID) {
		List<PwCollage> list = (List<PwCollage>) sqlHelper.select(
				PwCollage.class, "ReceptionID", receptionID);
		return list.size()==0?null:list.get(0);
	}

	@Override
	public List<ProductVo> selectDaoRuPeiJian(int receptionID, Long startIndex,
			Long pageSize) {
		List<ProductVo> list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectDaoRuPeiJian)
				.addEntity(ProductVo.class);
		sqlQuery.setInteger(0, receptionID);
		sqlQuery.setLong(1, startIndex);
		sqlQuery.setLong(2, pageSize);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectDaoRuPeiJianCount(int receptionID) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectDaoRuPeiJianCount);
		sqlQuery.setInteger(0, receptionID);
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public int updateListCollage(List<PwCollage> listCollage,
			List<SysCollageDetai> listCollageDetai) {
		int id = 0;
		if (listCollage.get(0).getCollageId() == null) {
			id = sqlHelper.insert(listCollage.get(0));
		} else {
			sqlHelper.update(listCollage.get(0));
			id = listCollage.get(0).getCollageId();
		}
		if (id > 0) {
			if (listCollageDetai != null) {
				List<Integer> oldID = new ArrayList<Integer>();// 原来ID
				List<Integer> newID = new ArrayList<Integer>();// 新ID
				List<SysCollageDetai> lists = (List<SysCollageDetai>) sqlHelper
						.select(SysCollageDetai.class, "CollageID", id);
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
					SysInventory listInventory = this.selectInventory(
							fittingsCode, warehouseID);
					// 查询配件信息
					List<SysFittingsInfo> listFittingsInfos = (List<SysFittingsInfo>) sqlHelper
							.select(SysFittingsInfo.class, "FittingsCode",
									fittingsCode);
					// 查询配件信息
					FittingsInfoVo listFittingsInfo = this
							.selectlistFittingsInfo(fittingsCode);

					if (listCollageDetai.get(i).getCollageDetaiId() == null) {
						sqlHelper.insert(listCollageDetai.get(i));
					} else {
						newID.add(listCollageDetai.get(i).getCollageDetaiId());
						sqlHelper.update(listCollageDetai.get(i));
					}
					if (listFittingsInfos != null) {
						List<SysCollageDetai> lis = (List<SysCollageDetai>) sqlHelper
								.select(SysCollageDetai.class, "CollageDetaiID",
										collageDetaiID);
						if (lis.size() != 0) {
							double Quantity = lis.get(0).getQuantity();
							listFittingsInfos.get(0).setInvenQuan(
									listFittingsInfos.get(0).getInvenQuan()
											+ (Quantity - invenQuan));// 修改配件表数量
						} else {
							double liInvenQuan = listFittingsInfos.get(0)
									.getInvenQuan() != null ? listFittingsInfos
									.get(0).getInvenQuan() : 0;
							listFittingsInfos.get(0).setInvenQuan(
									liInvenQuan - invenQuan);// 修改配件表数量
						}
						session=sessionFactory.getCurrentSession();
						session.update(listFittingsInfos.get(0));
					}
					if (listInventory != null)// 修改库存信息
					{
						if (listCollageDetai.get(i).getCollageDetaiId() != null) {
							List<SysCollageDetai> lis = (List<SysCollageDetai>) sqlHelper
									.select(SysCollageDetai.class,
											"CollageDetaiID", listCollageDetai
													.get(i).getCollageDetaiId());
							if (lis.get(0).getWarehouseId() != warehouseID) {
								int liWarehouseID = lis.get(0).getWarehouseId();
								String liFittingsCode = lis.get(0)
										.getFittingsCode();
								SysInventory oldInvenQuan = this
										.selectInventory(liFittingsCode,
												liWarehouseID);// 旧库存表
								oldInvenQuan.setInvenQuan(oldInvenQuan
										.getInvenQuan() + invenQuan);
								sqlHelper.update(oldInvenQuan);
								listInventory.setInvenQuan(listInventory
										.getInvenQuan() - invenQuan);
							} else {
								double Quantity = lis.get(0).getQuantity();
								listInventory.setInvenQuan(listInventory
										.getInvenQuan()
										+ (Quantity - invenQuan));
							}
						} else {
							listInventory.setInvenQuan(listInventory
									.getInvenQuan() - invenQuan);
						}
						sqlHelper.update(listInventory);
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
							sqlHelper.insert(sys_Inventory);// 新增
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
							sqlHelper.insert(sys_Inventory);// 新增
						}
					}
				}
				int receptionID = listCollage.get(0).getReceptionId();
				List<PwReception> listReception = (List<PwReception>) sqlHelper
						.select(PwReception.class, "ReceptionID", receptionID);
				listReception.get(0).setCollageState("已领料");
				sqlHelper.update(listReception.get(0));
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					List<SysCollageDetai> listQuantity = (List<SysCollageDetai>) sqlHelper
							.select(SysCollageDetai.class, "CollageDetaiID", item);
					String fittingsCode = listQuantity.get(0).getFittingsCode();
					int warehouseID = listQuantity.get(0).getWarehouseId();
					double quantity = listQuantity.get(0).getQuantity();
					SysInventory listInventory = this.selectInventory(
							fittingsCode, warehouseID);// 库存表
					List<SysFittingsInfo> listFittingsInfos = (List<SysFittingsInfo>) sqlHelper
							.select(SysFittingsInfo.class, "FittingsCode",
									fittingsCode);
					listInventory.setInvenQuan(listInventory.getInvenQuan()
							+ quantity);
					listFittingsInfos.get(0).setInvenQuan(
							listFittingsInfos.get(0).getInvenQuan() + quantity);
					sqlHelper.update(listInventory);// 修改库存数量
					sqlHelper.update(listFittingsInfos.get(0));// 修改配件数量
					sqlHelper.delete(SysCollageDetai.class, "CollageDetaiID",
							item);// 删除
				}
			} else {
				sqlHelper.delete(SysCollageDetai.class, "CollageID", id);// 删除全部
			}
		}
		return id;
	}

	@Override
	public List<CollageVo> selectCollage(Long startIndex, Long pageSize) {
		List<CollageVo> list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectCollageData)
				.addEntity(CollageVo.class);
		sqlQuery.setLong(0, startIndex);
		sqlQuery.setLong(1, pageSize);
		list = sqlQuery.list();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCollageState(
					list.get(i).getCollageState() != "已退料" ? list.get(i)
							.getCollageState() : "未领料");
		}
		return list;
	}

	@Override
	public Long selectCollageCount() {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectCollageCount);
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public List<SysCollageDetai> selectCollageDetai(int collageID) {
		List<SysCollageDetai> list = (List<SysCollageDetai>) sqlHelper.select(
				SysCollageDetai.class, "CollageID", collageID);
		return list;
	}

	@Override
	public RestockVo selectRestock(int receptionID) {
		RestockVo restockVo = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectRestock).addEntity(RestockVo.class);
		sqlQuery.setInteger(0, receptionID);
		restockVo = (RestockVo) sqlQuery.uniqueResult();
		return restockVo;
	}

	@Override
	public List<CollageDetaiVo> selectTableCollageDetai(int collageID,
			Long startIndex, Long pageSize) {
		List<CollageDetaiVo> list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectTableCollageDetai).addEntity(CollageDetaiVo.class);
		sqlQuery.setInteger(0, collageID);
	    sqlQuery.setLong(1, startIndex);
	    sqlQuery.setLong(2, pageSize);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long selectTableCollageDetaiCount(int collageID) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session
				.createSQLQuery(selectTableCollageDetaiCount);
		sqlQuery.setInteger(0, collageID);
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public ReturnJson judgingQuantity(List<RestockDetailVo> listRestockDetai) {
		ReturnJson returnJson = new ReturnJson();
		returnJson.setState(true);
		for (RestockDetailVo item : listRestockDetai) {
			List<SysCollageDetai> listCollage = (List<SysCollageDetai>) sqlHelper
					.select(SysCollageDetai.class, "CollageDetaiID",
							item.getCollageDetaiID());
			double quantity = item.getQuantity();
			if (listCollage != null) {
				if (quantity < 0) {
					returnJson.setText("配件编码为"
							+ listCollage.get(0).getFittingsCode()
							+ "的退料数量不能小于0!");
					returnJson.setState(false);
				} else if (quantity > listCollage.get(0).getQuantity()) {
					returnJson.setText("配件编码为"
							+ listCollage.get(0).getFittingsCode()
							+ "的退料数量不能超过领料的数量!");
					returnJson.setState(false);
				}
			}
		}
		return returnJson;
	}

	@Override
	public int updateListRestock(List<PwRestock> listRestock,
			List<RestockDetailVo> listRestockDetai) {
		int i = 0;
		for (int j = 0; j < listRestockDetai.size(); j++) {
			int collageDetaiID = listRestockDetai.get(i).getCollageDetaiID();
			String fittingsCode = listRestockDetai.get(i).getFittingsCode();
			int warehouseID = listRestockDetai.get(i).getWarehouseID();
			List<SysCollageDetai> listCollage = (List<SysCollageDetai>) sqlHelper
					.select(SysCollageDetai.class, "CollageDetaiID",
							collageDetaiID);
			List<SysFittingsInfo> listFittingsInfos = (List<SysFittingsInfo>) sqlHelper
					.select(SysFittingsInfo.class, "FittingsCode", fittingsCode);
			SysInventory listInventory = this.selectInventory(fittingsCode,
					warehouseID);
			double quantity = listRestockDetai.get(i).getQuantity();
			if (listCollage != null) {
				listCollage.get(0).setQuantity(
						listCollage.get(0).getQuantity() - quantity);
				if (listCollage.get(0).getQuantity() == 0.0)// 退料数量==领料数量则删除
				{
				/*	sqlHelper.delete(SysCollageDetai.class, "CollageDetaiID",
							collageDetaiID);*/
				} else// 退料数量！=领料数量则修改
				{
					sqlHelper.update(listCollage.get(0));
				}
			}
			listInventory.setInvenQuan(listInventory.getInvenQuan()
					+ quantity);
			sqlHelper.update(listInventory);
			if (listFittingsInfos != null) {
				sqlHelper.update(listFittingsInfos.get(0));
			}
		}
		int receptionID = listRestock.get(0).getReceptionId();
		List<PwReception> listReception = (List<PwReception>) sqlHelper.select(
				PwReception.class, "ReceptionID", receptionID);
		listReception.get(0).setCollageState("已退料");
		i = sqlHelper.update(listReception.get(0));
		return i;
	}

	@Override
	public int selectCollageToID(int receptionID) {
		int collageID = 0;
		List<PwCollage> list = (List<PwCollage>) sqlHelper.select(
				PwCollage.class, "ReceptionID", receptionID);
		if (list != null) {
			collageID = list.get(0).getCollageId();
		}
		return collageID;
	}

	// 查询库存信息
	private SysInventory selectInventory(String fittingsCode, int warehouseID) {
		SysInventory list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectInventory).addEntity(
				SysInventory.class);
		sqlQuery.setString(0, fittingsCode);
		sqlQuery.setInteger(1, warehouseID);
		list = (SysInventory) sqlQuery.uniqueResult();
		return list;
	}

	// 查询配件信息
	private FittingsInfoVo selectlistFittingsInfo(String fittingsCode) {
		FittingsInfoVo list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectlistFittingsInfo)
				.addEntity(FittingsInfoVo.class);
		sqlQuery.setString(0, fittingsCode);
		list = (FittingsInfoVo) sqlQuery.uniqueResult();
		return list;
	}
}
