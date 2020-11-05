package com.koi.po;

/**
 * SysMaintenanceCrew entity. @author MyEclipse Persistence Tools
 */

public class SysMaintenanceCrew implements java.io.Serializable {

	// Fields

	private Integer maintenanceCrewId;
	private String maintenanceCrewName;
	private String maintenanceCrewNum;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysMaintenanceCrew() {
	}

	/** minimal constructor */
	public SysMaintenanceCrew(String maintenanceCrewName,
			String maintenanceCrewNum) {
		this.maintenanceCrewName = maintenanceCrewName;
		this.maintenanceCrewNum = maintenanceCrewNum;
	}

	/** full constructor */
	public SysMaintenanceCrew(String maintenanceCrewName,
			String maintenanceCrewNum, Boolean toDeactivate) {
		this.maintenanceCrewName = maintenanceCrewName;
		this.maintenanceCrewNum = maintenanceCrewNum;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getMaintenanceCrewId() {
		return this.maintenanceCrewId;
	}

	public void setMaintenanceCrewId(Integer maintenanceCrewId) {
		this.maintenanceCrewId = maintenanceCrewId;
	}

	public String getMaintenanceCrewName() {
		return this.maintenanceCrewName;
	}

	public void setMaintenanceCrewName(String maintenanceCrewName) {
		this.maintenanceCrewName = maintenanceCrewName;
	}

	public String getMaintenanceCrewNum() {
		return this.maintenanceCrewNum;
	}

	public void setMaintenanceCrewNum(String maintenanceCrewNum) {
		this.maintenanceCrewNum = maintenanceCrewNum;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}