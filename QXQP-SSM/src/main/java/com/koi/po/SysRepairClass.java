package com.koi.po;

/**
 * SysRepairClass entity. @author MyEclipse Persistence Tools
 */

public class SysRepairClass implements java.io.Serializable {

	// Fields

	private Integer repairClassId;
	private String repairClassNum;
	private String repairClassName;
	private Double timeUnit;

	// Constructors

	/** default constructor */
	public SysRepairClass() {
	}

	/** minimal constructor */
	public SysRepairClass(String repairClassNum, String repairClassName) {
		this.repairClassNum = repairClassNum;
		this.repairClassName = repairClassName;
	}

	/** full constructor */
	public SysRepairClass(String repairClassNum, String repairClassName,
			Double timeUnit) {
		this.repairClassNum = repairClassNum;
		this.repairClassName = repairClassName;
		this.timeUnit = timeUnit;
	}

	// Property accessors

	public Integer getRepairClassId() {
		return this.repairClassId;
	}

	public void setRepairClassId(Integer repairClassId) {
		this.repairClassId = repairClassId;
	}

	public String getRepairClassNum() {
		return this.repairClassNum;
	}

	public void setRepairClassNum(String repairClassNum) {
		this.repairClassNum = repairClassNum;
	}

	public String getRepairClassName() {
		return this.repairClassName;
	}

	public void setRepairClassName(String repairClassName) {
		this.repairClassName = repairClassName;
	}

	public Double getTimeUnit() {
		return this.timeUnit;
	}

	public void setTimeUnit(Double timeUnit) {
		this.timeUnit = timeUnit;
	}

}