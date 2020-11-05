package com.koi.po;

/**
 * SysInsuranceDetail entity. @author MyEclipse Persistence Tools
 */

public class SysInsuranceDetail implements java.io.Serializable {

	// Fields

	private Integer insuranceDetailId;
	private Integer receptionId;
	private Integer insuranceComId;
	private String reportNum;
	private String policyNum;
	private Double policyMoney;
	private Double insuranceMoney;

	// Constructors

	/** default constructor */
	public SysInsuranceDetail() {
	}

	/** minimal constructor */
	public SysInsuranceDetail(Integer receptionId) {
		this.receptionId = receptionId;
	}

	/** full constructor */
	public SysInsuranceDetail(Integer receptionId, Integer insuranceComId,
			String reportNum, String policyNum, Double policyMoney,
			Double insuranceMoney) {
		this.receptionId = receptionId;
		this.insuranceComId = insuranceComId;
		this.reportNum = reportNum;
		this.policyNum = policyNum;
		this.policyMoney = policyMoney;
		this.insuranceMoney = insuranceMoney;
	}

	// Property accessors

	public Integer getInsuranceDetailId() {
		return this.insuranceDetailId;
	}

	public void setInsuranceDetailId(Integer insuranceDetailId) {
		this.insuranceDetailId = insuranceDetailId;
	}

	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}

	public Integer getInsuranceComId() {
		return this.insuranceComId;
	}

	public void setInsuranceComId(Integer insuranceComId) {
		this.insuranceComId = insuranceComId;
	}

	public String getReportNum() {
		return this.reportNum;
	}

	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}

	public String getPolicyNum() {
		return this.policyNum;
	}

	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}

	public Double getPolicyMoney() {
		return this.policyMoney;
	}

	public void setPolicyMoney(Double policyMoney) {
		this.policyMoney = policyMoney;
	}

	public Double getInsuranceMoney() {
		return this.insuranceMoney;
	}

	public void setInsuranceMoney(Double insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

}