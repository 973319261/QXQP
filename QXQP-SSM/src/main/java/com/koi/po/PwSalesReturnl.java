package com.koi.po;

import java.sql.Timestamp;

/**
 * PwSalesReturnl entity. @author MyEclipse Persistence Tools
 */

public class PwSalesReturnl implements java.io.Serializable {

	// Fields

	private Integer salesReturnlId;
	private Integer documentsTypeId;
	private Integer balanceStateId;
	private Integer salesCustomerId;
	private String oddseats;
	private String openDate;
	private String returnmode;
	private String operator;
	private Boolean toAudit;
	private String auditor;
	private Double theamount;
	private String stockremoval;
	private Timestamp auditDate;
	private String remark;

	// Constructors

	/** default constructor */
	public PwSalesReturnl() {
	}

	/** minimal constructor */
	public PwSalesReturnl(Integer salesCustomerId, String oddseats) {
		this.salesCustomerId = salesCustomerId;
		this.oddseats = oddseats;
	}

	/** full constructor */
	public PwSalesReturnl(Integer documentsTypeId, Integer balanceStateId,
			Integer salesCustomerId, String oddseats, String openDate,
			String returnmode, String operator, Boolean toAudit,
			String auditor, Double theamount, String stockremoval,
			Timestamp auditDate, String remark) {
		this.documentsTypeId = documentsTypeId;
		this.balanceStateId = balanceStateId;
		this.salesCustomerId = salesCustomerId;
		this.oddseats = oddseats;
		this.openDate = openDate;
		this.returnmode = returnmode;
		this.operator = operator;
		this.toAudit = toAudit;
		this.auditor = auditor;
		this.theamount = theamount;
		this.stockremoval = stockremoval;
		this.auditDate = auditDate;
		this.remark = remark;
	}

	// Property accessors

	public Integer getSalesReturnlId() {
		return this.salesReturnlId;
	}

	public void setSalesReturnlId(Integer salesReturnlId) {
		this.salesReturnlId = salesReturnlId;
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

	public Integer getSalesCustomerId() {
		return this.salesCustomerId;
	}

	public void setSalesCustomerId(Integer salesCustomerId) {
		this.salesCustomerId = salesCustomerId;
	}

	public String getOddseats() {
		return this.oddseats;
	}

	public void setOddseats(String oddseats) {
		this.oddseats = oddseats;
	}

	public String getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getReturnmode() {
		return this.returnmode;
	}

	public void setReturnmode(String returnmode) {
		this.returnmode = returnmode;
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

	public String getAuditor() {
		return this.auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Double getTheamount() {
		return this.theamount;
	}

	public void setTheamount(Double theamount) {
		this.theamount = theamount;
	}

	public String getStockremoval() {
		return this.stockremoval;
	}

	public void setStockremoval(String stockremoval) {
		this.stockremoval = stockremoval;
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