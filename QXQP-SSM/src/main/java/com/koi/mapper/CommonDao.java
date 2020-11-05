package com.koi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.koi.po.PwBalance;
import com.koi.po.PwReception;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysExpenses;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysFittingsType;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysMaintenanceCus;
import com.koi.po.SysRecOtherCostDetail;
import com.koi.po.SysRecProductDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysRepairItem;
import com.koi.po.SysThreePacksDetail;
import com.koi.vo.AppendOptionVo;
import com.koi.vo.FittingsInfoVo;


public interface CommonDao{
	/**
	 * 查询下拉框
	 * @param type 表名
	 * @return
	 */
	public List<AppendOptionVo>  selectAppendOption(@Param("params")Map<String, String> map);
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
	public List<SysMaintenanceCus> seleceMaintenanceCus(@Param("licenseCode") String licenseCode,@Param("startIndex")Long startIndex,@Param("pageSize")Long pageSize);
	/**
	 * 查询维修客户总数
	 * @param LicenseCode
	 * @return
	 */
	public Long seleceMaintenanceCusCount(@Param("licenseCode")String licenseCode);
	/**
	 * 新增维修客户
	 * @param maintenanceCus
	 * @return
	 */
	public int insertMaintenanceCus(SysMaintenanceCus maintenanceCus);
	/**
	 * 修改维修客户
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
	public List<FittingsInfoVo> seleceFittingsInfo(@Param("infoOne") String infoOne, @Param("infoTow")String infoTow,
			@Param("infoThree")String infoThree, @Param("infoFour")String infoFour,@Param("fittingsTypeID")int fittingsTypeID,
			@Param("startIndex")Long startIndex,@Param("pageSize")Long pageSize) ;
	/**
	 * 查询配件条数
	 * @param infoOne
	 * @param infoTow
	 * @param infoThree
	 * @param infoFour
	 * @param fittingsTypeID
	 * @return
	 */
	public Long seleceFittingsInfoCount(@Param("infoOne")String infoOne, @Param("infoTow")String infoTow,
			@Param("infoThree")String infoThree, @Param("infoFour")String infoFour,@Param("fittingsTypeID")int fittingsTypeID);
	/**
	 * 新增配件信息
	 * @param sys_FittingsInfo
	 * @return
	 */
	public int insertFittingsInfo(SysFittingsInfo sys_FittingsInfo);
	/**
	 * 修改配置信息
	 * @param sysFittingsInfo
	 * @return
	 */
	public int updateFittingsInfo (SysFittingsInfo sysFittingsInfo);
	/**
	 * 判断是否存在配件编码
	 * @param FittingsCode
	 * @return 
	 */
	public SysFittingsInfo selectFittingsInfo(String fittingsCode);
	/**
	 * 查询订单
	 * @param receptionID
	 * @return
	 */
	public PwReception selectReceptionById(int receptionID);
	/**
	 * 新增订单
	 * @param pwReception
	 * @return
	 */
	public int insertReception(PwReception pwReception);
	/**
	 * 修改订单
	 * @param listReception
	 * @return
	 */
	public int updateReception(PwReception pwReception);
	/**
	 * 删除订单
	 * @param receptionID
	 * @return
	 */
	public boolean deleteReception(int receptionID);
	 /**
	  * 修理项目明细表操作
	  */
	public List<SysRecRepairItemDetail> selectRecRepairItemDetail(int receptionID);
	public SysRecRepairItemDetail selectRecRepairItemDetailById(int recRepairItemDetailID);
	public int insertRecRepairItemDetail(SysRecRepairItemDetail sysRecRepairItemDetail);
	public int updateRecRepairItemDetail(SysRecRepairItemDetail sysRecRepairItemDetail);
	public int deleteRecRepairItemDetailById(int recRepairItemDetailID);
	public int deleteRecRepairItemDetail(int receptionID);
	 /**
	  *配件明细表操作
	  */
	public List<SysRecProductDetail> selectRecProductDetail(int receptionID);
	public int insertRecProductDetail(SysRecProductDetail sysRecProductDetail);
	public int updateRecProductDetail(SysRecProductDetail sysRecProductDetail);
	public int deleteRecProductDetailById(int recProductDetailID);
	public int deleteRecProductDetail(int receptionID);
	 /**
	  *费用明细表操作
	  */
	public List<SysRecOtherCostDetail> selectRecOtherCostDetail(int receptionID);
	public int insertRecOtherCostDetail(SysRecOtherCostDetail sysRecOtherCostDetail);
	public int updateRecOtherCostDetail(SysRecOtherCostDetail sysRecOtherCostDetail);
	public int deleteRecOtherCostDetailById(int recOtherCostDetailID);
	public int deleteRecOtherCostDetail(int receptionID);
	/**
	 * 保险索赔明细表操作
	 * @param sysInsuranceDetail
	 * @return
	 */
	public SysInsuranceDetail selectInsuranceDetailById(int insuranceDetailID);
	public List<SysInsuranceDetail> selectInsuranceDetail(int receptionID);
	public int insertInsuranceDetail(SysInsuranceDetail sysInsuranceDetail);
	public int updateInsuranceDetail(SysInsuranceDetail sysInsuranceDetail);
	public int deleteInsuranceDetail(int receptionID);
	/**
	 *  三包索赔明细表操作
	 * @param threePacksDetail
	 * @return
	 */
	public SysThreePacksDetail selectThreePacksDetailById(int threePacksDetailID);
	public List<SysThreePacksDetail> selectThreePacksDetail(int receptionID);
	public int insertThreePacksDetail(SysThreePacksDetail threePacksDetail);
	public int updateThreePacksDetail(SysThreePacksDetail threePacksDetail);
	public int deleteThreePacksDetail(int receptionID);
	/**
	 * 查询领料明细
	 * @param collageDetaiID
	 * @return
	 */
	public SysCollageDetai selectCollageDetaiById(int collageDetaiID);
	/**
	 * 修改领料明细
	 * @param sysCollageDetai
	 * @return
	 */
	public int insertCollageDetai(SysCollageDetai sysCollageDetai);
	/**
	 * 修改领料明细
	 * @param sysCollageDetai
	 * @return
	 */
	public int updateCollageDetai(SysCollageDetai sysCollageDetai);
	/**
	 * 删除领料明细
	 * @param collageDetaiID
	 * @return
	 */
	public int deleteCollageDetaiById(int collageDetaiID);
	public int deleteCollageDetai(int collageID);
	/**
	 * 查询单据付款状态
	 * @param businessNum
	 * @return
	 */
	public PwBalance selectBalance(String businessNum);
	/**
	 * 查询付款数据
	 * @param balanceID
	 * @return
	 */
	public PwBalance selectBalanceById(int balanceID);
	/**
	 * 新增结算单
	 * @param pwBalance
	 * @return
	 */
	public int insertBalance(PwBalance pwBalance);
	/**
	 * 修改结算单
	 * @param pwBalance
	 * @return
	 */
	public int updateBalance(PwBalance pwBalance);
}

