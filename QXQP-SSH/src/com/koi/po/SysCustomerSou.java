package com.koi.po;

/**
 * SysCustomerSou entity. @author MyEclipse Persistence Tools
 */

public class SysCustomerSou implements java.io.Serializable {

	// Fields

	private Integer customerSouId;
	private String customerSou;

	// Constructors

	/** default constructor */
	public SysCustomerSou() {
	}

	/** full constructor */
	public SysCustomerSou(String customerSou) {
		this.customerSou = customerSou;
	}

	// Property accessors

	public Integer getCustomerSouId() {
		return this.customerSouId;
	}

	public void setCustomerSouId(Integer customerSouId) {
		this.customerSouId = customerSouId;
	}

	public String getCustomerSou() {
		return this.customerSou;
	}

	public void setCustomerSou(String customerSou) {
		this.customerSou = customerSou;
	}

}