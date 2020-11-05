package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gx.dao.ICollageDao;
import com.gx.pojo.PW_Collage;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.PW_Restock;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.pojo.SYS_FittingsInfo;
import com.gx.pojo.SYS_Inventory;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;
import com.gx.vo.CollageDetaiVo;
import com.gx.vo.CollageVo;
import com.gx.vo.FittingsInfoVo;
import com.gx.vo.ProductVo;
import com.gx.vo.ReceptionVo;
import com.gx.vo.RestockDetail;
import com.gx.vo.ReturnJson;

public class CollageDaoImpl implements ICollageDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String selectReception = "SELECT * FROM(SELECT p.*,RepairName,CustomerSou,DocumentState,BalanceState,"
			+ "ROW_NUMBER()OVER(ORDER BY p.ReceptionID)Num FROM  PW_Reception p,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID)AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	String selectReceptionCount = "SELECT COUNT(*)  FROM  PW_Reception p,SYS_Repair,SYS_DocumentState,"
			+ "SYS_CustomerSou,SYS_BalanceState WHERE p.RepairID = SYS_Repair.RepairID AND p.DocumentStateID="
			+ "SYS_DocumentState.DocumentStateID AND p.BalanceStateID=SYS_BalanceState.BalanceStateID AND "
			+ "p.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	String selectCollageData = "SELECT * FROM(SELECT c.*,Owner,CarNum,MaintenanceNum,CollageState,ROW_NUMBER()OVER(ORDER BY p.ReceptionID)" +
			"Num FROM  PW_Collage c,PW_Reception p WHERE c.ReceptionID=p.ReceptionID)AS tmp";
	String selectCollageCount = "SELECT COUNT(*) FROM  PW_Collage,PW_Reception WHERE"
			+ " PW_Collage.ReceptionID=PW_Reception.ReceptionID";
	String selectDaoRuPeiJian = "SELECT * FROM(SELECT r.*,MaintainabilityName,ROW_NUMBER()OVER(ORDER BY r.ReceptionID)Num FROM " +
			"SYS_RecProductDetail r,SYS_Maintainability WHERE r.MaintainabilityID=SYS_Maintainability.MaintainabilityID  AND r.ReceptionID=?) " +
			"AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	String selectDaoRuPeiJianCount="SELECT COUNT(*) FROM  SYS_RecProductDetail,SYS_Maintainability WHERE SYS_RecProductDetail.MaintainabilityID"
			+ "=SYS_Maintainability.MaintainabilityID AND SYS_RecProductDetail.ReceptionID=?";
	String selectlistFittingsInfo = "SELECT f.*,FittingsTypeName,VehicleType,SystemUnit FROM SYS_FittingsInfo f,SYS_FittingsType,SYS_VehicleType,SYS_SystemUnit"
			+ " WHERE f.FittingsTypeID=SYS_FittingsType.FittingsTypeID AND f.VehicleTypeID=SYS_VehicleType.VehicleTypeID AND f.SystemUnitID="
			+ "SYS_SystemUnit.SystemUnitID AND f.FittingsCode=?";
	String selectInventory = "SELECT * FROM SYS_Inventory i WHERE i.FittingsCode=? AND i.WarehouseID=?";
	String selectTableCollageDetai="SELECT * FROM(SELECT c.*,WarehouseName,MaintainabilityName,MaintenanceNum,ROW_NUMBER()OVER(ORDER BY c.CollageDetaiID)Num FROM " +
			"SYS_CollageDetai c,SYS_Maintainability,SYS_Warehouse,PW_Collage,dbo.PW_Reception WHERE c.MaintainabilityID=" +
			"SYS_Maintainability.MaintainabilityID AND c.WarehouseID=SYS_Warehouse.WarehouseID AND c.CollageID=" +
			"PW_Collage.CollageID AND dbo.PW_Collage.ReceptionID=dbo.PW_Reception.ReceptionID AND  c.CollageID=?)" +
			"AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	String selectTableCollageDetaiCount="SELECT COUNT(*) FROM SYS_CollageDetai c,SYS_Maintainability," +
			"SYS_Warehouse,PW_Collage,dbo.PW_Reception WHERE c.MaintainabilityID=SYS_Maintainability.MaintainabilityID " +
			"AND c.WarehouseID=SYS_Warehouse.WarehouseID AND c.CollageID=PW_Collage.CollageID AND dbo.PW_Collage." +
			"ReceptionID=dbo.PW_Reception.ReceptionID AND  c.CollageID=?";
	String selectRestock="SELECT p.*,c.CollageID,c.ToAudit FROM PW_Reception p,PW_Collage c WHERE p.ReceptionID=c.ReceptionID " +
			"AND p.ReceptionID=?";
	@Override
	public List<ReceptionVo> selectReception(String startDate, String endDate,
			String collageState, String carNum, int startIndex, int pageSize) {
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
			if (carNum != "") {
				buffer.append(" AND tmp.CarNum like '%" + carNum + "%'");
			}
			if (collageState != "") {
				buffer.append(" AND tmp.CollageState like '%" + collageState
						+ "%'");
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
			String collageState, String carNum) {
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
			if (carNum != "") {
				buffer.append(" AND p.CarNum like '%" + carNum + "%'");
			}
			if (collageState != "") {
				buffer.append(" AND p.CollageState like '%" + collageState
						+ "%'");
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
	public CollageVo selectCollageData(int receptionID) {
		String sql="SELECT * FROM PW_Collage WHERE ReceptionID=?";
		CollageVo collageVo = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, receptionID);
			rs = ps.executeQuery();
			collageVo = JdbcHelper.getSingleResult(rs, CollageVo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return collageVo;
	}

	@Override
	public List<ProductVo> selectDaoRuPeiJian(int receptionID, int startIndex,
			int pageSize) {
		List<ProductVo> list = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectDaoRuPeiJian);
			ps.setInt(1, receptionID);
			ps.setInt(2, startIndex);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			list = JdbcHelper.getResult(rs, ProductVo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	@Override
	public int selectDaoRuPeiJianCount(int receptionID) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectDaoRuPeiJianCount);
			ps.setInt(1, receptionID);
			rs = ps.executeQuery();
			while(rs.next()){
				i=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return i;
	}
	@Override
	public int updateListCollage(List<PW_Collage> listCollage,
			List<SYS_CollageDetai> listCollageDetai) {
		int id = 0;
		if (listCollage.get(0).getCollageID() == null) {
			id = JdbcHelper.insert(listCollage.get(0));
		} else {
			JdbcHelper.update(listCollage.get(0));
			id = listCollage.get(0).getCollageID();
		}
		if (id > 0) {
			if (listCollageDetai != null) {
				List<Integer> oldID = new ArrayList<Integer>();// 原来ID
				List<Integer> newID = new ArrayList<Integer>();// 新ID
				rs = JdbcHelper.select(SYS_CollageDetai.class, "CollageID", id);
				List<SYS_CollageDetai> lists = JdbcHelper.getResult(rs,
						SYS_CollageDetai.class);
				for (SYS_CollageDetai item : lists) {
					oldID.add(item.getCollageDetaiID());
				}
				for (int i = 0; i < listCollageDetai.size(); i++) {
					listCollageDetai.get(i).setCollageID(id);
					int  collageDetaiID = listCollageDetai.get(i).getCollageDetaiID()==null?0:listCollageDetai.get(i).getCollageDetaiID();
					String fittingsCode = listCollageDetai.get(i)
							.getFittingsCode();// 配件编码
					String vehicleType = listCollageDetai.get(i)
							.getVehicleType();// 车型
					double invenQuan = listCollageDetai.get(i).getQuantity();// 数量
					int warehouseID = listCollageDetai.get(i).getWarehouseID();// 仓库ID
					// 查询库存表
					SYS_Inventory listInventory = this.selectInventory(
							fittingsCode, warehouseID);
					// 查询库存表
					rs = JdbcHelper.select(SYS_Inventory.class, "FittingsCode",
							fittingsCode);
					List<SYS_Inventory> listInventorys = JdbcHelper.getResult(
							rs, SYS_Inventory.class);
					// 查询配件信息
					rs = JdbcHelper.select(SYS_FittingsInfo.class,
							"FittingsCode", fittingsCode);
					SYS_FittingsInfo listFittingsInfos = JdbcHelper
							.getSingleResult(rs, SYS_FittingsInfo.class);
					// 查询配件信息
					FittingsInfoVo listFittingsInfo = this
							.selectlistFittingsInfo(fittingsCode);

					if (listCollageDetai.get(i).getCollageDetaiID() == null) {
						JdbcHelper.insert(listCollageDetai.get(i));
					} else {
						newID.add(listCollageDetai.get(i).getCollageDetaiID());
						JdbcHelper.update(listCollageDetai.get(i));
					}
					if (listFittingsInfos != null) {
						rs = JdbcHelper.select(SYS_CollageDetai.class,
								"CollageDetaiID", collageDetaiID);
						SYS_CollageDetai lis = JdbcHelper.getSingleResult(rs,
								SYS_CollageDetai.class);
						if (lis != null) {
							double Quantity = lis.getQuantity();
							listFittingsInfos.setInvenQuan(listFittingsInfos
									.getInvenQuan() + (Quantity - invenQuan));// 修改配件表数量
						} else {
							double liInvenQuan = listFittingsInfos
									.getInvenQuan() != null ? listFittingsInfos
									.getInvenQuan() : 0;
							listFittingsInfos.setInvenQuan(liInvenQuan
									- invenQuan);// 修改配件表数量
						}
						JdbcHelper.update(listFittingsInfos);
					}
					if (listInventory != null)// 修改库存信息
					{
						if (listCollageDetai.get(i).getCollageDetaiID() != null) {
							rs = JdbcHelper.select(SYS_CollageDetai.class,
									"CollageDetaiID", listCollageDetai.get(i)
											.getCollageDetaiID());
							SYS_CollageDetai lis = JdbcHelper.getSingleResult(
									rs, SYS_CollageDetai.class);
							if (lis.getWarehouseID() != warehouseID) {
								int liWarehouseID = lis.getWarehouseID();
								String liFittingsCode = lis.getFittingsCode();
								SYS_Inventory oldInvenQuan = this
										.selectInventory(liFittingsCode,
												liWarehouseID);// 旧库存表
								oldInvenQuan.setInvenQuan(oldInvenQuan
										.getInvenQuan() + invenQuan);
								JdbcHelper.update(oldInvenQuan);
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
						JdbcHelper.update(listInventory);
					} else// 新增库存信息
					{
						SYS_Inventory sys_Inventory = new SYS_Inventory();
						sys_Inventory.setPosition(listCollageDetai.get(i)
								.getPosition());
						sys_Inventory.setWarehouseID(listCollageDetai.get(i)
								.getWarehouseID());
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
							JdbcHelper.insert(sys_Inventory);// 新增
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
							JdbcHelper.insert(sys_Inventory);// 新增
						}
					}
				}
				int receptionID = listCollage.get(0).getReceptionID();
				rs = JdbcHelper.select(PW_Reception.class, "ReceptionID",
						receptionID);
				PW_Reception listReception = JdbcHelper.getSingleResult(rs,
						PW_Reception.class);
				listReception.setCollageState("已领料");
				JdbcHelper.update(listReception);
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					rs = JdbcHelper.select(SYS_CollageDetai.class,
							"CollageDetaiID", item);
					SYS_CollageDetai listQuantity = JdbcHelper.getSingleResult(
							rs, SYS_CollageDetai.class);

					String fittingsCode = listQuantity.getFittingsCode();
					int warehouseID = listQuantity.getWarehouseID();
					double quantity = listQuantity.getQuantity();
					SYS_Inventory listInventory = this.selectInventory(
							fittingsCode, warehouseID);// 库存表
					rs = JdbcHelper.select(SYS_FittingsInfo.class,
							"FittingsCode", fittingsCode);
					SYS_FittingsInfo listFittingsInfos = JdbcHelper
							.getSingleResult(rs, SYS_FittingsInfo.class);// 配件表
					listInventory.setInvenQuan(listInventory.getInvenQuan()
							+ quantity);
					listFittingsInfos.setInvenQuan(listFittingsInfos
							.getInvenQuan() + quantity);
					JdbcHelper.update(listInventory);// 修改库存数量
					JdbcHelper.update(listFittingsInfos);// 修改配件数量
					JdbcHelper.delete(SYS_CollageDetai.class, "CollageDetaiID",
							item);// 删除
				}
			} else {
				JdbcHelper.delete(SYS_CollageDetai.class, "CollageID", id);// 删除全部
			}
		}
		return id;
	}

	@Override
	public List<CollageVo> selectCollage(int startIndex, int pageSize) {
		List<CollageVo> list = null;
		StringBuffer buffer = new StringBuffer(selectCollageData);
		try {
			conn = DbUtil.getConnection();
			buffer.append(" WHERE tmp.Num BETWEEN " + startIndex + "AND "
					+ pageSize);
			ps = conn.prepareStatement(buffer.toString());
			rs = ps.executeQuery();
			list = JdbcHelper.getResult(rs, CollageVo.class);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCollageState(
						list.get(i).getCollageState() != "已退料" ? list.get(i)
								.getCollageState() : "未领料");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int selectCollageCount() {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectCollageCount);
			rs = ps.executeQuery();
			while(rs.next()){
				i=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return i;
	}
	@Override
	public List<SYS_CollageDetai> selectCollageDetai(int collageID) {
		List<SYS_CollageDetai> list = null;
		rs = JdbcHelper.select(SYS_CollageDetai.class, "CollageID", collageID);
		list = JdbcHelper.getResult(rs, SYS_CollageDetai.class);
		return list;
	}

	// 查询配件信息
	private FittingsInfoVo selectlistFittingsInfo(String fittingsCode) {
		FittingsInfoVo list = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectlistFittingsInfo);
			ps.setString(1, fittingsCode);
			rs = ps.executeQuery();
			list = JdbcHelper.getSingleResult(rs, FittingsInfoVo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	// 查询库存信息
	private SYS_Inventory selectInventory(String fittingsCode, int warehouseID) {
		SYS_Inventory list = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectInventory);
			ps.setString(1, fittingsCode);
			ps.setInt(2, warehouseID);
			rs = ps.executeQuery();
			list = JdbcHelper.getSingleResult(rs, SYS_Inventory.class);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public CollageVo selectRestock(int receptionID) {
		CollageVo collageVo=null;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(selectRestock);
			ps.setInt(1, receptionID);
			rs=ps.executeQuery();
			collageVo=JdbcHelper.getSingleResult(rs, CollageVo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collageVo;
	}

	@Override
	public List<CollageDetaiVo> selectTableCollageDetai(int collageID,
			int startIndex, int pageSize) {
		List<CollageDetaiVo> list = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectTableCollageDetai);
			ps.setInt(1, collageID);
			ps.setInt(2, startIndex);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			list = JdbcHelper.getResult(rs, CollageDetaiVo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	@Override
	public int selectTableCollageDetaiCount(int collageID) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectTableCollageDetaiCount);
			ps.setInt(1, collageID);
			rs = ps.executeQuery();
			while(rs.next()){
				i=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return i;
	}

	@Override
	public ReturnJson judgingQuantity(List<RestockDetail> listRestockDetai) {
		ReturnJson returnJson=new ReturnJson();
		returnJson.setState(true);
		for (RestockDetail item : listRestockDetai) {
			rs=JdbcHelper.select(SYS_CollageDetai.class, "CollageDetaiID", item.getCollageDetaiID());
			SYS_CollageDetai listCollage=JdbcHelper.getSingleResult(rs, SYS_CollageDetai.class);
			double quantity=item.getQuantity();
			if(listCollage!=null){
				if(quantity<0){
					returnJson.setText("配件编码为" + listCollage.getFittingsCode() + "的退料数量不能小于0!");
					returnJson.setState(false);
				}else if(quantity>listCollage.getQuantity()){
					returnJson.setText("配件编码为" + listCollage.getFittingsCode() + "的退料数量不能超过领料的数量!");
					returnJson.setState(false);
				}
			}
		}
		return returnJson;
	}

	@Override
	public int updateListRestock(List<PW_Restock> listRestock,
			List<RestockDetail> listRestockDetai) {
		int i=0;
		for (RestockDetail item : listRestockDetai) {
			int collageDetaiID=item.getCollageDetaiID();
			String fittingsCode=item.getFittingsCode();
			int warehouseID=item.getWarehouseID();
			rs=JdbcHelper.select(SYS_CollageDetai.class, "CollageDetaiID", collageDetaiID);
			SYS_CollageDetai listCollage=JdbcHelper.getSingleResult(rs, SYS_CollageDetai.class);
			rs=JdbcHelper.select(SYS_FittingsInfo.class, "FittingsCode", fittingsCode);
			SYS_FittingsInfo listFittingsInfos=JdbcHelper.getSingleResult(rs, SYS_FittingsInfo.class);
			SYS_Inventory listInventory = this.selectInventory(fittingsCode, warehouseID);
			double quantity=item.getQuantity();
			if(listCollage!=null){

                listCollage.setQuantity(listCollage.getQuantity() - quantity);
                if (listCollage.getQuantity() == 0)//退料数量==领料数量则删除
                {
                	JdbcHelper.delete(SYS_CollageDetai.class, "CollageDetaiID", collageDetaiID);
                }
                else//退料数量！=领料数量则修改
                {
                	JdbcHelper.update(listCollage);
                }
                listInventory.setInvenQuan(listInventory.getInvenQuan() + quantity);
                    JdbcHelper.update(listInventory);
                if (listFittingsInfos != null)
                {
                    listFittingsInfos.setInvenQuan(listFittingsInfos.getInvenQuan() + quantity);
                    JdbcHelper.update(listFittingsInfos);
                }
			}
		}
		 int receptionID = listRestock.get(0).getReceptionID();
		 rs=JdbcHelper.select(PW_Reception.class, "ReceptionID", receptionID);
		 PW_Reception listReception = JdbcHelper.getSingleResult(rs, PW_Reception.class);
         listReception.setCollageState("已退料");
         i=JdbcHelper.update(listReception);
		return i;
	}

	@Override
	public int selectCollageToID(int receptionID) {
		int collageID=0;
		rs=JdbcHelper.select(PW_Collage.class, "ReceptionID", receptionID);
		PW_Collage list = JdbcHelper.getSingleResult(rs, PW_Collage.class);
		if(list!=null){
			collageID = list.getCollageID();
		}
		return collageID;
	}

	@Override
	public PW_Reception arrlistToCollage(int receptionID) {
		PW_Reception pw_Reception=null;
		rs=JdbcHelper.select(PW_Reception.class, "ReceptionID", receptionID);
		pw_Reception=JdbcHelper.getSingleResult(rs, PW_Reception.class);
		return pw_Reception;
	}

	


	

	
}
