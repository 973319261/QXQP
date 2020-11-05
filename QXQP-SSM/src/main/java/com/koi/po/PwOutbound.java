package com.koi.po;

import java.sql.Timestamp;

/**
 * PwOutbound entity. @author MyEclipse Persistence Tools
 */

public class PwOutbound implements java.io.Serializable {

	// Fields

	private Integer outboundId;
	private Integer documentsTypeId;
	private Integer salesCustomerId;
	private String stockremoval;
	private String openDate;
	private Double tradeprice;
	private String processingman;
	private Boolean reviewwhet;
	private String therevie;
	private Timestamp auditdates;
	private String salesorderNum;
	private Double theamount;
	private String remark;
	private String docuState;
	private Integer balanceStateId;

	// Constructors

	/** default constructor */
	public PwOutbound() {
	}

	/** minimal constructor */
	public PwOutbound(Integer salesCustomerId, String stockremoval) {
		this.salesCustomerId = salesCustomerId;
		this.stockremoval = stockremoval;
	}

	/** full constructor */
	public PwOutbound(Integer documentsTypeId, Integer salesCustomerId,
			String stockremoval, String openDate, Double tradeprice,
			String processingman, Boolean reviewwhet, String therevie,
			Timestamp auditdates, String salesorderNum, Double theamount,
			String remark, String docuState, Integer balanceStateId) {
		this.documentsTypeId = documentsTypeId;
		this.salesCustomerId = salesCustomerId;
		this.stockremoval = stockremoval;
		this.openDate = openDate;
		this.tradeprice = tradeprice;
		this.processingman = processingman;
		this.reviewwhet = reviewwhet;
		this.therevie = therevie;
		this.auditdates = auditdates;
		this.salesorderNum = salesorderNum;
		this.theamount = theamount;
		this.remark = remark;
		this.docuState = docuState;
		this.balanceStateId = balanceStateId;
	}

	// Property accessors

	public Integer getOutboundId() {
		return this.outboundId;
	}

	public void setOutboundId(Integer outboundId) {
		this.outboundId = outboundId;
	}

	public Integer getDocumentsTypeId() {
		return this.documentsTypeId;
	}

	public void setDocumentsTypeId(Integer documentsTypeId) {
		this.documentsTypeId = documentsTypeId;
	}

	public Integer getSalesCustomerId() {
		return this.salesCustomerId;
	}

	public void setSalesCustomerId(Integer salesCustomerId) {
		this.salesCustomerId = salesCustomerId;
	}

	public String getStockremoval() {
		return this.stockremoval;
	}

	public void setStockremoval(String stockremoval) {
		this.stockremoval = stockremoval;
	}

	public String getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public Double getTradeprice() {
		return this.tradeprice;
	}

	public void setTradeprice(Double tradeprice) {
		this.tradeprice = tradeprice;
	}

	public String getProcessingman() {
		return this.processingman;
	}

	public void setProcessingman(String processingman) {
		this.processingman = processingman;
	}

	public Boolean getReviewwhet() {
		return this.reviewwhet;
	}

	public void setReviewwhet(Boolean reviewwhet) {
		this.reviewwhet = reviewwhet;
	}

	public String getTherevie() {
		return this.therevie;
	}

	public void setTherevie(String therevie) {
		this.therevie = therevie;
	}

	public Timestamp getAuditdates() {
		return this.auditdates;
	}

	public void setAuditdates(Timestamp auditdates) {
		this.auditdates = auditdates;
	}

	public String getSalesorderNum() {
		return this.salesorderNum;
	}

	public void setSalesorderNum(String salesorderNum) {
		this.salesorderNum = salesorderNum;
	}

	public Double getTheamount() {
		return this.theamount;
	}

	public void setTheamount(Double theamount) {
		this.theamount = theamount;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDocuState() {
		return this.docuState;
	}

	public void setDocuState(String docuState) {
		this.docuState = docuState;
	}

	public Integer getBalanceStateId() {
		return this.balanceStateId;
	}

	public void setBalanceStateId(Integer balanceStateId) {
		this.balanceStateId = balanceStateId;
	}

}