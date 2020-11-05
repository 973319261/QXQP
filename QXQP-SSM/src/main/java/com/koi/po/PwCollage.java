package com.koi.po;

import java.sql.Timestamp;

/**
 * PwCollage entity. @author MyEclipse Persistence Tools
 */

public class PwCollage implements java.io.Serializable {

	// Fields

	private Integer collageId;
	private String foreMan;
	private Integer receptionId;
	private String collageDate;
	private Double amount;
	private Boolean toAudit;
	private Timestamp auditDate;
	private String auditor;
	private String remark;
	private String operator;

	// Constructors

	/** default constructor */
	public PwCollage() {
	}

	/** minimal constructor */
	public PwCollage(String foreMan, Integer receptionId) {
		this.foreMan = foreMan;
		this.receptionId = receptionId;
	}

	/** full constructor */
	public PwCollage(String foreMan, Integer receptionId, String collageDate,
			Double amount, Boolean toAudit, Timestamp auditDate,
			String auditor, String remark, String operator) {
		this.foreMan = foreMan;
		this.receptionId = receptionId;
		this.collageDate = collageDate;
		this.amount = amount;
		this.toAudit = toAudit;
		this.auditDate = auditDate;
		this.auditor = auditor;
		this.remark = remark;
		this.operator = operator;
	}

	// Property accessors

	public Integer getCollageId() {
		return this.collageId;
	}

	public void setCollageId(Integer collageId) {
		this.collageId = collageId;
	}

	public String getForeMan() {
		return this.foreMan;
	}

	public void setForeMan(String foreMan) {
		this.foreMan = foreMan;
	}

	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}

	public String getCollageDate() {
		return this.collageDate;
	}

	public void setCollageDate(String collageDate) {
		this.collageDate = collageDate;
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

	public Timestamp getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditor() {
		return this.auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}