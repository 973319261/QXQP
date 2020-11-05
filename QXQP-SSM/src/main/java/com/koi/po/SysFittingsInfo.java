package com.koi.po;

/**
 * SysFittingsInfo entity. @author MyEclipse Persistence Tools
 */

public class SysFittingsInfo implements java.io.Serializable {

	// Fields

	private Integer fittingsInfoId;
	private Integer fittingsTypeId;
	private Integer systemUnitId;
	private Integer vehicleTypeId;
	private Integer suppliersId;
	private String fittingsName;
	private String fittingsCode;
	private String barcode;
	private String specification;
	private String brand;
	private Double intake;
	private Double salesPrice;
	private Double wholesalePrice;
	private Integer inventoryMax;
	private Integer inventoryMin;
	private Double openPrice;
	private String remark;
	private Double invenQuan;

	// Constructors

	


	/** default constructor */
	public SysFittingsInfo() {
	}
	
	

	public SysFittingsInfo(Integer fittingsInfoId, Integer fittingsTypeId,
			Integer systemUnitId, Integer vehicleTypeId, Integer suppliersId,
			String fittingsName, String fittingsCode, String barcode,
			String specification, String brand, Double intake,
			Double salesPrice, Double wholesalePrice, Integer inventoryMax,
			Integer inventoryMin, Double openPrice, String remark,
			Double invenQuan) {
		super();
		this.fittingsInfoId = fittingsInfoId;
		this.fittingsTypeId = fittingsTypeId;
		this.systemUnitId = systemUnitId;
		this.vehicleTypeId = vehicleTypeId;
		this.suppliersId = suppliersId;
		this.fittingsName = fittingsName;
		this.fittingsCode = fittingsCode;
		this.barcode = barcode;
		this.specification = specification;
		this.brand = brand;
		this.intake = intake;
		this.salesPrice = salesPrice;
		this.wholesalePrice = wholesalePrice;
		this.inventoryMax = inventoryMax;
		this.inventoryMin = inventoryMin;
		this.openPrice = openPrice;
		this.remark = remark;
		this.invenQuan = invenQuan;
	}



	// Property accessors

	public Integer getFittingsInfoId() {
		return this.fittingsInfoId;
	}

	public void setFittingsInfoId(Integer fittingsInfoId) {
		this.fittingsInfoId = fittingsInfoId;
	}

	public Integer getFittingsTypeId() {
		return this.fittingsTypeId;
	}

	public void setFittingsTypeId(Integer fittingsTypeId) {
		this.fittingsTypeId = fittingsTypeId;
	}

	public Integer getSystemUnitId() {
		return this.systemUnitId;
	}

	public void setSystemUnitId(Integer systemUnitId) {
		this.systemUnitId = systemUnitId;
	}

	public Integer getVehicleTypeId() {
		return this.vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Integer getSuppliersId() {
		return this.suppliersId;
	}

	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
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

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getIntake() {
		return this.intake;
	}

	public void setIntake(Double intake) {
		this.intake = intake;
	}

	public Double getSalesPrice() {
		return this.salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Double getWholesalePrice() {
		return this.wholesalePrice;
	}

	public void setWholesalePrice(Double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public Integer getInventoryMax() {
		return this.inventoryMax;
	}

	public void setInventoryMax(Integer inventoryMax) {
		this.inventoryMax = inventoryMax;
	}

	public Integer getInventoryMin() {
		return this.inventoryMin;
	}

	public void setInventoryMin(Integer inventoryMin) {
		this.inventoryMin = inventoryMin;
	}

	public Double getOpenPrice() {
		return this.openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getInvenQuan() {
		return this.invenQuan;
	}

	public void setInvenQuan(Double invenQuan) {
		this.invenQuan = invenQuan;
	}
}