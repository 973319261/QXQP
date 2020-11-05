package com.gx.service.impl;

import java.util.List;

import com.gx.dao.ICommonDao;
import com.gx.dao.impl.CommonDaoImpl;
import com.gx.pojo.PW_Predate;
import com.gx.pojo.SYS_Expenses;
import com.gx.pojo.SYS_FittingsInfo;
import com.gx.pojo.SYS_FittingsType;
import com.gx.pojo.SYS_MaintenanceCus;
import com.gx.pojo.SYS_PreOtherCostDetail;
import com.gx.pojo.SYS_PreProductDetail;
import com.gx.pojo.SYS_PreRepairItemDetail;
import com.gx.pojo.SYS_RepairItem;
import com.gx.service.ICommonService;
import com.gx.vo.AppendOptionVo;
import com.gx.vo.FittingsInfoVo;

public class CommonServiceImpl implements ICommonService {
	private ICommonDao commonDao=new CommonDaoImpl();

	@Override
	public List<AppendOptionVo> selectAppendOption(String type) {
		return commonDao.selectAppendOption(type);
	}

	@Override
	public List<SYS_MaintenanceCus> seleceMaintenanceCus(String LicenseCode,int startIndex,int pageSize) {
		return commonDao.seleceMaintenanceCus(LicenseCode,startIndex,pageSize);
	}

	@Override
	public int seleceMaintenanceCusCount(String LicenseCode) {
		return commonDao.seleceMaintenanceCusCount(LicenseCode);
	}

	@Override
	public int updateMaintenanceCus(SYS_MaintenanceCus maintenanceCus) {
		return commonDao.updateMaintenanceCus(maintenanceCus);
	}

	@Override
	public int customerNum(String d) {
		return commonDao.customerNum(d);
	}

	@Override
	public SYS_RepairItem selectRepairItemChange(int repairItemID) {
		return commonDao.selectRepairItemChange(repairItemID);
	}

	@Override
	public SYS_Expenses selectExpensesChange(int expensesID) {
		return commonDao.selectExpensesChange(expensesID);
	}

	@Override
	public List<SYS_FittingsType> selectFittingsType() {
		return commonDao.selectFittingsType();
	}

	@Override
	public List<FittingsInfoVo> seleceFittingsInfo(String infoOne,
			String infoTow, String infoThree, String infoFour,
			int fittingsTypeID, int startIndex, int pageSize) {
		return commonDao.seleceFittingsInfo(infoOne, infoTow, infoThree, infoFour, fittingsTypeID, startIndex, pageSize);
	}

	@Override
	public int seleceFittingsInfoCount(String infoOne, String infoTow,
			String infoThree, String infoFour, int fittingsTypeID) {
		return commonDao.seleceFittingsInfoCount(infoOne, infoTow, infoThree, infoFour, fittingsTypeID);
	}

	@Override
	public int updateFittingsInfo(SYS_FittingsInfo sys_FittingsInfo) {
		return commonDao.updateFittingsInfo(sys_FittingsInfo);
	}

	@Override
	public SYS_FittingsInfo judgingFittingsCode(String fittingsCode) {
		return commonDao.judgingFittingsCode(fittingsCode);
	}
}
