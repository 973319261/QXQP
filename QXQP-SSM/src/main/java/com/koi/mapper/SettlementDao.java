package com.koi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koi.po.SysCollageDetai;


public interface SettlementDao {
	/**
	 * 审核或者反审核预约单
	 * @param PredateID
	 * @return
	 */
	public int toAudit(@Param("toAudit")boolean toAudit, @Param("receptionID")int receptionID);
	/**
	 * 查询领料明细
	 * @param receptionID
	 * @return
	 */
	public List<SysCollageDetai> selectCollageDetai(int receptionID);	
}
