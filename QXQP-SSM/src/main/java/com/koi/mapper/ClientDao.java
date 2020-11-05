package com.koi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koi.po.SysFittingsInfo;
import com.koi.vo.FittingsInfoVo;
import com.koi.vo.MaintenanceCusVo;
import com.koi.vo.PreOtherCostDetailVo;
import com.koi.vo.PreProductDetailVo;
import com.koi.vo.PreRepairItemDetailVo;
import com.koi.vo.PredateVo;

public interface ClientDao {
	/**
	 * 根据手机号查询车主信息
	 * @param mobilePhone
	 * @return
	 */
	public MaintenanceCusVo selectMaintenanceCus(String mobilePhone);
	/**
	 * 查询订单总数
	 * @param licenseCode
	 * @param toAudit
	 * @return
	 */
	public int selectOrderCount(@Param("phone")String phone,@Param("toAudit")Integer toAudit);
	/**
	 * 查询订单信息
	 * @param licenseCode
	 * @param toAudit
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<PredateVo> selectOrder(@Param("phone")String phone,@Param("toAudit")Integer
			toAudit,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	/**
	 * 查询订单明细信息
	 * @param predateID
	 * @return
	 */
	public PredateVo selectPredate(int predateID);
	public List<PreRepairItemDetailVo> selectPreRepairItemDetail(int predateID);
	public List<PreProductDetailVo> selectPreProductDetail(int predateID);
	public List<PreOtherCostDetailVo> selectPreOtherCostDetail(int predateID);
	/**
	 * 查询配件总数
	 * @param value
	 * @return
	 */
	public int selectFittingsInfoCount(String value);
	/**
	 * 查询配件信息
	 * @param value
	 * @return
	 */
	public List<FittingsInfoVo> selectFittingsInfo(@Param("value")String value,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
}
