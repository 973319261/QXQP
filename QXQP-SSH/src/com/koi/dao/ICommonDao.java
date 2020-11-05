package com.koi.dao;

import java.util.List;

import com.koi.po.SysExpenses;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysFittingsType;
import com.koi.po.SysMaintenanceCus;
import com.koi.po.SysRepairItem;
import com.koi.vo.AppendOptionVo;
import com.koi.vo.FittingsInfoVo;


public interface ICommonDao{
	/**
	 * 查询下拉框
	 * @param type 表名
	 * @return
	 */
	public List<AppendOptionVo>  selectAppendOption(String type);
	/**
	 * 生成客户编号
	 * @return
	 */
	public int customerNum(String d);
	/**
	 * 查询维修客户表
	 * @param LicenseCode
	 * @return
	 */
	public List<SysMaintenanceCus> seleceMaintenanceCus(String licenseCode,Long startIndex,Long pageSize);
	/**
	 * 查询维修客户总数
	 * @param LicenseCode
	 * @return
	 */
	public Long seleceMaintenanceCusCount(String licenseCode);
	/**
	 * 新增/修改维修客户
	 * @param maintenanceCus
	 * @return
	 */
	public int updateMaintenanceCus(SysMaintenanceCus maintenanceCus);
	/**
	 * 表格修理项目下拉框改变查询
	 * @param RepairItemID 修理项目ID
	 * @return
	 */
	public  SysRepairItem selectRepairItemChange(int repairItemID);
	/**
	 * 表格其他费用下拉框改变查询
	 * @param ExpensesID 其他费用ID
	 */
	public SysExpenses selectExpensesChange(int expensesID); 
	/**
	 * 查询修理大类
	 * @return SYS_FittingsType
	 */
	public List<SysFittingsType> selectFittingsType();
	/**
	 * 查询配件信息
	 * @param InfoOne
	 * @param InfoTow
	 * @param InfoThree
	 * @param InfoFour
	 * @param FittingsTypeID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<FittingsInfoVo> seleceFittingsInfo(String infoOne, String infoTow,
            String infoThree, String infoFour,int fittingsTypeID,Long startIndex,Long pageSize) ;
	/**
	 * 查询配件条数
	 * @param infoOne
	 * @param infoTow
	 * @param infoThree
	 * @param infoFour
	 * @param fittingsTypeID
	 * @return
	 */
	public Long seleceFittingsInfoCount(String infoOne, String infoTow,
            String infoThree, String infoFour,int fittingsTypeID);
	/**
	 * 新增/修改配件信息
	 * @param sys_FittingsInfo
	 * @return
	 */
	public int updateFittingsInfo(SysFittingsInfo sys_FittingsInfo,byte[] picture);
	/**
	 * 判断是否存在配件编码
	 * @param FittingsCode
	 * @return 
	 */
	public SysFittingsInfo judgingFittingsCode(String fittingsCode);
}

