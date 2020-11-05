package com.gx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gx.dao.ICommonDao;
import com.gx.pojo.SYS_Expenses;
import com.gx.pojo.SYS_FittingsInfo;
import com.gx.pojo.SYS_FittingsType;
import com.gx.pojo.SYS_MaintenanceCus;
import com.gx.pojo.SYS_RepairItem;
import com.gx.util.DbUtil;
import com.gx.util.JdbcHelper;
import com.gx.vo.AppendOptionVo;
import com.gx.vo.FittingsInfoVo;

public class CommonDaoImpl implements ICommonDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	String seleceMaintenanceCus = "SELECT * FROM(select MaintenanceCusID,m.InsuranceComID,m.InsuranceSpeID,m.CustomerLevelID,m.RegionID,"
			+ "m.DepartmentID,m.CustomerSouID,m.CustomerTypeID,m.CarderID,m.Birthday,m.InitialStartDate,m.InitialEndDate,m.DriveDate,m.DrivingDate,m.Owner,m.IdNumber,"
			+ "m.MobilePhone,m.LicenseCode,m.VehicleType,m.EngineNum,m.FrameNum,m.RepairMan,m.RepairTele,m.Address,m.CustomerNum,m.InputPerson,ROW_NUMBER()OVER(ORDER BY "
			+ "m.MaintenanceCusID)Num FROM SYS_MaintenanceCus m,SYS_InsuranceCom,SYS_InsuranceSpe,SYS_Department,SYS_Carder,SYS_CustomerType,SYS_CustomerLevel,"
			+ "SYS_Region,SYS_CustomerSou WHERE m.InsuranceComID=SYS_InsuranceCom.InsuranceComID and m.InsuranceSpeID=SYS_InsuranceSpe.InsuranceSpeID and"
			+ " m.DepartmentID=SYS_Department.DepartmentID and m.CarderID=SYS_Carder.CarderID and m.CustomerTypeID=SYS_CustomerType.CustomerTypeID and "
			+ "m.CustomerLevelID=SYS_CustomerLevel.CustomerLevelID and m.RegionID =SYS_Region.RegionID and m.CustomerSouID=SYS_CustomerSou.CustomerSouID ) "
			+ "AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	String seleceMaintenanceCusCount = "SELECT count(*) FROM SYS_MaintenanceCus m,SYS_InsuranceCom,SYS_InsuranceSpe,SYS_Department,SYS_Carder,SYS_CustomerType,SYS_CustomerLevel,"
			+ "SYS_Region,SYS_CustomerSou WHERE m.InsuranceComID=SYS_InsuranceCom.InsuranceComID and m.InsuranceSpeID=SYS_InsuranceSpe.InsuranceSpeID and"
			+ " m.DepartmentID=SYS_Department.DepartmentID and m.CarderID=SYS_Carder.CarderID and m.CustomerTypeID=SYS_CustomerType.CustomerTypeID and "
			+ "m.CustomerLevelID=SYS_CustomerLevel.CustomerLevelID and m.RegionID =SYS_Region.RegionID and m.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	String selectRepairItemChange = "SELECT RepairItemID,m.MaintenanceID,RepairCharge FROM SYS_Maintenance m,SYS_RepairItem WHERE m.MaintenanceID = SYS_RepairItem.MaintenanceID AND RepairItemID=?";
	String selectExpensesChange = "SELECT * FROM SYS_Expenses WHERE ExpensesID=?";
	String selectFittingsType = "SELECT * FROM SYS_FittingsType";
	String seleceFittingsInfo = "SELECT * FROM(SELECT FittingsInfoID,f.FittingsTypeID,FittingsTypeName,FittingsCode,FittingsName,"
			+ "Specification,f.VehicleTypeID,VehicleType,f.SystemUnitID,SystemUnit,Intake,SalesPrice,f.SuppliersID,SuppliersName,"
			+ "f.Remark,ROW_NUMBER()OVER(ORDER BY f.FittingsInfoID)Num FROM SYS_FittingsInfo f,SYS_FittingsType,SYS_SystemUnit,"
			+ "SYS_VehicleType,dbo.SYS_Suppliers WHERE f.FittingsTypeID=SYS_FittingsType.FittingsTypeID AND f.SystemUnitID="
			+ "SYS_SystemUnit.SystemUnitID AND f.VehicleTypeID = SYS_VehicleType.VehicleTypeID AND f.SuppliersID=SYS_Suppliers."
			+ "SuppliersID) AS tmp WHERE Num BETWEEN ? AND ?";
	String seleceFittingsInfoCount = "SELECT COUNT(*) FROM SYS_FittingsInfo f,SYS_FittingsType,SYS_SystemUnit,"
			+ "SYS_VehicleType,dbo.SYS_Suppliers WHERE f.FittingsTypeID=SYS_FittingsType.FittingsTypeID AND f.SystemUnitID="
			+ "SYS_SystemUnit.SystemUnitID AND f.VehicleTypeID = SYS_VehicleType.VehicleTypeID AND f.SuppliersID=SYS_Suppliers."
			+ "SuppliersID";
	String judgingFittingsCode="SELECT * FROM SYS_FittingsInfo WHERE FittingsCode=?";
	@Override
	public List<AppendOptionVo> selectAppendOption(String type) {
		String sql = "select * from " + type;
		return JdbcHelper.getAppendOption(sql);
	}

	@Override
	public List<SYS_MaintenanceCus> seleceMaintenanceCus(String licenseCode,
			int startIndex, int pageSize) {
		List<SYS_MaintenanceCus> list = null;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(seleceMaintenanceCus);
			if (licenseCode != "") {
				buffer.append(" AND tmp.LicenseCode like '%" + licenseCode
						+ "%'");
			}
			ps = conn.prepareStatement(buffer.toString());
			ps.setInt(1, startIndex);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			list = JdbcHelper.getResult(rs, SYS_MaintenanceCus.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public int seleceMaintenanceCusCount(String licenseCode) {
		int i = 0;
		try {
			conn = DbUtil.getConnection();
			StringBuffer buffer = new StringBuffer(seleceMaintenanceCusCount);
			if (licenseCode != "") {
				buffer.append(" AND LicenseCode like '%" + licenseCode + "%'");
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
	public int updateMaintenanceCus(SYS_MaintenanceCus maintenanceCus) {
		int i = 0;
		if (maintenanceCus.getMaintenanceCusID() > 0) {
			i = JdbcHelper.update(maintenanceCus);// 修改
		} else {
			if (maintenanceCus.getCarderID() > 0) {
				i = JdbcHelper.insert(maintenanceCus);// 新增
			}
		}
		return i;
	}

	@Override
	public int customerNum(String d) {
		String customerNum = "SELECT TOP 1 SUBSTRING(CustomerNum,15,4) from SYS_MaintenanceCus "
				+ "WHERE CustomerNum LIKE '%"
				+ d
				+ "%' ORDER BY  CustomerNum DESC";
		int num = 0;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(customerNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(rs, ps, conn);
		}
		return num;
	}

	@Override
	public SYS_RepairItem selectRepairItemChange(int repairItemID) {
		SYS_RepairItem sys_RepairItem = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectRepairItemChange);
			ps.setInt(1, repairItemID);
			rs = ps.executeQuery();
			sys_RepairItem = JdbcHelper.getSingleResult(rs,
					SYS_RepairItem.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sys_RepairItem;
	}

	@Override
	public SYS_Expenses selectExpensesChange(int expensesID) {
		SYS_Expenses sys_Expenses = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectExpensesChange);
			ps.setInt(1, expensesID);
			rs = ps.executeQuery();
			sys_Expenses = JdbcHelper.getSingleResult(rs, SYS_Expenses.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sys_Expenses;
	}

	@Override
	public List<SYS_FittingsType> selectFittingsType() {
		List<SYS_FittingsType> sys_FittingsType = null;
		try {
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(selectFittingsType);
			rs = ps.executeQuery();
			sys_FittingsType = JdbcHelper.getResult(rs, SYS_FittingsType.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sys_FittingsType;
	}

	@Override
	public List<FittingsInfoVo> seleceFittingsInfo(String infoOne,
			String infoTow, String infoThree, String infoFour,
			int fittingsTypeID, int startIndex, int pageSize) {
		List<FittingsInfoVo> list=null;
		StringBuffer buffer=new StringBuffer(seleceFittingsInfo);
		try {
			conn=DbUtil.getConnection();
			if(fittingsTypeID>0){
				buffer.append(" AND FittingsTypeID="+ fittingsTypeID);
			}
			if(infoOne!=""){
				buffer.append(" AND FittingsTypeName like '%"+ infoOne +"%'");
			}
			if(infoTow!=""){
				buffer.append(" AND FittingsCode like '%"+ infoTow +"%'");
			}
			if(infoThree!=""){
				buffer.append(" AND FittingsName like '%"+ infoThree +"%'");
			}
			if(infoFour!=""){
				buffer.append(" AND VehicleType like '%"+ infoFour +"%'");
			}
			ps=conn.prepareStatement(buffer.toString());
			ps.setInt(1, startIndex);
			ps.setInt(2, pageSize);
			rs=ps.executeQuery();
			list=JdbcHelper.getResult(rs, FittingsInfoVo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int seleceFittingsInfoCount(String infoOne, String infoTow,
			String infoThree, String infoFour, int fittingsTypeID) {
		StringBuffer buffer=new StringBuffer(seleceFittingsInfoCount);
		int i=0;
		try {
			conn=DbUtil.getConnection();
			if(fittingsTypeID>0){
				buffer.append(" AND f.FittingsTypeID="+ fittingsTypeID);
			}
			if(infoOne!=""){
				buffer.append(" AND FittingsTypeName like '%"+ infoOne +"%'");
			}
			if(infoTow!=""){
				buffer.append(" AND FittingsCode like '%"+ infoTow +"%'");
			}
			if(infoThree!=""){
				buffer.append(" AND FittingsName like '%"+ infoThree +"%'");
			}
			if(infoFour!=""){
				buffer.append(" AND VehicleType like '%"+ infoFour +"%'");
			}
			ps=conn.prepareStatement(buffer.toString());
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
	public int updateFittingsInfo(SYS_FittingsInfo c) {
		String sql="insert into SYS_FittingsInfo(FittingsTypeID,SystemUnitID,VehicleTypeID,SuppliersID," +
				"InventoryMax,InventoryMin,Intake,SalesPrice,WholesalePrice,OpenPrice,InvenQuan,FittingsName," +
				"FittingsCode,Barcode,Specification,Brand,Remark,picture) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int i=0;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, c.getFittingsTypeID());
			ps.setInt(2, c.getSystemUnitID());
			ps.setInt(3, c.getVehicleTypeID());
			ps.setInt(4, c.getSuppliersID());
			ps.setDouble(5, c.getInventoryMax());
			ps.setDouble(6, c.getInventoryMin());
			ps.setDouble(7, c.getIntake());
			ps.setDouble(8, c.getSalesPrice());
			ps.setDouble(9, c.getWholesalePrice());
			ps.setDouble(10, c.getOpenPrice());
			ps.setDouble(11, c.getInvenQuan());
			ps.setString(12, c.getFittingsName());
			ps.setString(13, c.getFittingsCode());
			ps.setString(14, c.getBarcode());
			ps.setString(15, c.getSpecification());
			ps.setString(16, c.getBrand());
			ps.setString(17, c.getRemark());
			ps.setBytes(18, c.getPicture());
		    i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return i;
	}

	@Override
	public SYS_FittingsInfo judgingFittingsCode(String fittingsCode) {
		SYS_FittingsInfo sys_FittingsInfo=null;
		try {
			conn=DbUtil.getConnection();
			ps=conn.prepareStatement(judgingFittingsCode);
			ps.setString(1, fittingsCode);
			rs=ps.executeQuery();
			sys_FittingsInfo=JdbcHelper.getSingleResult(rs, SYS_FittingsInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtil.close(rs, ps, conn);
		}
		return sys_FittingsInfo;
	}
}
