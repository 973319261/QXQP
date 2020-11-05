package com.koi.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.koi.service.IRapidrepairService;

public class RapidrepairAction {
	@Autowired
	private IRapidrepairService rapidrepairService;
	// 接收测试
	private String pwReception;
	private String sysRecRepairItemDetail;
	private String sysCollageDetai;
	private String sysRecOtherCostDetail;
	private String sysInsuranceDetail;
	private String sysThreePacksDetail;
	// 返回参数
	private Object result;

	// 保存主页面信息
	public String updateListReceptione() {
		result = rapidrepairService.updateListReceptione(pwReception,
				sysRecRepairItemDetail, sysCollageDetai, sysRecOtherCostDetail,
				sysInsuranceDetail, sysThreePacksDetail);
		return "result";
	}

	// 生成单号
	public String maintenanceNum() {
		result = rapidrepairService.maintenanceNum();
		return "result";
	}

	public Object getResult() {
		return result;
	}

	public void setPwReception(String pwReception) {
		this.pwReception = pwReception;
	}

	public void setSysRecRepairItemDetail(String sysRecRepairItemDetail) {
		this.sysRecRepairItemDetail = sysRecRepairItemDetail;
	}

	public void setSysCollageDetai(String sysCollageDetai) {
		this.sysCollageDetai = sysCollageDetai;
	}

	public void setSysRecOtherCostDetail(String sysRecOtherCostDetail) {
		this.sysRecOtherCostDetail = sysRecOtherCostDetail;
	}

	public void setSysInsuranceDetail(String sysInsuranceDetail) {
		this.sysInsuranceDetail = sysInsuranceDetail;
	}

	public void setSysThreePacksDetail(String sysThreePacksDetail) {
		this.sysThreePacksDetail = sysThreePacksDetail;
	}

}
