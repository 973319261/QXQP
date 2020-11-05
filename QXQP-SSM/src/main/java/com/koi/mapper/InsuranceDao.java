package com.koi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koi.po.PwInsurance;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysInsuranceDetail;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.vo.InsuranceDetailVo;
import com.koi.vo.InsuranceVo;


public interface InsuranceDao{
	/**
	 * 生成预约单号
	 * 
	 * @param d
	 * @return
	 */
	public int insuranceNum(String d);
	/**
	 * 维修单号查询
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<InsuranceDetailVo> selectDanHao(@Param("maintenanceNum")String maintenanceNum,@Param("carNum") String carNum,
			@Param("documentStateID")int documentStateID, @Param("balanceStateID")int balanceStateID, @Param("toAudit")String toAudit,
			@Param("startIndex")Long startIndex, @Param("pageSize")Long pageSize);
	/**
	 * 维修单号总数
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @return
	 */
	public Long selectDanHaoCount(@Param("maintenanceNum")String maintenanceNum, @Param("carNum")String carNum,
			@Param("documentStateID")int documentStateID, @Param("balanceStateID")int balanceStateID, @Param("toAudit")String toAudit);
	/**
	 * 三包单据查询
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<InsuranceVo> selectInsurance(@Param("maintenanceNum")String maintenanceNum, @Param("carNum")String carNum,
			@Param("documentStateID")int documentStateID,@Param("balanceStateID") int balanceStateID,@Param("toAudit") String toAudit,
			@Param("startIndex")Long startIndex, @Param("pageSize")Long pageSize);
	/**
	 * 单据查询总数
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @return
	 */
	public Long selectInsuranceCount(@Param("maintenanceNum")String maintenanceNum, @Param("carNum")String carNum,
			@Param("documentStateID")int documentStateID, @Param("balanceStateID")int balanceStateID, @Param("toAudit")String toAudit);
	/**
	 * 查询是否存在索赔单据
	 * @param insuranceDetailID
	 * @return
	 */
	public InsuranceVo selectInsuranceData(int insuranceDetailID);
	/**
	 * 
	 * @param receptionID
	 * @return
	 */
	public InsuranceDetailVo selectInsurances(int receptionID);
	
	public List<SysRecRepairItemDetail> selectReceptionDetail(int receptionID);
	public List<SysCollageDetai> selectCollageDetai(int receptionID);
	/**
	 * 保险索赔表操作
	 * @return
	 */
	public PwInsurance selectInsuranceById(int insuranceID);
	public int insertInsurance(PwInsurance pwInsurance);
	public int updateInsurance(PwInsurance pwInsurance);
	public boolean delectInsurance(int insuranceID);


	
	
}
