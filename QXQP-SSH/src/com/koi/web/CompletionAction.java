package com.koi.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.koi.service.ICompletionService;

public class CompletionAction {
	@Autowired
	private ICompletionService completionService;
	// 接收参数
	private String receptionVo;
	private String startDate;
	private String endDate;
	private String toCompletion;
	private Long pageSize;
	private Long curPage;
	// 返回参数
	private Object result;

	// 完工状态修改
	public String updateCompletionFalse() {
		result = completionService.updateCompletionFalse(receptionVo);
		return "result";
	}

	// 完工状态修改
	public String updateCompletionTrue() {
		result = completionService.updateCompletionTrue(receptionVo);
		return "result";
	}

	// 查询客户接待单据信息
	public String selectReception() {
		result = completionService.selectReception(startDate, endDate,
				toCompletion, curPage, pageSize);
		return "result";
	}

	public Object getResult() {
		return result;
	}

	public void setReceptionVo(String receptionVo) {
		this.receptionVo = receptionVo;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setToCompletion(String toCompletion) {
		this.toCompletion = toCompletion;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

}
