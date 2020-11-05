package com.koi.po;

import java.sql.Timestamp;

/**
 * PwIncoming entity. @author MyEclipse Persistence Tools
 */

public class PwIncoming implements java.io.Serializable {

	// Fields

	private Integer incomingId;
	private Integer userId;
	private Integer warehouseId;
	private Integer suppliersId;
	private Integer documentsTypeId;
	private Integer balanceStateId;
	private String incomingNumber;
	private Boolean toAudit;
	private Timestamp bilDate;
	private Timestamp paymentDate;
	private String operator;
	private String auditor;
	private Timestamp auditDate;
	private Double aamount;
	private String rmark;
	private String indentNumber;

	// Constructors

	/** default constructor */
	public PwIncoming() {
	}

	/** minimal constructor */
	public PwIncoming(Integer userId, Integer warehouseId, Integer suppliersId,
			Integer balanceStateId, String incomingNumber, String operator) {
		this.userId = userId;
		this.warehouseId = warehouseId;
		this.suppliersId = suppliersId;
		this.balanceStateId = balanceStateId;
		this.incomingNumber = incomingNumber;
		this.operator = operator;
	}

	/** full constructor */
	public PwIncoming(Integer userId, Integer warehouseId, Integer suppliersId,
			Integer documentsTypeId, Integer balanceStateId,
			String incomingNumber, Boolean toAudit, Timestamp bilDate,
			Timestamp paymentDate, String operator, String auditor,
			Timestamp auditDate, Double aamount, String rmark,
			String indentNumber) {
		this.userId = userId;
		this.warehouseId = warehouseId;
		this.suppliersId = suppliersId;
		this.documentsTypeId = documentsTypeId;
		this.balanceStateId = balanceStateId;
		this.incomingNumber = incomingNumber;
		this.toAudit = toAudit;
		this.bilDate = bilDate;
		this.paymentDate = paymentDate;
		this.operator = operator;
		this.auditor = auditor;
		this.auditDate = auditDate;
		this.aamount = aamount;
		this.rmark = rmark;
		this.indentNumber = indentNumber;
	}

	// Property accessors

	public Integer getIncomingId() {
		return this.incomingId;
	}

	public void setIncomingId(Integer incomingId) {
		this.incomingId = incomingId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getSuppliersId() {
		return this.suppliersId;
	}

	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
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

	public String getIncomingNumber() {
		return this.incomingNumber;
	}

	public void setIncomingNumber(String incomingNumber) {
		this.incomingNumber = incomingNumber;
	}

	public Boolean getToAudit() {
		return this.toAudit;
	}

	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}

	public Timestamp getBilDate() {
		return this.bilDate;
	}

	public void setBilDate(Timestamp bilDate) {
		this.bilDate = bilDate;
	}

	public Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAuditor() {
		return this.auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Timestamp getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public Double getAamount() {
		return this.aamount;
	}

	public void setAamount(Double aamount) {
		this.aamount = aamount;
	}

	public String getRmark() {
		return this.rmark;
	}

	public void setRmark(String rmark) {
		this.rmark = rmark;
	}

	public String getIndentNumber() {
		return this.indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}

}