package com.koi.vo;

import java.io.Serializable;

public class FittingsInfoVo implements Serializable{
	private Integer fittingsInfoId;
	private Integer fittingsTypeId;
	private Integer vehicleTypeId;
	private Integer systemUnitId;
	private Integer suppliersId;
	private String fittingsTypeName;
	private String fittingsCode;
	private String fittingsName;
	private String specification;
	private String vehicleType;
	private String systemUnit;
	private Double intake;
	private Double salesPrice;
	private String suppliersName;
	private String remark;
	private Double wholesalePrice;
	private String pictureName;
	public FittingsInfoVo() {
	}

	public FittingsInfoVo(Integer fittingsInfoId, Integer fittingsTypeId,
			Integer vehicleTypeId, Integer systemUnitId, Integer suppliersId,
			String fittingsTypeName, String fittingsCode, String fittingsName,
			String specification, String vehicleType, String systemUnit,
			Double intake, Double salesPrice, String suppliersName,
			String remark, Double wholesalePrice) {
		super();
		this.fittingsInfoId = fittingsInfoId;
		this.fittingsTypeId = fittingsTypeId;
		this.vehicleTypeId = vehicleTypeId;
		this.systemUnitId = systemUnitId;
		this.suppliersId = suppliersId;
		this.fittingsTypeName = fittingsTypeName;
		this.fittingsCode = fittingsCode;
		this.fittingsName = fittingsName;
		this.specification = specification;
		this.vehicleType = vehicleType;
		this.systemUnit = systemUnit;
		this.intake = intake;
		this.salesPrice = salesPrice;
		this.suppliersName = suppliersName;
		this.remark = remark;
		this.wholesalePrice = wholesalePrice;
	}

	public Integer getFittingsInfoId() {
		return fittingsInfoId;
	}

	public void setFittingsInfoId(Integer fittingsInfoId) {
		this.fittingsInfoId = fittingsInfoId;
	}

	public Integer getFittingsTypeId() {
		return fittingsTypeId;
	}

	public void setFittingsTypeId(Integer fittingsTypeId) {
		this.fittingsTypeId = fittingsTypeId;
	}

	public Integer getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Integer getSystemUnitId() {
		return systemUnitId;
	}

	public void setSystemUnitId(Integer systemUnitId) {
		this.systemUnitId = systemUnitId;
	}

	public Integer getSuppliersId() {
		return suppliersId;
	}

	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
	}

	public String getFittingsTypeName() {
		return fittingsTypeName;
	}

	public void setFittingsTypeName(String fittingsTypeName) {
		this.fittingsTypeName = fittingsTypeName;
	}

	public String getFittingsCode() {
		return fittingsCode;
	}

	public void setFittingsCode(String fittingsCode) {
		this.fittingsCode = fittingsCode;
	}

	public String getFittingsName() {
		return fittingsName;
	}

	public void setFittingsName(String fittingsName) {
		this.fittingsName = fittingsName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getSystemUnit() {
		return systemUnit;
	}

	public void setSystemUnit(String systemUnit) {
		this.systemUnit = systemUnit;
	}

	public Double getIntake() {
		return intake;
	}

	public void setIntake(Double intake) {
		this.intake = intake;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getSuppliersName() {
		return suppliersName;
	}

	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(Double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName=pictureName;
	}
}
