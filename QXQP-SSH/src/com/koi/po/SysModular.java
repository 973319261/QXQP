package com.koi.po;

/**
 * SysModular entity. @author MyEclipse Persistence Tools
 */

public class SysModular implements java.io.Serializable {

	// Fields

	private Integer modularId;
	private Integer modularTypeId;
	private String modularName;
	private String modularCode;

	// Constructors

	/** default constructor */
	public SysModular() {
	}

	/** full constructor */
	public SysModular(Integer modularTypeId, String modularName,
			String modularCode) {
		this.modularTypeId = modularTypeId;
		this.modularName = modularName;
		this.modularCode = modularCode;
	}

	// Property accessors

	public Integer getModularId() {
		return this.modularId;
	}

	public void setModularId(Integer modularId) {
		this.modularId = modularId;
	}

	public Integer getModularTypeId() {
		return this.modularTypeId;
	}

	public void setModularTypeId(Integer modularTypeId) {
		this.modularTypeId = modularTypeId;
	}

	public String getModularName() {
		return this.modularName;
	}

	public void setModularName(String modularName) {
		this.modularName = modularName;
	}

	public String getModularCode() {
		return this.modularCode;
	}

	public void setModularCode(String modularCode) {
		this.modularCode = modularCode;
	}

}