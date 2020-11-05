package com.koi.po;

/**
 * SysCustomerType entity. @author MyEclipse Persistence Tools
 */

public class SysCustomerType implements java.io.Serializable {

	// Fields

	private Integer customerTypeId;
	private String customerType;

	// Constructors

	/** default constructor */
	public SysCustomerType() {
	}

	/** full constructor */
	public SysCustomerType(String customerType) {
		this.customerType = customerType;
	}

	// Property accessors

	public Integer getCustomerTypeId() {
		return this.customerTypeId;
	}

	public void setCustomerTypeId(Integer customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

}