package com.koi.po;

/**
 * SysSalesReturnDetails entity. @author MyEclipse Persistence Tools
 */

public class SysSalesReturnDetails implements java.io.Serializable {

	// Fields

	private Integer salesReturnDetailsId;
	private Integer fittingsInfoId;
	private Integer warehouseId;
	private Integer salesReturnlId;
	private Double quantity;
	private Double unitcoster;
	private Double discounts;
	private Double amount;
	private String userdefined;

	// Constructors

	/** default constructor */
	public SysSalesReturnDetails() {
	}

	/** minimal constructor */
	public SysSalesReturnDetails(Integer fittingsInfoId, Integer warehouseId,
			Integer salesReturnlId) {
		this.fittingsInfoId = fittingsInfoId;
		this.warehouseId = warehouseId;
		this.salesReturnlId = salesReturnlId;
	}

	/** full constructor */
	public SysSalesReturnDetails(Integer fittingsInfoId, Integer warehouseId,
			Integer salesReturnlId, Double quantity, Double unitcoster,
			Double discounts, Double amount, String userdefined) {
		this.fittingsInfoId = fittingsInfoId;
		this.warehouseId = warehouseId;
		this.salesReturnlId = salesReturnlId;
		this.quantity = quantity;
		this.unitcoster = unitcoster;
		this.discounts = discounts;
		this.amount = amount;
		this.userdefined = userdefined;
	}

	// Property accessors

	public Integer getSalesReturnDetailsId() {
		return this.salesReturnDetailsId;
	}

	public void setSalesReturnDetailsId(Integer salesReturnDetailsId) {
		this.salesReturnDetailsId = salesReturnDetailsId;
	}

	public Integer getFittingsInfoId() {
		return this.fittingsInfoId;
	}

	public void setFittingsInfoId(Integer fittingsInfoId) {
		this.fittingsInfoId = fittingsInfoId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getSalesReturnlId() {
		return this.salesReturnlId;
	}

	public void setSalesReturnlId(Integer salesReturnlId) {
		this.salesReturnlId = salesReturnlId;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitcoster() {
		return this.unitcoster;
	}

	public void setUnitcoster(Double unitcoster) {
		this.unitcoster = unitcoster;
	}

	public Double getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(Double discounts) {
		this.discounts = discounts;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUserdefined() {
		return this.userdefined;
	}

	public void setUserdefined(String userdefined) {
		this.userdefined = userdefined;
	}

}