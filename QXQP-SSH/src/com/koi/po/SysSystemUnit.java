package com.koi.po;

/**
 * SysSystemUnit entity. @author MyEclipse Persistence Tools
 */

public class SysSystemUnit implements java.io.Serializable {

	// Fields

	private Integer systemUnitId;
	private String systemUnit;

	// Constructors

	/** default constructor */
	public SysSystemUnit() {
	}

	/** full constructor */
	public SysSystemUnit(String systemUnit) {
		this.systemUnit = systemUnit;
	}

	// Property accessors

	public Integer getSystemUnitId() {
		return this.systemUnitId;
	}

	public void setSystemUnitId(Integer systemUnitId) {
		this.systemUnitId = systemUnitId;
	}

	public String getSystemUnit() {
		return this.systemUnit;
	}

	public void setSystemUnit(String systemUnit) {
		this.systemUnit = systemUnit;
	}

}