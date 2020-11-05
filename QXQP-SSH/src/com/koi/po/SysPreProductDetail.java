package com.koi.po;

/**
 * SysPreProductDetail entity. @author MyEclipse Persistence Tools
 */

public class SysPreProductDetail implements java.io.Serializable {

	// Fields

	private Integer preProductDetailId;
	private Integer predateId;
	private Integer maintainabilityId;
	private Double quantity;
	private Double unitPrice;
	private Double discount;
	private Double amount;
	private String remark;
	private String fittingsCode;
	private String fittingsName;
	private String systemUnit;
	private String vehicleType;

	// Constructors

	/** default constructor */
	public SysPreProductDetail() {
	}

	
	public SysPreProductDetail(Integer preProductDetailId, Integer predateId,
			Integer maintainabilityId, Double quantity, Double unitPrice,
			Double discount, Double amount, String remark, String fittingsCode,
			String fittingsName, String systemUnit, String vehicleType) {
		super();
		this.preProductDetailId = preProductDetailId;
		this.predateId = predateId;
		this.maintainabilityId = maintainabilityId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.amount = amount;
		this.remark = remark;
		this.fittingsCode = fittingsCode;
		this.fittingsName = fittingsName;
		this.systemUnit = systemUnit;
		this.vehicleType = vehicleType;
	}


	// Property accessors

	public Integer getPreProductDetailId() {
		return this.preProductDetailId;
	}

	public void setPreProductDetailId(Integer preProductDetailId) {
		this.preProductDetailId = preProductDetailId;
	}

	public Integer getPredateId() {
		return this.predateId;
	}

	public void setPredateId(Integer predateId) {
		this.predateId = predateId;
	}

	public Integer getMaintainabilityId() {
		return this.maintainabilityId;
	}

	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
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

	public String getFittingsCode() {
		return this.fittingsCode;
	}

	public void setFittingsCode(String fittingsCode) {
		this.fittingsCode = fittingsCode;
	}

	public String getFittingsName() {
		return this.fittingsName;
	}

	public void setFittingsName(String fittingsName) {
		this.fittingsName = fittingsName;
	}

	public String getSystemUnit() {
		return this.systemUnit;
	}

	public void setSystemUnit(String systemUnit) {
		this.systemUnit = systemUnit;
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

}