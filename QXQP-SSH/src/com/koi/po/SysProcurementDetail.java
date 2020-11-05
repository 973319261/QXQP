package com.koi.po;

/**
 * SysProcurementDetail entity. @author MyEclipse Persistence Tools
 */

public class SysProcurementDetail implements java.io.Serializable {

	// Fields

	private Integer procurementDetailId;
	private Integer procurementId;
	private Integer fittingsInfoId;
	private Double quantity;
	private Double unitPrice;
	private Double discount;
	private Double amount;
	private Double firmQuantity;
	private String fittingsType;

	// Constructors

	/** default constructor */
	public SysProcurementDetail() {
	}

	/** minimal constructor */
	public SysProcurementDetail(Integer procurementId) {
		this.procurementId = procurementId;
	}

	/** full constructor */
	public SysProcurementDetail(Integer procurementId, Integer fittingsInfoId,
			Double quantity, Double unitPrice, Double discount, Double amount,
			Double firmQuantity, String fittingsType) {
		this.procurementId = procurementId;
		this.fittingsInfoId = fittingsInfoId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.amount = amount;
		this.firmQuantity = firmQuantity;
		this.fittingsType = fittingsType;
	}

	// Property accessors

	public Integer getProcurementDetailId() {
		return this.procurementDetailId;
	}

	public void setProcurementDetailId(Integer procurementDetailId) {
		this.procurementDetailId = procurementDetailId;
	}

	public Integer getProcurementId() {
		return this.procurementId;
	}

	public void setProcurementId(Integer procurementId) {
		this.procurementId = procurementId;
	}

	public Integer getFittingsInfoId() {
		return this.fittingsInfoId;
	}

	public void setFittingsInfoId(Integer fittingsInfoId) {
		this.fittingsInfoId = fittingsInfoId;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getFirmQuantity() {
		return this.firmQuantity;
	}

	public void setFirmQuantity(Double firmQuantity) {
		this.firmQuantity = firmQuantity;
	}

	public String getFittingsType() {
		return this.fittingsType;
	}

	public void setFittingsType(String fittingsType) {
		this.fittingsType = fittingsType;
	}

}