package com.koi.po;

/**
 * SysMaintainability entity. @author MyEclipse Persistence Tools
 */

public class SysMaintainability implements java.io.Serializable {

	// Fields

	private Integer maintainabilityId;
	private String maintainabilityName;
	private String maintainabilityNum;

	// Constructors

	/** default constructor */
	public SysMaintainability() {
	}

	/** full constructor */
	public SysMaintainability(String maintainabilityName,
			String maintainabilityNum) {
		this.maintainabilityName = maintainabilityName;
		this.maintainabilityNum = maintainabilityNum;
	}

	// Property accessors

	public Integer getMaintainabilityId() {
		return this.maintainabilityId;
	}

	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
	}

	public String getMaintainabilityName() {
		return this.maintainabilityName;
	}

	public void setMaintainabilityName(String maintainabilityName) {
		this.maintainabilityName = maintainabilityName;
	}

	public String getMaintainabilityNum() {
		return this.maintainabilityNum;
	}

	public void setMaintainabilityNum(String maintainabilityNum) {
		this.maintainabilityNum = maintainabilityNum;
	}

}