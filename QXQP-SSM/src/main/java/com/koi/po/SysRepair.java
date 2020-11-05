package com.koi.po;

/**
 * SysRepair entity. @author MyEclipse Persistence Tools
 */

public class SysRepair implements java.io.Serializable {

	// Fields

	private Integer repairId;
	private String repairName;
	private String trackingDays;
	private String remark;

	// Constructors

	/** default constructor */
	public SysRepair() {
	}

	/** minimal constructor */
	public SysRepair(String repairName) {
		this.repairName = repairName;
	}

	/** full constructor */
	public SysRepair(String repairName, String trackingDays, String remark) {
		this.repairName = repairName;
		this.trackingDays = trackingDays;
		this.remark = remark;
	}

	// Property accessors

	public Integer getRepairId() {
		return this.repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public String getRepairName() {
		return this.repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}

	public String getTrackingDays() {
		return this.trackingDays;
	}

	public void setTrackingDays(String trackingDays) {
		this.trackingDays = trackingDays;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}