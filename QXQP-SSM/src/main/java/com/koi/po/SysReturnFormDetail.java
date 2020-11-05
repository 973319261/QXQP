package com.koi.po;

/**
 * SysReturnFormDetail entity. @author MyEclipse Persistence Tools
 */

public class SysReturnFormDetail implements java.io.Serializable {

	// Fields

	private Integer returnFormDetailId;
	private Integer returnFormId;
	private Integer fittingsInfoId;
	private Double quantity;
	private Double unitPrice;
	private Double discount;
	private Double amount;
	private String remark;

	// Constructors

	/** default constructor */
	public SysReturnFormDetail() {
	}

	/** minimal constructor */
	public SysReturnFormDetail(Integer returnFormId) {
		this.returnFormId = returnFormId;
	}

	/** full constructor */
	public SysReturnFormDetail(Integer returnFormId, Integer fittingsInfoId,
			Double quantity, Double unitPrice, Double discount, Double amount,
			String remark) {
		this.returnFormId = returnFormId;
		this.fittingsInfoId = fittingsInfoId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.amount = amount;
		this.remark = remark;
	}

	// Property accessors

	public Integer getReturnFormDetailId() {
		return this.returnFormDetailId;
	}

	public void setReturnFormDetailId(Integer returnFormDetailId) {
		this.returnFormDetailId = returnFormDetailId;
	}

	public Integer getReturnFormId() {
		return this.returnFormId;
	}

	public void setReturnFormId(Integer returnFormId) {
		this.returnFormId = returnFormId;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}