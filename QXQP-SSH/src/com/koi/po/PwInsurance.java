package com.koi.po;

import java.sql.Timestamp;

/**
 * PwInsurance entity. @author MyEclipse Persistence Tools
 */

public class PwInsurance implements java.io.Serializable {

	// Fields

	private Integer insuranceId;
	private Integer insuranceDetailId;
	private String insuranceNum;
	private Timestamp openDate;
	private String balanceDate;
	private String claimsStaff;
	private Boolean toTicket;
	private String remark;
	private Double amount;
	private Boolean toAudit;

	// Constructors

	/** default constructor */
	public PwInsurance() {
	}

	/** full constructor */
	public PwInsurance(Integer insuranceDetailId, String insuranceNum,
			Timestamp openDate, String balanceDate, String claimsStaff,
			Boolean toTicket, String remark, Double amount, Boolean toAudit) {
		this.insuranceDetailId = insuranceDetailId;
		this.insuranceNum = insuranceNum;
		this.openDate = openDate;
		this.balanceDate = balanceDate;
		this.claimsStaff = claimsStaff;
		this.toTicket = toTicket;
		this.remark = remark;
		this.amount = amount;
		this.toAudit = toAudit;
	}

	// Property accessors

	public Integer getInsuranceId() {
		return this.insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Integer getInsuranceDetailId() {
		return this.insuranceDetailId;
	}

	public void setInsuranceDetailId(Integer insuranceDetailId) {
		this.insuranceDetailId = insuranceDetailId;
	}

	public String getInsuranceNum() {
		return this.insuranceNum;
	}

	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public Timestamp getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}

	public String getBalanceDate() {
		return this.balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getClaimsStaff() {
		return this.claimsStaff;
	}

	public void setClaimsStaff(String claimsStaff) {
		this.claimsStaff = claimsStaff;
	}

	public Boolean getToTicket() {
		return this.toTicket;
	}

	public void setToTicket(Boolean toTicket) {
		this.toTicket = toTicket;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getToAudit() {
		return this.toAudit;
	}

	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}

}