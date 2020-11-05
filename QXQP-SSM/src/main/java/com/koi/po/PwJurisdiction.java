package com.koi.po;

/**
 * PwJurisdiction entity. @author MyEclipse Persistence Tools
 */

public class PwJurisdiction implements java.io.Serializable {

	// Fields

	private Integer jdtionId;
	private Integer userId;
	private Integer modularId;
	private Boolean toJdtion;

	// Constructors

	/** default constructor */
	public PwJurisdiction() {
	}

	/** full constructor */
	public PwJurisdiction(Integer userId, Integer modularId, Boolean toJdtion) {
		this.userId = userId;
		this.modularId = modularId;
		this.toJdtion = toJdtion;
	}

	// Property accessors

	public Integer getJdtionId() {
		return this.jdtionId;
	}

	public void setJdtionId(Integer jdtionId) {
		this.jdtionId = jdtionId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getModularId() {
		return this.modularId;
	}

	public void setModularId(Integer modularId) {
		this.modularId = modularId;
	}

	public Boolean getToJdtion() {
		return this.toJdtion;
	}

	public void setToJdtion(Boolean toJdtion) {
		this.toJdtion = toJdtion;
	}

}