package com.koi.po;

/**
 * SysCarder entity. @author MyEclipse Persistence Tools
 */

public class SysCarder implements java.io.Serializable {

	// Fields

	private Integer carderId;
	private String carderName;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysCarder() {
	}

	/** minimal constructor */
	public SysCarder(String carderName) {
		this.carderName = carderName;
	}

	/** full constructor */
	public SysCarder(String carderName, Boolean toDeactivate) {
		this.carderName = carderName;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getCarderId() {
		return this.carderId;
	}

	public void setCarderId(Integer carderId) {
		this.carderId = carderId;
	}

	public String getCarderName() {
		return this.carderName;
	}

	public void setCarderName(String carderName) {
		this.carderName = carderName;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}