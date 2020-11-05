package com.koi.po;

import java.sql.Timestamp;

/**
 * PwSalBalance entity. @author MyEclipse Persistence Tools
 */

public class PwSalBalance implements java.io.Serializable {

	// Fields

	private Integer salBalanceId;
	private Integer salesCustomerId;
	private Integer paymentId;
	private String receipt;
	private String accountNumber;
	private Double currentMany;
	private String auditor;
	private Timestamp receiptDate;
	private String operator;
	private Boolean toAudit;
	private Timestamp auditDate;
	private String remark;

	// Constructors

	/** default constructor */
	public PwSalBalance() {
	}

	/** minimal constructor */
	public PwSalBalance(Integer salesCustomerId, Integer paymentId,
			String receipt) {
		this.salesCustomerId = salesCustomerId;
		this.paymentId = paymentId;
		this.receipt = receipt;
	}

	/** full constructor */
	public PwSalBalance(Integer salesCustomerId, Integer paymentId,
			String receipt, String accountNumber, Double currentMany,
			String auditor, Timestamp receiptDate, String operator,
			Boolean toAudit, Timestamp auditDate, String remark) {
		this.salesCustomerId = salesCustomerId;
		this.paymentId = paymentId;
		this.receipt = receipt;
		this.accountNumber = accountNumber;
		this.currentMany = currentMany;
		this.auditor = auditor;
		this.receiptDate = receiptDate;
		this.operator = operator;
		this.toAudit = toAudit;
		this.auditDate = auditDate;
		this.remark = remark;
	}

	// Property accessors

	public Integer getSalBalanceId() {
		return this.salBalanceId;
	}

	public void setSalBalanceId(Integer salBalanceId) {
		this.salBalanceId = salBalanceId;
	}

	public Integer getSalesCustomerId() {
		return this.salesCustomerId;
	}

	public void setSalesCustomerId(Integer salesCustomerId) {
		this.salesCustomerId = salesCustomerId;
	}

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
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

}