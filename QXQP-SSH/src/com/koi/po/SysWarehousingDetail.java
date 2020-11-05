package com.koi.po;

/**
 * SysWarehousingDetail entity. @author MyEclipse Persistence Tools
 */

public class SysWarehousingDetail implements java.io.Serializable {

	// Fields

	private Integer warehousingDetailId;
	private Integer incomingId;
	private Integer fittingsInfoId;
	private Double quantity;
	private Double unitPrice;
	private Double discount;
	private Double amount;
	private String remark;
	private String position;

	// Constructors

	/** default constructor */
	public SysWarehousingDetail() {
	}

	/** minimal constructor */
	public SysWarehousingDetail(Integer incomingId) {
		this.incomingId = incomingId;
	}

	/** full constructor */
	public SysWarehousingDetail(Integer incomingId, Integer fittingsInfoId,
			Double quantity, Double unitPrice, Double discount, Double amount,
			String remark, String position) {
		this.incomingId = incomingId;
		this.fittingsInfoId = fittingsInfoId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.amount = amount;
		this.remark = remark;
		this.position = position;
	}

	// Property accessors

	public Integer getWarehousingDetailId() {
		return this.warehousingDetailId;
	}

	public void setWarehousingDetailId(Integer warehousingDetailId) {
		this.warehousingDetailId = warehousingDetailId;
	}

	public Integer getIncomingId() {
		return this.incomingId;
	}

	public void setIncomingId(Integer incomingId) {
		this.incomingId = incomingId;
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

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}