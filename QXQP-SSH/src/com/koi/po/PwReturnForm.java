package com.koi.po;

import java.sql.Timestamp;

/**
 * PwReturnForm entity. @author MyEclipse Persistence Tools
 */

public class PwReturnForm implements java.io.Serializable {

	// Fields

	private Integer returnFormId;
	private Integer suppliersId;
	private Integer warehouseId;
	private Integer userId;
	private Integer documentsTypeId;
	private Integer balanceStateId;
	private String returnNumber;
	private String maker;
	private String singleDate;
	private String auditor;
	private Timestamp auditDate;
	private Double amount;
	private Boolean toAudit;
	private String remark;
	private Timestamp paymentDate;
	private String sheetDate;
	private String incomingNumber;

	// Constructors

	/** default constructor */
	public PwReturnForm() {
	}

	/** minimal constructor */
	public PwReturnForm(Integer suppliersId, Integer warehouseId, Integer userId) {
		this.suppliersId = suppliersId;
		this.warehouseId = warehouseId;
		this.userId = userId;
	}

	/** full constructor */
	public PwReturnForm(Integer suppliersId, Integer warehouseId,
			Integer userId, Integer documentsTypeId, Integer balanceStateId,
			String returnNumber, String maker, String singleDate,
			String auditor, Timestamp auditDate, Double amount,
			Boolean toAudit, String remark, Timestamp paymentDate,
			String sheetDate, String incomingNumber) {
		this.suppliersId = suppliersId;
		this.warehouseId = warehouseId;
		this.userId = userId;
		this.documentsTypeId = documentsTypeId;
		this.balanceStateId = balanceStateId;
		this.returnNumber = returnNumber;
		this.maker = maker;
		this.singleDate = singleDate;
		this.auditor = auditor;
		this.auditDate = auditDate;
		this.amount = amount;
		this.toAudit = toAudit;
		this.remark = remark;
		this.paymentDate = paymentDate;
		this.sheetDate = sheetDate;
		this.incomingNumber = incomingNumber;
	}

	// Property accessors

	public Integer getReturnFormId() {
		return this.returnFormId;
	}

	public void setReturnFormId(Integer returnFormId) {
		this.returnFormId = returnFormId;
	}

	public Integer getSuppliersId() {
		return this.suppliersId;
	}

	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getReturnNumber() {
		return this.returnNumber;
	}

	public void setReturnNumber(String returnNumber) {
		this.returnNumber = returnNumber;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getSingleDate() {
		return this.singleDate;
	}

	public void setSingleDate(String singleDate) {
		this.singleDate = singleDate;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getSheetDate() {
		return this.sheetDate;
	}

	public void setSheetDate(String sheetDate) {
		this.sheetDate = sheetDate;
	}

	public String getIncomingNumber() {
		return this.incomingNumber;
	}

	public void setIncomingNumber(String incomingNumber) {
		this.incomingNumber = incomingNumber;
	}

}