package com.koi.po;

/**
 * SysInsuranceCom entity. @author MyEclipse Persistence Tools
 */

public class SysInsuranceCom implements java.io.Serializable {

	// Fields

	private Integer insuranceComId;
	private String insuranceComName;
	private Boolean toDeactivate;
	private String insuranceComNum;

	// Constructors

	/** default constructor */
	public SysInsuranceCom() {
	}

	/** minimal constructor */
	public SysInsuranceCom(String insuranceComName, String insuranceComNum) {
		this.insuranceComName = insuranceComName;
		this.insuranceComNum = insuranceComNum;
	}

	/** full constructor */
	public SysInsuranceCom(String insuranceComName, Boolean toDeactivate,
			String insuranceComNum) {
		this.insuranceComName = insuranceComName;
		this.toDeactivate = toDeactivate;
		this.insuranceComNum = insuranceComNum;
	}

	// Property accessors

	public Integer getInsuranceComId() {
		return this.insuranceComId;
	}

	public void setInsuranceComId(Integer insuranceComId) {
		this.insuranceComId = insuranceComId;
	}

	public String getInsuranceComName() {
		return this.insuranceComName;
	}

	public void setInsuranceComName(String insuranceComName) {
		this.insuranceComName = insuranceComName;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

	public String getInsuranceComNum() {
		return this.insuranceComNum;
	}

	public void setInsuranceComNum(String insuranceComNum) {
		this.insuranceComNum = insuranceComNum;
	}

}