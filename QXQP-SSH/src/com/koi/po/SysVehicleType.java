package com.koi.po;

/**
 * SysVehicleType entity. @author MyEclipse Persistence Tools
 */

public class SysVehicleType implements java.io.Serializable {

	// Fields

	private Integer vehicleTypeId;
	private String vehicleType;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysVehicleType() {
	}

	/** minimal constructor */
	public SysVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/** full constructor */
	public SysVehicleType(String vehicleType, Boolean toDeactivate) {
		this.vehicleType = vehicleType;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getVehicleTypeId() {
		return this.vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}