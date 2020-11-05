package com.koi.po;

/**
 * SysForeMan entity. @author MyEclipse Persistence Tools
 */

public class SysForeMan implements java.io.Serializable {

	// Fields

	private Integer foreManId;
	private String foreManName;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysForeMan() {
	}

	/** minimal constructor */
	public SysForeMan(String foreManName) {
		this.foreManName = foreManName;
	}

	/** full constructor */
	public SysForeMan(String foreManName, Boolean toDeactivate) {
		this.foreManName = foreManName;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getForeManId() {
		return this.foreManId;
	}

	public void setForeManId(Integer foreManId) {
		this.foreManId = foreManId;
	}

	public String getForeManName() {
		return this.foreManName;
	}

	public void setForeManName(String foreManName) {
		this.foreManName = foreManName;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}