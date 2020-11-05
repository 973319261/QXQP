package com.koi.po;

/**
 * SysCollageDetai entity. @author MyEclipse Persistence Tools
 */

public class SysCollageDetai implements java.io.Serializable {

	// Fields

	private Integer collageDetaiId;
	private Integer maintainabilityId;
	private Integer warehouseId;
	private Integer collageId;
	private Double quantity;
	private Double unitPrice;
	private Double amount;
	private String position;
	private String vehicleType;
	private String fittingsName;
	private String fittingsCode;
	private String fittingsSpec;
	private String systemUnit;

	// Constructors

	/** default constructor */
	public SysCollageDetai() {
	}

	/** minimal constructor */
	public SysCollageDetai(Integer maintainabilityId, Integer warehouseId,
			Integer collageId, Double amount, String vehicleType,
			String fittingsName, String fittingsCode) {
		this.maintainabilityId = maintainabilityId;
		this.warehouseId = warehouseId;
		this.collageId = collageId;
		this.amount = amount;
		this.vehicleType = vehicleType;
		this.fittingsName = fittingsName;
		this.fittingsCode = fittingsCode;
	}

	/** full constructor */
	public SysCollageDetai(Integer maintainabilityId, Integer warehouseId,
			Integer collageId, Double quantity, Double unitPrice,
			Double amount, String position, String vehicleType,
			String fittingsName, String fittingsCode, String fittingsSpec,
			String systemUnit) {
		this.maintainabilityId = maintainabilityId;
		this.warehouseId = warehouseId;
		this.collageId = collageId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.position = position;
		this.vehicleType = vehicleType;
		this.fittingsName = fittingsName;
		this.fittingsCode = fittingsCode;
		this.fittingsSpec = fittingsSpec;
		this.systemUnit = systemUnit;
	}

	// Property accessors

	public Integer getCollageDetaiId() {
		return this.collageDetaiId;
	}

	public void setCollageDetaiId(Integer collageDetaiId) {
		this.collageDetaiId = collageDetaiId;
	}

	public Integer getMaintainabilityId() {
		return this.maintainabilityId;
	}

	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getCollageId() {
		return this.collageId;
	}

	public void setCollageId(Integer collageId) {
		this.collageId = collageId;
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

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getFittingsName() {
		return this.fittingsName;
	}

	public void setFittingsName(String fittingsName) {
		this.fittingsName = fittingsName;
	}

	public String getFittingsCode() {
		return this.fittingsCode;
	}

	public void setFittingsCode(String fittingsCode) {
		this.fittingsCode = fittingsCode;
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