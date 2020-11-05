package com.koi.po;

import java.sql.Timestamp;

/**
 * PwThreePacks entity. @author MyEclipse Persistence Tools
 */

public class PwThreePacks implements java.io.Serializable {

	// Fields

	private Integer threePacksId;
	private Integer threePacksDetailId;
	private String insuranceNum;
	private Timestamp openDate;
	private String balanceDate;
	private String claimsStaff;
	private Boolean toTicket;
	private String selfCoding;
	private String remark;
	private Double amount;
	private Boolean toAudit;
	private String operator;

	// Constructors

	/** default constructor */
	public PwThreePacks() {
	}

	/** minimal constructor */
	public PwThreePacks(Integer threePacksDetailId, String insuranceNum,
			Timestamp openDate, Boolean toTicket) {
		this.threePacksDetailId = threePacksDetailId;
		this.insuranceNum = insuranceNum;
		this.openDate = openDate;
		this.toTicket = toTicket;
	}

	/** full constructor */
	public PwThreePacks(Integer threePacksDetailId, String insuranceNum,
			Timestamp openDate, String balanceDate, String claimsStaff,
			Boolean toTicket, String selfCoding, String remark, Double amount,
			Boolean toAudit, String operator) {
		this.threePacksDetailId = threePacksDetailId;
		this.insuranceNum = insuranceNum;
		this.openDate = openDate;
		this.balanceDate = balanceDate;
		this.claimsStaff = claimsStaff;
		this.toTicket = toTicket;
		this.selfCoding = selfCoding;
		this.remark = remark;
		this.amount = amount;
		this.toAudit = toAudit;
		this.operator = operator;
	}

	// Property accessors

	public Integer getThreePacksId() {
		return this.threePacksId;
	}

	public void setThreePacksId(Integer threePacksId) {
		this.threePacksId = threePacksId;
	}

	public Integer getThreePacksDetailId() {
		return this.threePacksDetailId;
	}

	public void setThreePacksDetailId(Integer threePacksDetailId) {
		this.threePacksDetailId = threePacksDetailId;
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

	public String getSelfCoding() {
		return this.selfCoding;
	}

	public void setSelfCoding(String selfCoding) {
		this.selfCoding = selfCoding;
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

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}