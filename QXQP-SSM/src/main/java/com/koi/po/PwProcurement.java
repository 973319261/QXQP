package com.koi.po;

import java.sql.Timestamp;

/**
 * PwProcurement entity. @author MyEclipse Persistence Tools
 */

public class PwProcurement implements java.io.Serializable {

	// Fields

	private Integer procurementId;
	private Integer suppliersId;
	private Integer userId;
	private Integer warehouseId;
	private String indentNumber;
	private String maker;
	private String makerDate;
	private Timestamp deliveryDeadline;
	private Double handsel;
	private Boolean toAudit;
	private String auditer;
	private String auditerDate;
	private String remark;
	private String documents;
	private Double aamount;

	// Constructors

	/** default constructor */
	public PwProcurement() {
	}

	/** minimal constructor */
	public PwProcurement(Integer suppliersId, Integer userId,
			Integer warehouseId, String indentNumber, String maker,
			String makerDate) {
		this.suppliersId = suppliersId;
		this.userId = userId;
		this.warehouseId = warehouseId;
		this.indentNumber = indentNumber;
		this.maker = maker;
		this.makerDate = makerDate;
	}

	/** full constructor */
	public PwProcurement(Integer suppliersId, Integer userId,
			Integer warehouseId, String indentNumber, String maker,
			String makerDate, Timestamp deliveryDeadline, Double handsel,
			Boolean toAudit, String auditer, String auditerDate, String remark,
			String documents, Double aamount) {
		this.suppliersId = suppliersId;
		this.userId = userId;
		this.warehouseId = warehouseId;
		this.indentNumber = indentNumber;
		this.maker = maker;
		this.makerDate = makerDate;
		this.deliveryDeadline = deliveryDeadline;
		this.handsel = handsel;
		this.toAudit = toAudit;
		this.auditer = auditer;
		this.auditerDate = auditerDate;
		this.remark = remark;
		this.documents = documents;
		this.aamount = aamount;
	}

	// Property accessors

	public Integer getProcurementId() {
		return this.procurementId;
	}

	public void setProcurementId(Integer procurementId) {
		this.procurementId = procurementId;
	}

	public Integer getSuppliersId() {
		return this.suppliersId;
	}

	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
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

	public String getIndentNumber() {
		return this.indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getMakerDate() {
		return this.makerDate;
	}

	public void setMakerDate(String makerDate) {
		this.makerDate = makerDate;
	}

	public Timestamp getDeliveryDeadline() {
		return this.deliveryDeadline;
	}

	public void setDeliveryDeadline(Timestamp deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}

	public Double getHandsel() {
		return this.handsel;
	}

	public void setHandsel(Double handsel) {
		this.handsel = handsel;
	}

	public Boolean getToAudit() {
		return this.toAudit;
	}

	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}

	public String getAuditer() {
		return this.auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public String getAuditerDate() {
		return this.auditerDate;
	}

	public void setAuditerDate(String auditerDate) {
		this.auditerDate = auditerDate;
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

	public Double getAamount() {
		return this.aamount;
	}

	public void setAamount(Double aamount) {
		this.aamount = aamount;
	}

}