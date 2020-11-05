package com.koi.po;

/**
 * SysCustomerLevel entity. @author MyEclipse Persistence Tools
 */

public class SysCustomerLevel implements java.io.Serializable {

	// Fields

	private Integer customerLevelId;
	private String customerLevel;

	// Constructors

	/** default constructor */
	public SysCustomerLevel() {
	}

	/** full constructor */
	public SysCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	// Property accessors

	public Integer getCustomerLevelId() {
		return this.customerLevelId;
	}

	public void setCustomerLevelId(Integer customerLevelId) {
		this.customerLevelId = customerLevelId;
	}

	public String getCustomerLevel() {
		return this.customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

}