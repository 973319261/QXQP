package com.koi.po;

/**
 * SysMaintenance entity. @author MyEclipse Persistence Tools
 */

public class SysMaintenance implements java.io.Serializable {

	// Fields

	private Integer maintenanceId;
	private String maintenanceName;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysMaintenance() {
	}

	/** minimal constructor */
	public SysMaintenance(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}

	/** full constructor */
	public SysMaintenance(String maintenanceName, Boolean toDeactivate) {
		this.maintenanceName = maintenanceName;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getMaintenanceId() {
		return this.maintenanceId;
	}

	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getMaintenanceName() {
		return this.maintenanceName;
	}

	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}