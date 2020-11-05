package com.koi.po;

import java.sql.Timestamp;

/**
 * PwSalesorder entity. @author MyEclipse Persistence Tools
 */

public class PwSalesorder implements java.io.Serializable {

	// Fields

	private Integer salesordeId;
	private Integer salesCustomerId;
	private String salesorderNum;
	private String invoicedatek;
	private String invoicno;
	private Timestamp deliverydate;
	private String operatorf;
	private Boolean reviewwhether;
	private String thereviewer;
	private Timestamp auditdate;
	private Double theamount;
	private String remark;
	private String documents;

	// Constructors

	/** default constructor */
	public PwSalesorder() {
	}

	/** minimal constructor */
	public PwSalesorder(Integer salesCustomerId, String salesorderNum) {
		this.salesCustomerId = salesCustomerId;
		this.salesorderNum = salesorderNum;
	}

	/** full constructor */
	public PwSalesorder(Integer salesCustomerId, String salesorderNum,
			String invoicedatek, String invoicno, Timestamp deliverydate,
			String operatorf, Boolean reviewwhether, String thereviewer,
			Timestamp auditdate, Double theamount, String remark,
			String documents) {
		this.salesCustomerId = salesCustomerId;
		this.salesorderNum = salesorderNum;
		this.invoicedatek = invoicedatek;
		this.invoicno = invoicno;
		this.deliverydate = deliverydate;
		this.operatorf = operatorf;
		this.reviewwhether = reviewwhether;
		this.thereviewer = thereviewer;
		this.auditdate = auditdate;
		this.theamount = theamount;
		this.remark = remark;
		this.documents = documents;
	}

	// Property accessors

	public Integer getSalesordeId() {
		return this.salesordeId;
	}

	public void setSalesordeId(Integer salesordeId) {
		this.salesordeId = salesordeId;
	}

	public Integer getSalesCustomerId() {
		return this.salesCustomerId;
	}

	public void setSalesCustomerId(Integer salesCustomerId) {
		this.salesCustomerId = salesCustomerId;
	}

	public String getSalesorderNum() {
		return this.salesorderNum;
	}

	public void setSalesorderNum(String salesorderNum) {
		this.salesorderNum = salesorderNum;
	}

	public String getInvoicedatek() {
		return this.invoicedatek;
	}

	public void setInvoicedatek(String invoicedatek) {
		this.invoicedatek = invoicedatek;
	}

	public String getInvoicno() {
		return this.invoicno;
	}

	public void setInvoicno(String invoicno) {
		this.invoicno = invoicno;
	}

	public Timestamp getDeliverydate() {
		return this.deliverydate;
	}

	public void setDeliverydate(Timestamp deliverydate) {
		this.deliverydate = deliverydate;
	}

	public String getOperatorf() {
		return this.operatorf;
	}

	public void setOperatorf(String operatorf) {
		this.operatorf = operatorf;
	}

	public Boolean getReviewwhether() {
		return this.reviewwhether;
	}

	public void setReviewwhether(Boolean reviewwhether) {
		this.reviewwhether = reviewwhether;
	}

	public String getThereviewer() {
		return this.thereviewer;
	}

	public void setThereviewer(String thereviewer) {
		this.thereviewer = thereviewer;
	}

	public Timestamp getAuditdate() {
		return this.auditdate;
	}

	public void setAuditdate(Timestamp auditdate) {
		this.auditdate = auditdate;
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

	public String getDocuments() {
		return this.documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

}