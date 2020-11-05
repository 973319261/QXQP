package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.ICommonDao;
import com.koi.po.SysExpenses;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysFittingsType;
import com.koi.po.SysMaintenanceCus;
import com.koi.po.SysRepairItem;
import com.koi.service.ICommonService;
import com.koi.util.Bsgrid;
import com.koi.vo.AppendOptionVo;
import com.koi.vo.FittingsInfoVo;

@Transactional
@Service("commonService")
public class CommonServiceImpl implements ICommonService {
	@Autowired
	private ICommonDao commonDao;

	@Override
	public List<AppendOptionVo> selectAppendOption(String type) {
		return commonDao.selectAppendOption(type);
	}

	@Override
	public String customerNum() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String d = sf.format(date);
		int num = commonDao.customerNum(d) + 1;
		String str = "KH" + d + "-" + String.format("%04d", num);
		return str;
	}

	@Override
	public Object seleceMaintenanceCus(String licenseCode, Long curPage,
			Long pageSize) {
		Long totalRows = commonDao.seleceMaintenanceCusCount(licenseCode);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<SysMaintenanceCus> list = commonDao.seleceMaintenanceCus(
				licenseCode, startIndex, pageSize);
		Object obj = Bsgrid.getList(list, curPage, totalRows);
		return obj;
	}

	@Override
	public int updateMaintenanceCus(SysMaintenanceCus maintenanceCus) {
		// TODO Auto-generated method stub
		return commonDao.updateMaintenanceCus(maintenanceCus);
	}

	@Override
	public SysRepairItem selectRepairItemChange(int repairItemID) {
		// TODO Auto-generated method stub
		return commonDao.selectRepairItemChange(repairItemID);
	}

	@Override
	public SysExpenses selectExpensesChange(int expensesID) {
		// TODO Auto-generated method stub
		return commonDao.selectExpensesChange(expensesID);
	}

	@Override
	public List<SysFittingsType> selectFittingsType() {
		// TODO Auto-generated method stub
		return commonDao.selectFittingsType();
	}

	@Override
	public Object seleceFittingsInfo(String infoOne, String infoTow,
			String infoThree, String infoFour, int fittingsTypeID,
			Long curPage, Long pageSize) {
		Long totalRows = commonDao.seleceFittingsInfoCount(infoOne, infoTow,
				infoThree, infoFour, fittingsTypeID);// 总数
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<FittingsInfoVo> list = commonDao.seleceFittingsInfo(infoOne,
				infoTow, infoThree, infoFour, fittingsTypeID, startIndex,
				pageSize);
		Object obj = Bsgrid.getList(list, curPage, totalRows);// 设置返回的参数
		return obj;
	}

	@Override
	public int updateFittingsInfo(SysFittingsInfo sys_FittingsInfo,
			byte[] picture) {
		// TODO Auto-generated method stub
		return commonDao.updateFittingsInfo(sys_FittingsInfo, picture);
	}

	@Override
	public boolean judgingFittingsCode(String fittingsCode) {
		boolean falg = false;
		SysFittingsInfo sys_FittingsInfo = commonDao
				.judgingFittingsCode(fittingsCode);
		if (sys_FittingsInfo == null) {
			falg = true;
		}
		return falg;
	}
}
