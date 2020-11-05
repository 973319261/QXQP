package com.koi.po;

/**
 * SysInsuranceSpe entity. @author MyEclipse Persistence Tools
 */

public class SysInsuranceSpe implements java.io.Serializable {

	// Fields

	private Integer insuranceSpeId;
	private String insuranceSpeName;
	private String insuranceSpeNum;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysInsuranceSpe() {
	}

	/** minimal constructor */
	public SysInsuranceSpe(String insuranceSpeName, String insuranceSpeNum) {
		this.insuranceSpeName = insuranceSpeName;
		this.insuranceSpeNum = insuranceSpeNum;
	}

	/** full constructor */
	public SysInsuranceSpe(String insuranceSpeName, String insuranceSpeNum,
			Boolean toDeactivate) {
		this.insuranceSpeName = insuranceSpeName;
		this.insuranceSpeNum = insuranceSpeNum;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getInsuranceSpeId() {
		return this.insuranceSpeId;
	}

	public void setInsuranceSpeId(Integer insuranceSpeId) {
		this.insuranceSpeId = insuranceSpeId;
	}

	public String getInsuranceSpeName() {
		return this.insuranceSpeName;
	}

	public void setInsuranceSpeName(String insuranceSpeName) {
		this.insuranceSpeName = insuranceSpeName;
	}

	public String getInsuranceSpeNum() {
		return this.insuranceSpeNum;
	}

	public void setInsuranceSpeNum(String insuranceSpeNum) {
		this.insuranceSpeNum = insuranceSpeNum;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}