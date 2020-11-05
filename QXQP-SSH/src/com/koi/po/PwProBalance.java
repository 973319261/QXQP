package com.koi.po;

import java.sql.Timestamp;

/**
 * PwProBalance entity. @author MyEclipse Persistence Tools
 */

public class PwProBalance implements java.io.Serializable {

	// Fields

	private Integer proBalanceId;
	private Integer suppliersId;
	private String receipt;
	private String accountNumber;
	private Double currentMany;
	private String auditor;
	private Timestamp receiptDate;
	private String operator;
	private Boolean toAudit;
	private Timestamp auditDate;
	private String remark;
	private Integer paymentId;

	// Constructors

	/** default constructor */
	public PwProBalance() {
	}

	/** minimal constructor */
	public PwProBalance(Integer suppliersId, String receipt, Boolean toAudit,
			Integer paymentId) {
		this.suppliersId = suppliersId;
		this.receipt = receipt;
		this.toAudit = toAudit;
		this.paymentId = paymentId;
	}

	/** full constructor */
	public PwProBalance(Integer suppliersId, String receipt,
			String accountNumber, Double currentMany, String auditor,
			Timestamp receiptDate, String operator, Boolean toAudit,
			Timestamp auditDate, String remark, Integer paymentId) {
		this.suppliersId = suppliersId;
		this.receipt = receipt;
		this.accountNumber = accountNumber;
		this.currentMany = currentMany;
		this.auditor = auditor;
		this.receiptDate = receiptDate;
		this.operator = operator;
		this.toAudit = toAudit;
		this.auditDate = auditDate;
		this.remark = remark;
		this.paymentId = paymentId;
	}

	// Property accessors

	public Integer getProBalanceId() {
		return this.proBalanceId;
	}

	public void setProBalanceId(Integer proBalanceId) {
		this.proBalanceId = proBalanceId;
	}

	public Integer getSuppliersId() {
		return this.suppliersId;
	}

	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
	}

	public String getReceipt() {
		return this.receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getCurrentMany() {
		return this.currentMany;
	}

	public void setCurrentMany(Double currentMany) {
		this.currentMany = currentMany;
	}

	public String getAuditor() {
		return this.auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Timestamp getReceiptDate() {
		return this.receiptDate;
	}

	public void setReceiptDate(Timestamp receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Boolean getToAudit() {
		return this.toAudit;
	}

	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}

	public Timestamp getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

}