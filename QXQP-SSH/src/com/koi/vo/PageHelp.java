package com.koi.vo;

import java.io.Serializable;

public class PageHelp implements Serializable{
	private Long curPage;// 当前页
	private Long pageSize;// 页面大小
	private Long startIndex = 1L;// 开始索引
	
	public PageHelp() {
		// TODO Auto-generated method stub
		super();
	}
	
	public PageHelp(Long curPage, Long pageSize) {
		super();
		this.curPage = curPage;
		this.pageSize = pageSize;
	}

	public Long getCurPage() {
		return curPage;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {

		pageSize *= curPage;// 结束索引
	}

	public Long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		if (curPage > 1) {
			startIndex += pageSize;
		}
	}
}
