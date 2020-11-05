package com.koi.service;

import java.util.List;

import com.koi.vo.ReceptionVo;


public interface ICompletionService {
	/**
	 * 查询客户接待单据信息
	 * @param startDate
	 * @param endDate
	 * @param toCompletion
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Object selectReception(String startDate, String endDate, String toCompletion,Long curPage,Long pageSize);
	/**
	 * 完工状态修改
	 * @param listReception
	 * @return
	 */
	public int updateCompletionTrue(String reception);
	public int updateCompletionFalse(String reception);
}
