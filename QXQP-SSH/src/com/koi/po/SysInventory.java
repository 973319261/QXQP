package com.koi.po;

/**
 * SysInventory entity. @author MyEclipse Persistence Tools
 */

public class SysInventory implements java.io.Serializable {

	// Fields

	private Integer inventoryId;
	private Integer warehouseId;
	private String fittingsCode;
	private String fittingsName;
	private String vehicleType;
	private String systemUnit;
	private Double invenQuan;
	private Double newIntake;
	private Double wholePrice;
	private Double salePrice;
	private Double minInventory;
	private String position;
	private String fittingsType;

	// Constructors

	/** default constructor */
	public SysInventory() {
	}

	/** minimal constructor */
	public SysInventory(Integer warehouseId, String fittingsCode,
			String fittingsName, String fittingsType) {
		this.warehouseId = warehouseId;
		this.fittingsCode = fittingsCode;
		this.fittingsName = fittingsName;
		this.fittingsType = fittingsType;
	}

	/** full constructor */
	public SysInventory(Integer warehouseId, String fittingsCode,
			String fittingsName, String vehicleType, String systemUnit,
			Double invenQuan, Double newIntake, Double wholePrice,
			Double salePrice, Double minInventory, String position,
			String fittingsType) {
		this.warehouseId = warehouseId;
		this.fittingsCode = fittingsCode;
		this.fittingsName = fittingsName;
		this.vehicleType = vehicleType;
		this.systemUnit = systemUnit;
		this.invenQuan = invenQuan;
		this.newIntake = newIntake;
		this.wholePrice = wholePrice;
		this.salePrice = salePrice;
		this.minInventory = minInventory;
		this.position = position;
		this.fittingsType = fittingsType;
	}

	// Property accessors

	public Integer getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
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

	public String getSystemUnit() {
		return this.systemUnit;
	}

	public void setSystemUnit(String systemUnit) {
		this.systemUnit = systemUnit;
	}

	public Double getInvenQuan() {
		return this.invenQuan;
	}

	public void setInvenQuan(Double invenQuan) {
		this.invenQuan = invenQuan;
	}

	public Double getNewIntake() {
		return this.newIntake;
	}

	public void setNewIntake(Double newIntake) {
		this.newIntake = newIntake;
	}

	public Double getWholePrice() {
		return this.wholePrice;
	}

	public void setWholePrice(Double wholePrice) {
		this.wholePrice = wholePrice;
	}

	public Double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getMinInventory() {
		return this.minInventory;
	}

	public void setMinInventory(Double minInventory) {
		this.minInventory = minInventory;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFittingsType() {
		return this.fittingsType;
	}

	public void setFittingsType(String fittingsType) {
		this.fittingsType = fittingsType;
	}

}