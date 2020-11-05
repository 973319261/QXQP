package com.koi.dao;

import java.util.List;

import com.koi.vo.ReceptionVo;


public interface ICompletionDao {
	/**
	 * 查询客户接待单据信息
	 * @param startDate
	 * @param endDate
	 * @param toCompletion
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ReceptionVo> selectReception(String startDate, String endDate, String toCompletion,Long startIndex,Long pageSize);
	/**
	 * 查询客户接待单据信息总数
	 * @param startDate
	 * @param endDate
	 * @param toCompletion
	 * @return
	 */
	public Long selectReceptionCount(String startDate, String endDate, String toCompletion);
	/**
	 * 完工状态修改
	 * @param listReception
	 * @return
	 */
	public int updateCompletionTrue(List<ReceptionVo> listReception);
	public int updateCompletionFalse(List<ReceptionVo> listReception);
}
