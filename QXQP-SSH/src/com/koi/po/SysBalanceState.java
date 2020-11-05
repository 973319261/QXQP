package com.koi.po;

/**
 * SysBalanceState entity. @author MyEclipse Persistence Tools
 */

public class SysBalanceState implements java.io.Serializable {

	// Fields

	private Integer balanceStateId;
	private String balanceState;

	// Constructors

	/** default constructor */
	public SysBalanceState() {
	}

	/** full constructor */
	public SysBalanceState(String balanceState) {
		this.balanceState = balanceState;
	}

	// Property accessors

	public Integer getBalanceStateId() {
		return this.balanceStateId;
	}

	public void setBalanceStateId(Integer balanceStateId) {
		this.balanceStateId = balanceStateId;
	}

	public String getBalanceState() {
		return this.balanceState;
	}

	public void setBalanceState(String balanceState) {
		this.balanceState = balanceState;
	}

}