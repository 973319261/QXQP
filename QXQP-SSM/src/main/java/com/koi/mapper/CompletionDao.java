package com.koi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koi.vo.ReceptionVo;


public interface CompletionDao {
	/**
	 * 查询客户接待单据信息
	 * @param startDate
	 * @param endDate
	 * @param toCompletion
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ReceptionVo> selectReception(@Param("startDate")String startDate, @Param("endDate")String endDate, 
			@Param("toCompletion")String toCompletion,@Param("startIndex")Long startIndex,@Param("pageSize")Long pageSize);
	/**
	 * 查询客户接待单据信息总数
	 * @param startDate
	 * @param endDate
	 * @param toCompletion
	 * @return
	 */
	public Long selectReceptionCount(@Param("startDate")String startDate, @Param("endDate")String endDate, 
			@Param("toCompletion")String toCompletion);
	
}
