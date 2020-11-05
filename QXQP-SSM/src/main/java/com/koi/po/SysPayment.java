package com.koi.po;

/**
 * SysPayment entity. @author MyEclipse Persistence Tools
 */

public class SysPayment implements java.io.Serializable {

	// Fields

	private Integer paymentId;
	private String paymentName;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysPayment() {
	}

	/** minimal constructor */
	public SysPayment(String paymentName) {
		this.paymentName = paymentName;
	}

	/** full constructor */
	public SysPayment(String paymentName, Boolean toDeactivate) {
		this.paymentName = paymentName;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentName() {
		return this.paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}