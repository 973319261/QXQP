package com.koi.po;

/**
 * SysModularType entity. @author MyEclipse Persistence Tools
 */

public class SysModularType implements java.io.Serializable {

	// Fields

	private Integer modularTypeId;
	private String modularTypeName;
	private String modularTypeCode;

	// Constructors

	/** default constructor */
	public SysModularType() {
	}

	/** minimal constructor */
	public SysModularType(String modularTypeName) {
		this.modularTypeName = modularTypeName;
	}

	/** full constructor */
	public SysModularType(String modularTypeName, String modularTypeCode) {
		this.modularTypeName = modularTypeName;
		this.modularTypeCode = modularTypeCode;
	}

	// Property accessors

	public Integer getModularTypeId() {
		return this.modularTypeId;
	}

	public void setModularTypeId(Integer modularTypeId) {
		this.modularTypeId = modularTypeId;
	}

	public String getModularTypeName() {
		return this.modularTypeName;
	}

	public void setModularTypeName(String modularTypeName) {
		this.modularTypeName = modularTypeName;
	}

	public String getModularTypeCode() {
		return this.modularTypeCode;
	}

	public void setModularTypeCode(String modularTypeCode) {
		this.modularTypeCode = modularTypeCode;
	}

}