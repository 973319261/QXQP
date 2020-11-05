package com.koi.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koi.dao.ICommonDao;
import com.koi.po.SysExpenses;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysFittingsType;
import com.koi.po.SysMaintenanceCus;
import com.koi.po.SysRepairItem;
import com.koi.util.SqlHelper;
import com.koi.vo.AppendOptionVo;
import com.koi.vo.FittingsInfoVo;

@Repository("commonDao")
public class CommonDaoImpl implements ICommonDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private SqlHelper sqlHelper;

	private String seleceMaintenanceCus = "SELECT * FROM(select MaintenanceCusID,m.InsuranceComID,m.InsuranceSpeID,m.CustomerLevelID,m.RegionID,"
			+ "m.DepartmentID,m.CustomerSouID,m.CustomerTypeID,m.CarderID,m.Birthday,m.InitialStartDate,m.InitialEndDate,m.DriveDate,m.DrivingDate,m.Owner,m.IdNumber,"
			+ "m.MobilePhone,m.LicenseCode,m.VehicleType,m.EngineNum,m.FrameNum,m.RepairMan,m.RepairTele,m.Address,m.CustomerNum,m.InputPerson,ROW_NUMBER()OVER(ORDER BY "
			+ "m.MaintenanceCusID)Num FROM SYS_MaintenanceCus m,SYS_InsuranceCom,SYS_InsuranceSpe,SYS_Department,SYS_Carder,SYS_CustomerType,SYS_CustomerLevel,"
			+ "SYS_Region,SYS_CustomerSou WHERE m.InsuranceComID=SYS_InsuranceCom.InsuranceComID and m.InsuranceSpeID=SYS_InsuranceSpe.InsuranceSpeID and"
			+ " m.DepartmentID=SYS_Department.DepartmentID and m.CarderID=SYS_Carder.CarderID and m.CustomerTypeID=SYS_CustomerType.CustomerTypeID and "
			+ "m.CustomerLevelID=SYS_CustomerLevel.CustomerLevelID and m.RegionID =SYS_Region.RegionID and m.CustomerSouID=SYS_CustomerSou.CustomerSouID ) "
			+ "AS tmp WHERE tmp.Num BETWEEN ? AND ?";
	private String seleceMaintenanceCusCount = "SELECT count(*) as num FROM SYS_MaintenanceCus m,SYS_InsuranceCom,SYS_InsuranceSpe,SYS_Department,SYS_Carder,SYS_CustomerType,SYS_CustomerLevel,"
			+ "SYS_Region,SYS_CustomerSou WHERE m.InsuranceComID=SYS_InsuranceCom.InsuranceComID and m.InsuranceSpeID=SYS_InsuranceSpe.InsuranceSpeID and"
			+ " m.DepartmentID=SYS_Department.DepartmentID and m.CarderID=SYS_Carder.CarderID and m.CustomerTypeID=SYS_CustomerType.CustomerTypeID and "
			+ "m.CustomerLevelID=SYS_CustomerLevel.CustomerLevelID and m.RegionID =SYS_Region.RegionID and m.CustomerSouID=SYS_CustomerSou.CustomerSouID";
	private String selectRepairItemChange = "SELECT SYS_RepairItem.* FROM SYS_Maintenance m,SYS_RepairItem WHERE m.MaintenanceID = SYS_RepairItem.MaintenanceID AND RepairItemID=?";
	private String selectExpensesChange = "SELECT * FROM SYS_Expenses WHERE ExpensesID=?";
	private String selectFittingsType = "SELECT * FROM SYS_FittingsType";
	private String seleceFittingsInfo = "SELECT * FROM(SELECT FittingsInfoID,f.FittingsTypeID,FittingsTypeName,FittingsCode,FittingsName,"
			+ "Specification,f.VehicleTypeID,VehicleType,f.SystemUnitID,SystemUnit,WholesalePrice,Intake,SalesPrice,f.SuppliersID,SuppliersName,"
			+ "f.Remark,ROW_NUMBER()OVER(ORDER BY f.FittingsInfoID)Num FROM SYS_FittingsInfo f,SYS_FittingsType,SYS_SystemUnit,"
			+ "SYS_VehicleType,dbo.SYS_Suppliers WHERE f.FittingsTypeID=SYS_FittingsType.FittingsTypeID AND f.SystemUnitID="
			+ "SYS_SystemUnit.SystemUnitID AND f.VehicleTypeID = SYS_VehicleType.VehicleTypeID AND f.SuppliersID=SYS_Suppliers."
			+ "SuppliersID) AS tmp WHERE Num BETWEEN ? AND ?";
	private String seleceFittingsInfoCount = "SELECT count(*) as num FROM SYS_FittingsInfo f,SYS_FittingsType,SYS_SystemUnit,"
			+ "SYS_VehicleType,dbo.SYS_Suppliers WHERE f.FittingsTypeID=SYS_FittingsType.FittingsTypeID AND f.SystemUnitID="
			+ "SYS_SystemUnit.SystemUnitID AND f.VehicleTypeID = SYS_VehicleType.VehicleTypeID AND f.SuppliersID=SYS_Suppliers."
			+ "SuppliersID";
	private String judgingFittingsCode = "SELECT * FROM SYS_FittingsInfo WHERE FittingsCode=?";

	@Override
	public List<AppendOptionVo> selectAppendOption(String type) {
		List<AppendOptionVo> list = null;
		Map<String, String> map = sqlHelper.sqlField(type);
		String sql = "select " + map.get("id") + " as id ," + map.get("name")
				+ " as name from " + type;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(
				AppendOptionVo.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public int customerNum(String d) {
		String customerNum = "SELECT TOP 1 SUBSTRING(CustomerNum,15,4) as num from SYS_MaintenanceCus "
				+ "WHERE CustomerNum LIKE '%"
				+ d
				+ "%' ORDER BY  CustomerNum DESC";
		Long num = 0l;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(customerNum);
		sqlQuery.addScalar("num", Hibernate.STRING);
		Object str = sqlQuery.uniqueResult();
		num = str == null ? 0l : Integer.valueOf(str.toString());
		return num.intValue();
	}

	@Override
	public List<SysMaintenanceCus> seleceMaintenanceCus(String licenseCode,
			Long startIndex, Long pageSize) {
		List<SysMaintenanceCus> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(seleceMaintenanceCus);
		if (!"".equals(licenseCode)) {
			buffer.append(" AND tmp.LicenseCode like '%" + licenseCode + "%'");
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString())
				.addEntity(SysMaintenanceCus.class);
		sqlQuery.setLong(0, startIndex);
		sqlQuery.setLong(1, pageSize);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long seleceMaintenanceCusCount(String licenseCode) {
		Long i = 0L;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(seleceMaintenanceCusCount);
		if (!"".equals(licenseCode)) {
			buffer.append(" AND LicenseCode like '%" + licenseCode + "%'");
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString());
		sqlQuery.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public int updateMaintenanceCus(SysMaintenanceCus maintenanceCus) {
		int i = 0;
		if (maintenanceCus.getMaintenanceCusId() != null) {
			i = sqlHelper.update(maintenanceCus);// 修改
		} else {
			i = sqlHelper.insert(maintenanceCus);// 新增
		}
		return i;
	}

	@Override
	public SysRepairItem selectRepairItemChange(int repairItemID) {
		SysRepairItem sysRepairItem = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectRepairItemChange)
				.addEntity(SysRepairItem.class);
		sqlQuery.setInteger(0, repairItemID);
		sysRepairItem = (SysRepairItem) sqlQuery.uniqueResult();
		return sysRepairItem;
	}

	@Override
	public SysExpenses selectExpensesChange(int expensesID) {
		SysExpenses sysExpenses = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectExpensesChange)
				.addEntity(SysExpenses.class);
		sqlQuery.setLong(0, expensesID);
		sysExpenses = (SysExpenses) sqlQuery.uniqueResult();
		return sysExpenses;
	}

	@Override
	public List<SysFittingsType> selectFittingsType() {
		List<SysFittingsType> list = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(selectFittingsType)
				.addEntity(SysFittingsType.class);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public List<FittingsInfoVo> seleceFittingsInfo(String infoOne,
			String infoTow, String infoThree, String infoFour,
			int fittingsTypeID, Long startIndex, Long pageSize) {
		List<FittingsInfoVo> list = null;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(seleceFittingsInfo);
		if (fittingsTypeID > 0) {
			buffer.append(" AND FittingsTypeID=" + fittingsTypeID);
		}
		if (!"".equals(infoOne)) {
			buffer.append(" AND FittingsTypeName like '%" + infoOne + "%'");
		}
		if (!"".equals(infoTow)) {
			buffer.append(" AND FittingsCode like '%" + infoTow + "%'");
		}
		if (!"".equals(infoThree)) {
			buffer.append(" AND FittingsName like '%" + infoThree + "%'");
		}
		if (!"".equals(infoFour)) {
			buffer.append(" AND VehicleType like '%" + infoFour + "%'");
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString())
				.addEntity(FittingsInfoVo.class);
		sqlQuery.setLong(0, startIndex);
		sqlQuery.setLong(1, pageSize);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public Long seleceFittingsInfoCount(String infoOne, String infoTow,
			String infoThree, String infoFour, int fittingsTypeID) {
		Long i = 0l;
		session = sessionFactory.getCurrentSession();
		StringBuffer buffer = new StringBuffer(seleceFittingsInfoCount);
		if (fittingsTypeID > 0) {
			buffer.append(" AND FittingsTypeID=" + fittingsTypeID);
		}
		if (!"".equals(infoOne)) {
			buffer.append(" AND FittingsTypeName like '%" + infoOne + "%'");
		}
		if (!"".equals(infoTow)) {
			buffer.append(" AND FittingsCode like '%" + infoTow + "%'");
		}
		if (!"".equals(infoThree)) {
			buffer.append(" AND FittingsName like '%" + infoThree + "%'");
		}
		if (!"".equals(infoFour)) {
			buffer.append(" AND VehicleType like '%" + infoFour + "%'");
		}
		SQLQuery sqlQuery = session.createSQLQuery(buffer.toString())
				.addScalar("num", Hibernate.LONG);
		i = (Long) sqlQuery.uniqueResult();
		return i;
	}

	@Override
	public int updateFittingsInfo(SysFittingsInfo c, byte[] picture) {
		String sql = "insert into SYS_FittingsInfo(FittingsTypeID,SystemUnitID,VehicleTypeID,SuppliersID,"
				+ "InventoryMax,InventoryMin,Intake,SalesPrice,WholesalePrice,OpenPrice,InvenQuan,FittingsName,"
				+ "FittingsCode,Barcode,Specification,Brand,Remark,picture) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int i = 0;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(
				SysFittingsInfo.class);
		sqlQuery.setInteger(0, c.getFittingsTypeId());
		sqlQuery.setInteger(1, c.getSystemUnitId());
		sqlQuery.setInteger(2, c.getVehicleTypeId());
		sqlQuery.setInteger(3, c.getSuppliersId());
		sqlQuery.setDouble(4, c.getInventoryMax());
		sqlQuery.setDouble(5, c.getInventoryMin());
		sqlQuery.setDouble(6, c.getIntake());
		sqlQuery.setDouble(7, c.getSalesPrice());
		sqlQuery.setDouble(8, c.getWholesalePrice());
		sqlQuery.setDouble(9, c.getOpenPrice());
		sqlQuery.setDouble(10, 0);
		sqlQuery.setString(11, c.getFittingsName());
		sqlQuery.setString(12, c.getFittingsCode());
		sqlQuery.setString(13, c.getBarcode());
		sqlQuery.setString(14, c.getSpecification());
		sqlQuery.setString(15, c.getBrand());
		sqlQuery.setString(16, c.getRemark());
		sqlQuery.setBinary(17, picture);
		i = sqlQuery.executeUpdate();
		return i;
	}

	@Override
	public SysFittingsInfo judgingFittingsCode(String fittingsCode) {
		SysFittingsInfo sysFittingsInfo = null;
		session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(judgingFittingsCode)
				.addEntity(SysFittingsInfo.class);
		sqlQuery.setString(0, fittingsCode);
		sysFittingsInfo = (SysFittingsInfo) sqlQuery.uniqueResult();
		return sysFittingsInfo;
	}
}
