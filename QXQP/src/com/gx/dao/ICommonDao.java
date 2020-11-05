package com.gx.dao;

import java.util.List;

import com.gx.pojo.PW_Predate;
import com.gx.pojo.SYS_Expenses;
import com.gx.pojo.SYS_FittingsInfo;
import com.gx.pojo.SYS_FittingsType;
import com.gx.pojo.SYS_MaintenanceCus;
import com.gx.pojo.SYS_PreOtherCostDetail;
import com.gx.pojo.SYS_PreProductDetail;
import com.gx.pojo.SYS_PreRepairItemDetail;
import com.gx.pojo.SYS_RepairItem;
import com.gx.vo.AppendOptionVo;
import com.gx.vo.FittingsInfoVo;

public interface ICommonDao {
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
	public List<SYS_MaintenanceCus> seleceMaintenanceCus(String licenseCode,int startIndex,int pageSize);
	/**
	 * 查询维修客户总数
	 * @param LicenseCode
	 * @return
	 */
	public int seleceMaintenanceCusCount(String licenseCode);
	/**
	 * 新增/修改维修客户
	 * @param maintenanceCus
	 * @return
	 */
	public int updateMaintenanceCus(SYS_MaintenanceCus maintenanceCus);
	/**
	 * 表格修理项目下拉框改变查询
	 * @param RepairItemID 修理项目ID
	 * @return
	 */
	public  SYS_RepairItem selectRepairItemChange(int repairItemID);
	/**
	 * 表格其他费用下拉框改变查询
	 * @param ExpensesID 其他费用ID
	 */
	public SYS_Expenses selectExpensesChange(int expensesID); 
	/**
	 * 查询修理大类
	 * @return SYS_FittingsType
	 */
	public List<SYS_FittingsType> selectFittingsType();
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
            String infoThree, String infoFour,int fittingsTypeID,int startIndex,int pageSize) ;
	/**
	 * 查询配件条数
	 * @param infoOne
	 * @param infoTow
	 * @param infoThree
	 * @param infoFour
	 * @param fittingsTypeID
	 * @return
	 */
	public int seleceFittingsInfoCount(String infoOne, String infoTow,
            String infoThree, String infoFour,int fittingsTypeID);
	/**
	 * 新增/修改配件信息
	 * @param sys_FittingsInfo
	 * @return
	 */
	public int updateFittingsInfo(SYS_FittingsInfo sys_FittingsInfo);
	/**
	 * 判断是否存在配件编码
	 * @param FittingsCode
	 * @return 
	 */
	public SYS_FittingsInfo judgingFittingsCode(String fittingsCode);
	
}
