package com.koi.po;

/**
 * SysWarehouse entity. @author MyEclipse Persistence Tools
 */

public class SysWarehouse implements java.io.Serializable {

	// Fields

	private Integer warehouseId;
	private String warehouseName;
	private String warehouseNum;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysWarehouse() {
	}

	/** minimal constructor */
	public SysWarehouse(String warehouseName, String warehouseNum) {
		this.warehouseName = warehouseName;
		this.warehouseNum = warehouseNum;
	}

	/** full constructor */
	public SysWarehouse(String warehouseName, String warehouseNum,
			Boolean toDeactivate) {
		this.warehouseName = warehouseName;
		this.warehouseNum = warehouseNum;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseNum() {
		return this.warehouseNum;
	}

	public void setWarehouseNum(String warehouseNum) {
		this.warehouseNum = warehouseNum;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}