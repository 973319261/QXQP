package com.koi.web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import com.koi.po.SysFittingsInfo;
import com.koi.po.SysMaintenanceCus;
import com.koi.service.ICommonService;
import com.koi.util.Util;
import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport {
	@Autowired
	private ICommonService commonService;
	// 接收参数
	private String fittingsCode;
	private Integer fittingsTypeID;
	private String infoOne;
	private String infoTow;
	private String infoThree;
	private String infoFour;
	private Integer expensesID;
	private Integer repairItemID;
	private String licenseCode;
	private String type;
	private Long pageSize;
	private Long curPage;
	private File picture;
	private SysFittingsInfo sysFittingsInfo;
	private SysMaintenanceCus sysMaintenanceCus;
	// 返回参数
	private Object result;// 返回json值

	// 判断是否存在配件编码
	public String judgingFittingsCode() {
		result = commonService.judgingFittingsCode(fittingsCode);
		return "result";
	}

	// 新增配件信息
	public String updateFittingsInfo() {
		result = commonService.updateFittingsInfo(sysFittingsInfo,
				Util.getBytes(picture));
		return "result";
	}

	// 查询配件信息
	public String seleceFittingsInfo() {
		result = commonService.seleceFittingsInfo(infoOne, infoTow, infoThree,
				infoFour, fittingsTypeID, curPage, pageSize);
		return "result";
	}

	// 查询配件类型
	public String selectFittingsType() {
		result = commonService.selectFittingsType();
		return "result";
	}

	// 表格其他费用下拉框改变查询
	public String selectExpensesChange() {
		result = commonService.selectExpensesChange(expensesID);
		return "result";
	}

	// 表格修理项目下拉框改变查询
	public String selectRepairItemChange() {
		result = commonService.selectRepairItemChange(repairItemID);
		return "result";
	}

	// 生成客户编号
	public String customerNum() {
		result = commonService.customerNum();
		return "result";
	}

	// 新增/修改维修客户
	public String updateMaintenanceCus() {
		result = commonService.updateMaintenanceCus(sysMaintenanceCus);
		return "result";
	}

	// 查询维修客户
	public String seleceMaintenanceCus() {
		result = commonService.seleceMaintenanceCus(licenseCode, curPage,
				pageSize);
		return "result";
	}

	// 绑定下拉框
	public String selectAppendOption() {
		result = commonService.selectAppendOption(type);
		return "result";
	}

	public Object getResult() {
		return result;
	}

	public void setFittingsCode(String fittingsCode) {
		this.fittingsCode = fittingsCode;
	}

	public void setFittingsTypeID(Integer fittingsTypeID) {
		this.fittingsTypeID = fittingsTypeID;
	}

	public void setInfoOne(String infoOne) {
		this.infoOne = infoOne;
	}

	public void setInfoTow(String infoTow) {
		this.infoTow = infoTow;
	}

	public void setInfoThree(String infoThree) {
		this.infoThree = infoThree;
	}

	public void setInfoFour(String infoFour) {
		this.infoFour = infoFour;
	}

	public void setExpensesID(Integer expensesID) {
		this.expensesID = expensesID;
	}

	public void setRepairItemID(Integer repairItemID) {
		this.repairItemID = repairItemID;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public void setSysFittingsInfo(SysFittingsInfo sysFittingsInfo) {
		this.sysFittingsInfo = sysFittingsInfo;
	}

	public SysFittingsInfo getSysFittingsInfo() {
		return sysFittingsInfo;
	}

	public void setSysMaintenanceCus(SysMaintenanceCus sysMaintenanceCus) {
		this.sysMaintenanceCus = sysMaintenanceCus;
	}

	public SysMaintenanceCus getSysMaintenanceCus() {
		return sysMaintenanceCus;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

}
