package com.koi.po;

/**
 * SysClaimCom entity. @author MyEclipse Persistence Tools
 */

public class SysClaimCom implements java.io.Serializable {

	// Fields

	private Integer claimComId;
	private String claimComName;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysClaimCom() {
	}

	/** full constructor */
	public SysClaimCom(String claimComName, Boolean toDeactivate) {
		this.claimComName = claimComName;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getClaimComId() {
		return this.claimComId;
	}

	public void setClaimComId(Integer claimComId) {
		this.claimComId = claimComId;
	}

	public String getClaimComName() {
		return this.claimComName;
	}

	public void setClaimComName(String claimComName) {
		this.claimComName = claimComName;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}