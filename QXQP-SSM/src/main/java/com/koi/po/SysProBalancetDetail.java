package com.koi.po;

import java.sql.Timestamp;

/**
 * SysProBalancetDetail entity. @author MyEclipse Persistence Tools
 */

public class SysProBalancetDetail implements java.io.Serializable {

	// Fields

	private Integer proBalancetDetailId;
	private Integer balanceStateId;
	private Integer documentsTypeId;
	private Integer proBalanceId;
	private Boolean toCleared;
	private String singleNumber;
	private Timestamp documentsDate;
	private Double shouldAmount;
	private Double paidAmount;
	private Double superioAmountr;
	private String remark;
	private Double thisSuperioAmountr;
	private Double thisShouldAmount;
	private Integer paymentId;

	// Constructors

	/** default constructor */
	public SysProBalancetDetail() {
	}

	/** minimal constructor */
	public SysProBalancetDetail(Integer proBalanceId, String singleNumber,
			Integer paymentId) {
		this.proBalanceId = proBalanceId;
		this.singleNumber = singleNumber;
		this.paymentId = paymentId;
	}

	/** full constructor */
	public SysProBalancetDetail(Integer balanceStateId,
			Integer documentsTypeId, Integer proBalanceId, Boolean toCleared,
			String singleNumber, Timestamp documentsDate, Double shouldAmount,
			Double paidAmount, Double superioAmountr, String remark,
			Double thisSuperioAmountr, Double thisShouldAmount,
			Integer paymentId) {
		this.balanceStateId = balanceStateId;
		this.documentsTypeId = documentsTypeId;
		this.proBalanceId = proBalanceId;
		this.toCleared = toCleared;
		this.singleNumber = singleNumber;
		this.documentsDate = documentsDate;
		this.shouldAmount = shouldAmount;
		this.paidAmount = paidAmount;
		this.superioAmountr = superioAmountr;
		this.remark = remark;
		this.thisSuperioAmountr = thisSuperioAmountr;
		this.thisShouldAmount = thisShouldAmount;
		this.paymentId = paymentId;
	}

	// Property accessors

	public Integer getProBalancetDetailId() {
		return this.proBalancetDetailId;
	}

	public void setProBalancetDetailId(Integer proBalancetDetailId) {
		this.proBalancetDetailId = proBalancetDetailId;
	}

	public Integer getBalanceStateId() {
		return this.balanceStateId;
	}

	public void setBalanceStateId(Integer balanceStateId) {
		this.balanceStateId = balanceStateId;
	}

	public Integer getDocumentsTypeId() {
		return this.documentsTypeId;
	}

	public void setDocumentsTypeId(Integer documentsTypeId) {
		this.documentsTypeId = documentsTypeId;
	}

	public Integer getProBalanceId() {
		return this.proBalanceId;
	}

	public void setProBalanceId(Integer proBalanceId) {
		this.proBalanceId = proBalanceId;
	}

	public Boolean getToCleared() {
		return this.toCleared;
	}

	public void setToCleared(Boolean toCleared) {
		this.toCleared = toCleared;
	}

	public String getSingleNumber() {
		return this.singleNumber;
	}

	public void setSingleNumber(String singleNumber) {
		this.singleNumber = singleNumber;
	}

	public Timestamp getDocumentsDate() {
		return this.documentsDate;
	}

	public void setDocumentsDate(Timestamp documentsDate) {
		this.documentsDate = documentsDate;
	}

	public Double getShouldAmount() {
		return this.shouldAmount;
	}

	public void setShouldAmount(Double shouldAmount) {
		this.shouldAmount = shouldAmount;
	}

	public Double getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getSuperioAmountr() {
		return this.superioAmountr;
	}

	public void setSuperioAmountr(Double superioAmountr) {
		this.superioAmountr = superioAmountr;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getThisSuperioAmountr() {
		return this.thisSuperioAmountr;
	}

	public void setThisSuperioAmountr(Double thisSuperioAmountr) {
		this.thisSuperioAmountr = thisSuperioAmountr;
	}

	public Double getThisShouldAmount() {
		return this.thisShouldAmount;
	}

	public void setThisShouldAmount(Double thisShouldAmount) {
		this.thisShouldAmount = thisShouldAmount;
	}

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

}