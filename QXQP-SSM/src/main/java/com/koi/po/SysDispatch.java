package com.koi.po;

/**
 * SysDispatch entity. @author MyEclipse Persistence Tools
 */

public class SysDispatch implements java.io.Serializable {

	// Fields

	private Integer dispatchId;
	private String dispatchName;
	private Double price;

	// Constructors

	/** default constructor */
	public SysDispatch() {
	}

	/** minimal constructor */
	public SysDispatch(String dispatchName) {
		this.dispatchName = dispatchName;
	}

	/** full constructor */
	public SysDispatch(String dispatchName, Double price) {
		this.dispatchName = dispatchName;
		this.price = price;
	}

	// Property accessors

	public Integer getDispatchId() {
		return this.dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getDispatchName() {
		return this.dispatchName;
	}

	public void setDispatchName(String dispatchName) {
		this.dispatchName = dispatchName;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}