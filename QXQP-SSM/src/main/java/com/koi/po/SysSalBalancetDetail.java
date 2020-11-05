package com.koi.po;

import java.sql.Timestamp;

/**
 * SysSalBalancetDetail entity. @author MyEclipse Persistence Tools
 */

public class SysSalBalancetDetail implements java.io.Serializable {

	// Fields

	private Integer salBalancetDetailId;
	private Integer salBalanceId;
	private Integer documentsTypeId;
	private Integer balanceStateId;
	private Boolean toCleared;
	private String singleNumber;
	private Timestamp documentsDate;
	private Double shouldAmount;
	private Double paidAmount;
	private Double superioAmountr;
	private Double thisSuperioAmountr;
	private Double thisShouldAmount;

	// Constructors

	/** default constructor */
	public SysSalBalancetDetail() {
	}

	/** full constructor */
	public SysSalBalancetDetail(Integer salBalanceId, Integer documentsTypeId,
			Integer balanceStateId, Boolean toCleared, String singleNumber,
			Timestamp documentsDate, Double shouldAmount, Double paidAmount,
			Double superioAmountr, Double thisSuperioAmountr,
			Double thisShouldAmount) {
		this.salBalanceId = salBalanceId;
		this.documentsTypeId = documentsTypeId;
		this.balanceStateId = balanceStateId;
		this.toCleared = toCleared;
		this.singleNumber = singleNumber;
		this.documentsDate = documentsDate;
		this.shouldAmount = shouldAmount;
		this.paidAmount = paidAmount;
		this.superioAmountr = superioAmountr;
		this.thisSuperioAmountr = thisSuperioAmountr;
		this.thisShouldAmount = thisShouldAmount;
	}

	// Property accessors

	public Integer getSalBalancetDetailId() {
		return this.salBalancetDetailId;
	}

	public void setSalBalancetDetailId(Integer salBalancetDetailId) {
		this.salBalancetDetailId = salBalancetDetailId;
	}

	public Integer getSalBalanceId() {
		return this.salBalanceId;
	}

	public void setSalBalanceId(Integer salBalanceId) {
		this.salBalanceId = salBalanceId;
	}

	public Integer getDocumentsTypeId() {
		return this.documentsTypeId;
	}

	public void setDocumentsTypeId(Integer documentsTypeId) {
		this.documentsTypeId = documentsTypeId;
	}

	public Integer getBalanceStateId() {
		return this.balanceStateId;
	}

	public void setBalanceStateId(Integer balanceStateId) {
		this.balanceStateId = balanceStateId;
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

}