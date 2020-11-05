package com.koi.po;

/**
 * SysRecProductDetail entity. @author MyEclipse Persistence Tools
 */

public class SysRecProductDetail implements java.io.Serializable {

	// Fields

	private Integer recProductDetailId;
	private Integer maintainabilityId;
	private Integer receptionId;
	private Double quantity;
	private Double unitPrice;
	private Double discount;
	private Double amount;
	private String fittingsCode;
	private String fittingsName;
	private String vehicleType;
	private String remark;
	private String fittingsSpec;
	private String systemUnit;

	// Constructors

	/** default constructor */
	public SysRecProductDetail() {
	}

	/** minimal constructor */
	public SysRecProductDetail(Integer maintainabilityId, Integer receptionId,
			Double quantity, Double unitPrice, Double discount, Double amount,
			String fittingsCode, String fittingsName, String vehicleType,
			String systemUnit) {
		this.maintainabilityId = maintainabilityId;
		this.receptionId = receptionId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.amount = amount;
		this.fittingsCode = fittingsCode;
		this.fittingsName = fittingsName;
		this.vehicleType = vehicleType;
		this.systemUnit = systemUnit;
	}

	/** full constructor */
	public SysRecProductDetail(Integer maintainabilityId, Integer receptionId,
			Double quantity, Double unitPrice, Double discount, Double amount,
			String fittingsCode, String fittingsName, String vehicleType,
			String remark, String fittingsSpec, String systemUnit) {
		this.maintainabilityId = maintainabilityId;
		this.receptionId = receptionId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.amount = amount;
		this.fittingsCode = fittingsCode;
		this.fittingsName = fittingsName;
		this.vehicleType = vehicleType;
		this.remark = remark;
		this.fittingsSpec = fittingsSpec;
		this.systemUnit = systemUnit;
	}

	// Property accessors

	public Integer getRecProductDetailId() {
		return this.recProductDetailId;
	}

	public void setRecProductDetailId(Integer recProductDetailId) {
		this.recProductDetailId = recProductDetailId;
	}

	public Integer getMaintainabilityId() {
		return this.maintainabilityId;
	}

	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
	}

	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
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

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFittingsSpec() {
		return this.fittingsSpec;
	}

	public void setFittingsSpec(String fittingsSpec) {
		this.fittingsSpec = fittingsSpec;
	}

	public String getSystemUnit() {
		return this.systemUnit;
	}

	public void setSystemUnit(String systemUnit) {
		this.systemUnit = systemUnit;
	}

}